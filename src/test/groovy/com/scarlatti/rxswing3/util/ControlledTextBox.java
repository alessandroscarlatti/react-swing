package com.scarlatti.rxswing3.util;

import com.scarlatti.rxswing3.component.ReactComponent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.function.Consumer;

public class ControlledTextBox extends ReactComponent<String, String> {

    public ControlledTextBox(String key, String props) {
        super(key, props);
        state = props;
    }

    @Override
    public Object render() {
        JTextField jTextField = new JTextField(state);
        jTextField.setPreferredSize(new Dimension(100, 15));
        setupListeners(jTextField);

        return jTextField;
    }

    @Override
    public void componentWillReceiveProps(String props) {
        this.props = props;
    }

    private void setupListeners(JTextField jTextField) {
        jTextField.getDocument().addDocumentListener(new OnTextChanged(jTextField, this::onTextChanged));
    }

    private void onTextChanged(String text) {
        setState(text + "s");
    }

    private static class OnTextChanged implements DocumentListener {
        private Consumer<String> consumer;
        private JTextField jTextField;

        public OnTextChanged(JTextField jTextField, Consumer<String> consumer) {
            this.jTextField = jTextField;
            this.consumer = consumer;
        }

        private void onChange() {
            consumer.accept(jTextField.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            onChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            onChange();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            onChange();
        }
    }
}