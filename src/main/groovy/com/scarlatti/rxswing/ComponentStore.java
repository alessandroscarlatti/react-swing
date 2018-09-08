package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.RxComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 9/7/2018
 */
public class ComponentStore {

    private Map<String, RxComponent> components = new HashMap<>();

    public RxComponent put(String key, RxComponent value) {
        return components.put(key, value);
    }

    public RxComponent get(String key) {
        return components.get(key);
    }
}
