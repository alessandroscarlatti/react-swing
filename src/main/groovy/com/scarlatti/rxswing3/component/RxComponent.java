package com.scarlatti.rxswing3.component;

import com.scarlatti.rxswing1.RxElement;

import java.awt.*;
import java.util.function.Consumer;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 3/28/2018
 *
 * All ReactComponents will implement this interface.
 */
public interface RxComponent<P, S> {

    P getProps();
    S getState();

    void setState(S state);

    void render(Consumer<Component> consumer);

    Object render();

    boolean componentShouldUpdate(P newProps);
    void componentWillReceiveProps(P props);

    String getKey();
}
