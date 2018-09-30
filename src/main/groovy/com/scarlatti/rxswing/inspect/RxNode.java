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
    private List<RxNode> children = new RxNodeChildContainer();
    private RxNode parent;

    public RxNode() {
    }

    public RxNode(Class<? extends RxComponent> clazz) {
        type = clazz;
    }

    public RxNode(RxNode other) {
        this.id = other.id;
        this.type = other.type;
        this.props = other.props;
        this.children = new ArrayList<>();
        for (RxNode node : other.getChildren()) {
            this.children(new RxNode(node));
        }
    }

    public RxNode props(String key, Object value) {
        props.put(key, value);
        return this;
    }

    public RxNode set(String key, Object value) {
        props.put(key, value);
        return this;
    }

    public Object get(String key) {
        return props.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T) props.get(key);
    }

    public RxNode props(Map<String, Object> props) {
        this.props = props;
        return this;
    }

    public RxNode id(String id) {
        setId(id);
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
            protected void visitNode(RxNode node) {
                consumer.accept(node);
            }
        });
    }

    public void walkTree(Visitor visitor) {
        visitor.visitNode(this);
    }

    public RxNode searchTree(Predicate<RxNode> predicate) {
        SearchingVisitor visitor = new SearchingVisitor(predicate);
        walkTree(visitor);

        return visitor.getResult();
    }

    public void replace(String id, RxNode replacement) {
        replaceRecursive(id, replacement);
    }

    private void replaceRecursive(String id, RxNode replacement) {
        walkTree(new RxNode.Visitor() {

            @Override
            protected void visitNode(RxNode node) {
                for (int i = 0; i < node.getChildren().size(); i++) {
                    if (Objects.equals(node.getChildren().get(i).getId(), id)) {
                        node.getChildren().set(i, replacement);
                        return;
                    }

                    super.visitNode(node);
                }
            }

            @Override
            protected void visitChildNode(RxNode child, RxNode parent) {

            }
        });
    }

    public RxNode getParent() {
        return parent;
    }

    public static abstract class Visitor {
        protected void visitNode(RxNode node) {
            for (RxNode child : node.getChildren()) {
                visitChildNode(child, node);
            }
        }

        protected void visitChildNode(RxNode child, RxNode parent) {
            visitNode(child);
        }
    }

    private class SearchingVisitor extends Visitor {

        private Predicate<RxNode> predicate;
        private RxNode result;

        public SearchingVisitor(Predicate<RxNode> predicate) {
            this.predicate = predicate;
        }

        @Override
        public void visitNode(RxNode rxNode) {
            if (predicate.test(rxNode)) {
                result = rxNode;
                return;
            }

            super.visitNode(rxNode);
        }

        protected RxNode getResult() {
            return result;
        }
    }

    private class RxNodeChildContainer extends ArrayList<RxNode> {
        @Override
        public boolean add(RxNode node) {
            boolean result = super.add(node);
            if (result) {
                node.parent = RxNode.this;
            }
            return result;
        }

        @Override
        public boolean addAll(Collection<? extends RxNode> c) {
            boolean result = super.addAll(c);
            if (result) {
                for (RxNode node : c) {
                    node.parent = RxNode.this;
                }
            }
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
