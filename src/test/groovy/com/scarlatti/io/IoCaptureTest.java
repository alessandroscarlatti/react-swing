package com.scarlatti.io;

import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Wednesday, 4/4/2018
 */
public class IoCaptureTest {

    @Test
    public void canCaptureIo() throws Exception {
        new Thread(this::captureThread).start();

        Thread.sleep(2000);

        System.out.println("hello");
        System.out.println("what");
        System.out.printf("%s%n", "asdf");



        Thread.sleep(10000);
    }

    private void captureThread() {
        try {
            InputStream stream = captureStdOut();
            Scanner scanner = new Scanner(stream);
            String str = scanner.nextLine();
            while (str != null) {
                JOptionPane.showMessageDialog(null, str);
                str = scanner.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private InputStream captureStdOut() throws Exception {
        PipedOutputStream pipeOut = new PipedOutputStream();
        System.setOut(new PrintStream(pipeOut));
        return new PipedInputStream(pipeOut);
    }
}
