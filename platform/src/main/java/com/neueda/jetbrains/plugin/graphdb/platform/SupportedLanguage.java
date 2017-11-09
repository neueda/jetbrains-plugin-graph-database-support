package com.neueda.jetbrains.plugin.graphdb.platform;

import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

public enum SupportedLanguage {
    CYPHER("Cypher");

    private final String languageId;

    SupportedLanguage(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public static boolean isSupported(String languageId) {
        checkNotNull(languageId, "'languageId' is undefined");
        return Stream.of(values())
                .anyMatch(language -> language.getLanguageId().equals(languageId));
    }

}
