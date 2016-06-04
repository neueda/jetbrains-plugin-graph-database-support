package com.neueda.jetbrains.plugin.graphdb.ui.datasource.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CreateDataSourceDialog extends DialogWrapper {

    private JPanel content;

    public CreateDataSourceDialog(@Nullable Project project) {
        super(project);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return content;
    }
}
