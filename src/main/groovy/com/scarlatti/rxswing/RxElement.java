package com.scarlatti.rxswing;

import java.awt.*;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 * <p>
 * An RxElement is able to provide a real Swing object to the caller.
 * This object is fully functional; it is the actual object that will
 * be displayed by swing to the client.
 * <p>
 * Implementations may provide the component based on captured interactions
 * with the object.
 */
public interface RxElement {

    /**
     * Provide the actual Swing component.
     * In most implementations this is probably going to be
     * based on captured developer interactions with the
     * RxSwing component.
     *
     * @return the actual Swing component to render
     */
    Component provideComponent();

    /**
     * Provide the reactId to the caller.
     *
     * @return the reactId for this component.
     */
    String provideElementId();

    /**
     * TODO is this necessary?
     *
     * Provide the reactIndex to the caller.
     * The reactIndex indicates the index of this child
     * within the parent component's list of children.
     *
     * @return the reactIndex for this component.
     */
    int provideElementIndex();

    /**
     * Provide all direct React component children to this element.
     * @return all direct React component children to this element.
     */
    List<AbstractReactComponent> provideDirectChildren();

    void addSwingChild(Component child);
}
