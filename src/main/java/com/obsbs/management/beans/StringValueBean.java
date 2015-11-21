package com.obsbs.management.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class StringValueBean extends HasValue<String> implements Serializable {
    private String value;

    public StringValueBean() {
    }

    public StringValueBean(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getValueAsString() {
        return value;
    }
}
