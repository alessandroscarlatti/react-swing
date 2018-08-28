package com.scarlatti.rxswing.component.ntv;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 8/26/2018
 */
public class RxJLabel extends JLabel implements RxNtvComponent {

    public static final String TEXT_PROPERTY = "text";
    private Map<String, Object> data;

    public RxJLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    public RxJLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public RxJLabel(String text) {
        super(text);
    }

    public RxJLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public RxJLabel(Icon image) {
        super(image);
    }

    public RxJLabel() {
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
