package com.scarlatti.rxswing

import com.scarlatti.rxswing.component.RxComponent
import com.scarlatti.rxswing.component.ntv.RxJPanel
import com.scarlatti.rxswing.inspect.RxNode
import org.junit.Test

import static com.scarlatti.rxswing.RxNodeGroovySyntaxTest.RxJPanel2.jPanel

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 9/14/2018
 */
class RxNodeGroovySyntaxTest {

    abstract class RxBaseProps {
        List<RxNode> children = []

        void child(RxNode child) {
            children.add(child)
        }
    }

    class RxJPanel2 extends RxComponent {
        static RxNode jPanel(@DelegatesTo(value = Props, strategy = Closure.DELEGATE_FIRST) Closure config) {
            return Rx.node(RxJPanel)
        }

        static class Props extends RxBaseProps {
            int count
        }
    }

    @Test
    void testSyntax() {
        jPanel {
            count = 1

            child jPanel {
                count = 1

                child jPanel {

                }
            }
        }
    }
}
