package com.scarlatti.rxswing.component;

import java.util.ArrayList;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public interface NtvBoundComponent {

    Class<? extends RxComponent> getType();

    String getNtvRndId();

    default List<NtvBoundComponent> getChildren() {
        return new ArrayList<>();
    }

    void setNtvRndId(String id);

    String getNextChildNtvRndId();

    default void markComponentAsNextChild(NtvBoundComponent component) {
        String nextNtvRndId = getNextChildNtvRndId();
        component.setNtvRndId(nextNtvRndId);
    }

    class ChildIdIncrementer {
        private int count = -1;

        public String getNextId() {
            count++;
            return String.valueOf(count);
        }
    }
}
