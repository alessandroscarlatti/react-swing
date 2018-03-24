package com.scarlatti.rxswing;

import java.awt.*;
import java.util.Objects;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 * <p>
 * This is the class that the developer will implement
 * to make RxSwing components.
 */
public abstract class ReactComponent {

    private Container swingParent;
    protected Object state;

    /**
     * This method may be called in two scenarios:
     * - through an ancestor render chain
     * - through a state change render
     *
     * @return the component to be rendered.
     */
    public abstract RxComponent render();

    /**
     * Associate this React component to the actual Swing container
     * into which it has been injected.
     *
     * This should be called when {@link React#render(Container, ReactComponent)} is connecting components.
     *
     * @param swingParent the actual swing parent container to
     *                    this component's actual Swing component
     */
    protected void setSwingParent(Container swingParent) {
        this.swingParent = swingParent;
    }

    /**
     * Set a new state for this component.
     * Immediately initiates a new render chain starting at this component.
     *
     * TODO look at adding support for mutable state
     * TODO look at adding generics
     *
     * @param state the new state for this component.
     */
    protected void setState(Object state) {
        Objects.requireNonNull(swingParent, "Swing parent component must not be null");
        this.state = state;
        React.render(swingParent, this);
    }
}
