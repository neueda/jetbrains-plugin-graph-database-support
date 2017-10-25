package com.neueda.jetbrains.plugin.graphdb.platform;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.stream.Stream.of;

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
        return of(values())
                .anyMatch(language -> language.getLanguageId().equals(languageId));
    }

}
