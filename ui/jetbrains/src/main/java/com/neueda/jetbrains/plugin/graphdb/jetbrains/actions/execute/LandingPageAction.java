package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.icons.AllIcons;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.ide.browsers.BrowserLauncherAppless;
import com.intellij.ide.browsers.UrlOpener;
import com.intellij.ide.browsers.WebBrowserService;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.Analytics;

import java.net.URI;
import java.net.URISyntaxException;

public class LandingPageAction {
    public static void open() {
        Analytics.event("landingPage", "clicked");
        int ok = Messages.showOkCancelDialog("todo https://google.com", "todo", "Read more", "Cancel", AllIcons.General.QuestionDialog);
        if (ok == 0) {
            Analytics.event("landingPage", "land");
            BrowserLauncher.getInstance().browse(URI.create("https://google.com"));
        } else {
            Analytics.event("landingPage", "cancel");
        }
    }
}
