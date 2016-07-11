package com.neueda.jetbrains.plugin.graphdb.jetbrains.configuration;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.ui.components.JBCheckBox;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.AnalyticsApplicationComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GraphDatabaseSupportConfiguration implements Configurable{

    private boolean isModified = false;
    private JBCheckBox analyticsCheckBox;
    private AnalyticsApplicationComponent analytics;

    @Nls
    @Override
    public String getDisplayName() {
        return "Graph Database";
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return "Graph Database support plugin configuration";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        isModified = false;

        analytics = AnalyticsApplicationComponent.getInstance();
        analyticsCheckBox = new JBCheckBox("Send anonymous usage statistics", analytics.isAnalyticEnabled());
        analyticsCheckBox.addActionListener(e -> isModified = true);

        JPanel rootPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rootPane.add(analyticsCheckBox);
        return rootPane;
    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public void apply() throws ConfigurationException {
        analytics.enableAnalytics(analyticsCheckBox.isSelected());
        isModified = false;
    }

    @Override
    public void reset() {
        analyticsCheckBox.setSelected(analytics.isAnalyticEnabled());
        isModified = false;
    }

    @Override
    public void disposeUIResources() {
    }
}
