package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.component.RxComponent;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 9/7/2018
 */
public class RxComponentLifecycleManager {

    private RxComponent component;

    public RxComponentLifecycleManager(RxComponent component) {
        this.component = component;
    }
}
