package com.obsbs.ui.panels;

import com.obsbs.Main;
import com.obsbs.management.DataManager;
import com.obsbs.management.beans.DataBean;
import com.obsbs.ui.FXMLContent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.*;

public class MainPanel extends FXMLContent<BorderPane> {
    private final DataManager dataManager = new DataManager();

    private String filter;
    private boolean sortByEligibility = false;

    @FXML
    private TextField filterField;

    @FXML
    private Button sortEntriesButton;

    @FXML
    private Button newEntryButton;

    @FXML
    private Button saveEntryButton;

    @FXML
    private Button deleteEntryButton;

    @FXML
    private ListView<DataBean> entries;

    @FXML
    private EntryConfigurationPanel entryConfigurationPanel;

    public MainPanel() {
        super("main-panel.fxml");
        updateListView();
    }

    public void initialize(URL location, ResourceBundle resources) {
        entries.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        entries.setOnMouseClicked(event -> entryConfigurationPanel.updateEntrySections(entries.getSelectionModel().getSelectedItem()));

        filterField.setOnKeyReleased(event -> updateListView(filterField.getText() == null ? null : filterField.getText()));

        sortEntriesButton.setOnMouseClicked(event -> {
            sortByEligibility = !sortByEligibility;
            updateListView();
        });

        deleteEntryButton.setOnMouseClicked(event -> {
            DataBean dataBean = entries.getSelectionModel().getSelectedItem();
            Main.openYesNoDialog("Wirklich löschen?", "Möchtest du den Eintrag \"" + dataBean + "\" wirlich Löschen?", new YesNoPanel.AbstractYesNoCallback() {
                @Override
                public void onYes() {
                    dataManager.remove(entries.getSelectionModel().getSelectedItem());
                    updateListView();
                }
            });
        });

        saveEntryButton.setOnMouseClicked(event -> {
            dataManager.add(entryConfigurationPanel.getDataBean());
            updateListView();
        });

        newEntryButton.setOnMouseClicked(event -> {
            DataBean dataBean = entryConfigurationPanel.getDataBean();
            if (!dataBean.getValues().isEmpty()) {
                dataManager.add(dataBean);
            }
            entryConfigurationPanel.initEntrySections();
            updateListView();
        });
    }

    private void updateListView() {
        updateListView(filter);
    }

    private void updateListView(List<DataBean> dataBeans) {
        entries.setItems(FXCollections.observableList(dataBeans));
        entries.refresh();
    }

    private void updateListView(String filter) {
        this.filter = filter;

        DataManager.AsyncCallback<List<DataBean>> asyncCallback = value -> {
            if (sortByEligibility) {
                updateListView(sortByEligibility(value));
            } else {
                updateListView(value);
            }
        };

        dataManager.terminateAllJobs();

        if (filter != null) {
            dataManager.findSorted(asyncCallback, filter, EntryConfigurationPanel.convert(EntryConfigurationPanel.getFieldsInOrder()));
        } else {
            dataManager.findAllSorted(asyncCallback, EntryConfigurationPanel.convert(EntryConfigurationPanel.getFieldsInOrder()));
        }
    }

    public void export() {
        dataManager.export();
    }

    private static List<DataBean> sortByEligibility(List<DataBean> dataBeans) {
        List<DataBean> sorted = new ArrayList<>(dataBeans);
        // ToDo
        // Collections.sort(sorted, new Comparator<DataBean>() {
        //     @Override
        //     public int compare(DataBean d0, DataBean d1) {
        //         return 0;
        //     }
        // });
        return sorted;
    }
}
