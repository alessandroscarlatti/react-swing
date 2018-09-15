package com.scarlatti.rxswing

import org.junit.Test

import static com.scarlatti.rxswing.component.ntv.RxJPanel.jPanel

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 9/14/2018
 */
class RxNodeGroovySyntaxTest {


    @Test
    void testSyntax() {
        jPanel {
            it.set("count", 1)

            it.child jPanel {
                it.set("count", 1)

                it.child jPanel {
                }
            }

            it.child jPanel {
                it.set("text", 3)
                it.set("text", 3)
                it.child jPanel {

                }
            }
        }
    }
}
