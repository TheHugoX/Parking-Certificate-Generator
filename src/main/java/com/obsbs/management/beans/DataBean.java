package com.obsbs.management.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataBean implements Serializable {
    private final Map<String, String> values = new HashMap<>();

    public DataBean() {
    }

    public DataBean(Map<String, String> values) {
        this.values.putAll(values);
    }

    public Map<String, String> getValues() {
        return values;
    }

    public String getValue(String key) {
        return values.get(key);
    }

    public boolean setValue(String key, String valueBean) {
        if (key == null) {
            return false;
        }
        if (valueBean == null) {
            values.remove(key);
        } else {
            values.put(key, valueBean);
        }
        return true;
    }
}
