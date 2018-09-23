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
 * Tuesday, 8/28/2018
 */
public class RxJButton extends RxNtvComponent {
    @Override
    public Class<? extends Component> getNtvType() {
        return JButton.class;
    }

    @Override
    public Component construct(RxNode rxNode) {
        RxJButtonNode rxNodeWrapper = new RxJButtonNode(rxNode);
        JButton jButton = new JButton();
        jButton.setText(rxNodeWrapper.getText());
        return jButton;
    }

    public static RxNode jButton(Consumer<RxJButtonNode> consumer) {
        RxNode rxNode = Rx.node(RxJButton.class);
        RxJButtonNode rxNodeWrapper = new RxJButtonNode(rxNode);
        consumer.accept(rxNodeWrapper);
        return rxNode;
    }

    public static class RxJButtonNode extends RxNodeWrapper {
        public RxJButtonNode(RxNode rxNode) {
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
