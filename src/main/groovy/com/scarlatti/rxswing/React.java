package com.scarlatti.rxswing;

import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 *
 * This is the base class that the user will call.
 * It is not intended to be instantiated.
 */
public final class React {

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
     *
     * We assume that the parent is provided in a "clean" condition.
     * However, to support patterns such as the Redux pattern, for example,
     * we will need to be able to reset the parent to the "clean" condition.
     * Initially, we'll just remove any existing children before inserting
     * the given child.
     *
     * @param parent the parent within which to render.
     * @param child the child to render in the parent.
     */
    public static void render(Container parent, ReactComponent child) {
        // so now we would have to read the actual swing component
        // from the child and insert it into the parent.
        //
        // calling for the swing component from the child will
        // kick off the process of actually calling the render function
        // within the react component.
        //
        // any nested calls will ultimately wind up returning a single
        // rxComponent which we can use to get the actual swing component.

        RxComponent rxComponent = child.render();
        Component swingComponent = rxComponent.provideComponent();

        // now render the component into the parent
        // after removing any existing children.
        parent.removeAll();
        parent.add(swingComponent);

        // if successful, now link child to parent
        child.setSwingParent(parent);
    }
}
