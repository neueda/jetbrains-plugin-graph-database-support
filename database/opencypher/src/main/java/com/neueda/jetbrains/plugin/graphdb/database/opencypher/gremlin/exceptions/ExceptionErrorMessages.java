package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.exceptions;

public enum ExceptionErrorMessages {
    ERROR_OCCURRED("Error occurred."),
    SYNTAX_WARNING("Please note that Cypher query is translated to Gremlin and may fail" +
            " because of translation or database specifics. Make sure that flavor is properly configured" +
            " in database connection configuration."),
    SERIALIZER_EXCEPTION("Wrong serializer selected. Please check connection configuration."),
    RESPONSE_EXCEPTION("Database connection failed. Please check database configuration" +
            " (including username and password) and retry to connect."),
    CONNECTION_EXCEPTION("Database connection failed. Please check database configuration" +
            " (including username and password) and retry to connect.");

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
