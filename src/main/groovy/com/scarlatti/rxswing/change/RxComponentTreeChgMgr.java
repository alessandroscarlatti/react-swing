package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.inspect.RxNode;

import java.util.Iterator;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 9/11/2018
 */
public class RxComponentTreeChgMgr {

    private RxNode currentNode;
    private RxNode newNode;

    /**
     * This store MAY contain a native component that matches a key in the rxNode.
     * However, if it does not, that just means that that particular node has not
     * been rendered to a native component yet.  A change management process
     * will handle that scenario.
     */
    private SwChgMgrFactory swChgMgrFactory;

    /**
     * This class represents a change manager for transforming an existing node tree
     * into a new node tree.
     *
     * @param currentNode the root of the tree (or subtree) that will be transformed.
     *                    The id of this node is expected to match the id of the new node.
     * @param newNode     The root of the tree (or subtree within the complete current dom)
     *                    that we hope to attain.
     *                    This new tree will begin with the component actually returned from rendering
     *                    the component that initiated a re-render.
     *                    For example if MySpinnerComponent #render() returns an RxJPanel node,
     *                    the newNode in this change manager should be that RxJPanel node.
     */
    public RxComponentTreeChgMgr(RxNode currentNode, RxNode newNode) {
        this.currentNode = currentNode;
        this.newNode = newNode;
        swChgMgrFactory = new SwChgMgrFactory();
    }

    public RxSwCompChgPacket createChangePacket() {
        // at this point, each rxNode is expected
        // to have a matching component instance
        // in the component store.
        // whether or not those components yet have
        // bound instances to the dom store, we DON'T know.

        // IMPORTANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // for right now, we'll pretend all items already exist.
        // we'll preload them in the test.

        // so now, we just need to walk through the new tree.
        // IF WE HAVE A MATCHING node...
        // find what are its changes.
        // send the changes to the change manager for that node.

        // 9-29-2018 NOW THAT WE ARE WORKING ON BEING ABLE TO INSERT AND DELETE NODES
        // we'll need to look at the types of each node to see if comparison can be done.

        return createChangePacketRecursiveComparingRxNodes(currentNode, newNode);
    }

    private RxSwCompChgPacket createChangePacketRecursiveComparingRxNodes(RxNode currentNode, RxNode newNode) {

        RxSwCompChgPacket compositeChgPacket = new RxSwCompChgPacket();

        // compare this node...
        RxChgMger chgMger = swChgMgrFactory.getChangeManagerFor(currentNode, newNode);
        List<Runnable> selfChgPacket = chgMger.pleaseCreateChgPkt();
        compositeChgPacket.getChangesForSelf().addAll(selfChgPacket);

        // then compare each of the children...
        Iterator<RxNode> crntNodeIter = currentNode.getChildren().iterator();
        Iterator<RxNode> newNodeIter = newNode.getChildren().iterator();
        for (; crntNodeIter.hasNext(); ) {
            RxNode nextCrntNode = crntNodeIter.next();
            RxNode nextNewNode = newNodeIter.next();
            RxSwCompChgPacket childChgPacket = createChangePacketRecursiveComparingRxNodes(nextCrntNode, nextNewNode);
            compositeChgPacket.getChangesForChildren().add(childChgPacket);
        }

        return compositeChgPacket;
    }
}
