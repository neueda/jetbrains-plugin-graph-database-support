package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.icons.AllIcons;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.openapi.ui.Messages;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;

import java.awt.*;
import java.net.URI;

public class LandingPageAction {
    public static final String URL = "https://google.com";

    public static void open() {
        Analytics.event("landingPage", "clicked");
        int ok = Messages.showOkCancelDialog("todo " + URL, "todo", "Read more", "Cancel",
                AllIcons.General.QuestionDialog);
        if (ok == 0) {
            Analytics.event("landingPage", "land");
            try {
                Desktop.getDesktop().browse(URI.create(URL));
            } catch (Exception e) {
                BrowserLauncher.getInstance().browse(URI.create(URL));
            }
        } else {
            Analytics.event("landingPage", "cancel");
        }
    }
}
