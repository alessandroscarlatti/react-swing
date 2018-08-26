package com.scarlatti.rxswing;

import javax.swing.*;
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
public class RdrMgr {
    private static RdrMgr ourInstance = new RdrMgr();

    // this would be replaced by a comprehensive dictionary of all maps???
    private Map<String, Object> tmpMap = new HashMap<>();

    public static RdrMgr getInstance() {
        return ourInstance;
    }

    private RdrMgr() {
        tmpInitMap();
    }

    private void tmpInitMap() {
        tmpMap.put("text", "0");
    }

    // tmp limitation, only accept jLabel.
    public void pleaseRdr(TestCmp comp, JLabel jLabel) {
        // get the maps...
        Map<String, Object> crntMap = tmpMap;
        Map<String, Object> newMap = comp.render();

        // now compare the maps...
        List<Runnable> runnables = NtvUiChgMgr.getInstance().pleaseCreateChgPkt(jLabel, crntMap, newMap);

        for (Runnable runnable : runnables) {
            runnable.run();
        }

        // now replace the old map with the new map.
        tmpMap = newMap;
    }

    // the component will ask the the mgr for the rdrMap.
    public Map<String, Object> getRdrMap() {
        return tmpMap;
    }
}
