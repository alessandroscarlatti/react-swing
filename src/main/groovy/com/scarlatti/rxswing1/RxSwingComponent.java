package com.scarlatti.rxswing1;

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
public abstract class RxSwingComponent<P, S> extends Component implements RxComponent<P, S> {

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

    private ReactContext reactContext;

    private ReactComponentContext selfContext;

    protected S state;
    protected P props;

    public RxSwingComponent() {
        setDefaultReactId();
        selfContext = new SimpleReactComponentContext();
    }

    public RxSwingComponent(P props) {
        this.props = props;
    }

    /**
     * This method may be called in two scenarios:
     * - through an ancestor render chain
     * - through a state change render
     *
     * N.B. This method will only be called if this component has never previously been rendered.
     *
     * @return the component to be rendered (for the first time)
     */
    @Override
    public RxElement abstractRender(Object props) {

        selfContext = new SimpleReactComponentContext();

        // before returning, render the children (which will render their children)

        return selfContext.getRenderedRxElement();
    }

    @Override
    public RxElement abstractRerender(RxComponent<P, S> virtual) {
//        if (componentShouldUpdate(props, virtual.getProps())) {
//            ReactComponentContext virtualSelfContext = new SimpleReactComponentContext(render());
//
//            List<RxSwingComponent> newVirtualChildren = virtualSelfContext.getRenderedRxElement().provideDirectChildren();
//
//            // now go through the virtual component's new children
//            // and compare to the currentChildren
//            for (RxSwingComponent component : newVirtualChildren) {
//                if (selfContext.virtuallyContains(component)) {
//                    // this child has already existed
//
//                } else {
//                    // this child is brand new
//                }
//            }
//
//            selfContext.close();
//            selfContext = virtualSelfContext;  // now replace the old context with the new one.
//        }

        return null;
    }

    @Override
    public boolean componentShouldUpdate(P newProps) {
        return true;
    }

    /**
     * Pass in new props to the component.
     * The default implementation will be blank to encourage
     * the user to define this method.
     *
     * @param newProps the new props for the component.
     */
    @Override
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
    public abstract Object render();

    /**
     * Associate this React component to the actual Swing container
     * into which it has been injected.
     *
     * This should be called when {@link React#renderOld(Container, RxSwingComponent)} is connecting components.
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
    public void setState(S state) {
        Objects.requireNonNull(swingParent, "Swing parent component must not be null");
        this.state = state;
        reactContext.render(this);
    }

    boolean virtualEquals(RxSwingComponent component) {
        return Objects.equals(this.reactId, component.reactId)
            && Objects.equals(this.elementIndex, component.elementIndex);
    }

    @Override
    public S getState() {
        return state;
    }

    @Override
    public P getProps() {
        return props;
    }

    public void setProps(P props) {
        this.props = props;
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

    public ReactContext getReactContext() {
        return reactContext;
    }

    public void setReactContext(ReactContext reactContext) {
        this.reactContext = reactContext;
    }

    private static class Pair<X, Y> {
        final X x;
        final Y y;
        Pair(X virtualComponent, Y actualComponent) {
            this.x = virtualComponent;
            this.y = actualComponent;
        }
    }

    private static class RenderingPair extends Pair<RxSwingComponent, RxSwingComponent> {
        RenderingPair(RxSwingComponent virtualComponent, RxSwingComponent actualComponent) {
            super(virtualComponent, actualComponent);
        }

        RxSwingComponent getVirtual() {
            return x;
        }

        RxSwingComponent getActual() {
            return y;
        }
    }
}
