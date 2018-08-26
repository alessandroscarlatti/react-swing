package com.scarlatti.concurrencyApi;

import com.scarlatti.rxswing3.component.ReactComponent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 4/7/2018
 */
public class TaskList extends ReactComponent<TaskList.Props, Object> {

    public TaskList(String key, List<Task> tasks) {
        super(key);
        props = new Props(tasks);
    }

    @Override
    public Object render() {
        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();
        scrollPane.setViewportView(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#343434"));

        for (Task task : props.tasks) {
            JToggleButton btn = new JToggleButton(task.getName());
            panel.add(btn);
        }

        return scrollPane;
    }

    static class Props {
        List<Task> tasks;

        public Props(List<Task> tasks) {
            this.tasks = tasks;
        }
    }
}
