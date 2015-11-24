package com.obsbs.ui.sections;


import com.obsbs.Main;
import com.obsbs.management.beans.DataBean;
import com.obsbs.management.beans.PassengerValueBean;
import com.obsbs.ui.FXMLContent;
import com.obsbs.ui.panels.EntryConfigurationPanel;
import com.obsbs.ui.panels.YesNoPanel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PassengerSection extends FXMLContent<VBox> {
    @FXML
    private TextField firstNameEntry;

    @FXML
    private TextField lastNameEntry;

    @FXML
    private TextField classEntry;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private ListView<DataBean> entries;

    private final List<DataBean> dataBeans = new ArrayList<>();
    private DataBean dataBean = new DataBean();
    private Label sectionTitle;

    public PassengerSection() {
        super("passenger-section.fxml");
        init();
    }

    public PassengerSection(PassengerValueBean passengerValueBean) {
        this();
        if (passengerValueBean != null) {
            dataBeans.addAll(passengerValueBean.getValue());
        }
        init();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        entries.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        entries.setOnMouseClicked(event -> {
            dataBean = entries.getSelectionModel().getSelectedItem();
            firstNameEntry.setText(dataBean.getValue(EntryConfigurationPanel.Field.FIRST_NAME.getKey()));
            lastNameEntry.setText(dataBean.getValue(EntryConfigurationPanel.Field.LAST_NAME.getKey()));
            classEntry.setText(dataBean.getValue(EntryConfigurationPanel.Field.CLASS.getKey()));
        });

        deleteButton.setOnMouseClicked(event -> {
            DataBean dataBean = entries.getSelectionModel().getSelectedItem();
            Main.openYesNoDialog("Wirklich löschen?", "Möchtest du den Eintrag \"" + dataBean + "\" wirlich Löschen?", new YesNoPanel.AbstractYesNoCallback() {
                @Override
                public void onYes() {
                    dataBeans.remove(entries.getSelectionModel().getSelectedItem());
                    init();
                }
            });
        });

        saveButton.setOnMouseClicked(event -> {
            String firstNameEntry = this.firstNameEntry.getText();
            String lastNameEntry = this.lastNameEntry.getText();
            String classEntry = this.classEntry.getText();
            dataBean.setValue(EntryConfigurationPanel.Field.FIRST_NAME.getKey(), firstNameEntry == null || firstNameEntry.isEmpty() ? null : firstNameEntry);
            dataBean.setValue(EntryConfigurationPanel.Field.LAST_NAME.getKey(), lastNameEntry == null || lastNameEntry.isEmpty() ? null : lastNameEntry);
            dataBean.setValue(EntryConfigurationPanel.Field.CLASS.getKey(), classEntry == null || classEntry.isEmpty() ? null : classEntry);
            if (dataBean.getValues().isEmpty()) {
                dataBeans.remove(dataBean);
            } else if (!dataBeans.contains(dataBean)) {
                dataBeans.add(dataBean);
            }
            init();
        });
    }

    public void setSectionTitle(String sectionTitle) {
        if (this.sectionTitle == null) {
            this.sectionTitle = new Label();
            getNode().getChildren().add(0, this.sectionTitle);
        }
        this.sectionTitle.setText(sectionTitle);
    }

    private void init() {
        dataBean = new DataBean(EntryConfigurationPanel.Field.FIRST_NAME.getKey(),
                EntryConfigurationPanel.Field.LAST_NAME.getKey(),
                EntryConfigurationPanel.Field.CLASS.getKey());
        firstNameEntry.setText("");
        lastNameEntry.setText("");
        classEntry.setText("");
        updateListView();
    }

    private void updateListView() {
        entries.setItems(FXCollections.observableList(dataBeans));
        entries.refresh();
    }

    public PassengerValueBean getPassengerValueBean() {
        return new PassengerValueBean(dataBeans);
    }
}
