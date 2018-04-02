package com.scarlatti.rxswing2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/30/2018
 */
public class ComponentRenderingInterface implements RenderingInterface {

    private Container parent;
    private Container self;
    private List<RenderingInterface> children;

    private Component originalSelf;

    public ComponentRenderingInterface(Container parent, Container self) {
        // determine what kind of self I am in order to extract the Swing component
        this.parent = parent;
        this.originalSelf = self;
        this.children = Collections.emptyList();
    }

    @Override
    public Container getParent() {
        return parent;
    }

    @Override
    public Container getSelf() {
        if (self != null) return self;

        self = getRenderedSelfRecursive(originalSelf);
        return self;
    }

    @Override
    public List<RenderingInterface> getChildren() {
        if (children != null) return children;

        // now build children
        return buildChildren();
    }

    private Container getRenderedSelfRecursive(Object instance) {
        if (instance instanceof RxComponent) {
            Object rendered = ((RxComponent) instance).render();
            return getRenderedSelfRecursive(rendered);  // only needed for type and id for now
        } else if (instance instanceof Container) {
            return (Container) instance;
        }

        throw new RuntimeException("Unexpected Component type: " + instance.getClass().getName());
    }

    private List<RenderingInterface> buildChildren() {
        if (getSelf() instanceof RxComponent) return buildChildrenFromRxComponent();

        List<RenderingInterface> interfaces = buildChildrenFromContainer();

        return Collections.emptyList();
    }

    private List<RenderingInterface> buildChildrenFromRxComponent() {
        return Collections.emptyList();
    }

    private List<RenderingInterface> buildChildrenFromContainer() {
//        if (!(getSelf() instanceof Container)) return Collections.emptyList();
//
//        Component[] components = ((Container) getSelf()).getComponents();
//        List<RenderingInterface> interfaces = new ArrayList<>(components.length);
//        for (Component component : components) {
//            interfaces.add(new ComponentRenderingInterface(parent, component));
//        }

        return Collections.emptyList();
    }
}
