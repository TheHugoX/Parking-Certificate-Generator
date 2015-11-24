package com.obsbs.ui.fields;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import com.obsbs.ui.content.Content;

import java.util.ArrayList;
import java.util.List;

public class EntryField implements Content<TextField> {
    private final TextField textField = new TextField();

    public EntryField(String text, String promtText) {
        if (text != null) {
            textField.setText(text);
        }

        if (promtText != null) {

            textField.setPromptText(promtText);
        }

        getNode().setOnKeyReleased(event -> {
            List<String> autoCompleteEntries = onAutoComplete(event.getText());
            // ToDo
        });
    }

    public String getValue() {
        return textField.getText();
    }

    protected List<String> onAutoComplete(String text) {
        return new ArrayList<String>();
    }

    public TextField getNode() {
        return textField;
    }
}
