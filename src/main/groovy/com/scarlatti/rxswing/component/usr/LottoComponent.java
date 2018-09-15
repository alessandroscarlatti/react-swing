package com.scarlatti.rxswing.component.usr;

import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.Rx;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.ntv.RxJButton;
import com.scarlatti.rxswing.component.ntv.RxJLabel;
import com.scarlatti.rxswing.component.ntv.RxJPanel;
import com.scarlatti.rxswing.inspect.RxNode;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 * <p>
 * just a wrapper around the same simple jButton example...
 */
public class LottoComponent extends RxComponent {

    private int count = 0;  // this is the state...

    public int getState() {
        return count;
    }

    public void setState(int count) {
        this.count = count;

        // now initiate render.
        RdrMger.getInstance().pleaseRdr(this);
    }

    @Override
    public RxNode render() {

        return Rx.node(RxJPanel.class)
            .child(
                Rx.node(RxJLabel.class)
                    .props("text", String.valueOf(count))
            )
            .child(
                Rx.node(RxJLabel.class)
                    .props("text", "what: " + String.valueOf(count))
            );
    }
}
