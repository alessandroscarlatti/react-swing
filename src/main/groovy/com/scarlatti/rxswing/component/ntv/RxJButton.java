package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.component.RxNtvComponent;

import javax.swing.*;
import java.awt.*;

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
    public Component construct() {
        return new JButton();
    }
}
