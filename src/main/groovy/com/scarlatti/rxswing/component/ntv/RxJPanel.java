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
public class RxJPanel extends RxNtvComponent {

    @Override
    public Class<? extends Component> getNtvType() {
        return JPanel.class;
    }

    @Override
    public Component construct(RxNode rxNode) {
        return new JPanel();
    }

    public static RxNode jPanel(Consumer<RxJPanelNode> consumer) {
        RxNode rxNode = Rx.node(RxJPanel.class);
        RxJPanelNode rxNodeWrapper = new RxJPanelNode(rxNode);
        consumer.accept(rxNodeWrapper);
        return rxNode;
    }

    public static class RxJPanelNode extends RxNodeWrapper {
        public RxJPanelNode(RxNode rxNode) {
            super(rxNode);
        }
    }
}
