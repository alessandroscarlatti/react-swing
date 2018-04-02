package com.scarlatti.rxswing1;

import java.awt.*;
import java.util.Objects;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 3/26/2018
 */
public class RootReactContext implements ReactContext {

    private String id;
    private Container swingParent;
    private RxSwingComponent renderedComponent;

    RootReactContext(String id, Container swingParent) {
        Objects.requireNonNull(id, "React Context ID cannot be null.");
        Objects.requireNonNull(id, "Swing Parent cannot be null.");
        this.id = id;
        this.swingParent = swingParent;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public RxSwingComponent getRenderedComponent() {
        return renderedComponent;
    }

    /**
     * Rendered from outside.
     * @param component the component to render.
     */
    @Override
    public void render(RxSwingComponent component) {
        component.setReactContext(this);

        // render this child in the parent
        if (component.virtualEquals(renderedComponent)) {
            // already exists
            renderComponentAsExisting(component, renderedComponent);
        } else {
            // is new
            renderComponentAsNew(component);
        }
    }

    @SuppressWarnings("unchecked")
    private void renderComponentAsExisting(RxSwingComponent virtual, RxSwingComponent actual) {
        swingParent.remove(actual.getElementIndex());
        RxElement rxElement = actual.abstractRerender(virtual); // this line will set in motion all second level components...
        Component swingComponent = rxElement.provideComponent();
        swingParent.add(swingComponent, actual.getElementIndex());
        renderedComponent = actual;
    }

    private void renderComponentAsNew(RxSwingComponent actual) {
        swingParent.removeAll();  // TODO this probably shouldn't be here
        RxElement rxElement = actual.abstractRender(null); // this line will set in motion all second level components...
        Component swingComponent = rxElement.provideComponent();
        swingParent.add(swingComponent, actual.getElementIndex());
        renderedComponent = actual;
    }
}
