package com.scarlatti.rxswing3;

import com.scarlatti.rxswing3.render.ReactContext;
import com.scarlatti.rxswing3.render.RootReactContext;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Friday, 3/23/2018
 * <p>
 * This is the base class that the user will call.
 * It is not intended to be instantiated.
 */
public final class ReactSwing {

    private static Map<String, ReactContext> contexts;

    /**
     * React class should not be instantiated.
     *
     * @throws UnsupportedOperationException on invocation
     */
    private ReactSwing() {
        throw new UnsupportedOperationException("React class should not be instantiated");
    }

    public static void render(Container swingParent, Component reactComponent) {
        if (contexts == null) contexts = new HashMap<>();

        // get the id of the caller from the stack
        StackTraceElement frame = getRenderCallerInfo();

        // example: "com.somebody.SomeApp:main:SomeApp.java:48"
        String id = frame.getClassName() + ":" + frame.getMethodName() + ":" + frame.getFileName() + ":" + frame.getLineNumber();

        ReactContext context = contexts.get(id);
        if (context == null) {
            context = new RootReactContext(id, swingParent);
            contexts.put(id, context);
        }

        // now we have added the context to the list if it does not already exist.
        // Time to use it!
        context.render(reactComponent);
    }

    private static StackTraceElement getRenderCallerInfo() {
        StackTraceElement[] frames = Thread.currentThread().getStackTrace();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].getClassName().equals(ReactSwing.class.getName()) &&
                frames[i].getMethodName().equals("render")) {
                return frames[i + 1];
            }
        }

        throw new IllegalStateException("React.render() not called in stacktrace.");
    }
}
