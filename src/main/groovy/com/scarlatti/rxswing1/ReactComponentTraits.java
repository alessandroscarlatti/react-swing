package com.scarlatti.rxswing1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 */
public class ReactComponentTraits {
    protected List<RxSwingComponent> rxComponentChildren = new ArrayList<>();
    protected int childCount = 0;
    protected RxElement self;

    protected ReactComponentTraits(RxElement self) {
        this.self = self;
    }

    /**
     * reactId is a string key that identifies the type of this component.
     * For example: "com.scarlatti.rxswing.RxJPanel"
     */
    protected String reactId;

    protected void addChild(Component child) {
        if (child instanceof RxSwingComponent) {
//            ((RxSwingComponent) child).setReactId(provideElementId());
            ((RxSwingComponent) child).setElementIndex(childCount);
            rxComponentChildren.add((RxSwingComponent) child);
        } else {
            self.addSwingChild(child);
        }

        childCount++;
    }


}