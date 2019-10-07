package com.neueda.jetbrains.plugin.graphdb.jetbrains.util;

public enum ExceptionErrorMessages {
    SYNTAX_EXCEPTION("Please note that Cypher query is translated to Gremlin and may fail" +
            "because of translation or database specifics. Make sure that flavor is properly configured in database connection configuration."),
    SERIALIZER_EXCEPTION("Wrong serializer selected. Please check connection configuration."),
    RESPONSE_EXCEPTION("Database connection failed. Please check database configuration and retry to connect."),
    CONNECTION_EXCEPTION("Database connection failed. Please check database configuration and retry to connect.");

    private final String description;

    ExceptionErrorMessages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
