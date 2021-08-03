package it.unibo.pyxis.model.ecs.component;

import it.unibo.pyxis.model.ecs.entity.Entity;

public interface Component<E extends Entity> {
    /**
     * Attach this component into an {@link Entity}.
     */
    void attach();

    /**
     * Detach this component.
     */
    void detach();

    /**
     * Return the {@link Entity} linked to this {@link Component}.
     * @return
     *          An {@link Entity} instance
     */
    E getEntity();

    /**
     * Return true if this {@link Component} is attacched to an
     * {@link Entity}.
     * @return
     *         True if the component is attached, false otherwise
     */
    boolean isAttached();
}
