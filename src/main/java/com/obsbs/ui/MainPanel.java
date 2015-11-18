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
        staffSection.addEntryField(null, Field.TEACHER.getDisplayValue());
        staffSection.addEntryField(null, Field.CLASS.getDisplayValue());

        EntrySection nameSection = addEntrySection("Name:");
        nameSection.addEntryField(null, Field.FIRST_NAME.getDisplayValue());
        nameSection.addEntryField(null, Field.LAST_NAME.getDisplayValue());

        EntrySection addressSection0 = addEntrySection("Addresse:");
        addressSection0.addEntryField(null, Field.STREET.getDisplayValue());
        addressSection0.addEntryField(null, Field.HOUSE_NUMBER.getDisplayValue());
        EntrySection addressSection1 = addEntrySection(null);
        addressSection1.addEntryField(null, Field.POSTAL_CODE.getDisplayValue());
        addressSection1.addEntryField(null, Field.CITY.getDisplayValue());

        EntrySection companySection0 = addEntrySection("Firma:");
        companySection0.addEntryField(null, Field.COMPANY_NAME.getDisplayValue());
        EntrySection companySection1 = addEntrySection(null);
        companySection1.addEntryField(null, Field.COMPANY_STREET.getDisplayValue());
        companySection1.addEntryField(null, Field.COMPANY_HOUSE_NUMBER.getDisplayValue());
        EntrySection companySection2 = addEntrySection(null);
        companySection2.addEntryField(null, Field.COMPANY_POSTAL_CODE.getDisplayValue());
        companySection2.addEntryField(null, Field.COMPANY_CITY.getDisplayValue());
    }

    private EntrySection addEntrySection(String sectionTitle) {
        EntrySection entrySection = new EntrySection();
        if (sectionTitle != null) {
            entrySection.setSectionTitle(sectionTitle);
        }
        content.getChildren().add(entrySection.getNode());
        return entrySection;
    }

    public enum Field {
        TEACHER("teacher", "Lehrer/in"), CLASS("class", "Klasse"),
        FIRST_NAME("first_name", "Vorname"), LAST_NAME("last_name", "Nachname"),
        STREET("street", "Straße"), HOUSE_NUMBER("house_number", "Hausnummer"),
        POSTAL_CODE("postal_code", "PLZ"), CITY("city", "Stadt"),
        COMPANY_NAME("company_name", "Firmen Name"),
        COMPANY_STREET("company_street", "Straße"), COMPANY_HOUSE_NUMBER("company_house_number", "Hausnummer"),
        COMPANY_POSTAL_CODE("company_postal_code", "PLZ"), COMPANY_CITY("company_city", "Stadt");

        private final String key;
        private final String displayValue;

        Field(String key, String displayValue) {
            this.key = key;
            this.displayValue = displayValue;
        }

        public String getKey() {
            return key;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }
}
