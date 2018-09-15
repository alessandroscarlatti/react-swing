package com.scarlatti.rxswing.inspect;

import java.util.Objects;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 9/4/2018
 */
public class RxDom {

    private RxNode root;

    // search the dom for a node with the given id
    public RxNode resolve(String id) {
        return root.searchTree(node -> Objects.equals(node.getId(), id));
    }

    public void replace(String id, RxNode replacement) {
        if (Objects.equals(root.getId(), id)) {
            root = replacement;
        }
        else {
            root.replace(id, replacement);
        }
    }

    public RxNode getRoot() {
        return root;
    }

    public void setRoot(RxNode root) {
        this.root = root;
    }
}
