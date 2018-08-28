package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.component.RxNtvComponent;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public class RxJButton extends JButton implements RxNtvComponent {

    public static final String TEXT_PROPERTY = "text";
    private Map<String, Object> data;

    public RxJButton() {
    }

    public RxJButton(Icon icon) {
        super(icon);
    }

    public RxJButton(String text) {
        super(text);
    }

    public RxJButton(Action a) {
        super(a);
    }

    public RxJButton(String text, Icon icon) {
        super(text, icon);
    }

    public Map<String, Object> getData() {
        return data;
    }

    private void initData() {
        if (data == null)
            data = new HashMap<>();
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        initData();
        data.put(TEXT_PROPERTY, text);
    }
}
