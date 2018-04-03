package com.scarlatti.rxswing3.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Monday, 4/2/2018
 */
public class ClickListener implements MouseListener {

    private Runnable runnable;

    public ClickListener(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        runnable.run();
    }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
