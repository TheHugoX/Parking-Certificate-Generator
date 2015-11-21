package com.obsbs.ui;

import com.obsbs.ui.content.Content;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;
import java.util.function.BooleanSupplier;

public class EntrySelection implements Content<VBox> {
    private final VBox content = new VBox(10);

    private Map<String, CheckboxField> checkBoxes = new HashMap<>();

    public EntrySelection(List<String> entries, Collection<String> selectedEntries) {
        initCheckboxes(new ArrayList<>(entries), new HashSet<>(selectedEntries));
    }

    private void initCheckboxes(List<String> entries, Collection<String> selectedEntries) {
        for (String entry : entries) {
            if (entry != null && !checkBoxes.containsKey(entry)) {
                CheckboxField checkboxField = new CheckboxField();
                checkboxField.setDescription(entry);
                checkboxField.setSelected(selectedEntries.contains(entry));
                checkBoxes.put(entry, checkboxField);
                content.getChildren().add(checkboxField.getNode());
            }
        }
    }

    public Set<String> getSelectedEntries() {
        Set<String> selectedEntries = new HashSet<>();
        for (Map.Entry<String, CheckboxField> entry : checkBoxes.entrySet()) {
            if (entry.getValue().isSelected()) {
                selectedEntries.add(entry.getKey());
            }
        }
        return selectedEntries;
    }

    @Override
    public VBox getNode() {
        return content;
    }
}
