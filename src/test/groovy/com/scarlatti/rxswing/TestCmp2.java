package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.usr.MyCoolComponent;
import com.scarlatti.rxswing.component.ntv.RxJLabel;

import javax.swing.*;
import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class TestCmp2 {
    private JPanel panel;
    private JButton upButton;
    private JButton downButton;

    public TestCmp2() {
        MyCoolComponent coolComponent1 = new MyCoolComponent();
        RxJLabel label1 = coolComponent1.render();
        panel.add(label1);
        RdrMger.getInstance().mountComponent(coolComponent1.getNtvRndId(), label1);

        MyCoolComponent coolComponent2 = new MyCoolComponent();
        RxJLabel label2 = coolComponent2.render();
        RdrMger.getInstance().mountComponent(coolComponent2.getNtvRndId(), label2);
        panel.add(label2);

        // go in opposite directions!!!
        upButton.addActionListener(e -> {
            coolComponent1.setState(coolComponent1.getState() + 1);
            coolComponent2.setState(coolComponent2.getState() - 1);
        });
        downButton.addActionListener(e -> {
            coolComponent1.setState(coolComponent1.getState() - 1);
            coolComponent2.setState(coolComponent2.getState() + 1);
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        upButton = new JButton();
        upButton.setText("Up");
        panel.add(upButton);
        downButton = new JButton();
        downButton.setText("Down");
        panel.add(downButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
