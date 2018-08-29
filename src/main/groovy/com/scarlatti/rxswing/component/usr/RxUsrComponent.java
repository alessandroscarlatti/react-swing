package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.component.NtvBoundComponent;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public interface RxUsrComponent extends NtvBoundComponent {

    @Override
    default String getType() {
        return RxUsrComponent.class.getSimpleName();
    }

    String obtainNtvRndId();

    int getState();

    void setState(int count);

    Object render();
}
