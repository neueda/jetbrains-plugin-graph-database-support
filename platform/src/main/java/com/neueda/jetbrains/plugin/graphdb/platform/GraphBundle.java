package com.neueda.jetbrains.plugin.graphdb.platform;

import com.intellij.CommonBundle;
import org.jetbrains.annotations.PropertyKey;

import java.util.ResourceBundle;

public class GraphBundle {

    private static final String BUNDLE_NAME = "graphdb.messages.GraphBundle";

    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    public static String message(@PropertyKey(resourceBundle = BUNDLE_NAME) String key, Object... params) {
        return CommonBundle.message(BUNDLE, key, params);
    }

    public static String messageOrDefault(@PropertyKey(resourceBundle = BUNDLE_NAME) String key, String defaultValue,
                                          Object... params) {
        return CommonBundle.messageOrDefault(BUNDLE, key, defaultValue, params);
    }

}
