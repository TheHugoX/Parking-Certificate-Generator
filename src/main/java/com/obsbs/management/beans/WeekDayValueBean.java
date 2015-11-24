package com.obsbs.management.beans;

import java.io.Serializable;
import java.util.*;

public class WeekDayValueBean extends HasValue<List<WeekDayValueBean.WeekDay>> implements Serializable {
    private final List<WeekDay> values = new ArrayList<>();

    public WeekDayValueBean() {
    }

    public WeekDayValueBean(String... values) {
        if (values != null) {
            for (String value : values) {
                addAndSort(this.values, value);
            }
        }
    }

    public WeekDayValueBean(Collection<String> values) {
        if (values != null) {
            for (String value : values) {
                addAndSort(this.values, value);
            }
        }
    }

    public WeekDayValueBean(String commaSeparatedList) {
        if (commaSeparatedList != null && commaSeparatedList.contains(",")) {
            for (String value : commaSeparatedList.replace(" ", "").split(",")) {
                addAndSort(this.values, value);
            }
        }
    }

    @Override
    public List<WeekDay> getValue() {
        return Collections.unmodifiableList(values);
    }

    @Override
    public String getValueAsString() {
        return convert(values);
    }

    @Override
    public String toString() {
        return convert(values);
    }

    public enum WeekDay {
        MONDAY(0, "Montag"), TUESDAY(1, "Dienstag"), WEDNESDAY(2, "Mittwoch"), THURSDAY(3, "Donnerstag"),
        FRIDAY(4, "Freitag");

        private final int weight;
        private final String value;

        WeekDay(int weight, String value) {
            this.weight = weight;
            this.value = value;
        }

        public int getWeight() {
            return weight;
        }

        public String getValue() {
            return value;
        }
    }

    private static WeekDay convert(String value) {
        for (WeekDay weekDay : WeekDay.values()) {
            if (weekDay.getValue().equals(value)) {
                return weekDay;
            }
        }
        return null;
    }

    private static String convert(List<WeekDay> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }
        String valueAsString = values.get(0).getValue();
        for (int i = 1; i < values.size(); i++) {
            valueAsString += ", " + values.get(i).getValue();
        }
        return valueAsString;
    }

    private static boolean addAndSort(List<WeekDay> values, String value) {
        WeekDay weekDay = convert(value);
        if (weekDay == null || values.contains(weekDay)) {
            return false;
        }
        values.add(weekDay);
        Collections.sort(values, new Comparator<WeekDay>() {
            @Override
            public int compare(WeekDay weekDay0, WeekDay weekDay1) {
                return Integer.compare(weekDay0.getWeight(), weekDay1.getWeight());
            }
        });
        return true;
    }
}
