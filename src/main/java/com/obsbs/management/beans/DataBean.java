package com.obsbs.management.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataBean implements Serializable {
    private final Map<String, HasValue> values = new HashMap<>();

    public DataBean() {
    }

    public DataBean(Map<String, HasValue> values) {
        this.values.putAll(values);
    }

    public Map<String, String> getValues() {
        Map<String, String> values = new HashMap<>();
        for (Map.Entry<String, HasValue> entry : this.values.entrySet()) {
            values.put(entry.getKey(), entry.getValue().getValueAsString());
        }
        return values;
    }

    public HasValue getValue(String key) {
        return values.get(key);
    }

    public String getValueAsString(String key) {
        HasValue value = values.get(key);
        if (value != null) {
            return value.getValueAsString();
        }
        return null;
    }

    public boolean setValue(String key, String value) {
        if (key == null) {
            return false;
        }
        if (value == null) {
            values.remove(key);
        } else {
            values.put(key, new StringValueBean(value));
        }
        return true;
    }

    public boolean setValue(String key, HasValue value) {
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
}
