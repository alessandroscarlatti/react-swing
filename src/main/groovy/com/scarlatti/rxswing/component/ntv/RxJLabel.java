package com.scarlatti.rxswing.component.ntv;

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
    public Component construct() {
        return new JLabel();
    }

    public static RxNode jLabel(Consumer<RxJLabelNode> consumer) {
        RxJLabelNode node = new RxJLabelNode();
        consumer.accept(node);
        return node;
    }

    public static class RxJLabelNode extends RxNode {
        public RxJLabelNode() {
            super(RxJLabel.class);
        }

        public void setText(String text) {
            set("text", text);
        }

        public String getText() {
            return get("text", String.class);
        }
    }
}
