package com.scarlatti.rxswing;

import com.scarlatti.rxswing.change.DomChangeManager;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.RxNtvComponent;
import com.scarlatti.rxswing.inspect.RxDom;
import com.scarlatti.rxswing.inspect.RxNode;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class RdrMger {
    private static RdrMger ourInstance = new RdrMger();

    private Map<String, Component> myMtdSwingComps = new HashMap<>();
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

        // OK.  Now the new dom is fully resolved.
        // time to create a change manager for it.
        List<Runnable> changes = new DomChangeManager(myDom.getRoot(), newDom).pleaseCreateChgPkt();

        // now run the changes
        for (Runnable change : changes) {
            change.run();
        }

        // now replace the old dom with the new dom
        myDom.setRoot(newDom);
    }

    // take a partially resolved rxNode tree and finish resolving it...
    // returns a new RxNode...
    // Resolve the declarations into concrete components by retrieving or creating them
    // keep going until the node component type is one of the native components
    private RxNode fullyRndrRxNodeRcrsv(RxNode rxNode) {

        // check to see if this is a native component.
        // if it is, we're done.  Just return the node.
        // todo this is not OK, because we are actually invoking
        // the constructors every time!!! in instantiateRxCompFromNode
        RxComponent comp = myMtdRxComps.putIfAbsent(rxNode.getId(), instantiateRxCompFromNode(rxNode));
        if (RxNtvComponent.class.isAssignableFrom(rxNode.getType())) {
            Component swingComp = instantiateRxNtvCompFromNode(comp);
            myMtdSwingComps.putIfAbsent(rxNode.getId(), swingComp);
            return rxNode;
        } else {
            // modify the tree...
            // todo what about props???
            rxNode.setType(comp.getType());
            return fullyRndrRxNodeRcrsv(rxNode);
        }


        // but if it's not a native component, we need to resolve it.
        // this means instantiate it as a component....

//        for (RxNode child : rxNode.getChildren()) {
//            // check to see if the the component id is already in the mounted components map
//            // if not, create it
//            myMtdRxComps.putIfAbsent(child.getId(), instantiateRxCompFromNode(child));
//
//            // now we call render on that component...
//            RxComponent comp = myMtdRxComps.get(child.getId());
//            RxNode node = comp.render();
//            child.
//        }
    }

    public RxComponent instantiateRxCompFromNode(RxNode n) {
        return null;
//        try {
//            RxComponent comp = n.getType().newInstance();
//            comp.setProps(n.getProps());
//            comp.getLifecycleManager().addToStore(myMtdRxComps, n.getId());
//            return comp;
//        } catch (Exception e) {
//            throw new RuntimeException("Error instantiating component of class " + n.getType().getName(), e);
//        }
    }

    private Component instantiateRxNtvCompFromNode(RxComponent n) {
        try {
            // this could also be done using a "NtvComponentImpl" class.
            Component comp = ((RxNtvComponent) n).getNtvType().newInstance();

            // ...add swing-specific properties
            // (maybe using a "ntvComponentImpl" class.
            return comp;
        } catch (Exception e) {
            throw new RuntimeException("Error instantiating component of class " + n.getType().getName(), e);
        }
    }

    public Map<String, Component> getMtdSwingComps() {
        return myMtdSwingComps;
    }

    public ComponentStore getMtdRxComps() {
        return myMtdRxComps;
    }

    public RxDom getCurrentDom() {
        return myDom;
    }
}
