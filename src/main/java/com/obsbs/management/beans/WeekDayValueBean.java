package com.obsbs.management.beans;

import java.io.Serializable;
import java.util.*;

public class WeekDayValueBean extends HasValue<List<WeekDayValueBean.WeekDay>> implements Serializable {
    private final List<WeekDay> value = new ArrayList<>();

    public WeekDayValueBean() {
    }

    public WeekDayValueBean(Collection<WeekDay> value) {
        for (WeekDay weekDay : value) {
            addValue(weekDay);
        }
    }

    public boolean addValue(WeekDay weekDay) {
        if (weekDay == null || value.contains(weekDay)) {
            return false;
        }
        value.add(weekDay);
        Collections.sort(value, new Comparator<WeekDay>() {
            @Override
            public int compare(WeekDay weekDay0, WeekDay weekDay1) {
                return Integer.compare(weekDay0.getWeight(), weekDay1.getWeight());
            }
        });
        return true;
    }

    @Override
    public List<WeekDay> getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        String valueAsString = value.size() > 0 ? value.get(0).getValue() : null;
        for (int i = 1; i < value.size(); i++) {
            valueAsString += ", " + value.get(i).getValue();
        }
        return valueAsString;
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
}
