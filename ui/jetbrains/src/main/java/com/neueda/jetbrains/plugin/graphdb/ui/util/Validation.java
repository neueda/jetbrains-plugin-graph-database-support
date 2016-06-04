package com.neueda.jetbrains.plugin.graphdb.ui.util;

import com.intellij.openapi.ui.ValidationInfo;

import javax.swing.JComponent;

public class Validation {

    public static ValidationInfo validation(String message, JComponent component) {
        return new ValidationInfo(message, component);
    }
}
