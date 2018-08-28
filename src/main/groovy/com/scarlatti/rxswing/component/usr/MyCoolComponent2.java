package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.component.ntv.RxJButton;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 * <p>
 * the same as the other component, just using a button, not a label.
 */
public class MyCoolComponent2 implements RxUsrComponent {
    private int count = 0;  // this is the state...
    private String myNtvRndId;

    public MyCoolComponent2() {
        this.myNtvRndId = obtainNtvRndId();
    }

    public MyCoolComponent2(String myNtvRndId) {
        this.myNtvRndId = myNtvRndId;
    }

    @Override
    public String obtainNtvRndId() {
        return RdrMger.getInstance().getNextNtvRndId();
    }

    public void setState(int count) {
        this.count = count;

        // now initiate render.
        RdrMger.getInstance().pleaseRdr(this);
    }

    public RxJButton render() {
        return new RxJButton(String.valueOf(count));
    }

    public int getState() {
        return count;
    }

    public String getNtvRndId() {
        return myNtvRndId;
    }
}