package com.neueda.jetbrains.plugin.graphdb.jetbrains.util;

public class ExceptionWrapper {
    private final static String NON_THIN_CHARS = "[^iIl1\\.,']";

    private static int textWidth(String str) {
        return str.length() - str.replaceAll(NON_THIN_CHARS, "").length() / 2;
    }

    public static String truncateString(String text, int targetLength) {
        if (textWidth(text) <= targetLength)
            return text;
        int end = text.lastIndexOf(' ', targetLength - 3);
        if (end == -1)
            return text.substring(0, targetLength - 3) + "...";
        int newEnd = end;
        do {
            end = newEnd;
            newEnd = text.indexOf(' ', end + 1);
            if (newEnd == -1)
                newEnd = text.length();

        } while (textWidth(text.substring(0, newEnd) + "...") < targetLength);

        return text.substring(0, end) + "...";
    }

    public static String getCause(Exception exception) {
        StringBuilder exceptionCauses = new StringBuilder();
        Throwable cause = exception.getCause();
        while (cause != null) {
            exceptionCauses.append(cause.getMessage()).append("\n");
            cause = cause.getCause();
        }
        return exceptionCauses.toString();
    }


    public static String wrapExceptionInMeaningMessage(Exception exception) {
        String exceptionName = exception.getClass().getCanonicalName();
        System.out.println("+++++++++++ Exception name: " + exceptionName + "+++++++++++++");
        switch (exceptionName) {
            case "org.apache.tinkerpop.gremlin.driver.exception.ResponseException":
                return "Response exception occurred";
            case "org.apache.tinkerpop.gremlin.driver.exception.ConnectionException":
                return "Connection exception occurred";
            case "java.util.concurrent.TimeoutException":
                return "Timed out while waiting for available host - check the client configuration" +
                        "and connectivity to the server";
            case "java.lang.RuntimeException":
            default:
                return exception.getMessage();
        }
    }
}
