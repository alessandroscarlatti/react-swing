package com.scarlatti.rxswing.inspect;

import com.scarlatti.rxswing.component.RxComponent;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 8/28/2018
 */
public class RxNode {
    private String id;
    private Class<? extends RxComponent> type;
    private Map<String, Object> props = new HashMap<>();
    private List<RxNode> children = new ArrayList<>();

    public RxNode() {
    }

    public RxNode(Class<? extends RxComponent> clazz) {
        type = clazz;
    }

    public RxNode(RxNode other) {
        this.id = other.id;
        this.type = other.type;
        this.props = other.props;
        this.children = other.children;
    }

    public RxNode props(String key, Object value) {
        props.put(key, value);
        return this;
    }

    public RxNode child(RxNode child) {
        children.add(child);
        return this;
    }

    public RxNode children(RxNode... children) {
        for (RxNode child : children) {
            this.child(child);
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RxNode rxNode = (RxNode) o;
        return Objects.equals(id, rxNode.id) &&
            Objects.equals(type, rxNode.type) &&
            Objects.equals(props, rxNode.props) &&
            Objects.equals(children, rxNode.children);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, props, children);
    }

    @Override
    public String toString() {
        return "RxNode{" +
            "id='" + id + '\'' +
            ", type=" + type +
            ", props=" + props +
            ", children=" + children +
            '}';
    }

    public void walkTree(Consumer<RxNode> consumer) {
        walkTree(new Visitor() {
            @Override
            public void accept(RxNode rxNode) {
                consumer.accept(rxNode);
            }
        });
    }

    public void walkTree(Visitor visitor) {
        visitor.accept(this);
        if (visitor.keepWalking) {
            for (RxNode child : children) {
                child.walkTree(visitor);
            }
        }
    }

    public RxNode searchTree(Predicate<RxNode> predicate) {
        SearchingVisitor visitor = new SearchingVisitor(predicate);
        walkTree(visitor);

        return visitor.getResult();
    }

    public static abstract class Visitor implements Consumer<RxNode> {
        private boolean keepWalking = true;

        protected void stopWalking() {
            keepWalking = false;
        }
    }

    private class SearchingVisitor extends Visitor {

        private Predicate<RxNode> predicate;
        private RxNode result;

        public SearchingVisitor(Predicate<RxNode> predicate) {
            this.predicate = predicate;
        }

        @Override
        public void accept(RxNode rxNode) {
            if (predicate.test(rxNode)) {
                result = rxNode;
                stopWalking();
            }
        }

        protected RxNode getResult() {
            return result;
        }
    }

    public Class<? extends RxComponent> getType() {
        return type;
    }

    public void setType(Class<? extends RxComponent> type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<RxNode> getChildren() {
        return children;
    }

    public void setChildren(List<RxNode> children) {
        this.children = children;
    }

    public Map<String, Object> getProps() {
        return props;
    }

    public void setProps(Map<String, Object> props) {
        this.props = props;
    }
}
