package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.icons.AllIcons;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.openapi.ui.Messages;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;

import java.awt.*;
import java.net.URI;

public class LandingPageAction {
    public static final String URL = "https://technologies.neueda.com/plugin";

    public static void open() {
        Analytics.event("landingPage", "clicked");
        int ok = Messages.showOkCancelDialog("This feature is planned for a \nfuture release of the premium version.\n" +
                        "If you are interested, please visit: \n\n" + URL, "Premium Version", "Find more", "Cancel",
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
