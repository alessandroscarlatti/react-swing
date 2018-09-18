package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.Rx;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.ntv.RxJPanel;
import com.scarlatti.rxswing.inspect.RxNode;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 *
 * just a wrapper around the same simple jButton example...
 */
public class LottoComponent2 extends RxComponent {

    private int count = 0;  // this is the state...

    public int getState() {
        return count;
    }

    public void setState(int count) {
        this.count = count;

        // now initiate render.
        RdrMger.getInstance().pleaseRdrMeExisting(this);
    }

    @Override
    public RxNode render() {
        return Rx.node(RxJPanel.class);
    }

    private RxJPanel innerPanel() {
        RxJPanel innerPanel = new RxJPanel();
//        innerPanel.add(new RxJButton(String.valueOf(count)));
//        innerPanel.add(new RxJButton(String.valueOf(count + 1)));
////        innerPanel.add(new MyCoolComponent());
        return innerPanel;
    }
}
