package com.scarlatti.rxswing3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 3/31/2018
 */
public class ComponentRenderingManager {

    private Consumer<Component> swingParentRenderStrategy;
    private Supplier<Object> selfSupplier;
    private String id;

    private Object self;
    private List<ComponentRenderingManager> children;

    private boolean firstRender = true;

    public ComponentRenderingManager(Consumer<Component> swingParentRenderStrategy, Supplier<Object> selfSupplier) {
        this.swingParentRenderStrategy = swingParentRenderStrategy;
        this.selfSupplier = selfSupplier;
    }

    public void render() {
        // decide between first and second times...

        if (firstRender) {
            firstRender();
        } else {
            secondRender();
        }
    }

    public void firstRender() {
        Object self = selfSupplier.get();

        // interpret the self correctly to get the swing component.
        if (self instanceof RxComponent) {
            // pass on this parent render strategy to the component
            // and render this component
            this.self = self;
            ((RxComponent) self).render(swingParentRenderStrategy);

        } else if (self instanceof Container) {
            // we need to look for children and render them

            List<RenderingManager> managers = generateRenderingManagers((Container) self);
            for (RenderingManager manager : managers) {
                manager.render();
            }

            swingParentRenderStrategy.accept((Container) self);
        } else if (self instanceof Component) {
            // we only need to render this component in the parent, using the strategy
            swingParentRenderStrategy.accept((Component) self);
        } else {
            throw new RuntimeException("Unexpected Component type: " + self.getClass().getName());
        }

        firstRender = false;
    }

    public void secondRender() {
        Object self = selfSupplier.get();

        // interpret the self correctly to get the swing component.
        if (self instanceof RxComponent) {
            // pass on this parent render strategy to the component
            // and render this component
            // ... need to check if this component is the currently rendered component


        } else if (self instanceof Container) {
            // we need to look for children and render them
        } else if (self instanceof Component) {
            // we only need to render this component in the parent, using the strategy
            swingParentRenderStrategy.accept((Component) self);
        } else {
            throw new RuntimeException("Unexpected Component type: " + self.getClass().getName());
        }
    }

    private List<RenderingManager> generateRenderingManagers(Container container) {
        Component[] components = container.getComponents();
        List<RenderingManager> managers = new ArrayList<>(components.length);

        for (int i = 0; i < components.length; i++) {

            final Component component = components[i];

            // build the self supplier for the new rendering manager
            Supplier<Object> selfSupplier;

            if (component instanceof RxComponent) {
                selfSupplier = ((RxComponent) component)::render;
            } else {
                selfSupplier = () -> component;
            }

            final int index = i;
            Consumer<Component> swingParentRenderStrategy = (Component newComponent) -> {
                container.remove(index);
                container.add(newComponent);
            };

            managers.add(new ComponentRenderingManager(swingParentRenderStrategy, selfSupplier);
        }

        return managers;
    }

    public Consumer<Component> getSwingParentRenderStrategy() {
        return swingParentRenderStrategy;
    }

    public void setSwingParentRenderStrategy(Consumer<Component> strategy) {
        swingParentRenderStrategy = strategy;
    }

    public Supplier<Object> getSelfSupplier() {
        return selfSupplier;
    }

    public void setSelfSupplier(Supplier<Object> selfSupplier) {
        this.selfSupplier = selfSupplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
