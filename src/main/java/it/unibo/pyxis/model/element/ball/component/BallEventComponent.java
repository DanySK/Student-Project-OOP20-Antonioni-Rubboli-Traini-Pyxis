package it.unibo.pyxis.model.element.ball.component;

import it.unibo.pyxis.model.ecs.component.event.AbstractEventComponent;
import it.unibo.pyxis.model.element.ball.Ball;
import it.unibo.pyxis.model.event.collision.BallCollisionWithBorderEvent;
import it.unibo.pyxis.model.event.collision.BallCollisionWithBrickEvent;
import it.unibo.pyxis.model.event.collision.BallCollisionWithPadEvent;
import it.unibo.pyxis.model.event.collision.CollisionEvent;
import it.unibo.pyxis.model.hitbox.HitEdge;
import it.unibo.pyxis.model.util.Dimension;
import it.unibo.pyxis.model.util.Vector;
import org.greenrobot.eventbus.Subscribe;

public class BallEventComponent extends AbstractEventComponent<Ball> {

    private static final double MIN_LEFT_PERCENTAGE = 0.1;
    private static final double MIN_RIGHT_PERCENTAGE = 0.9;

    public BallEventComponent(final Ball entity) {
        super(entity);
    }

    private void registerCollision(final CollisionEvent collisionEvent) {
        final HitEdge hitEdge = collisionEvent.getCollisionInformation().getHitEdge();
        final Dimension borderOffset = collisionEvent.getCollisionInformation().getBorderOffset();
        this.getEntity().registerCollision(hitEdge, borderOffset);
    }

    private void applyPaceChange(final double padHitPercentage) {
        final double angle = Math.PI * Math.min(Math.max(padHitPercentage, MIN_LEFT_PERCENTAGE), MIN_RIGHT_PERCENTAGE);
        final double module = this.getEntity().getPace().getModule();
        final Vector newPace = this.getEntity().getPace();
        newPace.setX(Math.cos(angle) * module);
        newPace.setY(Math.sin(angle) * module);
        this.getEntity().setPace(newPace);
    }

    @Subscribe
    public void handleBrickCollision(final BallCollisionWithBrickEvent collisionEvent) {
        if (this.getEntity().getId() == collisionEvent.getBallId() && this.getEntity().getType().bounce()) {
            this.registerCollision(collisionEvent);
        }
    }

    @Subscribe
    public void handleBorderCollision(final BallCollisionWithBorderEvent collisionEvent) {
        if (this.getEntity().getId() == collisionEvent.getBallId()) {
            this.registerCollision(collisionEvent);
        }
    }

    @Subscribe
    public void handlePadCollision(final BallCollisionWithPadEvent collisionEvent) {
        if (this.getEntity().getId() == collisionEvent.getBallId()) {
            if (collisionEvent.getCollisionInformation().getHitEdge() == HitEdge.TOP) {
                this.applyPaceChange(collisionEvent.getPadHitPercentage());
                collisionEvent.getCollisionInformation().setHitEdge(HitEdge.HORIZONTAL);
            }
            this.registerCollision(collisionEvent);
        }
    }
}
