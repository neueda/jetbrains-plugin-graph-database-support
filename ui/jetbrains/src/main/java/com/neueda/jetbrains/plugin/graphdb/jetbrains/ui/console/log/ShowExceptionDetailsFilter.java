package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.log;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;
import java.util.Optional;

public class ShowExceptionDetailsFilter implements Filter {
    public static final String SHOW_DETAILS = "Show details";

    private ConsoleView log;
    private Map<String, String> exceptions;

    ShowExceptionDetailsFilter(ConsoleView log, Map<String, String> exceptions) {
        this.log = log;
        this.exceptions = exceptions;
    }

    @Nullable
    @Override
    public Result applyFilter(@NotNull String textLine, int endPoint) {
        return createLink(textLine, endPoint, SHOW_DETAILS).orElse(null);
    }

    private Optional<Filter.Result> createLink(String textLine, int endPoint, String linkText) {
        int startPoint = endPoint - textLine.length();

        int tabLinkPos = textLine.lastIndexOf(linkText);
        if (tabLinkPos > -1) {
            HyperlinkInfo showPopup = e -> {
                showPopup("Error details", exceptions.get(textLine));
            };
            Result graphTabLink = new Result(
                    startPoint + tabLinkPos,
                    startPoint + tabLinkPos + linkText.length(),
                    showPopup);

            return Optional.of(graphTabLink);
        }
        return Optional.empty();
    }

    private void showPopup(String title, String text) {
        JBPopupFactory.getInstance()
                .createComponentPopupBuilder(
                        new JLabel(text,
                                AllIcons.General.Error,
                                JLabel.LEFT),
                        log.getComponent())
                .setTitle(title)
                .createPopup()
                .showInFocusCenter();
    }
}
