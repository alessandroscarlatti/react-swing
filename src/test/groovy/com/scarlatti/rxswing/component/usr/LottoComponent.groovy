package com.scarlatti.rxswing.component.usr

import com.scarlatti.rxswing.component.RxComponent
import com.scarlatti.rxswing.inspect.RxNode

import static com.scarlatti.rxswing.component.ntv.RxJLabel.jLabel
import static com.scarlatti.rxswing.component.ntv.RxJPanel.jPanel

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 * <p>
 * just a wrapper around the same simple jButton example...
 */
class LottoComponent extends RxComponent {

    private int count = 0  // this is the state...

    int getState() {
        return count
    }

    void setStateTemp(int count) {
        setState {
            this.count = count
        }
    }

    @Override
    RxNode render() {
        return jPanel {
            it.child jLabel {
                it.text = String.valueOf(count)
            }

            it.child jLabel {
                it.text = String.valueOf(count)
            }
        }
    }
}
