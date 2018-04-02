package com.scarlatti.rxswing1;

import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 3/26/2018
 */
public class ReactUtils {

    static boolean virtuallyContains(List<RxSwingComponent> components, RxSwingComponent target) {
        for (RxSwingComponent component : components) {
            if (target.virtualEquals(component)) {
                return true;
            }
        }

        return false;
    }
}