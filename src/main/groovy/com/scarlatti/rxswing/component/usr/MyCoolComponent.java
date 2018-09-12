package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.Rx;
import com.scarlatti.rxswing.component.NtvBoundComponent;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.ntv.RxJLabel;
import com.scarlatti.rxswing.inspect.RxNode;

import java.time.chrono.JapaneseEra;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
// this will eventually include the control buttons AND the label, but for now, just the label.
// everything else will be simulated from the outside.
public class MyCoolComponent extends RxComponent {

    private int count = 0;  // this is the state...

    public void setState(int count) {
        this.count = count;

        // now initiate render.
        RdrMger.getInstance().pleaseRdr(this);
    }

    public RxNode render() {
        return Rx.node(RxJLabel.class)
        .props("text", String.valueOf(count));
    }

    public int getState() {
        return count;
    }
}
