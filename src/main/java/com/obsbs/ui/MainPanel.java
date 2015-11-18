package com.obsbs.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPanel extends FXMLContent<BorderPane> {
    @FXML
    private TextField filterField;

    @FXML
    private Button addEntryButton;

    @FXML
    private Button deleteEntryButton;

    @FXML
    private ListView entries;

    @FXML
    private VBox content;

    public MainPanel() {
        super("main-panel.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        initEntrySections();
    }

    private void initEntrySections() {
        EntrySection staffSection = addEntrySection("Klasse:");
        staffSection.addEntryField(null, "Lehrer/Lehrerin");
        staffSection.addEntryField(null, "Klasse");
    }

    private EntrySection addEntrySection(String sectionTitle) {
        EntrySection entrySection = new EntrySection();
        entrySection.setSectionTitle(sectionTitle);
        content.getChildren().add(entrySection.getNode());
        return entrySection;
    }
}
