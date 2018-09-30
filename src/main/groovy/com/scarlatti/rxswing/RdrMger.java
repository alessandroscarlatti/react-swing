package com.scarlatti.rxswing;

import com.scarlatti.rxswing.change.RxComponentTreeChgMgr;
import com.scarlatti.rxswing.change.RxSwCompChgPacket;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.RxNtvComponent;
import com.scarlatti.rxswing.inspect.RxDom;
import com.scarlatti.rxswing.inspect.RxNode;
import com.scarlatti.rxswing.inspect.RxNodeRealizer;
import com.scarlatti.rxswing.inspect.RxNodeSwingifier;

import java.awt.*;
import java.util.Collections;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class RdrMger {
    private static RdrMger ourInstance = new RdrMger();

    private SwComponentStore mySwComps = new SwComponentStore();
    private RxComponentStore myRxComps = new RxComponentStore();
    private RxDom myDom = new RxDom();

    public static RdrMger getInstance() {
        return ourInstance;
    }

    private RdrMger() {
    }

    public void pleaseRdrMeExisting(RxComponent comp) {

        // get a rxNode tree...
        RxNode newNode = comp.getLifecycleManager().performRender(null, Collections.emptyList());

        // temporary...
        // where should this be set?
        // what about rendering a component that is not the root component?
        // the actual id would be based on the previous render??
        // newDom.setId("jlabel");

        // now fully resolve the dom...
        // todo this appears to be duplicating the jPanel rendered from the DoubleLabel component.
        newNode = new RxNodeRealizer(newNode, myRxComps).realize();

        // OK.  Now the new dom is fully resolved.
        // time to create a change manager for it.
        //
        // we cannot assume that we are starting with the root.
        // this will not always be true.
        // but we don't have to start with the root.
        // we can just replace the old node on the tree with the new node.

        // 9-29-2018 NOW THAT WE ARE WORKING ON INSERTING AND DELETING NODES
        // we can't expect that "resolving" the newDom's Id in the existing dom
        // is going to return anything.
        // If we find a match it will need to be based on the "first child of the parent".
        // todo should we refactor this stuff so that we are always doing
        // the tree change manager by comparing the COMPONENT that started the render?
        // We DO KNOW that that won't be different regardless of whether the type
        // rendered is the same or different.

        RxNode nodeToReplace = myDom.resolve(newNode.getId());

        // swingify both nodes
        RxNode swingyNodeToReplace = new RxNodeSwingifier(nodeToReplace).swingify();
        RxNode newSwingyNode = new RxNodeSwingifier(nodeToReplace).swingify();

        // it looks like this is assuming a swingy node, which it might not be!
        // todo before we can work on isertions and deletions, we need to fix this part...
        RxSwCompChgPacket chgPacket = new RxComponentTreeChgMgr(swingyNodeToReplace, newSwingyNode).createChangePacket();

        // now run the changes
        applyChgPacketRecursive(chgPacket);

        // now replace the old dom with the new dom
        //
        // we need to know which piece to replace.
        // it may not be the root!
        // and we need to know which child it was...
        myDom.replace(nodeToReplace.getId(), newNode);
    }

    public void pleaseRdrMeFirstTimeTemp(RxNode swingyNode) {
        // send a visitor through the node tree
        // and instantiate every swing component!
        // this is temporary because we definitely want something
        // far more robust!

        swingyNode.walkTree(new RxNode.Visitor() {
            @Override
            protected void visitNode(RxNode node) {

                // this is a swing component.
                // INSTANTIATE IT using #construct().
                RxNtvComponent ntvComponent = (RxNtvComponent) RdrMger.getInstance().getRxComponentStore().get(node.getId());
                Component swComponent = ntvComponent.construct(node);

                // attach the new swing component to its parent swing component
                // we use the swing component store to get the parent.
                RxNode parent = node.getParent();
                if (parent != null) {
                    Container ntvParent = (Container) RdrMger.getInstance().getSwComponentStore().get(parent.getId());
                    ntvParent.add(swComponent);
                }

                // add it to the store.
                RdrMger.getInstance().getSwComponentStore().putIfAbsent(node.getId(), swComponent);

                // keep walking the tree
                super.visitNode(node);
            }
        });
    }

    private void applyChgPacketRecursive(RxSwCompChgPacket chgPacket) {
        for (Runnable change : chgPacket.getChangesForSelf()) {
            change.run();
        }

        for (RxSwCompChgPacket childChgPacket : chgPacket.getChangesForChildren()) {
            applyChgPacketRecursive(childChgPacket);
        }
    }

    public SwComponentStore getSwComponentStore() {
        return mySwComps;
    }

    public RxComponentStore getRxComponentStore() {
        return myRxComps;
    }

    public RxDom getCurrentDom() {
        return myDom;
    }
}
