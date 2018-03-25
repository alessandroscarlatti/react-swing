package com.scarlatti.rxswing;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 *
 * This would be the class that the developer uses.
 * It will be as if using a real JPanel.
 */
public class RxJPanel extends JPanel implements RxElement {

    protected ReactComponentTraits reactComponentTraits = new ReactComponentTraits();

    /**
     * Returning this component for now.  Will implement a different
     * strategy when we start performing diffs.
     *
     * Support for nested ReactComponents:
     * We need to be able to render a chain of ReactComponents.
     * This means we will read any children from the reactComponentTraits
     * before actually providing this Swing component.
     *
     * @return the actual Swing component
     */
    @Override
    public Component provideComponent() {
        // This is a bit naive of an implementation.
        // We will eventually need to keep track of what has been rendered?
        // I think we will be doing this side effect somewhere in abstractRender()...
        // React.render(this, reactComponentTraits.rxComponentChildren);

        return this;
    }

    @Override
    public String provideElementId() {
        return null;
    }

    @Override
    public int provideElementIndex() {
        return 0;
    }

    /**
     * When adding the child to the list, if it is an RxElement we specifically
     * DO NOT actually add it to the list of swing components
     * because it doesn't actually exist as a real component.
     *
     * However, if it is an RxElement only then do we add it to the reactComponentTraits
     * list of children components.
     *
     * TODO Additionally, we will set the reactId on the new child.
     *
     * TODO should stop allowing the user to insert a non-React component?
     *
     * @param child the child
     * @return the child just added
     */
    @Override
    public Component add(Component child) {
        Objects.requireNonNull(child, "Cannot add null child");

        if (child instanceof AbstractReactComponent) {
            ((AbstractReactComponent) child).setReactId(provideElementId());
            ((AbstractReactComponent) child).setElementIndex(provideElementIndex());
            reactComponentTraits.rxComponentChildren.add((AbstractReactComponent) child);
        } else {
            super.add(child);
        }

        return child;
    }

    /**
     * This would be able to provide all React components added
     * to this panel during its rendering.
     *
     * @return any direct child React components.
     */
    @NotNull
    @Override
    public List<AbstractReactComponent> provideDirectChildren() {
        // TODO would pull here from a list built earlier
        return reactComponentTraits.rxComponentChildren;
    }
}
