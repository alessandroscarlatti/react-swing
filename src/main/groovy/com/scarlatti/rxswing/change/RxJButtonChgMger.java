package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.component.ntv.RxJButton;
import com.scarlatti.rxswing.inspect.RxNode;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
public class RxJButtonChgMger implements RxChgMger {
    private JButton ntv;
    private RxNode currentNode;
    private RxNode newNode;

    public RxJButtonChgMger(JButton ntv, RxNode currentNode, RxNode newNode) {
        this.ntv = ntv;
        this.currentNode = currentNode;
        this.newNode = newNode;
    }

    // limitation: using "master" right now, because we aren't replacing components at all...
    public List<Runnable> pleaseCreateChgPkt() {
        return Collections.singletonList(() -> {
            ntv.setText(new RxJButton.RxJButtonNode(newNode).getText());
        });
    }
}
