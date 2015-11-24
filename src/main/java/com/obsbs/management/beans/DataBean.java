package com.obsbs.management.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataBean implements Serializable {
    private final Map<String, String> values = new HashMap<>();
    private String[] displayedKeys;

    public DataBean(String... displayedKeys) {
        this.displayedKeys = displayedKeys;
    }

    public DataBean(Map<String, String> values, String... displayedKeys) {
        this(displayedKeys);
        this.values.putAll(values);
    }

    public Map<String, String> getValues() {
        Map<String, String> values = new HashMap<>();
        for (Map.Entry<String, String> entry : this.values.entrySet()) {
            values.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(values);
    }

    public String getValue(String key) {
        return values.get(key);
    }

    public boolean setValue(String key, String value) {
        if (key == null) {
            return false;
        }
        if (value == null) {
            values.remove(key);
        } else {
            values.put(key, value);
        }
        return true;
    }

    public String[] getDisplayedKeys() {
        return displayedKeys;
    }

    public void setDisplayedKeys(String... displayedKeys) {
        this.displayedKeys = displayedKeys;
    }

    @Override
    public String toString() {
        String result = "";

        if (displayedKeys != null) {
            for (String displayedKey : displayedKeys) {
                String value = getValue(displayedKey);
                if (value != null && !value.isEmpty()) {
                    result += (result.isEmpty() ? "" : ", ") + value;
                }
            }
        } else {
            result = super.toString();
        }

        return result;
    }
}