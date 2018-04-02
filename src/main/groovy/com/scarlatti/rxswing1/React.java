package com.scarlatti.rxswing1;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 * <p>
 * This is the base class that the user will call.
 * It is not intended to be instantiated.
 */
public final class React {

    /**
     * This will not work if I have multiple windows using React...
     * I will need to have a way to identify the root element.
     */
    // TODO remove this...
    private static RxSwingComponent mostRecentReactComponent;

    private static Map<String, ReactContext> contexts;

    /**
     * React class should not be instantiated.
     *
     * @throws UnsupportedOperationException on invocation
     */
    private React() {
        throw new UnsupportedOperationException("React class should not be instantiated");
    }

    public static void render(Container swingParent, RxSwingComponent reactComponent) {
        if (contexts == null) contexts = new HashMap<>();

        // get the id of the caller from the stack
        StackTraceElement frame = getRenderCallerInfo();

        // example: "com.somebody.SomeApp:main:SomeApp.java:48"
        String id = frame.getClassName() + ":" + frame.getMethodName() + ":" + frame.getFileName() + ":" + frame.getLineNumber();

        ReactContext context = contexts.get(id);
        if (context == null) {
            context = new RootReactContext(id, swingParent);
            contexts.put(id, context);
        }

        // now we have added the context to the list if it does not already exist.
        // Time to use it!
        context.render(reactComponent);
    }

    private static StackTraceElement getRenderCallerInfo() {
        StackTraceElement[] frames = Thread.currentThread().getStackTrace();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].getClassName().equals(React.class.getName()) &&
                frames[i].getMethodName().equals("render")) {
                return frames[i + 1];
            }
        }

        throw new IllegalStateException("React.render() not called in stacktrace.");
    }

    // TODO basically everything after here should go.

    /**
     * Render the child component into the parent container as its only child.
     * <p>
     * We assume that the parent is provided in a "clean" condition.
     * However, to support patterns such as the Redux pattern, for example,
     * we will need to be able to reset the parent to the "clean" condition.
     * Initially, we'll just remove any existing children before inserting
     * the given child.
     *
     * OK for root level render.
     *
     * @param parent the parent within which to render.
     * @param child  the child to render in the parent.
     */
    public static void renderOld(Container parent, RxSwingComponent child) {

        RxSwingComponent childToUse = child;

        // determine if we've seen this component before
        if (mostRecentReactComponent == null) {
            mostRecentReactComponent = child;
        } else if (mostRecentReactComponent.getReactId().equals(child.getReactId())) {
            childToUse = mostRecentReactComponent;
        }

        render(parent, childToUse, React::renderOnlyChild);
    }

    static void renderOnStateChange(Container parent, RxSwingComponent child) {
        render(parent, child, React::renderReplace);
    }

    static void render(Container parent, List<RxSwingComponent> children) {
        clearParent(parent);

        for (RxSwingComponent child : children) {
            render(parent, child, React::renderAppendChild);
        }
    }

    /**
     * Render another child into this parent, immediately following the
     * last existing child.
     *
     * @param parent the parent within which to render.
     * @param child  the child to render in the parent.
     */
    public static void renderAppend(Container parent, RxSwingComponent child) {
        render(parent, child, React::renderAppendChild);
    }

    private static void render(Container parent, RxSwingComponent<?, ?> child, SwingRenderStrategy renderStrategy) {
        // so now we would have to read the actual swing component
        // from the child and insert it into the parent.
        //
        // calling for the swing component from the child will
        // kick off the process of actually calling the render function
        // within the react component.
        //
        // any nested calls will ultimately wind up returning a single
        // rxElement which we can use to get the actual swing component.

        // TODO this seems naive: render must pass props like this?
        // Could we pull the props from the child props?
        //
        // Now we need to make sure we are getting the right component
        // if we already have a record in the components map for the id of the child
        // we will not use the actual given child, but instead
        // we will use the child from the components map
        //
        // TODO we would need to find the correct child, if any, to replace, if we are in replace mode
        // now render the component into the parent
        // using the strategy (replace/append)
        renderStrategy.render(parent, child);

        // if successful, now link child to parent
        child.setSwingParent(parent);
        parent.revalidate();
        parent.repaint();
    }

    private static void renderAppendChild(Container parent, RxSwingComponent<?, ?> child) {
        RxElement rxElement = child.abstractRender(null); // this line will set in motion all second level components...
        Component swingComponent = rxElement.provideComponent();
        parent.add(swingComponent);
    }

    private static void renderOnlyChild(Container parent, RxSwingComponent<?, ?> child) {
        parent.removeAll();
        RxElement rxElement = child.abstractRender(null); // this line will set in motion all second level components...
        Component swingComponent = rxElement.provideComponent();
        parent.add(swingComponent);
    }

    private static void renderReplace(Container parent, RxSwingComponent<?, ?> child) {
        parent.remove(child.getElementIndex());
        RxElement rxElement = child.abstractRender(null); // this line will set in motion all second level components...
        Component swingComponent = rxElement.provideComponent();
        parent.add(swingComponent, child.getElementIndex());
    }

    private static void clearParent(Container parent) {
        parent.removeAll();
    }

    private void renderFromOutside(Container swingParent, RxSwingComponent component) {
        // take the component, render it as new, and replace all nodes in the parent with that component's rendered swing component
    }

    private void renderOnStateChangeRoot(RxSwingComponent root) {
        // we would have to call
    }

    private void renderOnStateChangeChild(RxSwingComponent root) {

    }

    @FunctionalInterface
    private interface SwingRenderStrategy {
        void render(Container parent, RxSwingComponent child);
    }
}