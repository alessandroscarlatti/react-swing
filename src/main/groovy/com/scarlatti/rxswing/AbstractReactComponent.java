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

    /**
     * reactId is a string key that identifies this logical component
     * instance to React from one render to another.
     *
     * The id is essentially a "fully-qualified" path name, where each
     * piece of the path is a class name and an index.
     *
     * For example "/com.scarlatti.rxswing.RxJPanel(0)/com.scarlatti.rxswing.RxJButton(0)"
     */
    private String parentReactId;

    /**
     * The index of this component within the parent
     * component's list of children.
     */
    private int elementIndex;

    /**
     * We need to store the Swing parent of this logical component
     * once it is rendered so that we can rerender this component.
     */
    private Container swingParent;

    /**
     * We need to cache the most recently rendered RxComponent
     * so that in the case of a pure component we do not need
     * to rerender.
     */
    private RxComponent mostRecentlyRenderedRxComponent;

    protected S state;
    protected P props;

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

    public String getParentReactId() {
        return parentReactId;
    }

    public void setParentReactId(String parentReactId) {
        this.parentReactId = parentReactId;
    }

    public int getElementIndex() {
        return elementIndex;
    }

    public void setElementIndex(int elementIndex) {
        this.elementIndex = elementIndex;
    }
}
