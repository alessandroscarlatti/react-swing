package com.scarlatti.rxswing.component;

import com.scarlatti.rxswing.inspect.RxNode;

import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 9/8/2018
 */
public class RxComponentProps {
    private String id;
    private List<RxNode> children;

    public void child(RxNode child) {
        children.add(child);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RxNode> getChildren() {
        return children;
    }

    public void setChildren(List<RxNode> children) {
        this.children = children;
    }
}
