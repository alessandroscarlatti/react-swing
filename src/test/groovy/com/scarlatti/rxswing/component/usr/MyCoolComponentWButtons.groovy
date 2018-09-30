package com.scarlatti.rxswing.component.usr

import com.scarlatti.rxswing.RdrMger
import com.scarlatti.rxswing.component.RxComponent
import com.scarlatti.rxswing.inspect.RxNode

import static com.scarlatti.rxswing.component.ntv.RxJButton.jButton
import static com.scarlatti.rxswing.component.ntv.RxJLabel.jLabel
import static com.scarlatti.rxswing.component.ntv.RxJPanel.jPanel

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
// this will eventually include the control buttons AND the label, but for now, just the label.
// everything else will be simulated from the outside.
class MyCoolComponentWButtons extends RxComponent {

    private int count = 0  // this is the state...

    void setState(int count) {
        this.count = count

        // now initiate render.
        RdrMger.getInstance().pleaseRdrMeExisting(this)
    }

    RxNode render() {
        return jPanel {
            it.child jButton {
                it.text = "W Up"

                // this is where it may become necessary to do "batchedUpdates" like react JS by listening
                // to some indicator on this component to see whether or not an update needs to be called.
                // Then we would initiate a callback once the re-render has occurred.
                // This might come into play when components are being removed at runtime due to a state change.
                it.onClick = {
                    setState(getState() + 1)
                }
            }
            it.child jButton {
                it.text = "W Down"
                it.onClick = {
                    setState(getState() - 1)
                }
            }
            it.child jLabel {
                it.text = String.valueOf(count)
            }
        }
    }

    int getState() {
        return count
    }
}