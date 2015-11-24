package com.obsbs.ui.panels;

import com.obsbs.management.beans.DataBean;
import com.obsbs.management.beans.WeekDayValueBean;
import com.obsbs.ui.fields.EntryField;
import com.obsbs.ui.sections.EntrySection;
import com.obsbs.ui.sections.EntrySelection;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.stream.Collectors;

public class EntryConfigurationPanel extends VBox {
    private Map<String, EntryField> entryFieldLookup = new HashMap<>();
    private EntrySelection schoolDayEntry;

    private DataBean dataBean;

    public EntryConfigurationPanel() {
        super(10);
        initEntrySections();
    }

    public void initEntrySections() {
        updateEntrySections(new DataBean(Field.FIRST_NAME.getKey(), Field.LAST_NAME.getKey()));
    }

    public void updateEntrySections(DataBean dataBean) {
        this.dataBean = dataBean;
        getChildren().clear();
        entryFieldLookup.clear();

        EntrySection staffSection = addEntrySection("Klasse:");
        addEntry(staffSection, dataBean, Field.TEACHER, Field.CLASS);

        EntrySection nameSection = addEntrySection("Name:");
        addEntry(nameSection, dataBean, Field.FIRST_NAME, Field.LAST_NAME);

        EntrySection addressSection0 = addEntrySection("Addresse:");
        addEntry(addressSection0, dataBean, Field.STREET, Field.HOUSE_NUMBER);
        EntrySection addressSection1 = addEntrySection(null);
        addEntry(addressSection1, dataBean, Field.POSTAL_CODE, Field.CITY);

        EntrySection schoolDaySection = addEntrySection("Unterichtstage:");
        List<String> entries = new ArrayList<>();
        for (WeekDayValueBean.WeekDay weekDay : WeekDayValueBean.WeekDay.values()) {
            entries.add(weekDay.getValue());
        }
        WeekDayValueBean weekDayValueBean = new WeekDayValueBean(dataBean.getValue(Field.SCHOOL_DAYS.getKey()));
        Set<String> selectedEntries = weekDayValueBean.getValue().stream().map(WeekDayValueBean.WeekDay::getValue).collect(Collectors.toSet());
        schoolDayEntry = schoolDaySection.addEntrySelection(entries, selectedEntries);

        EntrySection furtherInformationSection = addEntrySection("Weitere Informationen:");
        addEntry(furtherInformationSection, dataBean, Field.REGISTRATION_CODE, Field.DISTANCE);

        EntrySection companySection0 = addEntrySection("Firma:");
        addEntry(companySection0, dataBean, Field.COMPANY_NAME);
        EntrySection companySection1 = addEntrySection(null);
        addEntry(companySection1, dataBean, Field.COMPANY_STREET, Field.COMPANY_HOUSE_NUMBER);
        EntrySection companySection2 = addEntrySection(null);
        addEntry(companySection2, dataBean, Field.COMPANY_POSTAL_CODE, Field.COMPANY_CITY);
    }

    public DataBean getDataBean() {
        for (Map.Entry<String, EntryField> entry : entryFieldLookup.entrySet()) {
            String value = entry.getValue().getValue();
            dataBean.setValue(entry.getKey(), value == null || value.isEmpty() ? null : value);
        }

        Set<String> selectedEntries = schoolDayEntry.getSelectedEntries();
        WeekDayValueBean weekDayValueBean = new WeekDayValueBean(selectedEntries);
        dataBean.setValue(Field.SCHOOL_DAYS.getKey(), selectedEntries.isEmpty() ? null : weekDayValueBean.getValueAsString());

        return dataBean;
    }

    private EntrySection addEntrySection(String sectionTitle) {
        EntrySection entrySection = new EntrySection();
        if (sectionTitle != null) {
            entrySection.setSectionTitle(sectionTitle);
        }
        getChildren().add(entrySection.getNode());
        return entrySection;
    }

    private void addEntry(EntrySection entrySection, DataBean dataBean, Field... fields) {
        for (Field field : fields) {
            String key = field.getKey();
            String displayValue = field.getDisplayValue();
            EntryField entryField = entrySection.addEntryField(dataBean.getValue(key), displayValue);
            entryFieldLookup.put(key, entryField);
        }
    }

    public static List<Field> getFieldsInOrder() {
        List<Field> fields = new ArrayList<>();

        fields.add(Field.FIRST_NAME);
        fields.add(Field.LAST_NAME);
        fields.add(Field.CLASS);

        fields.add(Field.POSTAL_CODE);
        fields.add(Field.CITY);
        fields.add(Field.STREET);
        fields.add(Field.HOUSE_NUMBER);

        fields.add(Field.COMPANY_NAME);
        fields.add(Field.COMPANY_POSTAL_CODE);
        fields.add(Field.COMPANY_CITY);
        fields.add(Field.COMPANY_STREET);
        fields.add(Field.COMPANY_HOUSE_NUMBER);

        fields.add(Field.TEACHER);
        fields.add(Field.DISTANCE);
        fields.add(Field.SCHOOL_DAYS);
        fields.add(Field.REGISTRATION_CODE);

        return fields;
    }

    public static String[] convert(List<Field> fields) {
        if (fields == null) {
            return null;
        }
        String[] array = new String[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            array[i] = fields.get(i).getKey();
        }
        return array;
    }

    public enum Field {
        TEACHER("teacher", "Lehrer/in"), CLASS("class", "Klasse"),
        FIRST_NAME("first_name", "Vorname"), LAST_NAME("last_name", "Nachname"),
        STREET("street", "Straße"), HOUSE_NUMBER("house_number", "Hausnummer"),
        POSTAL_CODE("postal_code", "PLZ"), CITY("city", "Stadt"),
        SCHOOL_DAYS("school_days", "Unterichtstage"),
        REGISTRATION_CODE("registration_code", "KFZ-Kennzeichen"), DISTANCE("distance", "Entfernung"),
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
