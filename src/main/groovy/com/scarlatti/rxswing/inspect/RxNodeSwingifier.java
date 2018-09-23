package com.scarlatti.rxswing.inspect;

import com.scarlatti.rxswing.component.RxNtvComponent;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 9/5/2018
 * <p>
 * Trim the RxNode down to just NtvNodes...
 */
public class RxNodeSwingifier {

    private RxNode root;

    public RxNodeSwingifier(RxNode root) {
        this.root = root;
    }

    // return a new rxNode...
    public RxNode swingify() {
        return removeNonSwingRecursive(root);
    }

    private RxNode removeNonSwingRecursive(RxNode node) {

        // let's try a strategy of start at the end, and
        // just make sure you don't regress while adding conditions.

        if (RxNtvComponent.class.isAssignableFrom(node.getType())) {
            // this is a native component
            // if it is the tip of a branch, that is all we want.
            if (node.getChildren().size() == 0) {
                return new RxNode(node);
            }
            else {
                // this native component has children
                // we need to find the actual native component children
                // within each child branch.
                // todo this will need to not lose the subclass info when copying to a new instance.
                // todo this also means it will need to copy the value of props
                RxNode copy = new RxNode();
                copy.setType(node.getType());
                copy.setId(node.getId());
                for (RxNode child : node.getChildren()) {
                    RxNode actualChild = removeNonSwingRecursive(child);
                    copy.getChildren().add(actualChild);
                }
                return copy;
            }
        } else {
            // this is a usr component.
            // it should ultimately be represented by a native component at some point.
            if (node.getChildren().size() == 0) {
                // surely a usr component must have rendered something!
                throw new IllegalStateException("Component tree branches must end in native component.");
            }
            else {
                // this usr component has children.
                // however, according to the render rules,
                // a usr component may only render a single other component.
                // therefore, we will assert that there is only a single child.
                // we will use that single child to continue searching
                // down the branch for a native component.
                if (node.getChildren().size() > 1) {
                    throw new IllegalStateException("Component must only render only a single component.");
                }

                // so now we will traverse the first (and only)
                // child component.
                return removeNonSwingRecursive(node.getChildren().get(0));
            }
        }
    }
}
