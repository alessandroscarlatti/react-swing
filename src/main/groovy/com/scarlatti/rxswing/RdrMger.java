package com.scarlatti.rxswing;

import com.scarlatti.rxswing.change.RxJButtonChgMger;
import com.scarlatti.rxswing.change.RxJLabelChgMger;
import com.scarlatti.rxswing.change.RxJPanelChgMger;
import com.scarlatti.rxswing.component.ntv.RxJButton;
import com.scarlatti.rxswing.component.ntv.RxJLabel;
import com.scarlatti.rxswing.component.ntv.RxJPanel;
import com.scarlatti.rxswing.component.ntv.RxNtvComponent;
import com.scarlatti.rxswing.component.usr.RxUsrComponent;

import java.util.ArrayList;
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
    private static int ntvRndId = 0;

    // this would be replaced by a comprehensive dictionary of all maps???
    // this is a map by the renderedInstanceId
    private Map<String, RxNtvComponent> myRdrdNtvs = new HashMap<>();

    public static RdrMger getInstance() {
        return ourInstance;
    }

    private RdrMger() {
    }

    public String getNextNtvRndId() {
        ntvRndId++;
        return String.valueOf(ntvRndId);
    }

    public void putNtvComp(String id, RxNtvComponent label) {
        myRdrdNtvs.put(id, label);
    }

    public void pleaseRdr(RxUsrComponent comp) {
        RxNtvComponent prevNtvComponent = myRdrdNtvs.get(comp.getNtvRndId());
        RxNtvComponent newNtvComponent = (RxNtvComponent) comp.render();
        List<Runnable> changes = pleaseMakeChgPktFromAToB(prevNtvComponent, newNtvComponent);
        for (Runnable change : changes) {
            change.run();
        }
    }

    public List<Runnable> pleaseMakeChgPktFromAToB(RxNtvComponent a, RxNtvComponent b) {
        List<Runnable> changes = new ArrayList<>();

        if (a instanceof RxJLabel)
            changes = RxJLabelChgMger.getInstance((RxJLabel) a, (RxJLabel) b).pleaseCreateChgPkt();

        if (a instanceof RxJButton)
            changes = RxJButtonChgMger.getInstance((RxJButton) a, (RxJButton) b).pleaseCreateChgPkt();

        if (a instanceof RxJPanel)
            changes = RxJPanelChgMger.getInstance((RxJPanel) a, (RxJPanel) b).pleaseCreateChgPkt();

        return changes;
    }

}
