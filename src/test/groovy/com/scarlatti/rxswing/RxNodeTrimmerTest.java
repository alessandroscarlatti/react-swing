package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.RxNtvComponent;
import com.scarlatti.rxswing.inspect.RxNode;
import com.scarlatti.rxswing.inspect.RxNodeTrimmer;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 9/5/2018
 */
public class RxNodeTrimmerTest {
    private static class RxJPanel extends RxComponent implements RxNtvComponent {

        @Override
        public Class<? extends Component> getNtvType() {
            return JPanel.class;
        }
    }

    private static class RxJButton extends RxComponent implements RxNtvComponent {

        @Override
        public Class<? extends Component> getNtvType() {
            return JButton.class;
        }
    }

    private static class MyComp1 extends RxComponent {
    }

    @Test
    public void trimTree() {
        RxNode original = new RxNode(RxJPanel.class)
            .child(new RxNode(MyComp1.class)
                .child(new RxNode(RxJButton.class)
                )
            );

        RxNode expected = new RxNode(RxJPanel.class)
            .child(new RxNode(RxJButton.class));

        List<RxNode> trimmed = new RxNodeTrimmer(original).trim();

        assertEquals(Collections.singletonList(expected), trimmed);
    }

    @Test
    public void trimTreeWithUsrOuterComponent() {
        RxNode original =
            new RxNode(MyComp1.class)
                .child(
                    new RxNode(RxJPanel.class)
                        .child(new RxNode(MyComp1.class)
                            .child(new RxNode(RxJButton.class)
                            )
                        )
                )
                .child(
                    new RxNode(RxJPanel.class)
                        .child(new RxNode(MyComp1.class)
                            .child(new RxNode(RxJButton.class)
                            )
                        )
                );

        List<RxNode> expected = Arrays.asList(
            new RxNode(RxJPanel.class)
                .child(new RxNode(RxJButton.class)),
            new RxNode(RxJPanel.class)
                .child(new RxNode(RxJButton.class))
        );

        List<RxNode> trimmed = new RxNodeTrimmer(original).trim();

        assertEquals(expected, trimmed);
    }
}
