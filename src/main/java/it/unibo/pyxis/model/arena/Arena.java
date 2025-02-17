package it.unibo.pyxis.model.arena;

import java.util.Set;

import it.unibo.pyxis.ecs.Entity;
import it.unibo.pyxis.model.powerup.handler.PowerupHandler;
import it.unibo.pyxis.model.util.Coord;
import it.unibo.pyxis.model.util.Dimension;
import it.unibo.pyxis.model.element.ball.Ball;
import it.unibo.pyxis.model.element.brick.Brick;
import it.unibo.pyxis.model.element.pad.Pad;
import it.unibo.pyxis.model.element.powerup.Powerup;


public interface Arena extends Entity {

    /**
     * Adds a {@link Ball} in the {@link Arena}.
     *
     * @param ball The {@link Ball} to add.
     */
    void addBall(Ball ball);

    /**
     * Adds a {@link Brick} in the {@link Arena}.
     *
     * @param brick The {@link Brick} to add.
     */
    void addBrick(Brick brick);

    /**
     * Adds a new {@link Powerup} in the {@link Arena}.
     *
     * @param powerup The {@link Powerup} to add.
     */
    void addPowerup(Powerup powerup);

    /**
     * Procedure of cleanup of the {@link Arena}.
     * Unregisters all the elements from the {@link org.greenrobot.eventbus.EventBus}
     * and shutdown the {@link it.unibo.pyxis.model.powerup.handler.PowerupHandler}.
     */
    void cleanUp();

    /**
     * Removes all the {@link Ball}s in the {@link Arena} unsubscribing them
     * from the {@link org.greenrobot.eventbus.EventBus}.
     */
    void clearBalls();

    /**
     * Removes all the {@link Brick}s in the {@link Arena} unsubscribing them
     * from the {@link org.greenrobot.eventbus.EventBus}.
     */
    void clearBricks();

    /**
     * Removes all the {@link Powerup}s in the {@link Arena} unsubscribing them
     * from the {@link org.greenrobot.eventbus.EventBus}.
     */
    void clearPowerups();

    /**
     * Returns a {@link java.util.Set} of {@link Ball} that are currently present in
     * the {@link Arena}.
     *
     * @return A {@link java.util.Set} of {@link Ball}.
     */
    Set<Ball> getBalls();

    /**
     * Returns a {@link java.util.Set} of {@link Brick} that are currently present in
     * the {@link Arena}.
     *
     * @return A {@link java.util.Set} of {@link Brick}.
     */
    Set<Brick> getBricks();

    /**
     * Returns the dimensions of the {@link Arena}.
     *
     * @return A {@link Dimension} instance.
     */
    Dimension getDimension();

    /**
     * Returns the last {@link Ball} id inserted in the {@link Arena}.
     *
     * @return The integer representing the last id inserted
     *         in the {@link Arena}.
     */
    int getLastBallId();

    /**
     * Gets the current {@link Pad} in the {@link Arena}.
     *
     * @return The {@link Pad} actually present in the {@link Arena}.
     */
    Pad getPad();

    /**
     * Returns the {@link PowerupHandler} of the {@link Arena}.
     *
     * @return A {@link PowerupHandler}.
     */
    PowerupHandler getPowerupHandler();

    /**
     * Returns a {@link java.util.Set} of {@link Powerup} that are currently present in
     * the {@link Arena}.
     *
     * @return A {@link java.util.Set} of {@link Powerup}.
     */
    Set<Powerup> getPowerups();

    /**
     * Returns a random {@link Ball} registered in this {@link Arena}.
     *
     * @return A {@link Ball} in the {@link Arena}.
     */
    Ball getRandomBall();

    /**
     * Increases the {@link Pad}'s {@link Dimension}'s width of an input amount.
     *
     * @param amount The amount to increase.
     */
    void increasePadWidth(double amount);

    /**
     * Checks if the {@link Arena} is cleared, or rather, there aren't any bricks left except for the ones
     * of indestructible type.
     *
     * @return True if the {@link Arena} is cleared.
     *         False otherwise.
     */
    boolean isCleared();

    /**
     * Moves pad to the left of the {@link Arena}.
     */
    void movePadLeft();

    /**
     * Moves pad to the right of the {@link Arena}.
     */
    void movePadRight();

    /**
     * Removes a {@link Ball} from the {@link Arena}.
     *
     * @param ball The {@link Ball} to remove from the {@link Arena}.
     */
    void removeBall(Ball ball);

    /**
     * Removes a {@link Brick} in the {@link Arena}.
     *
     * @param brickCoord The {@link Coord} of the {@link Brick} to remove.
     */
    void removeBrick(Coord brickCoord);

    /**
     * Removes a {@link Powerup} from the {@link Arena}.
     *
     * @param powerup The {@link Powerup} to remove.
     */
    void removePowerup(Powerup powerup);

    /**
     * Resets the {@link Pad} and the {@link Ball} to the starting {@link Coord}.
     */
    void resetStartingPosition();

    /**
     * Restore the dimension of the {@link Pad}.
     */
    void restorePadDimension();

    /**
     * Sets the {@link Pad} in the {@link Arena}.
     *
     * @param pad The {@link Pad} to add.
     */
    void setPad(Pad pad);

    /**
     * Updates the elements of the {@link Arena}.
     *
     * @param delta The elapsed time.
     */
    void update(double delta);
}
