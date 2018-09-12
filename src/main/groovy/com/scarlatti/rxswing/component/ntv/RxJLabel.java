package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.RxNtvComponent;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
public class RxJLabel extends RxComponent implements RxNtvComponent {

    @Override
    public Class<? extends Component> getNtvType() {
        return JLabel.class;
    }
}
