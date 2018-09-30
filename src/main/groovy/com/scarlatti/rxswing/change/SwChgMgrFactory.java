package com.scarlatti.rxswing.change;

import com.scarlatti.rxswing.RxComponentStore;
import com.scarlatti.rxswing.SwComponentStore;
import com.scarlatti.rxswing.RdrMger;
import com.scarlatti.rxswing.component.RxNtvComponent;
import com.scarlatti.rxswing.inspect.RxNode;

import javax.swing.*;
import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 9/11/2018
 */
public class SwChgMgrFactory {

    private SwComponentStore swComponentStore;
    private RxComponentStore componentStore;

    public SwChgMgrFactory() {
        this.swComponentStore = RdrMger.getInstance().getSwComponentStore();
        this.componentStore = RdrMger.getInstance().getRxComponentStore();
    }

    public RxChgMger getChangeManagerFor(RxNode currentNode, RxNode newNode) {

        Class<? extends Component> clazz = getNtvComponentType(newNode);
        Component ntvComponent = swComponentStore.get(newNode.getId());

        if (clazz == JLabel.class) {
            return new RxJLabelChgMger((JLabel) ntvComponent, currentNode, newNode);
        }
        else if (clazz == JButton.class) {
            return new RxJButtonChgMger((JButton) ntvComponent, currentNode, newNode);
        }
        else if (clazz == JPanel.class) {
            return new RxJPanelChgMger((JPanel) ntvComponent, currentNode, newNode);
        }

        throw new IllegalStateException("no change manager found for nodes " + currentNode + " and " + newNode);
    }

    private Class<? extends Component> getNtvComponentType(RxNode node) {
        try {
            return ((RxNtvComponent) componentStore.get(node.getId())).getNtvType();
        } catch (Exception e) {
            throw new IllegalStateException("RxNode is not a native node. " + node, e);
        }
    }
}