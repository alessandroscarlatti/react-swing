package com.scarlatti.rxswing3.util;

import com.scarlatti.rxswing3.component.ReactComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class PropsStaticButton extends ReactComponent<PropsStaticButton.Props, Integer> {

    public PropsStaticButton(String key, String text) {
        super(key);
        props = new Props(text);
        state = 0;
    }

    @Override
    public Object render() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < state; i++) {
            sb.append(props.text);
        }

        JButton jButton = new JButton(sb.toString());
        setupListeners(jButton);

        return jButton;
    }

    @Override
    public void componentWillReceiveProps(Props props) {
        this.props = props;
    }

    @Override
    public boolean componentShouldUpdate(Props newProps) {
        return false;
    }

    private void setupListeners(JButton jButton) {
        jButton.addActionListener(this::click);
    }

    private void click(ActionEvent e) {
        setState(state + 1);
    }

    static class Props {
        String text;

        public Props(String text) {
            this.text = text;
        }
    }
}