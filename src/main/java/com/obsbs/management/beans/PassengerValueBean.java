package com.obsbs.management.beans;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PassengerValueBean extends HasValue<List<DataBean>> implements Serializable {
    private static final Gson GSON = new Gson();
    private final List<DataBean> values;

    public PassengerValueBean() {
        values = new ArrayList<>();
    }

    public PassengerValueBean(List<DataBean> values) {
        this.values = values;
    }

    @Override
    public List<DataBean> getValue() {
        return values;
    }

    @Override
    public String getValueAsString() {
        return values.isEmpty() ? null : GSON.toJson(this);
    }

    public static PassengerValueBean fromJSON(String json) {
        if (json == null) {
            return null;
        }
        return GSON.fromJson(json, PassengerValueBean.class);
    }
}
