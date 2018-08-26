package com.scarlatti.rxswing;

import org.junit.Test;

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

    @Test
    public void runTestFormWithTwoComponents() {
        TestUtils.displayJPanel(() -> {
            return new TestCmp2().getPanel();
        });
    }
}
