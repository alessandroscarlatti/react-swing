package com.scarlatti.concurrencyApi;

import com.scarlatti.rxswing1.TestUtils;
import com.scarlatti.rxswing3.PropsComponentsDemo;
import com.scarlatti.rxswing3.ReactSwing;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 4/7/2018
 */
public class Demo {

    @Test
    public void taskListDemo() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new JPanel();

            // now render an RxSwing component
            // this would simulate the root render statement

            List<Task> tasks = Arrays.asList(
                new Task("task1", "Task 1"),
                new Task("task2", "Task 2")
            );

            ReactSwing.render(jPanel, new TaskList("taskList", tasks));

            return jPanel;
        });
    }
}
