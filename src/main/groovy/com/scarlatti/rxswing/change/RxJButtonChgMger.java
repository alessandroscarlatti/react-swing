package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.component.ntv.RxJButton;
import com.scarlatti.rxswing.inspect.RxNode;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
public class RxJButtonChgMger implements RxChgMger {
    private JButton ntv;
    private RxJButton.RxJButtonNode currentNode;
    private RxJButton.RxJButtonNode newNode;

    public RxJButtonChgMger(JButton ntv, RxNode currentNode, RxNode newNode) {
        this.ntv = ntv;
        this.currentNode = new RxJButton.RxJButtonNode(currentNode);
        this.newNode = new RxJButton.RxJButtonNode(newNode);
    }

    // limitation: using "master" right now, because we aren't replacing components at all...
    public List<Runnable> pleaseCreateChgPkt() {

        Runnable onClickChg = () -> {
            for (ActionListener actionListener : ntv.getActionListeners()) {
                ntv.removeActionListener(actionListener);
            }

            ntv.addActionListener(e -> newNode.getOnClick().run());
        };


        return Collections.singletonList(() -> {
            ntv.setText(newNode.getText());
        });
    }
}
