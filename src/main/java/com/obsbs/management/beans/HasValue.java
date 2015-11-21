package com.obsbs.management.beans;

public abstract class HasValue<V> {
    public abstract V getValue();

    public abstract String getValueAsString();

    @Override
    public String toString() {
        return getValueAsString();
    }
}
