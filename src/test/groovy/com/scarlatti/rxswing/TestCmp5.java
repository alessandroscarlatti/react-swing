package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.ntv.RxJPanel;
import com.scarlatti.rxswing.component.usr.LottoComponent;
import com.scarlatti.rxswing.component.usr.LottoComponent2;

import javax.swing.*;
import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class TestCmp5 {
    private JPanel panel;
    private JButton upButton;
    private JButton downButton;

    public TestCmp5() {
        LottoComponent2 lottoComponent2 = new LottoComponent2();
        RxJPanel jp = lottoComponent2.render();
        panel.add(jp);
        RdrMger.getInstance().putNtvComp(lottoComponent2.getNtvRndId(), jp);

        // go in opposite directions!!!
        upButton.addActionListener(e -> {
            lottoComponent2.setState(lottoComponent2.getState() + 1);
        });

        downButton.addActionListener(e -> {
            lottoComponent2.setState(lottoComponent2.getState() - 1);
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