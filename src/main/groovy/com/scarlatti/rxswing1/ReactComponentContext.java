package com.scarlatti.rxswing1;

import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 3/26/2018
 */
public interface ReactComponentContext {
    RxElement getRenderedRxElement();

    List<RxElement> getRxElementChildren();

    List<RxSwingComponent> getReactComponentChildren();

    boolean virtuallyContains(RxSwingComponent component);

    List<Object> getAllChildren();

    void close();

    void renderChildren();
}