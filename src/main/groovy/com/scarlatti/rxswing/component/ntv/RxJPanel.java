package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.component.RxComponent;
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
public class RxJPanel extends RxComponent implements RxNtvComponent {

    @Override
    public Class<? extends Component> getNtvType() {
        return JPanel.class;
    }

    public static RxNode jPanel(Consumer<RxJPanelNode> consumer) {
        RxJPanelNode node = new RxJPanelNode();
        consumer.accept(node);
        return node;
    }

    public static class RxJPanelNode extends RxNode {
        public RxJPanelNode() {
            super(RxJPanel.class);
        }
    }
}
