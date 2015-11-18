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
    private HBox sectionContent;

    private Label sectionTitle;

    public EntrySection() {
        super("entry-section.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setSectionTitle(String sectionTitle) {
        if (this.sectionTitle == null) {
            this.sectionTitle = new Label();
            this.sectionTitle.setPadding(new Insets(0, 0, 10, 0));
            getNode().getChildren().add(0, this.sectionTitle);
        }
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
