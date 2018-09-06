package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.inspect.RxNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 9/5/2018
 */
public class RxNodeSyntaxTest {

    private static class MyComp1 extends RxComponent {
    }

    private static class RxJPanel extends RxComponent {
    }

    private static class Optional extends RxComponent {
    }

    @Test
    public void emptyComponent() {
        RxNode node = Rx.node(MyComp1.class);
        System.out.println(node);
    }

    @Test
    public void emptyComponentWithProps() {
        RxNode node = Rx.node(MyComp1.class)
            .props("key1", "value1");
        System.out.println(node);
    }

    @Test
    public void componentWithPropsAndSingleChild() {
        RxNode node = Rx.node(MyComp1.class)
            .props("key1", "value1")
            .child(Rx.node(RxJPanel.class));
        System.out.println(node);

        // another way
        RxNode node2 = Rx.node(MyComp1.class, n -> {
            n.child(Rx.node(RxJPanel.class));
        });

        // another way

        // another way
        RxNode node3 = Rx.node(MyComp1.class)
            .props("key1", "value1")
            .props("key1", "value1")
            .props("key1", "value1")
            .props("key1", "value1")
            .props("key1", "value1")
            .child(Rx.node(MyComp1.class)
                .props("key1", "value1")
                .props("key1", "value1")
                .props("key1", "value1")
            )
            .child(Rx.node(MyComp1.class)
                .props("key1", "value1")
                .props("key1", "value1")
                .props("key1", "value1")
            );

        System.out.println(node3);

        // another way
        // with logic...
        RxNode node4 = new RxNode(MyComp1.class)
            .props("key1", "value1".equals("sdf") ? "1" : "2")
            .props("key1", "value1")
            .props("key1", "value1")
            .props("key1", "value1")
            .props("key1", "value1")
            .child(new RxNode(Optional.class)
                .props("test", "value1".equals("sdf"))
                .child(new RxNode(RxJPanel.class)
                    .props("text", "hey1")
                )
            )
            .child(new RxNode(RxJPanel.class)
            .props("key1", "value1")
            .props("key1", "value1")
            .props("key1", "value1")
        );

        System.out.println(node4);
    }

    @Test
    public void testEquality() {
        RxNode node1 = Rx.node(MyComp1.class)
            .props("key1", "value1")
            .child(Rx.node(RxJPanel.class)
                .props("key1", "value1")
                .props("key2", "value2")
            );

        RxNode node2 = Rx.node(MyComp1.class)
            .props("key1", "value1")
            .child(Rx.node(RxJPanel.class)
                .props("key1", "value1")
                .props("key2", "value2")
            );

        RxNode node3 = Rx.node(MyComp1.class)
            .props("key1", "value1")
            .props("key2", "value3")
            .child(Rx.node(RxJPanel.class)
                .props("key1", "value1")
                .props("key2", "value2")
            );

        RxNode node4 = Rx.node(MyComp1.class)
            .props("key1", "value1")
            .props("key2", "value3")
            .child(Rx.node(RxJPanel.class)
                .props("key1", "value1")
                .props("key2", "value2")
            ).child(Rx.node(RxJPanel.class)
                .props("key1", "value1")
                .props("key2", "value2")
            );

        assertTrue(node1.equals(node2));
        assertFalse(node1.equals(node3));
        assertFalse(node3.equals(node4));
    }
}
