package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.component.ntv.RxJLabel;
import com.scarlatti.rxswing.inspect.RxNode;

import javax.swing.*;
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
public class RxJLabelChgMger implements RxChgMger {
    private JLabel ntv;
    private RxJLabel.RxJLabelNode currentNode;
    private RxJLabel.RxJLabelNode newNode;

    public RxJLabelChgMger(JLabel ntv, RxNode currentNode, RxNode newNode) {
        Objects.requireNonNull(newNode, "Change Manager requires an RxNode.");
        Objects.requireNonNull(ntv, "Change Manager must be bound to a Swing component.  No Swing component given for " + newNode);
        this.ntv = ntv;
        this.currentNode = new RxJLabel.RxJLabelNode(currentNode);
        this.newNode = new RxJLabel.RxJLabelNode(newNode);
    }

    public List<Runnable> pleaseCreateChgPkt() {
        return Collections.singletonList(() -> {
            ntv.setText(newNode.getText());
        });
    }
}
