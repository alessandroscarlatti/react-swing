package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.inspect.RxNode;
import com.scarlatti.rxswing.inspect.RxNodeRealizer;
import com.scarlatti.rxswing.inspect.RxNodeTrimmer;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 9/2/2018
 */
public class Rx {

    public static RxNode node(Class<? extends RxComponent> clazz) {
        RxNode rxNode = new RxNode();
        rxNode.setType(clazz);
        return rxNode;
    }

    public static RxNode node(Class<? extends RxComponent> clazz, Object props) {
        return node(clazz);
    }

    public static RxNode node(Class<? extends RxComponent> clazz, Consumer<RxNode> consumer) {
        RxNode node = node(clazz);
        consumer.accept(node);
        return node;
    }

    // alright, so what we should do instead is something like:
    // Rx.render({
    //     it.child coolComponent()
    // }, intoJPanel)
    //
    // this render method will do the following:
    // - create an unrealized rxNode.
    // - assign it some unique id within the context
    // of the RdrMgr component store.  This is necessary
    // so that all child nodes can be named properly.
    // however, it will need to be the same node name upon
    // successive invocations in order to facilitate re-rendering
    // all the way from the root.
    // The first coolComponent would be named coolComponent[0],
    // coolComponent[1] and so on.
    // - next, realize the node.  At this point, the component
    // store is filled with components.
    //
    // Now we are in the quasi-change management process.
    // Because we got here through Rx.render, not RxComponent#setState()
    // the change management is not built in.  We will have to
    // initiate it.  That will part of the next major development.
    // For now, I can fake it by pulling out the component
    // from the store once it has been instantiated.
    //
    // for now, we'll just use container#add.  We can add strategies later.
    public static void render(Supplier<RxNode> nodeSupplier, Container swingContainer) {
        RxNode unrealizedNode = nodeSupplier.get();

        // set an id for this node.
        // for now, we'll pretend it's all being called only once.
        unrealizedNode.setId(unrealizedNode.getType().getName());

        // now we will have a realized node
        // any components it had to create have been added to the component store.
        RxNode realizedNode = new RxNodeRealizer(unrealizedNode, RdrMger.getInstance().getComponentStore()).realize();

        // now we have only nodes that are actually swing components.
        RxNode trimmedNode = new RxNodeTrimmer(realizedNode).trim();

        // Now we would need to apply the change management process to the realized node.
        // with respect to the swing container.
        // But for now, let's just create all the swing objects on the tree.
    }
}
