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
public abstract class AbstractReactComponent<P, S> extends Component {

    private Container swingParent;
    protected S state;
    protected P props;
    private RxComponent mostRecentlyRenderedRxComponent;

    public AbstractReactComponent() {
    }

    public AbstractReactComponent(P props) {
        this.props = props;
    }

    /**
     * This method may be called in two scenarios:
     * - through an ancestor render chain
     * - through a state change render
     *
     * This method has the responsibility of determining
     * if we should update the component.
     *
     * If we will update the component, we return the new rendering.
     * If we will not update the component, we return the most recently
     * rendered component.
     *
     * @return the component to be rendered.
     */
    RxComponent abstractRender(P newProps) {

        if (mostRecentlyRenderedRxComponent == null) {
            mostRecentlyRenderedRxComponent = render();
        }

        if (componentShouldUpdate(props, newProps)) {
            mostRecentlyRenderedRxComponent = render();
        }

        return mostRecentlyRenderedRxComponent;
    }

    public boolean componentShouldUpdate(P oldProps, P newProps) {
        return true;
    }

    /**
     * Pass in new props to the component.
     * The default implementation will be blank to encourage
     * the user to define this method.
     *
     * @param newProps the new props for the component.
     */
    public void componentWillReceiveProps(P newProps) {
        // encourage user to implement this method
    }

    /**
     * This method may be called in two scenarios:
     * - through an ancestor render chain
     * - through a state change render
     *
     * The method should be implemented by the user.
     *
     * @return the component to be rendered.
     */
    public abstract RxComponent render();

    /**
     * Associate this React component to the actual Swing container
     * into which it has been injected.
     *
     * This should be called when {@link React#render(Container, AbstractReactComponent)} is connecting components.
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
     *
     * @param state the new state for this component.
     */
    protected void setState(S state) {
        Objects.requireNonNull(swingParent, "Swing parent component must not be null");
        this.state = state;
        React.render(swingParent, this);
    }
}
