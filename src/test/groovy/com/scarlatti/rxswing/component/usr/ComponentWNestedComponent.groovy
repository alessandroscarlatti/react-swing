package com.scarlatti.rxswing.component.usr

import com.scarlatti.rxswing.RdrMger
import com.scarlatti.rxswing.component.RxComponent
import com.scarlatti.rxswing.inspect.RxNode

import static com.scarlatti.rxswing.component.ntv.RxJButton.jButton
import static com.scarlatti.rxswing.component.ntv.RxJPanel.jPanel
import static com.scarlatti.rxswing.component.usr.DoubleLabel.doubleLabel

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 9/29/2018
 */
class ComponentWNestedComponent extends RxComponent {

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
                it.onClick = {
                    setState {
                        count = count + 1
                    }
                }
            }
            it.child jButton {
                it.text = "W Down"
                it.onClick = {
                    setState {
                        count = count - 1
                    }
                }
            }

            it.child doubleLabel {
                it.text = count
            }
        }
    }

}