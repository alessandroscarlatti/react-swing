package com.scarlatti.rxswing.component.ntv;

import com.scarlatti.rxswing.component.NtvBoundComponent;
import com.scarlatti.rxswing.component.usr.RxUsrComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public class RxJPanel extends JPanel implements RxNtvComponent {

    private String ntvRndId;
    private ChildIdIncrementer idIncrementer = new ChildIdIncrementer();

    private List<NtvBoundComponent> children = new ArrayList<>();

    public RxJPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public RxJPanel(LayoutManager layout) {
        super(layout);
    }

    public RxJPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public RxJPanel() {
    }

    @Override
    public String getNextChildNtvRndId() {
        return idIncrementer.getNextId();
    }

    @Override
    public String getNtvRndId() {
        return ntvRndId;
    }

    @Override
    public void setNtvRndId(String id) {
        ntvRndId = id;
    }

    @Override
    public Component add(Component component) {
        if (component instanceof NtvBoundComponent) {
            markComponentAsNextChild((NtvBoundComponent) component);
            children.add((NtvBoundComponent) component);
        }

        return super.add(component);
    }

    public Component add(RxUsrComponent comp) {
        NtvBoundComponent renderedComponent = (NtvBoundComponent) comp.render();
        markComponentAsNextChild(renderedComponent);
        children.add(renderedComponent);
        return super.add((Component) renderedComponent);
    }

    public Component add(Supplier<NtvBoundComponent> supplier) {
        NtvBoundComponent renderedComponent = supplier.get();
        markComponentAsNextChild(renderedComponent);
        children.add(renderedComponent);
        return super.add((Component) renderedComponent);
    }

    private Map<String, Object> data = new HashMap<>();

    @Override
    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public List<NtvBoundComponent> getChildren() {
        return children;
    }
}
