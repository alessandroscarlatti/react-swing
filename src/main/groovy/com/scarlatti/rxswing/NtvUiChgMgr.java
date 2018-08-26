package com.scarlatti.rxswing;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class NtvUiChgMgr {
    private static NtvUiChgMgr ourInstance = new NtvUiChgMgr();

    // would be parameterized by type of ui component.
    public static NtvUiChgMgr getInstance() {
        return ourInstance;
    }

    private NtvUiChgMgr() {
    }

    // tmp limitation, only handles JLabel...
    // could use the visitor pattern for identification.
    public List<Runnable> pleaseCreateChgPkt(JLabel comp, Map<String, Object> crntMap, Map<String, Object> newMap) {

        List<Runnable> chgPkt = new ArrayList<>();

        // tmp limitation only compares existing properties...
        for (String key : crntMap.keySet()) {
            if (newMap.containsKey(key)) {
                if (!crntMap.get(key).equals(newMap.get(key))) {

                    if (key.equals("text")) {
                        chgPkt.add(() -> {
                            comp.setText(String.valueOf(newMap.get(key)));
                            comp.getParent().invalidate();
                        });
                    }
                }
            }
        }

        return chgPkt;
    }
}
