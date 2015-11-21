package com.obsbs.ui;

import com.google.gson.Gson;
import com.obsbs.management.DataManager;
import com.obsbs.management.beans.DataBean;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.*;

public class MainPanel extends FXMLContent<BorderPane> {
    private final DataManager dataManager = new DataManager();

    private String filter;

    @FXML
    private TextField filterField;

    @FXML
    private Button createEntryButton;

    @FXML
    private Button updateEntryButton;

    @FXML
    private Button deleteEntryButton;

    @FXML
    private ListView<DataBean> entries;

    @FXML
    private EntryConfigurationPanel entryConfigurationPanel;

    public MainPanel() {
        super("main-panel.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        entries.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        entries.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                entryConfigurationPanel.updateEntrySections(entries.getSelectionModel().getSelectedItem());
            }
        });

        updateEntryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dataManager.add(entryConfigurationPanel.getDataBean());
                // ToDo currently just for testing purposes
                System.out.println(new Gson().toJson(entryConfigurationPanel.getDataBean()));
                updateListView();
            }
        });

        createEntryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                entryConfigurationPanel.initEntrySections();
            }
        });

        filterField.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                updateListView(filterField.getText() == null ? null : filterField.getText());
            }
        });

        deleteEntryButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dataManager.remove(entries.getSelectionModel().getSelectedItem());
                updateListView();
            }
        });
    }

    private void updateListView() {
        updateListView(filter);
    }

    private void updateListView(List<DataBean> dataBeans) {
        entries.setItems(FXCollections.observableList(dataBeans));
    }

    private void updateListView(String filter) {
        this.filter = filter;

        DataManager.AsyncCallback<List<DataBean>> asyncCallback = new DataManager.AsyncCallback<List<DataBean>>() {
            @Override
            public void onCallback(List<DataBean> value) {
                updateListView(value);
            }
        };

        dataManager.terminateAllJobs();

        if (filter != null) {
            dataManager.findSorted(asyncCallback, filter, EntryConfigurationPanel.Field.FIRST_NAME.getKey(),
                    EntryConfigurationPanel.Field.LAST_NAME.getKey());
        } else {
            dataManager.findAllSorted(asyncCallback, EntryConfigurationPanel.Field.FIRST_NAME.getKey(),
                    EntryConfigurationPanel.Field.LAST_NAME.getKey());
        }
    }
}
