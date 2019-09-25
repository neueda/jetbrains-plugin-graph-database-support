package com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.IconButton;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.OpenCypherGremlinSimpleTranslator;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TranslateQueryAction extends QueryAction {
    @Override
    protected void actionPerformed(AnActionEvent e, Project project, Editor editor, String query, Map<String, Object> parameters) {
        String gremlin = new OpenCypherGremlinSimpleTranslator().translate(query, parameters);

        JTextArea translation = new JTextArea(gremlin);
        translation.setEditable(false);
        translation.setLineWrap(true);

        JButton configure = new JButton("Configure/optimize translation");
        configure.addActionListener(v -> LandingPageAction.open());

        JBPopup popup = JBPopupFactory.getInstance().createComponentPopupBuilder(translation, null)
                .setAdText("Query translated using Cypher for Gremlin")
                .setSettingButtons(configure)
                .setCancelButton(new IconButton("Cancel", AllIcons.Actions.Cancel))
                .setRequestFocus(true)
                .setResizable(true)
                .setMovable(true)
                .setMinSize(new Dimension(200, 150))
                .createPopup();

        if (editor == null) {
            popup.showCenteredInCurrentWindow(project);
        } else {
            popup.showInBestPositionFor(editor);
        }
    }
}
