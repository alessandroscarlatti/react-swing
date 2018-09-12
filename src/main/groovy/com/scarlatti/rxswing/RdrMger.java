package com.scarlatti.rxswing;

import com.scarlatti.rxswing.change.RxComponentTreeChgMgr;
import com.scarlatti.rxswing.change.RxNtvCompChgPacket;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.inspect.RxDom;
import com.scarlatti.rxswing.inspect.RxNode;
import com.scarlatti.rxswing.inspect.RxNodeRealizer;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class RdrMger {
    private static RdrMger ourInstance = new RdrMger();

    private NtvComponentStore myMtdSwingComps = new NtvComponentStore();
    private ComponentStore myMtdRxComps = new ComponentStore();
    private RxDom myDom = new RxDom();

    public static RdrMger getInstance() {
        return ourInstance;
    }

    private RdrMger() {
    }

    public void pleaseRdr(RxComponent comp) {

        // get a rxNode tree...
        RxNode newDom = comp.render();

        // now fully resolve the dom...
        // we can skip this for the basic implementation with a single layer of component...
        //////////....
        newDom = new RxNodeRealizer(newDom, myMtdRxComps).realizeNode();

        // OK.  Now the new dom is fully resolved.
        // time to create a change manager for it.
        RxNtvCompChgPacket chgPacket = new RxComponentTreeChgMgr(myDom.getRoot(), newDom).createChangePacket();

        // now run the changes
        applyChgPacketRecursive(chgPacket);

        // now replace the old dom with the new dom
        myDom.setRoot(newDom);
    }

    private void applyChgPacketRecursive(RxNtvCompChgPacket chgPacket) {
        for (Runnable change : chgPacket.getChangesForSelf()) {
            change.run();
        }

        for (RxNtvCompChgPacket childChgPacket : chgPacket.getChangesForChildren()) {
            applyChgPacketRecursive(childChgPacket);
        }
    }

    public NtvComponentStore getNtvComponentStore() {
        return myMtdSwingComps;
    }

    public ComponentStore getMtdRxComps() {
        return myMtdRxComps;
    }

    public RxDom getCurrentDom() {
        return myDom;
    }
}
