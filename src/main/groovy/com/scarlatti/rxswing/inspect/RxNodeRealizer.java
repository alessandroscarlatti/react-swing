package com.scarlatti.rxswing.inspect;

import com.scarlatti.rxswing.ComponentStore;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.RxNtvComponent;

import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Thursday, 9/6/2018
 */
public class RxNodeRealizer {

    private RxNode root;
    private ComponentStore componentStore;

    public RxNodeRealizer(RxNode root, ComponentStore componentStore) {
        this.root = root;
        this.componentStore = componentStore;
    }

    // walk through the tree,
    // realizing it until there are no branches having Usr components as tips.
    // That is, each branch MUST have a "native" component at its tip.
    // That's when we're done.
    //
    // I guess we should try the reverse strategy!!!
    public RxNode realizeNode() {
        return realizeNodeRecursive(root);
    }

    private RxNode realizeNodeRecursive(RxNode node) {

        // if the component is not already a mounted instance, create one and mount it.
        // instantiate usr AND native components into the component store
        RxComponent comp = componentStore.putIfAbsent(
            node.getId(),
            () -> instantiateRxCompFromNode(node)
        );

        if (RxNtvComponent.class.isAssignableFrom(node.getType())) {
            if (node.getChildren().size() == 0) {
                // this is the tip of the branch.
                return node;
            }
            else {
                // there are children within this node...
                // realize them...
                RxNode newNode = new RxNode();
                newNode.setId(node.getId());
                newNode.setType(node.getType());

                for (RxNode child : node.getChildren()) {
                    child.setId(computeChildId(child, newNode));
                    RxNode newChild = realizeNodeRecursive(child);
                    newNode.getChildren().add(newChild);
                }

                return newNode;
            }
        }
        else {
            // this is a usr component.
            // the children are just "data" RxNodes passed to the actual component.
            // they are not actually rendered until they are really needed, if they ever are.
            // at that time they become part of the tree, so they are rendered.

            // this is all the props, except for the children!!!
            // we should look at combining those into a RxProps class, perhaps...
            Object nextProps = node.getProps();

            // now we need to make sure that each child is properly defined.
            // actually we don't need the fully rendered children.
            // the data is what we want, not the actual rendering.
            // They may never be rendered!!!

            List<RxNode> nextChildren = node.getChildren();

            // this realized node would be perhaps a native component that was rendered by a usr component
            RxNode realizedNode = comp.getLifecycleManager().performRender(nextProps, nextChildren);
            realizedNode.setId(computeChildId(realizedNode, node));

            // the node we have just realized is now to become the single child of this usr component node.
            RxNode copy = new RxNode(node);
            copy.child(realizedNode);

            return copy;
        }
    }

    private String computeChildId(RxNode child, RxNode newParent) {
        // find nth-of-type index.
        long nthOfTypeIndex = newParent.getChildren()
            .stream()
            .filter(node -> node.getType() == child.getType())
            .count();

        return formatId(newParent.getId(), child.getType(), nthOfTypeIndex);
    }

    public static String formatId(String parentId, Class<? extends RxComponent> childClass, long nthOfTypeIndex) {
        return parentId + "/" + childClass.getName() + "[" + nthOfTypeIndex + "]";
    }

    private RxComponent instantiateRxCompFromNode(RxNode n) {
        try {
            RxComponent comp = n.getType().newInstance();
            comp.setProps(n.getProps());
            comp.getLifecycleManager().addToStore(componentStore, n.getId());
            return comp;
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating component of class " + n.getType().getName(), e);
        }
    }
}
