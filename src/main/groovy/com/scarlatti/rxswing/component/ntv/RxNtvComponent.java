package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.component.NtvBoundComponent;
import com.scarlatti.rxswing.component.RxComponent;

import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public interface RxNtvComponent extends NtvBoundComponent {
    @Override
    default Class<? extends RxComponent> getType() {
        return RxNtvComponent.class.getSimpleName();
    }

    Map<String, Object> getData();
}
