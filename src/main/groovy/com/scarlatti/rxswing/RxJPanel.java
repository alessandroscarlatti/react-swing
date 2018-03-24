package com.scarlatti.rxswing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

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
public class RxJPanel extends JPanel implements RxComponent {

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

        List<Component> childComponents = new ArrayList<>();
        for (RxComponent rxComponent : reactComponentTraits.rxComponentChildren) {
            // instruct the child rxComponent to provide its Swing component
            Component swingComponent = rxComponent.provideComponent();

        }

        return this;
    }

    /**
     * When adding the child to the list, if it is an RxComponent We specifically
     * DO NOT actually add it to the list of swing components
     * because it doesn't actually exist as a real component.
     *
     * However, if it is an RxComponent only then do we add it to the reactComponentTraits
     * list of children components.
     *
     * @param child the child
     * @return the child just added
     */
    @Override
    public Component add(Component child) {
        Objects.requireNonNull(child, "Cannot add null child");

        if (child instanceof RxComponent) {
            reactComponentTraits.rxComponentChildren.add((RxComponent) child);
        } else {
            super.add(child);
        }

        return this;
    }
}
