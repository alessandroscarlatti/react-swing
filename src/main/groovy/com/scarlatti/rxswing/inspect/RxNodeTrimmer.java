package com.scarlatti.rxswing.inspect;

import com.scarlatti.rxswing.component.RxNtvComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 9/5/2018
 * <p>
 * Trim the RxNode down to just NtvNodes...
 */
public class RxNodeTrimmer {

    private RxNode root;

    public RxNodeTrimmer(RxNode root) {
        this.root = root;
    }

    // return a new rxNode...
    public List<RxNode> trim() {
        return internalTrimRecursive(root);
    }

    private List<RxNode> internalTrimRecursive(RxNode node) {

        // let's try a strategy of start at the end, and
        // just make sure you don't regress while adding conditions.

        if (RxNtvComponent.class.isAssignableFrom(node.getType())) {
            if (node.getChildren().size() == 0) {
                return Collections.singletonList(new RxNode(node));
            }
            else {
                RxNode copy = new RxNode();
                copy.setType(node.getType());
                for (RxNode child : node.getChildren()) {
                    List<RxNode> actualChildren = internalTrimRecursive(child);
                    copy.getChildren().addAll(actualChildren);
                }
                return Collections.singletonList(copy);
            }
        } else {
            if (node.getChildren().size() == 0) {
                throw new IllegalStateException("Component tree branches must end in native component.");
            }
            else {
                // this case needs to not return ANYTHING, but instead
                // attach each discovered child to this root's parent???
                List<RxNode> actualChildren = new ArrayList<>();
                for (RxNode child : node.getChildren()) {
                    List<RxNode> nativeChildren = internalTrimRecursive(child);
                    actualChildren.addAll(nativeChildren);
                }
                return actualChildren;
            }
        }
    }
}
