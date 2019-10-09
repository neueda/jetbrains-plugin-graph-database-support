package com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionWrapper {
    public static final int SHORT_STRING_LENGTH = 150;
    private static final String NON_THIN_CHARS = "[^iIl1\\.,']";

    private static int textWidth(String str) {
        return str.length() - str.replaceAll(NON_THIN_CHARS, "").length() / 2;
    }

    public static String ellipseString(String text, int targetLength) {
        if (textWidth(text) <= targetLength) {
            return text;
        }
        int end = text.lastIndexOf(' ', targetLength - 3);
        if (end == -1) {
            return text.substring(0, targetLength - 3) + "...";
        }
        int newEnd = end;
        do {
            end = newEnd;
            newEnd = text.indexOf(' ', end + 1);
            if (newEnd == -1) {
                newEnd = text.length();
            }

        } while (textWidth(text.substring(0, newEnd) + "...") < targetLength);

        return text.substring(0, end) + "...";
    }

    public static String getCause(Exception exception) {
        StringBuilder exceptionCauses = new StringBuilder();
        Throwable cause = exception.getCause();
        int counter = 0;
        while (cause != null && counter <= 50) {
            exceptionCauses.append(cause.getMessage()).append(System.lineSeparator());
            cause = cause.getCause();
            counter++;
        }
        return exceptionCauses.toString();
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

    public static String wrapExceptionInMeaningMessage(Exception exception) {
        String exceptionMessage = exception.getMessage();
        if (exceptionMessage != null) {
            if (exceptionMessage.contains("SerializationException")) {
                return ExceptionErrorMessages.SERIALIZER_EXCEPTION.getDescription();
            }
            if (exceptionMessage.contains("ResponseException")) {
                return ExceptionErrorMessages.RESPONSE_EXCEPTION.getDescription();
            }
            if (exceptionMessage.contains("ConnectionException")) {
                return ExceptionErrorMessages.CONNECTION_EXCEPTION.getDescription();
            }
            if (exceptionMessage.length() > SHORT_STRING_LENGTH) {
                return ellipseString(exceptionMessage, SHORT_STRING_LENGTH);
            }
            return exceptionMessage;
        } else {
            return ExceptionErrorMessages.ERROR_OCCURRED.getDescription();
        }
    }
}
