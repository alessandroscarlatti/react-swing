package com.scarlatti.rxswing3;

import com.scarlatti.rxswing1.TestUtils;
import com.scarlatti.rxswing3.component.ReactComponent;
import com.scarlatti.rxswing3.util.*;
import org.junit.Test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 */
public class ControlledTextBoxDemo {

    @Test
    public void controlledTextBoxDemo() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new JPanel();

            // now render an RxSwing component
            // this would simulate the root render statement

            ReactSwing.render(jPanel, new DynamicPanel("panel1"));

            return jPanel;
        });
    }

    public static class DynamicPanel extends ReactComponent<Void, DynamicPanel.State> {

        public DynamicPanel(String key) {
            super(key);
            state = new State();
        }

        @Override
        public Object render() {
            JPanel jPanel = new JPanel();
            jPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
            jPanel.setBackground(new Color(state.red, 34, 34));

            jPanel.add(new ControlledTextBox("textBox", "what"));

            setupListeners(jPanel);

            return jPanel;
        }

        private void setupListeners(JPanel jPanel) {
            jPanel.addMouseListener(new ClickListener(DynamicPanel.this::click));
        }

        private void click() {
            setState(new State(state.red + 10, !state.showPeakedText));
        }

        public static class State {
            int red;
            boolean showPeakedText;

            public State() {
            }

            public State(int red, boolean showPeakedText) {
                this.red = red;
                this.showPeakedText = showPeakedText;
            }
        }
    }
}
