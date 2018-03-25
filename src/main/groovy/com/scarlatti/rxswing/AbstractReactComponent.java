package com.scarlatti.rxswing;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 * <p>
 * This is the behind-the-scenes class which implements the bulk of
 * React behavior for React components.
 * to make RxSwing components.
 */
public abstract class AbstractReactComponent<P, S> extends Component {

    /**
     * reactId is a string key that identifies this logical component
     * instance to React from one render to another.
     *
     * The id is essentially a "fully-qualified" path name.
     *
     * For example "com.scarlatti.components.MyCoolComponent"
     */
    private String reactId;

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
     * We need to cache the most recently rendered RxElement
     * so that in the case of a pure component we do not need
     * to rerender.
     */
    private RxElement mostRecentlyRenderedRxElement;

    /**
     * These are the rendered children for this component.
     * Each child is identified by its "reactId".
     *
     * This allows us to use that ID to determine whether or not
     * to use that component instance for rerendering, or if we
     * need to create a new instance.
     */
    private List<AbstractReactComponent> children;

    protected S state;
    protected P props;

    public AbstractReactComponent() {
        children = new ArrayList<>();
        setDefaultReactId();
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
    RxElement abstractRender(P newProps) {

        if (mostRecentlyRenderedRxElement == null) {
            mostRecentlyRenderedRxElement = render();
        }

        if (componentShouldUpdate(props, newProps)) {
            mostRecentlyRenderedRxElement = render();
        }

        List<AbstractReactComponent> currentChildren = new ArrayList<>(children);
        List<AbstractReactComponent> newChildren = mostRecentlyRenderedRxElement.provideDirectChildren();

        children.clear();

        if (newChildren.size() > 0) {
            // if there are children ReactComponents
            // now render those, calling to React.render(),
            // using this mostRecentlyRenderedRxElement as
            // the parent container.

            // but compare them to the list in current react children
            for (int i = 0; i < newChildren.size(); i++) {
                if (currentChildren.size() > i && currentChildren.get(i).getReactId().equals(newChildren.get(i).getReactId())) {
                    // if the two elements are equivalent, use the existing one...
                    children.add(currentChildren.get(i));
                } else {
                    children.add(newChildren.get(i));
                }
            }
        }

        // now <children> contains a list of children to render
        // so we need to render each child into this component...
        React.render((Container) mostRecentlyRenderedRxElement.provideComponent(), children);

        return mostRecentlyRenderedRxElement;
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
    public abstract RxElement render();

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
        React.renderOnStateChange(swingParent, this);
    }

    public String getReactId() {
        return reactId;
    }

    protected void setDefaultReactId() {
        setReactId(this.getClass().getName());
    }

    /**
     * TODO could also do this in a withKey() method
     * @param reactId the reactId to use
     */
    public void setReactId(String reactId) {
        this.reactId = reactId;
    }

    public int getElementIndex() {
        return elementIndex;
    }

    public void setElementIndex(int elementIndex) {
        this.elementIndex = elementIndex;
    }
}
