package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.component.usr.RxUsrComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public class RxJPanel extends JPanel implements RxNtvComponent {

    public RxJPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public RxJPanel(LayoutManager layout) {
        super(layout);
    }

    public RxJPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public RxJPanel() {
    }

    public Component add(RxUsrComponent comp) {
        // todo this is where we would need to use first-time-rendering features.
        return null;
    }

    private Map<String, Object> data = new HashMap<>();

    @Override
    public Map<String, Object> getData() {
        return data;
    }
}
