package com.scarlatti.rxswing;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 9/7/2018
 */
public class SwComponentStore {

    private Map<String, Component> components = new HashMap<>();

    public Component put(String key, Component value) {
        validateKey(key);
        return components.putIfAbsent(key, value);
    }

    public Component putIfAbsent(String key, Component value) {
        validateKey(key);
        return components.putIfAbsent(key, value);
    }

    public Component putIfAbsent(String key, Supplier<Component> value) {
        validateKey(key);

        // don't use #compute()! it will mess up the internal state of the map!
        if (components.containsKey(key)) {
            return components.get(key);
        }
        else {
            return components.put(key, value.get());
        }
    }

    public Component get(String key) {
        return components.get(key);
    }

    public int size() {
        return components.size();
    }

    /**
     * The id should look like:
     *
     * it's a class/index sort of thing, referenced from the root node.
     *
     * so for:
     * <div>
     *     <MyComp1>
     *         <div>
     *              <Button></Button>
     *              <Button></Button>
     *         </div>
     *     </MyComp1>
     * </div>
     *
     * we should have a store with:
     *
     * /div[0]
     * /div[0]/MyComp1[0]
     * /div[0]/MyComp1[0]/div[0]
     * /div[0]/MyComp1[0]/div[0]/Button[0]
     * /div[0]/MyComp1[0]/div[0]/Button[1]
     *
     * @param key the key for the component being added to the store.
     */
    private void validateKey(String key) {
        Objects.requireNonNull(key, "Each component in the store must have an id");
    }
}
