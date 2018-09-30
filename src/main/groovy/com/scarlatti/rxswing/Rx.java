package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.inspect.RxNode;
import com.scarlatti.rxswing.inspect.RxNodeRealizer;
import com.scarlatti.rxswing.inspect.RxNodeSwingifier;

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

    // convenience api
    public static void render(RxNode rxNode, Container swingContainer) {
        render(() -> rxNode, swingContainer);
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
    // all the way from the root.  This will be accomplished
    // by creating the root component's unique ID based on the
    // identity of the Swing Component it is being attached to!!
    //
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

        // set a globally unique id for this node.
        // it is ensured to be globally unique by using fully qualified class name
        // and system identity hash code.
        // for now, we'll pretend it's all being called only once.
        String globallyUniqueSwingContainerId = unrealizedNode.getType().getSimpleName() + "@" +
                swingContainer.getClass().getName() +
                "[" + System.identityHashCode(swingContainer) + "]";
        unrealizedNode.setId(globallyUniqueSwingContainerId);

        // now we will have a realized node
        // any components it had to create have been added to the component store.
        RxNode realizedNode = new RxNodeRealizer(unrealizedNode, RdrMger.getInstance().getRxComponentStore()).realize();

        // now we will have a node tree that contains ONLY swing components.
        RxNode swingyNode = new RxNodeSwingifier(realizedNode).swingify();

        // Now we would need to apply the change management process to the realized node.
        // with respect to the swing container.
        // But for now, let's just create all the swing objects on the tree.
        // temporarily, set up the native components
        RdrMger.getInstance().pleaseRdrMeFirstTimeTemp(swingyNode);

        // now manually attach the rendered swing component
        // to the swing container that was given us in this method.
        // this is the code that actually causes the new swing components to be rendered.
        Component swComponent = RdrMger.getInstance().getSwComponentStore().get(swingyNode.getId());
        swingContainer.add(swComponent);

        // now set the current DOM root
        RdrMger.getInstance().getCurrentDom().setRoot(realizedNode);
    }
}
