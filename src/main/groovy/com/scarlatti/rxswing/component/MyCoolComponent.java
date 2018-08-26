package com.scarlatti.rxswing.component;

import com.scarlatti.rxswing.RdrMgr;
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
public class MyCoolComponent {

    private int count = 0;  // this is the state...
    private RxJLabel rdrdComp;

    public void tmpRdrFirstTime() {
        rdrdComp = new RxJLabel("0");
    }

    public void setState(int count) {
        this.count = count;

        // now initiate render.
        RdrMgr.getInstance().pleaseRdr(this);
    }

    public RxJLabel render() {
        return new RxJLabel(String.valueOf(count));
    }

    public RxJLabel getRdrdComp() {
        return rdrdComp;
    }

    public int getCount() {
        return count;
    }
}
