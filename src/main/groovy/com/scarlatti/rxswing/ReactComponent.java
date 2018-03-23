package com.scarlatti.rxswing;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 * <p>
 * This is the class that the developer will implement
 * to make RxSwing components.
 */
public abstract class ReactComponent {

    /**
     * This method may be called in two scenarios:
     * - through an ancestor render chain
     * - through a state change render
     *
     * @return the component to be rendered.
     */
    public abstract RxComponent render();
}
