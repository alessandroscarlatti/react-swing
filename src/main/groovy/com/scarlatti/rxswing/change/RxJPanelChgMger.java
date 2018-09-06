package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.component.NtvBoundComponent;
import com.scarlatti.rxswing.component.ntv.RxJPanel;
import com.scarlatti.rxswing.component.ntv.RxNtvComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
public class RxJPanelChgMger implements RxChgMger {
    private RxJPanel master;
    private RxJPanel other;

    public static RxJPanelChgMger getInstance(RxJPanel master, RxJPanel other) {
        return new RxJPanelChgMger(master, other);
    }

    private RxJPanelChgMger(RxJPanel master, RxJPanel other) {
        this.master = master;
        this.other = other;
    }

    // what will we do here, because this component may contain children...
    public List<Runnable> pleaseCreateChgPkt() {

        List<Runnable> chgPkt = new ArrayList<>();
        List<NtvBoundComponent> masterChildren = master.getChildren();
        List<NtvBoundComponent> otherChildren = other.getChildren();

        // tmp limitation only compares existing children
        for (int i = 0; i < masterChildren.size(); i++) {
            // create a change packet for this component
            // (we are assuming it also exists in the other component)

            // getting it to compile...commenting it all out...
//            List<Runnable> childChanges = RdrMger.getInstance().pleaseMakeChgPktFromAToB(
//                ((RxNtvComponent) masterChildren.get(i)),
//                ((RxNtvComponent) otherChildren.get(i))
//            );

//            chgPkt.addAll(childChanges);
        }

        return chgPkt;
    }
}
