package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.component.ntv.RxJLabel;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
// this will eventually include the control buttons AND the label, but for now, just the label.
// everything else will be simulated from the outside.
public class MyCoolComponent implements RxUsrComponent {

    private int count = 0;  // this is the state...
    private String myNtvRndId;
    private static int ntvRndId = 0;

    public MyCoolComponent() {
        this.myNtvRndId = obtainNtvRndId();
    }

    public MyCoolComponent(String myNtvRndId) {
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

    public RxJLabel render() {
        return new RxJLabel(String.valueOf(count));
    }

    public int getState() {
        return count;
    }

    public String getNtvRndId() {
        return myNtvRndId;
    }
}