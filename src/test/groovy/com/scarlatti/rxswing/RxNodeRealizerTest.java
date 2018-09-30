package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.RxNtvComponent;
import com.scarlatti.rxswing.inspect.RxNode;
import com.scarlatti.rxswing.inspect.RxNodeRealizer;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static com.scarlatti.rxswing.inspect.RxNodeRealizer.formatId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 9/8/2018
 */
public class RxNodeRealizerTest {

    public static class Div extends RxNtvComponent {
        @Override
        public Class<? extends Component> getNtvType() {
            return JPanel.class;
        }

        @Override
        public Component construct(RxNode rxNode) {
            return null;
        }
    }

    public static class Button extends RxNtvComponent {
        @Override
        public Class<? extends Component> getNtvType() {
            return JButton.class;
        }

        @Override
        public Component construct(RxNode rxNode) {
            return null;
        }
    }

    public static class MyComp1 extends RxComponent {
        @Override
        public RxNode render() {
            return Rx.node(Button.class);
        }
    }

    public static class MyComp2 extends RxComponent {
        @Override
        public RxNode render() {
            return Rx.node(Div.class)
                .child(Rx.node(MyComp1.class));
        }
    }

    @Test
    public void testRxNodeCanBeRealized() {
        RxNode node = Rx.node(Div.class)
            .child(Rx.node(MyComp1.class))
            .child(Rx.node(MyComp1.class));

        // this would usually be set by the lifecycle setState process...
        // from the component instance id.
        node.setId("rootNode");

        RxComponentStore componentStore = new RxComponentStore();
        RxNodeRealizer realizer = new RxNodeRealizer(node, componentStore);
        RxNode realizedNode = realizer.realize();

        String comp1_0 = formatId("rootNode", MyComp1.class, 0);
        String comp1_1 = formatId("rootNode", MyComp1.class, 1);

        String button1 = formatId(comp1_0, Button.class, 0);
        String button2 = formatId(comp1_1, Button.class, 0);

        RxNode expectedNode = Rx.node(Div.class).id("rootNode")
            .child(Rx.node(MyComp1.class).id(comp1_0)
                .child(Rx.node(Button.class).id(button1)))
            .child(Rx.node(MyComp1.class).id(comp1_1)
                .child(Rx.node(Button.class).id(button2)));

        assertEquals(expectedNode, realizedNode);
        assertEquals(5, componentStore.size());
        assertTrue(componentStore.get(comp1_0).getType() == MyComp1.class);
        assertTrue(componentStore.get("rootNode").getType() == Div.class);
    }

    @Test
    public void testRxNodeWithNestedUsrCompsCanBeRealized() {
        RxNode node = Rx.node(Div.class).id("rootNode")
            .child(Rx.node(Button.class))
            .child(Rx.node(MyComp2.class));

        RxComponentStore rxComponentStore = new RxComponentStore();
        RxNode realizedNode = new RxNodeRealizer(node, rxComponentStore).realize();

        String comp2_0 = formatId("rootNode", MyComp2.class, 0);
        String button0 = formatId("rootNode", Button.class, 0);
        String div1 = formatId(comp2_0, Div.class, 0);
        String comp1_0 = formatId(div1, MyComp1.class, 0);
        String button1 = formatId(comp1_0, Button.class, 0);

        RxNode expectedNode = Rx.node(Div.class).id("rootNode")
            .child(
                Rx.node(Button.class).id(button0)
            )
            .child(
                Rx.node(MyComp2.class).id(comp2_0)
                    .child(
                        Rx.node(Div.class).id(div1)
                            .child(Rx.node(MyComp1.class).id(comp1_0)
                                .child(Rx.node(Button.class).id(button1)
                                )
                            )
                    )
            );

        assertEquals(expectedNode, realizedNode);
        assertEquals(6, rxComponentStore.size());

        RxNode realizedNode2 = new RxNodeRealizer(node, rxComponentStore).realize();
        assertEquals(expectedNode, realizedNode2);
        assertEquals(6, rxComponentStore.size());
    }
}
