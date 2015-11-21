package com.obsbs.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CheckboxField extends FXMLContent<HBox> {
    @FXML
    private CheckBox checkBox;

    @FXML
    private Label description;

    public CheckboxField() {
        super("checkbox-field.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setSelected(boolean selected) {
        checkBox.setSelected(selected);
    }

    public boolean isSelected() {
        return checkBox.isSelected();
    }

    public void setDescription(String description) {
        if (description != null) {
            this.description.setText(description);
        }
    }
}
