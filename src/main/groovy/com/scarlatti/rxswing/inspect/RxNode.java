package com.scarlatti.rxswing.inspect;

import com.scarlatti.rxswing.component.RxComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private Map<String, Object> props;
    private List<RxNode> children = new ArrayList<>();

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
