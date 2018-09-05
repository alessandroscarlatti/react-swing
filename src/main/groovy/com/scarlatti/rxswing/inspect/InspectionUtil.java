package com.scarlatti.rxswing.inspect;

import com.scarlatti.rxswing.component.NtvBoundComponent;
import com.scarlatti.rxswing.component.ntv.RxNtvComponent;

import java.awt.*;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public class InspectionUtil {
    private static InspectionUtil ourInstance = new InspectionUtil();

    public static InspectionUtil getInstance() {
        return ourInstance;
    }

    private InspectionUtil() {
    }

    // this may not be necessary...render will have already created the tree...
    public RxNode getInspectionTree(NtvBoundComponent component) {
        RxNode root;
        if (component instanceof RxNtvComponent)
            root = new RxNode();
        else
            throw new IllegalStateException("Unrecognized component " + component);

        root.setType(component.getType());
        root.setId(component.getNtvRndId());
        root.setProps(((RxNtvComponent) component).getData());

        if (component instanceof Container) {
            for (NtvBoundComponent child : component.getChildren()) {
                RxNode childInspected = getInspectionTree(child);
                root.getChildren().add(childInspected);
            }
        }
        return root;
    }
}
