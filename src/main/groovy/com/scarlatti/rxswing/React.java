package com.scarlatti.rxswing;

import java.awt.*;
import java.util.List;

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
    private static AbstractReactComponent mostRecentReactComponent;

    /**
     * React class should not be instantiated.
     *
     * @throws UnsupportedOperationException on invocation
     */
    private React() {
        throw new UnsupportedOperationException("React class should not be instantiated");
    }

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
    public static void render(Container parent, AbstractReactComponent child) {

        AbstractReactComponent childToUse = child;

        // determine if we've seen this component before
        if (mostRecentReactComponent == null) {
            mostRecentReactComponent = child;
        } else if (mostRecentReactComponent.getReactId().equals(child.getReactId())) {
            childToUse = mostRecentReactComponent;
        }

        render(parent, childToUse, React::renderReplace);
    }

    static void renderOnStateChange(Container parent, AbstractReactComponent child) {
        render(parent, child, React::renderReplace);
    }

    static void render(Container parent, List<AbstractReactComponent> children) {
        clearParent(parent);

        for (AbstractReactComponent child : children) {
            render(parent, child, React::renderAppend);
        }
    }

    /**
     * Render another child into this parent, immediately following the
     * last existing child.
     *
     * @param parent the parent within which to render.
     * @param child  the child to render in the parent.
     */
    public static void renderAppend(Container parent, AbstractReactComponent child) {
        render(parent, child, React::renderAppend);
    }

    private static void render(Container parent, AbstractReactComponent<?, ?> child, SwingRenderStrategy renderStrategy) {
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
        // TODO so now we would have the correct child
        RxElement rxElement = child.abstractRender(null); // this line will set in motion all second level components...
        Component swingComponent = rxElement.provideComponent();

        // now render the component into the parent
        // using the strategy (replace/append)
        renderStrategy.render(parent, swingComponent);

        // if successful, now link child to parent
        child.setSwingParent(parent);
        parent.revalidate();
        parent.repaint();
    }

    private static void renderAppend(Container parent, Component child) {
        parent.add(child);
    }

    private static void renderReplace(Container parent, Component child) {
        parent.removeAll();
        parent.add(child);
    }

    private static void clearParent(Container parent) {
        parent.removeAll();
    }

    @FunctionalInterface
    private interface SwingRenderStrategy {
        void render(Container parent, Component child);
    }
}
