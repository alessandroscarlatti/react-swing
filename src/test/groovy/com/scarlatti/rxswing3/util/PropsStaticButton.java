package com.scarlatti.rxswing3.util;

import com.scarlatti.rxswing3.component.ReactComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PropsStaticButton extends ReactComponent<String, Integer> {

    public PropsStaticButton(String key, String props) {
        super(key, props);
        state = 0;
    }

    @Override
    public Object render() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < state; i++) {
            sb.append(props);
        }

        JButton jButton = new JButton(sb.toString());
        setupListeners(jButton);

        return jButton;
    }

    @Override
    public void componentWillReceiveProps(String props) {
        this.props = props;
    }

    @Override
    public boolean componentShouldUpdate(String newProps) {
        return false;
    }

    private void setupListeners(JButton jButton) {
        jButton.addActionListener(this::click);
    }

    private void click(ActionEvent e) {
        setState(state + 1);
    }
}