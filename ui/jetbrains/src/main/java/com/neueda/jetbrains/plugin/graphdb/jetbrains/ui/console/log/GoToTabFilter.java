package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.log;

import com.intellij.execution.filters.Filter;
import com.intellij.execution.filters.HyperlinkInfo;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.event.OpenTabEvent;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphConstants.ToolWindow.Tabs;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoToTabFilter implements Filter {
    static final String GRAPH_TAB_LINK = "as Graph";
    static final String TABLE_TAB_LINK = "as Table";

    private ConsoleView log;

    GoToTabFilter(ConsoleView log) {
        this.log = log;
    }

    @Nullable
    @Override
    public Result applyFilter(@NotNull String textLine, int endPoint) {
        List<ResultItem> links = Stream.of(
            createLink(textLine, endPoint, GRAPH_TAB_LINK, Tabs.GRAPH),
            createLink(textLine, endPoint, TABLE_TAB_LINK, Tabs.TABLE))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

        return new Result(links);
    }

    private Optional<Result> createLink(String textLine, int endPoint, String linkText, String tabName) {
        int startPoint = endPoint - textLine.length();

        int tabLinkPos = textLine.lastIndexOf(linkText);
        if (tabLinkPos > -1) {
            HyperlinkInfo gotoTab = e -> {
                if (log.getContentSize() - 1 == endPoint) {
                    e.getMessageBus().syncPublisher(OpenTabEvent.OPEN_TAB_TOPIC).openTab(tabName);
                } else {
                    showPopup("Query results outdated", "Please run query again");
                }
            };

            Result graphTabLink = new Result(
                startPoint + tabLinkPos,
                startPoint + tabLinkPos + linkText.length(),
                gotoTab);

            return Optional.of(graphTabLink);
        }
        return Optional.empty();
    }

    private void showPopup(String title, String text) {
        JBPopupFactory.getInstance()
            .createComponentPopupBuilder(
                new JLabel(text,
                    AllIcons.General.BalloonInformation,
                    JLabel.LEFT),
                log.getComponent())
            .setTitle(title)
            .createPopup()
            .showInFocusCenter();
    }
}
