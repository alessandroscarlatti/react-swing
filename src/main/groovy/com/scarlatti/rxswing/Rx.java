package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.RxComponent;
import com.scarlatti.rxswing.component.usr.RxUsrComponent;
import com.scarlatti.rxswing.inspect.RxNode;

import java.util.function.Consumer;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 9/2/2018
 */
public class Rx {

    public static RxNode node(Class<? extends RxComponent> clazz) {
        RxNode rxNode = new RxNode();
        rxNode.setType(clazz);
        return rxNode;
    }

    public static RxNode node(Class<? extends RxComponent> clazz, Object props) {
        RxNode rxNode = new RxNode();
        rxNode.setType(clazz);
        return rxNode;
    }

    public static RxNode node(Class<? extends RxComponent> clazz, Consumer<RxNode> consumer) {
        RxNode node = node(clazz);
        consumer.accept(node);
        return node;
    }

    // we can use a lambda overload to allow the user to supply a value if props are required.
    public static RxSwCompDef createElement(Class<? extends RxUsrComponent> clazz) {
        return new RxSwCompDef(clazz, null);
    }

    // we can add "children" later.
    public static RxSwCompDef createElement(Class<? extends RxUsrComponent> clazz, Object props) {
        return new RxSwCompDef(clazz, props);
    }

    public static class RxSwCompDef {
        private Class<? extends RxUsrComponent> clazz;
        private Object props;

        public RxSwCompDef() {
        }

        public RxSwCompDef(Class<? extends RxUsrComponent> clazz, Object props) {
            this.clazz = clazz;
            this.props = props;
        }

        public Class<? extends RxUsrComponent> getClazz() {
            return clazz;
        }

        public void setClazz(Class<? extends RxUsrComponent> clazz) {
            this.clazz = clazz;
        }

        public Object getProps() {
            return props;
        }

        public void setProps(Object props) {
            this.props = props;
        }
    }
}
