package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers;

public class KeyValuePair {

    private final String key;
    private final Object value;
    private final boolean isValueData;

    public KeyValuePair(String key, Object value) {
        this(key, value, false);
    }

    public KeyValuePair(String key, Object value, boolean isValueData) {
        this.key = key;
        this.value = value;
        this.isValueData = isValueData;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public boolean isValueData() {
        return isValueData;
    }
}
