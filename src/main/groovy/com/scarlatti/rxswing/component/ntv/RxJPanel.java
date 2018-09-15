package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.Rx;
import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.RxNtvComponent;
import com.scarlatti.rxswing.component.RxPropsBase;
import com.scarlatti.rxswing.inspect.RxNode;
import groovy.lang.Closure;
import groovy.lang.DelegatesTo;

import javax.swing.*;
import java.awt.*;

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

    static RxNode jPanel(@DelegatesTo(value = Props.class, strategy = Closure.DELEGATE_FIRST) Closure config) {
        RxNode node = Rx.node(RxJPanel.class);

        config.setDelegate(node.getProps());
        config.setResolveStrategy(Closure.DELEGATE_FIRST);
        config.call();

        return node;
    }

    public static class Props extends RxPropsBase {
    }
}
