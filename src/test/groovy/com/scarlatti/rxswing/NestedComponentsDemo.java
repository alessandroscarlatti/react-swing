package com.scarlatti.rxswing;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 */
public class NestedComponentsDemo {

    @Test
    public void panelDemo() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new JPanel();

            // now render an RxSwing component
            // this would simulate the root render statement

            React.render(jPanel, new SimplePanel());

            return jPanel;
        });
    }

    public static class SimpleButton extends PureComponent<Object, String> {

        public SimpleButton() {
            state = "<|>";
        }

        @Override
        public RxElement render() {
            RxJButton jButton = new RxJButton(state);
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

    public static class SimplePanel extends ReactComponent<Void, Integer> {

        public SimplePanel() {
            state = 34;
        }

        @Override
        public RxElement render() {
            RxJPanel jPanel = new RxJPanel();
            jPanel.setBackground(new Color(state, 34, 34));

            // How do I nest a ReactComponent?
            jPanel.add(new SimpleButton());

            setupListeners(jPanel);

            return jPanel;
        }

        private void setupListeners(JPanel jPanel) {
            jPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SimplePanel.this.click();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });

        }

        private void click() {
            setState(state + 10);
        }
    }
}
