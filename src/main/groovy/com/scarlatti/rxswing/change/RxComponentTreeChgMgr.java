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
    private NtvChgMgrFactory ntvChgMgrFactory;

    public RxComponentTreeChgMgr(RxNode currentNode, RxNode newNode) {
        this.currentNode = currentNode;
        this.newNode = newNode;
        ntvChgMgrFactory = new NtvChgMgrFactory();
    }

    public RxNtvCompChgPacket createChangePacket() {
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

        return createChangePacketRecursive(currentNode, newNode);
    }

    private RxNtvCompChgPacket createChangePacketRecursive(RxNode currentNode, RxNode newNode) {

        RxNtvCompChgPacket compositeChgPacket = new RxNtvCompChgPacket();

        // compare this node...
        RxChgMger chgMger = ntvChgMgrFactory.getChangeManagerFor(currentNode, newNode);
        List<Runnable> selfChgPacket = chgMger.pleaseCreateChgPkt();
        compositeChgPacket.getChangesForSelf().addAll(selfChgPacket);

        // then compare each of the children...
        Iterator<RxNode> crntNodeIter = currentNode.getChildren().iterator();
        Iterator<RxNode> newNodeIter = currentNode.getChildren().iterator();
        for (; crntNodeIter.hasNext(); ) {
            RxNode nextCrntNode = crntNodeIter.next();
            RxNode nextNewNode = newNodeIter.next();
            RxNtvCompChgPacket childChgPacket = createChangePacketRecursive(nextCrntNode, nextNewNode);
            compositeChgPacket.getChangesForChildren().add(childChgPacket);
        }

        return compositeChgPacket;
    }
}
