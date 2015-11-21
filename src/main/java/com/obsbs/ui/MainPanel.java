package com.obsbs.ui;

import com.google.gson.Gson;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.*;

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
    private EntryConfigurationPanel entryConfigurationPanel;

    public MainPanel() {
        super("main-panel.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        addEntryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // ToDo currently just for testing purposes
                System.out.println(new Gson().toJson(entryConfigurationPanel.getDataBean()));
                entryConfigurationPanel.updateEntrySections(entryConfigurationPanel.getDataBean());
            }
        });
    }
}
