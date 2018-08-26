package com.scarlatti.rxswing;

import com.scarlatti.rxswing.change.RxJLabelChgMgr;
import com.scarlatti.rxswing.component.MyCoolComponent;
import com.scarlatti.rxswing.component.ntv.RxJLabel;

import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class RdrMgr {
    private static RdrMgr ourInstance = new RdrMgr();

    // this would be replaced by a comprehensive dictionary of all maps???
//    private RxJLabel tmpRxJLabel;

    public static RdrMgr getInstance() {
        return ourInstance;
    }

    private RdrMgr() {
        tmpInitMap();
    }

    private void tmpInitMap() {
//        tmpRxJLabel = new RxJLabel("0");
    }

    // tmp limitation, only accept jLabel as a change strategy...
    public void pleaseRdr(MyCoolComponent comp) {
        // get the maps...
        RxJLabel newJLabel = comp.render();

        // now compare the maps...
        List<Runnable> changes = RxJLabelChgMgr.getInstance(comp.getRdrdComp(), newJLabel).pleaseCreateChgPkt();

        for (Runnable change : changes) {
            change.run();
        }
    }
}
