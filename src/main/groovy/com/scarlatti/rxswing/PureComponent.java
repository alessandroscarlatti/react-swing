package com.scarlatti.rxswing;

import java.util.Objects;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 */
public abstract class PureComponent<P, S> extends AbstractReactComponent<P, S> {

    public PureComponent() {
        super();
    }

    public PureComponent(P props) {
        this.props = props;
    }

    @Override
    public boolean componentShouldUpdate(P oldProps, P newProps) {
        return !Objects.equals(oldProps, newProps);
    }
}
