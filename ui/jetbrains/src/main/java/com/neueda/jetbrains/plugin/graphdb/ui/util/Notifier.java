package com.neueda.jetbrains.plugin.graphdb.ui.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

public class Notifier {

    private static final String GROUP_DISPLAY_ID = "Graph Database Support";

    public static void info(String title, String message) {
        notify(title, message, NotificationType.INFORMATION);
    }

    public static void warn(String title, String message) {
        notify(title, message, NotificationType.WARNING);
    }

    public static void error(String title, String message) {
        notify(title, message, NotificationType.ERROR);
    }

    private static void notify(String title, String message, NotificationType notificationType) {
        Notifications.Bus.notify(new Notification(GROUP_DISPLAY_ID, title, message, notificationType));
    }
}
