package com.scarlatti.rxswing2;

import com.scarlatti.rxswing1.RxElement;

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

    RxElement abstractRender();
    RxElement abstractRerender(RxComponent<P, S> virtual);

    Object render();

    boolean componentShouldUpdate(P newProps);
    void componentWillReceiveProps(P props);

    String getKey();
}
