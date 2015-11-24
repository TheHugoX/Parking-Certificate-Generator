package com.obsbs.ui.sections;

import com.obsbs.ui.content.Content;
import com.obsbs.ui.fields.CheckboxField;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.stream.Collectors;

public class EntrySelection implements Content<VBox> {
    private final VBox content = new VBox(10);

    private Map<String, CheckboxField> checkBoxes = new HashMap<>();

    public EntrySelection(List<String> entries, Collection<String> selectedEntries) {
        initCheckboxes(new ArrayList<>(entries), new HashSet<>(selectedEntries));
    }

    private void initCheckboxes(List<String> entries, Collection<String> selectedEntries) {
        entries.stream().filter(entry -> entry != null && !checkBoxes.containsKey(entry)).forEach(entry -> {
            CheckboxField checkboxField = new CheckboxField();
            checkboxField.setDescription(entry);
            checkboxField.setSelected(selectedEntries.contains(entry));
            checkBoxes.put(entry, checkboxField);
            content.getChildren().add(checkboxField.getNode());
        });
    }

    public Set<String> getSelectedEntries() {
        return checkBoxes.entrySet().stream().filter(entry -> entry.getValue().isSelected()).map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    @Override
    public VBox getNode() {
        return content;
    }
}
