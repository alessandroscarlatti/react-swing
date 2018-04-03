package com.scarlatti.rxswing3.util;

import com.scarlatti.rxswing3.component.ReactComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SimpleButton extends ReactComponent<Object, String> {

    public SimpleButton(String key) {
        super(key);
        state = "<|>";
    }

    @Override
    public Object render() {
        JButton jButton = new JButton(state);
        setupListeners(jButton);

        return jButton;
    }

    private void setupListeners(JButton jButton) {
        jButton.addActionListener(this::click);
    }

    private void click(ActionEvent e) {
        setState(state + "<|>");
    }
}