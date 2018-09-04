package com.scarlatti.rxswing;

import com.scarlatti.rxswing.component.usr.RxUsrComponent;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Sunday, 9/2/2018
 */
public class Rx {

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
