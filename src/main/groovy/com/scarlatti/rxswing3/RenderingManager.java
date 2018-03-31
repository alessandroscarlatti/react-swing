package com.scarlatti.rxswing3;

import com.scarlatti.rxswing2.NotNull;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/30/2018
 *
 * Designed for tree structure.
 */
public interface RenderingManager {

    Consumer<Component> getSwingParentRenderStrategy();

    void setSwingParentRenderStrategy(Consumer<Component> strategy);

    Container getSelf();

    String getId();

    @NotNull
    List<RenderingManager> getChildren();

    void render();
}
