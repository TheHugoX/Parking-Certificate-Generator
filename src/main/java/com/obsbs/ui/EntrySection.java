package com.obsbs.ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EntrySection extends FXMLContent<VBox> {
    @FXML
    private Label sectionTitle;

    @FXML
    private HBox sectionContent;

    public EntrySection() {
        super("entry-section.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        // getNode().setPadding(new Insets(5));
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle.setText(sectionTitle);
    }

    public EntryField addEntryField(String text, String promptText) {
        EntryField entryField = new EntryField(text, promptText);
        sectionContent.getChildren().add(entryField.getNode());
        return entryField;
    }

    public EntryField addEntryField(String promptText) {
        return addEntryField(null, promptText);
    }
}
