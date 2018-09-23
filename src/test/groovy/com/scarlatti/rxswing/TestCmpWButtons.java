package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.usr.MyCoolComponent;
import com.scarlatti.rxswing.component.usr.MyCoolComponentWButtons;
import com.scarlatti.rxswing.inspect.RxNode;

import javax.swing.*;
import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class TestCmpWButtons {
    private JPanel panel;

    public TestCmpWButtons() {
//        coolComponent = new MyCoolComponent();
//        JLabel label1 = new JLabel("what");
//        panel.add(label1);

        Rx.render(() -> Rx.node(MyCoolComponentWButtons.class), panel);

        // this is just an example of what the java syntax might look like.
        // for generic node creation, not Rx.render per se.
//        RxJPanel.jPanel(jPanel -> {
//            jPanel.child(RxJLabel.jLabel(jLabel -> {
//                jLabel.setText("what");
//            }));
//        });


        // this will wind up happening first...
        // there should be a slightly separate method for this initiating process.
        // that's for the same reasons as using a performAction and performActionRecursive method...I think...
        // preload into the component store
//        coolComponent.getLifecycleManager().addToStore(RdrMger.getInstance().getRxComponentStore(), "coolComponent");

        // preload into the native component store...will need the right key...
//        RdrMger.getInstance().getSwComponentStore().putIfAbsent("coolComponent/com.scarlatti.rxswing.component.ntv.RxJLabel[0]", label1);

//        RdrMger.getInstance().getCurrentDom().setRoot(
//            Rx.node(RxJLabel.class)
//                .id("coolComponent/com.scarlatti.rxswing.component.ntv.RxJLabel[0]")
//                .props("text", "0")
//        );

        RxNode domRoot = RdrMger.getInstance().getCurrentDom().getRoot();
        MyCoolComponentWButtons coolComponent = (MyCoolComponentWButtons) RdrMger.getInstance().getRxComponentStore().get(domRoot.getId());

        // OK... now to get this stuff to be inside the component...
        // we will have to start thinking of it as props of each button component.
        // how do we determine the "value" of a lambda while comparing props during the change management process?
        // But how are those methods created anyway?  The methods themselves here, and even in my own ReactJS examples
        // are always created first and share lifecycle with the component in which they live.  The INSTANCE of the
        // function is passed to a child component as a prop.
        // That means we are just comparing identity.
        // FIRST we will need to add these buttons into the component...
        // Probably should copy this example component so that we can always have this super simple example.
        // At least until controlling the component from the outside is a thing of the past.
        // THEN see about making them do something.
//        upButton.addActionListener(e -> coolComponent.setState(coolComponent.getState() + 1));
//        downButton.addActionListener(e -> coolComponent.setState(coolComponent.getState() - 1));
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
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
