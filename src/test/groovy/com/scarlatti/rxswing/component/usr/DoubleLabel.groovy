package com.scarlatti.rxswing.component.usr

import com.scarlatti.rxswing.Rx
import com.scarlatti.rxswing.component.RxComponent
import com.scarlatti.rxswing.component.RxNodeWrapper
import com.scarlatti.rxswing.component.ntv.RxJPanel
import com.scarlatti.rxswing.inspect.RxNode

import java.util.function.Consumer

import static com.scarlatti.rxswing.component.ntv.RxJLabel.jLabel
import static com.scarlatti.rxswing.component.ntv.RxJPanel.jPanel

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 9/29/2018
 */
class DoubleLabel extends RxComponent {

    @Override
    RxNode render() {
        return jPanel {
            it.child jLabel {
                it.text = "text"
            }
            it.child jLabel {
                it.text = "text"
            }
        }
    }

    static RxNode doubleLabel(Consumer<DoubleLabelNode> consumer) {
        RxNode rxNode = Rx.node(DoubleLabel.class)
        DoubleLabelNode rxNodeWrapper = new DoubleLabelNode(rxNode)
        consumer.accept(rxNodeWrapper)
        return rxNode
    }

    static class DoubleLabelNode extends RxNodeWrapper {
        DoubleLabelNode(RxNode rxNode) {
            super(rxNode)
        }

        void setText(String text) {
            set("text", text)
        }

        String getText() {
            return get("text", String.class)
        }
    }
}
