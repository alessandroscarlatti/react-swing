package com.scarlatti.rxswing3.render;

import com.scarlatti.rxswing3.component.ReactComponent;
import com.scarlatti.rxswing3.component.RxComponent;

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

    private ReactComponent associatedComponent;

    private Component mostRecentRendering;

    private boolean firstRender = true;

    public ComponentRenderingManager(ReactComponent associatedComponent, String id, Supplier<Object> selfSupplier) {
        this.associatedComponent = associatedComponent;
        this.id = id;
        this.selfSupplier = selfSupplier;
        children = new ArrayList<>();
    }

    public ComponentRenderingManager(Consumer<Component> swingParentRenderStrategy, Supplier<Object> selfSupplier) {
        this.swingParentRenderStrategy = swingParentRenderStrategy;
        this.selfSupplier = selfSupplier;
        children = new ArrayList<>();
    }

    public ComponentRenderingManager(String id, Consumer<Component> swingParentRenderStrategy, Supplier<Object> selfSupplier) {
        this.id = id;
        this.swingParentRenderStrategy = swingParentRenderStrategy;
        this.selfSupplier = selfSupplier;
        children = new ArrayList<>();
    }

    public Component render() {
        // decide between first and second times...

        Component renderedComponent;

        if (firstRender) {
            renderedComponent = firstRender();
        } else {
            renderedComponent = secondRender();
        }

        mostRecentRendering = renderedComponent;
        return renderedComponent;
    }

    public Component firstRender() {
        firstRender = false;
        Object self = selfSupplier.get();

        // interpret the self correctly to get the swing component.
        if (self instanceof RxComponent) {
            // pass on this parent render strategy to the component
            // and render this component
            this.self = self;
            return ((RxComponent) self).render(swingParentRenderStrategy);

        } else if (self instanceof Container) {
            // we need to look for children and render them

            List<ComponentRenderingManager> managers = generateRenderingManagersFirstTime((Container) self);
            children = managers;
            for (ComponentRenderingManager manager : managers) {
                manager.render();
            }

            swingParentRenderStrategy.accept((Container) self);

            return (Container) self;
        } else if (self instanceof Component) {
            // we only need to render this component in the parent, using the strategy
            swingParentRenderStrategy.accept((Component) self);

            return (Component) self;
        } else {
            throw new RuntimeException("Unexpected Component type: " + self.getClass().getName());
        }
    }

    public Component secondRender() {

        Object self = selfSupplier.get();

        // interpret the self correctly to get the swing component.
        if (self instanceof RxComponent) {
            // pass on this parent render strategy to the component
            // and render this component


            // check if we are going to use this component or not
            ReactComponent selfComponent = (ReactComponent) self;

            if (((RxComponent) self).getKey().equals(id)) {
                // now check for updating props since we're using an existing component
                if (((ReactComponent) self).componentShouldUpdate(selfComponent.getProps())) {
                    selfComponent.getRenderingManager().associatedComponent.componentWillReceiveProps(selfComponent.getProps());
                    return selfComponent.render(swingParentRenderStrategy);
                } else {
                    swingParentRenderStrategy.accept(mostRecentRendering);
                    return mostRecentRendering;
                }
            } else {
                this.self = self;
            }

            return ((RxComponent) self).render(swingParentRenderStrategy);

        } else if (self instanceof Container) {
            // we need to look for children and render them

            List<ComponentRenderingManager> managers = generateRenderingManagersSecondTime((Container) self);
            children = managers;
            for (ComponentRenderingManager manager : managers) {
                manager.render();
            }

            swingParentRenderStrategy.accept((Container) self);

            return (Container) self;
        } else if (self instanceof Component) {
            // we only need to render this component in the parent, using the strategy
            swingParentRenderStrategy.accept((Component) self);

            return (Component) self;
        } else {
            throw new RuntimeException("Unexpected Component type: " + self.getClass().getName());
        }


//        Object self = selfSupplier.get();
//
//        // interpret the self correctly to get the swing component.
//        if (self instanceof RxComponent) {
//            // pass on this parent render strategy to the component
//            // and render this component
//            // ... need to check if this component is the currently rendered component
//
//
//        } else if (self instanceof Container) {
//            // we need to look for children and render them
//        } else if (self instanceof Component) {
//            // we only need to render this component in the parent, using the strategy
//            swingParentRenderStrategy.accept((Component) self);
//        } else {
//            throw new RuntimeException("Unexpected Component type: " + self.getClass().getName());
//        }
    }

    private List<ComponentRenderingManager> generateRenderingManagersFirstTime(Container container) {
        Component[] components = container.getComponents();
        List<ComponentRenderingManager> managers = new ArrayList<>(components.length);

        for (int i = 0; i < components.length; i++) {

            Component component = components[i];

            ComponentRenderingManager manager;
            if (component instanceof RxComponent) {
                manager = generateManagerForRxComponentFirstTime(container, (RxComponent) component, i);

            } else {
                manager = generateManagerForSwingComponent(container, component, i);
            }

            managers.add(manager);
        }

        return managers;
    }

    private List<ComponentRenderingManager> generateRenderingManagersSecondTime(Container container) {
        Component[] components = container.getComponents();
        List<ComponentRenderingManager> managers = new ArrayList<>(components.length);

        for (int i = 0; i < components.length; i++) {

            Component component = components[i];

            ComponentRenderingManager manager;
            if (component instanceof RxComponent) {
                manager = generateManagerForRxComponentSecondTime(container, (RxComponent) component, i);
            } else {
                manager = generateManagerForSwingComponent(container, component, i);
            }

            managers.add(manager);
        }

        return managers;
    }

    // HAS SIDE EFFECT!!!
    private ComponentRenderingManager generateManagerForRxComponentFirstTime(Container container, RxComponent component, int index) {

//        if (managerAlreadyExists(virtualComponent))
//            return ((ReactComponent) virtualComponent).getRenderingManager();

        Consumer<Component> swingParentRenderStrategy = (Component newComponent) -> {
            container.remove(index);
            container.add(newComponent, index);
            container.revalidate();
            container.repaint();
        };

        ((ReactComponent) component).getRenderingManager().setSwingParentRenderStrategy(swingParentRenderStrategy);

        return ((ReactComponent) component).getRenderingManager();
    }

    private ComponentRenderingManager generateManagerForRxComponentSecondTime(Container container, RxComponent component, int index) {

        ComponentRenderingManager existingManager = getExistingManager(component);
        if (existingManager != null) {

            Consumer<Component> swingParentRenderStrategy = (Component newComponent) -> {
                container.remove(index);
                container.add(newComponent, index);
                container.revalidate();
                container.repaint();
            };

            existingManager.setSwingParentRenderStrategy(swingParentRenderStrategy);

            // now check for updating props since we're using an existing component
            if (existingManager.associatedComponent.componentShouldUpdate(component.getProps())) {
                existingManager.associatedComponent.componentWillReceiveProps(component.getProps());
            } else {
                existingManager.setSelfSupplier(() -> existingManager.mostRecentRendering);
            }

            return existingManager;
        }

        return generateManagerForRxComponentFirstTime(container, component, index);
    }

    private ComponentRenderingManager generateManagerForSwingComponent(Container container, Component virtualComponent, int index) {

        Supplier<Object> selfSupplier = () -> virtualComponent;

        Consumer<Component> swingParentRenderStrategy = (Component newComponent) -> {
            container.remove(index);
            container.add(newComponent, index);
            container.revalidate();
            container.repaint();
        };

        return new ComponentRenderingManager(swingParentRenderStrategy, selfSupplier);
    }

    private ComponentRenderingManager getExistingManager(RxComponent rxComponent) {
        for (ComponentRenderingManager manager : children) {
            if (manager.getId().equals(rxComponent.getKey()))
                return manager;
        }

        return null;
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
