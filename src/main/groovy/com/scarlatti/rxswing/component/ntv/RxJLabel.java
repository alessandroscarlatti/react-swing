package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.Rx;
import com.scarlatti.rxswing.component.RxNodeWrapper;
import com.scarlatti.rxswing.component.RxNtvComponent;
import com.scarlatti.rxswing.inspect.RxNode;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
public class RxJLabel extends RxNtvComponent {

    @Override
    public Class<? extends Component> getNtvType() {
        return JLabel.class;
    }

    @Override
    public Component construct(RxNode rxNode) {
        // I could simply decorate the plain old vanilla RxNode
        // as an RxJLabelNode, or any other node I'll ever need.
        // This would leave generics out of the change management process
        // for the time being.  And keep the specific information really
        // only where it is right next to the UI definitions.
        RxJLabelNode rxNodeWrapper = new RxJLabelNode(rxNode);
        JLabel jLabel = new JLabel();
        jLabel.setText(rxNodeWrapper.getText());
        return jLabel;
    }

    public static RxNode jLabel(Consumer<RxJLabelNode> consumer) {
        RxNode rxNode = Rx.node(RxJLabel.class);
        RxJLabelNode rxJLabelNode = new RxJLabelNode(rxNode);
        consumer.accept(rxJLabelNode);
        return rxNode;
    }

    public static class RxJLabelNode extends RxNodeWrapper {
        public RxJLabelNode(RxNode rxNode) {
            super(rxNode);
        }

        public void setText(String text) {
            set("text", text);
        }

        public String getText() {
            return get("text", String.class);
        }
    }
}
