package com.scarlatti.rxswing.component;

import com.scarlatti.rxswing.inspect.RxNode;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 9/4/2018
 */
public class RxComponent {

    private Object props;

    public Class<? extends RxComponent> getType() {
        return this.getClass();
    }

    public void setProps(Object props) {
        this.props = props;
    }

    public RxNode render() {
        return null;
    }
}
