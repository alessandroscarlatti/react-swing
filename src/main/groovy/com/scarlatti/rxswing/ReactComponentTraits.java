package com.scarlatti.rxswing;

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
    protected List<AbstractReactComponent> rxComponentChildren = new ArrayList<>();

    /**
     * reactId is a string key that identifies this logical component
     * instance to React from one render to another.
     *
     * The id is essentially a "fully-qualified" path name, where each
     * piece of the path is a class name and an index.
     *
     * For example "/com.scarlatti.rxswing.RxJPanel(0)/com.scarlatti.rxswing.RxJButton(0)"
     */
    protected String reactId;
}
