package com.scarlatti.rxswing;

import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 */
public class BasicDemo {
    @Test
    public void showRxJPanel() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new JPanel();

            // now render an RxSwing component
            // this would simulate the root render statement

            React.render(jPanel, new SimpleButton());

            return jPanel;
        });
    }

    public static class SimpleButton extends ReactComponent {

        public SimpleButton() {
            state = "<|>";
        }

        @Override
        public RxComponent render() {
            RxJButton jButton = new RxJButton((String)state);
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
}
