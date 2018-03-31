package com.scarlatti.rxswing1;

import java.util.ArrayList;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 3/26/2018
 */
public class SimpleReactComponentContext implements ReactComponentContext {

    private RxElement renderedRxElement;
    private List<RxElement> rxElementChildren;

    public SimpleReactComponentContext(RxElement renderedRxElement) {
        this.renderedRxElement = renderedRxElement;
        rxElementChildren = new ArrayList<>();
    }

    public SimpleReactComponentContext() {
        rxElementChildren = new ArrayList<>();
    }

    @Override
    public RxElement getRenderedRxElement() {
        return renderedRxElement;
    }

    @Override
    public List<RxElement> getRxElementChildren() {
        return rxElementChildren;
    }

    @Override
    public List<RxSwingComponent> getReactComponentChildren() {
        return renderedRxElement.provideDirectChildren();
    }

    @Override
    public List<Object> getAllChildren() {
        List<Object> children = new ArrayList<>();
        children.addAll(rxElementChildren);
        children.addAll(getReactComponentChildren());
        return children;
    }

    @Override
    public boolean virtuallyContains(RxSwingComponent target) {
        for (RxSwingComponent component : getReactComponentChildren()) {
            if (target.virtualEquals(component)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void close() {
        rxElementChildren = null;
        renderedRxElement = null;
    }

    @Override
    public void renderChildren() {
        // take each direct child...
        // directly add RxElements to this renderedRxElement
        // HOWEVER...for child ReactComponents...
        // get the real component to use, either an existing component, or the new virtual one
    }

    private void renderRxElement() {

    }

    private void renderReactComponent() {

    }
}
