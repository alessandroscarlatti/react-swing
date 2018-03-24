package com.scarlatti.rxswing;

import javax.swing.*;
import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 * <p>
 * This would be the class that the developer uses.
 * It will be as if using a real JPanel.
 */
public class RxJButton extends JButton implements RxComponent {

    public RxJButton(String text) {
        super(text);
    }

    /**
     * Returning this component for now.  Will implement a different
     * strategy when we start performing diffs.
     *
     * @return the actual Swing component
     */
    @Override
    public Component provideComponent() {
        return this;
    }

    @Override
    public String provideElementId() {
        return null;
    }

    @Override
    public int provideElementIndex() {
        return 0;
    }
}
