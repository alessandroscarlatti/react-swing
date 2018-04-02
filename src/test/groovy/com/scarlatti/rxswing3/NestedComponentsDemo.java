package com.scarlatti.rxswing3;

import com.scarlatti.rxswing1.TestUtils;
import com.scarlatti.rxswing3.component.ReactComponent;
import org.junit.Test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
    public void dynamicChildDemo() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new JPanel();

            // now render an RxSwing component
            // this would simulate the root render statement

            ReactSwing.render(jPanel, new DynamicPanel("panel1"));

            return jPanel;
        });
    }

    public static class SimpleButton extends ReactComponent<Object, String> {

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

    public static class DynamicPanel extends ReactComponent<Void, Integer> {

        public DynamicPanel(String key) {
            super(key);
            state = 34;
        }

        @Override
        public Object render() {
            JPanel jPanel = new JPanel();
            jPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
            jPanel.setBackground(new Color(state, 34, 34));
            jPanel.add(new SimpleButton("button1"));
            jPanel.add(new SimpleButton("button2"));

            setupListeners(jPanel);

            return jPanel;
        }

        private void setupListeners(JPanel jPanel) {
            jPanel.addMouseListener(clickListener(DynamicPanel.this::click));
        }

        private void click() {
            setState(state + 10);
        }
    }

    static MouseListener clickListener(Runnable runnable) {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                runnable.run();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        };
    }
}
