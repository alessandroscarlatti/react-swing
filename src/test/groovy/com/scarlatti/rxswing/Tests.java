package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.usr.ComponentWNestedComponent;
import com.scarlatti.rxswing.component.usr.MyCoolComponentWButtons;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 8/25/2018
 */
public class Tests {

    @Test
    public void runTestForm() {
        TestUtils.displayJPanel(() -> {
            return new TestCmp().getPanel();
        });
    }

    // this will not work because we are only allowing a single root component
    @Test
    @Ignore
    public void runTestFormWithTwoComponents() {
        TestUtils.displayJPanel(() -> {
            return new TestCmp2().getPanel();
        });
    }

    // this will also not work yet because we are only allowing a single root component
    @Test
    @Ignore
    public void runTestFormWithTwoTypesOfComponents() {
        TestUtils.displayJPanel(() -> {
            return new TestCmp3().getPanel();
        });
    }

    @Test
    public void runTestFormWithWrappedComponent() {
        TestUtils.displayJPanel(() -> {
            return new TestCmp4().getjPanel();
        });
    }

    @Test
    public void runTestFormUsingRxCreateElement() {
        TestUtils.displayJPanel(() -> {
            return new TestCmp5().getPanel();
        });
    }

    // this is the most advanced test right now...
    // the buttons are included in the component!
    @Test
    public void runTestFormWActiveNestedButtons() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new EmptyDialog().getPanel();
            Rx.render(Rx.node(MyCoolComponentWButtons.class), jPanel);
            return jPanel;
        });
    }

    // this test should break right now, because we don't
    // fully support nested user components
    @Test
    public void runTestFormWNestedUsrComponent() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new EmptyDialog().getPanel();
            Rx.render(Rx.node(ComponentWNestedComponent.class), jPanel);
            return jPanel;
        });
    }
}
