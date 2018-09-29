package com.scarlatti.rxswing.component;

import com.scarlatti.rxswing.inspect.RxNode;

/**
 * This is a weak decorator.
 * We would ultimately wind up wanting
 * to use this with RxNode being an interface.
 */
public class RxNodeWrapper {
    private RxNode rxNode;

    public RxNodeWrapper(RxNode rxNode) {
        this.rxNode = rxNode;
    }

    public RxNode set(String key, Object value) {
        return rxNode.set(key, value);
    }

    public Object get(String key) {
        return rxNode.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T) rxNode.getProps().get(key);
    }

    public RxNode child(RxNode child) {
        return rxNode.child(child);
    }

    public RxNode children(RxNode... children) {
        return rxNode.children(children);
    }
}
