package com.scarlatti.rxswing.component;

import com.scarlatti.rxswing.ComponentStore;
import com.scarlatti.rxswing.inspect.RxNode;
import com.scarlatti.rxswing.inspect.RxNodeRealizer;

import java.util.List;

/**
 * ______    __                         __           ____             __     __  __  _
 * ___/ _ | / /__ ___ ___ ___ ____  ___/ /______    / __/______ _____/ /__ _/ /_/ /_(_)
 * __/ __ |/ / -_|_-<(_-</ _ `/ _ \/ _  / __/ _ \  _\ \/ __/ _ `/ __/ / _ `/ __/ __/ /
 * /_/ |_/_/\__/___/___/\_,_/_//_/\_,_/_/  \___/ /___/\__/\_,_/_/ /_/\_,_/\__/\__/_/
 * Tuesday, 9/4/2018
 */
public class RxComponent {

    private Object props;
    private ComponentLifecycleManager lifecycleManager = new ComponentLifecycleManager();

    public Class<? extends RxComponent> getType() {
        return this.getClass();
    }

    public void setProps(Object props) {
        this.props = props;
    }

    public RxNode render() {
        return null;
    }

    public ComponentLifecycleManager getLifecycleManager() {
        return lifecycleManager;
    }

    public class ComponentLifecycleManager {

        private String id;
        private ComponentStore mtdCompStre;
        private RxComponent component = RxComponent.this;

        public void addToStore(ComponentStore store, String id) {
            mtdCompStre = store;
            this.id = id;
            mtdCompStre.put(id, component);
        }

        // do this when it's time to render the component instance
        // does this mean it's a recursive render?  Yes.
        // the "children" case was where the node is just data that
        // might never be used.
        // here we are returning the single rendered child of the
        // usr component. That means we might reach back to the node realizer.
        public RxNode performRender(Object nextProps, List<RxNode> children) {

            // need to pass props
            // for right now, no lifecycle checks.  Just pass in the props.
            component.setProps(nextProps);
            RxNode node = component.render();

            RxNodeRealizer realizer = new RxNodeRealizer(node, mtdCompStre);
            return realizer.realizeNode();
        }
    }
}
