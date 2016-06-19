package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree.dto;


import javax.swing.Icon;

public class ValueWithIcon {

    private final Icon icon;
    private final String value;

    public ValueWithIcon(Icon icon, String value) {
        this.icon = icon;
        this.value = value;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getValue() {
        return value;
    }
}
