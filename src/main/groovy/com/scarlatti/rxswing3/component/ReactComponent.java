package com.scarlatti.rxswing3.component;

import com.scarlatti.rxswing3.render.ComponentRenderingManager;

import java.awt.*;
import java.util.function.Consumer;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 3/31/2018
 */
public abstract class ReactComponent<P, S> extends Container implements RxComponent<P, S> {

    protected P props;
    protected S state;

    protected String key;

    private ComponentRenderingManager renderingManager;

    public ReactComponent(String key) {
        this.key = key;
        renderingManager = new ComponentRenderingManager(key, this::render);
    }

    @Override
    public P getProps() {
        return props;
    }

    @Override
    public S getState() {
        return state;
    }

    @Override
    public void setState(S state) {
        this.state = state;

        renderingManager.render();
    }

    @Override
    public void render(Consumer<Component> consumer) {
        renderingManager.setSwingParentRenderStrategy(consumer);
        renderingManager.render();
    }

    @Override
    public boolean componentShouldUpdate(P newProps) {
        return true;
    }

    @Override
    public void componentWillReceiveProps(P props) {
    }

    @Override
    public String getKey() {
        return key;
    }

    public ComponentRenderingManager getRenderingManager() {
        return renderingManager;
    }
}
