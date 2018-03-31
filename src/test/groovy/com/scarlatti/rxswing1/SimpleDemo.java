package com.scarlatti.rxswing1;

import org.junit.Test;

import javax.swing.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Saturday, 2/24/2018
 */
public class SimpleDemo {

    @Test
    public void runApp() {
        new Animal();

        System.out.println("now new dog:");
        new Dog();
    }

    @Test
    public void showJPanel() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new JPanel();
            jPanel.add(new JButton("Hello"));
            return jPanel;
        });
    }

    @Test
    public void showRxJPanel() {
        TestUtils.displayJPanel(() -> {
            JPanel jPanel = new RxJPanel();
            jPanel.add(new RxJButton("Hello"));
            return jPanel;
        });
    }

    public static class Animal {
        public Animal() {
            System.out.println("animal");
        }
    }

    public static class Dog extends Animal {
        public Dog() {
            System.out.println("dog");
        }
    }
}
