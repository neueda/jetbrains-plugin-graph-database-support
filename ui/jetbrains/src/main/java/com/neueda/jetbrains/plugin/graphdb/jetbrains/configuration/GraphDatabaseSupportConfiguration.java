package com.neueda.jetbrains.plugin.graphdb.jetbrains.configuration;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.ui.components.JBCheckBox;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.analytics.AnalyticsApplicationComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GraphDatabaseSupportConfiguration implements Configurable {

    private boolean isModified = false;
    private JBCheckBox analyticsCheckBox, globalParametersCheckbox;
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
        analyticsCheckBox = new JBCheckBox("Send anonymous usage statistics",
                SettingsComponent.getInstance().isAnalyticEnabled());
        analyticsCheckBox.addActionListener(e -> isModified = true);

        JPanel rootPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rootPane.add(analyticsCheckBox);

        // Enable when changes in Settings will automatically affect the button state in Graph Database Console
//        globalParametersCheckbox = new JBCheckBox("Use custom parameters for each Cypher file separately",
//                SettingsComponent.getInstance().areFileSpecificParamsUsed());
//        globalParametersCheckbox.addActionListener(e -> isModified = true);
//        rootPane.add(globalParametersCheckbox);

        return rootPane;
    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    @Override
    public void apply() throws ConfigurationException {
        SettingsComponent.getInstance().enableAnalytics(analyticsCheckBox.isSelected());
        SettingsComponent.getInstance().enableFileSpecificParams(globalParametersCheckbox.isSelected());

        isModified = false;
    }

    @Override
    public void reset() {
        analyticsCheckBox.setSelected(SettingsComponent.getInstance().isAnalyticEnabled());
        globalParametersCheckbox.setSelected(SettingsComponent.getInstance().areFileSpecificParamsUsed());
        isModified = false;
    }

    @Override
    public void disposeUIResources() {
    }
}
