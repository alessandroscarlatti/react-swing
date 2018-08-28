package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.component.ntv.RxJButton;
import com.scarlatti.rxswing.component.ntv.RxJPanel;

import javax.swing.border.CompoundBorder;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 *
 * just a wrapper around the same simple jButton example...
 */
public class LottoComponent implements RxUsrComponent {

    private int count = 0;  // this is the state...
    private String myNtvRndId;

    @Override
    public String obtainNtvRndId() {
        return RdrMger.getInstance().getNextNtvRndId();
    }

    @Override
    public int getState() {
        return count;
    }

    @Override
    public void setState(int count) {
        this.count = count;

        // now initiate render.
        RdrMger.getInstance().pleaseRdr(this);
    }

    @Override
    public RxJPanel render() {
        RxJPanel panel = new RxJPanel();
        panel.setBorder(new CompoundBorder());
        panel.add(new RxJButton(String.valueOf(count)));
        return panel;
    }

    @Override
    public String getNtvRndId() {
        return myNtvRndId;
    }
}
