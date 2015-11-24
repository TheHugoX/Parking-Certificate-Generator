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
    private static final DataManager DATA_MANAGER = new DataManager();

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
                    DATA_MANAGER.remove(entries.getSelectionModel().getSelectedItem());
                    updateListView();
                }
            });
        });

        saveEntryButton.setOnMouseClicked(event -> {
            ;
            updateListView(DATA_MANAGER.add(entryConfigurationPanel.getDataBean()));
        });

        newEntryButton.setOnMouseClicked(event -> {
            DataBean dataBean = entryConfigurationPanel.getDataBean();
            if (!dataBean.getValues().isEmpty()) {
                DATA_MANAGER.add(dataBean);
            }
            entryConfigurationPanel.initEntrySections();
            updateListView();
        });
    }

    private void updateListView() {
        updateListView(filter, null);
    }

    private void updateListView(DataBean dataBean) {
        updateListView(filter, dataBean);
    }

    private void updateListView(List<DataBean> dataBeans) {
        updateListView(dataBeans, null);
    }

    private void updateListView(List<DataBean> dataBeans, DataBean dataBean) {
        DataBean toSelect = dataBean == null ? entries.getSelectionModel().getSelectedItem() : dataBean;
        entries.setItems(FXCollections.observableList(dataBeans));
        entries.refresh();
        if (toSelect != null && dataBeans.contains(toSelect)) {
            entries.getSelectionModel().select(toSelect);
        } else {
            entries.getSelectionModel().selectFirst();
            entries.getOnMouseClicked().handle(null);
        }
    }

    private void updateListView(String filter) {
        updateListView(filter, null);
    }

    private void updateListView(String filter, DataBean dataBean) {
        this.filter = filter;

        DataManager.AsyncCallback<List<DataBean>> asyncCallback = value -> {
            if (sortByEligibility) {
                updateListView(sortByEligibility(value), dataBean);
            } else {
                updateListView(value, dataBean);
            }
        };

        DATA_MANAGER.terminateAllJobs();

        if (filter != null) {
            DATA_MANAGER.findSorted(asyncCallback, filter, EntryConfigurationPanel.convert(EntryConfigurationPanel.getFieldsInOrder()));
        } else {
            DATA_MANAGER.findAllSorted(asyncCallback, EntryConfigurationPanel.convert(EntryConfigurationPanel.getFieldsInOrder()));
        }
    }

    public void export() {
        DATA_MANAGER.export();
    }

    private static List<DataBean> sortByEligibility(List<DataBean> dataBeans) {
        List<DataBean> sorted = new ArrayList<>(dataBeans);
        // ToDo sort
        return sorted;
    }
}
