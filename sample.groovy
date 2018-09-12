import com.scarlatti.rxswing.Rx
import com.scarlatti.rxswing.component.RxComponent
import com.scarlatti.rxswing.inspect.RxNode
import com.scarlatti.rxswing.inspect.RxNodeRealizer

import static Components.jButton
import static Components.jPanel

class RxJPanel extends RxComponent {
}

class RxJButton extends RxComponent {
}

class RxComponentProps {
    RxNode child(RxNode child) {
        return child
    }
}

class RxJPanelProps extends RxComponentProps {
    String backgroundColor
}

class RxJButtonProps extends RxComponentProps {
    String text
}

class RxNodeRenderDelegate {

    RxNode child(RxNode child) {
        return child
    }

    RxNode child(Class<? extends RxComponent> clazz) {
        return Rx.node(clazz)
    }

    RxNode child(Class<? extends RxComponent> clazz,
                 @DelegatesTo(value = RxNodeRenderDelegate, strategy = Closure.DELEGATE_FIRST) Closure config) {
        return Rx.node(clazz)
    }

    RxNode child(Map<String, Object> configurations,
                 @DelegatesTo(value = RxNodeRenderDelegate, strategy = Closure.DELEGATE_FIRST) Closure config) {
        return Rx.node((Class) configurations.type)
    }

    void setProps(Map<String, Object> props) {

    }
}

class RxRenderDelegate {
    RxNode render(RxNode child) {
        return child
    }

    RxNode render(Class<? extends RxComponent> clazz) {
        return Rx.node(clazz)
    }

    RxNode render(Class<? extends RxComponent> clazz,
                  @DelegatesTo(value = RxNodeRenderDelegate, strategy = Closure.DELEGATE_FIRST) Closure config) {
        return Rx.node(clazz)
    }

    RxNode render(Class<? extends RxComponent> clazz,
                  Map<String, Object> configurations,
                  @DelegatesTo(value = RxNodeRenderDelegate, strategy = Closure.DELEGATE_FIRST) Closure config) {
        return Rx.node((Class) configurations.type)
    }

    RxNode render(Map<String, Object> configurations,
                  @DelegatesTo(value = RxNodeRenderDelegate, strategy = Closure.DELEGATE_FIRST) Closure config) {
        return Rx.node((Class) configurations.type)
    }
}

void render(@DelegatesTo(value = RxRenderDelegate, strategy = Closure.DELEGATE_FIRST) Closure closure) {
    println "Asdf"
}

class Components {
    static RxNode jPanel(@DelegatesTo(value = RxJPanelProps, strategy = Closure.DELEGATE_FIRST) Closure config) {
        return Rx.node(RxJPanel.class)
    }

    static RxNode jButton(@DelegatesTo(value = RxJButtonProps, strategy = Closure.DELEGATE_FIRST) Closure config) {
        return Rx.node(RxJButton.class)
    }
}

render {
    render(RxJPanel) {
        props {
            backgroundColor = "what do you know"
        }
        child(RxJButton) {
            props = [
                    text: "hey"
            ]
        }
    }
}

render {
    // but how in the world would this jPanel static
    // method be able to know enough??
    // actually that should be fine, right?
    // it's just declarative...
    // ah...but what about children?
    // how do they wind up being assigned
    // to the parent as children if the method is static??

    // but to define new components...
    // we would have to define a new component class...
    // then define a new delegate...
    // however, the delegate would be the props...
    // and it would get the children functionality
    // built in...
    render jPanel {
        backgroundColor = "what do you know"
        background = "asdf"
        what = "asdf"
        anotherprop = "asdf"
        andanother = "asdf"
        yesthisisit = "asdf"

        // this would mean we would need to pass a dummy swing component
        // to this closure, which would capture any sophisticated properties.
        // or it might, in fact, be the case that the props are best created
        // by standard swing code.
        configure {

        }

        child jButton {
            text = "hey"
        }

        child jPanel {
            backgroundColor = "what do you know"

            // this would work...
            // we can use spaces, no parentheses
            // AND we can get the custom closure delegate.
            child jButton {
                text = "hey"
            }
        }
    }
}

render {
    render(type: RxJPanel, props: [:]) {
        props = [
                background : "asdf",
                what       : "asdf",
                anotherprop: "asdf",
                andanother : "asdf",
                yesthisisit: "asdf"
        ]
        child(RxJButton) {
            props = [
                    background : "asdf",
                    what       : "asdf",
                    anotherprop: "asdf",
                    andanother : "asdf",
                    yesthisisit: "asdf"
            ]
        }
    }

    render(
            type: RxJPanel,
            background: "asdf",
            what: "asdf",
            anotherprop: "asdf",
            andanother: "asdf",
            yesthisisit: "asdf"
    ) {
        child(RxJButton) {
            props = [
                    text: "hey"
            ]
        }
    }
}