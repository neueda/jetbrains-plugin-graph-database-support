package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.util.Disposer;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPropertyContainer;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class EditEntityDialog extends DialogWrapper {

    private JLabel addLabel;
    private JLabel addProperty;
    private JPanel propertyContainer;
    private JPanel labelContainer;
    private JLabel nodeLabel;
    private JPanel container;
    private JPanel labelsPanel;

    private GraphEntity node;

    public EditEntityDialog(Project project, GraphEntity node) {
        super(project);
        this.node = node;

        boolean isCreateMode = node == null;
        boolean isNodeEdit = isCreateMode || node instanceof GraphNode;

        Disposer.register(project, myDisposable);
        init();

        setTitle(isCreateMode, isNodeEdit);

        addLabel.setIcon(AllIcons.General.Add);
        addLabel.setBorder(BorderFactory.createEmptyBorder());

        addProperty.setIcon(AllIcons.General.Add);
        addProperty.setBorder(BorderFactory.createEmptyBorder());

        if (isNodeEdit) {
            labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.Y_AXIS));
            if (!isCreateMode) {
                node.getTypes()
                        .forEach(type -> labelContainer.add(createRemovableInput(type, labelContainer)));
            }
            addLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    labelContainer.add(createRemovableInput(null, labelContainer));
                    labelContainer.revalidate();
                    validate();
                }
            });
        } else {
            labelContainer.setVisible(false);
            addLabel.setVisible(false);
            labelsPanel.setVisible(false);
        }

        propertyContainer.setLayout(new BoxLayout(propertyContainer, BoxLayout.Y_AXIS));
        if (!isCreateMode) {
            node.getPropertyContainer().getProperties()
                    .forEach((key, value) ->
                            propertyContainer.add(createRemovablePairInputs(key, value.toString(), propertyContainer)));
        }
        addProperty.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                propertyContainer.add(createRemovablePairInputs(null, null, propertyContainer));
                propertyContainer.revalidate();
                validate();
            }
        });
    }

    private void setTitle(boolean isCreateNode, boolean isEditNode) {
        if (isCreateNode) {
            setTitle("Create New Node");
            nodeLabel.setText("Node [?]");
        } else {
            setTitle(isEditNode ? "Edit Node" : "Edit relationship");
            nodeLabel.setText(node.getRepresentation());
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return container;
    }

    private Component createRemovableInput(String initialData, Container parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JTextField textField = new JTextField(initialData);
        textField.setMaximumSize(new Dimension(500, 30));
        panel.add(textField);

        JLabel remove = new JLabel(AllIcons.Actions.Close);
        remove.setBorder(BorderFactory.createEmptyBorder());
        panel.add(remove);

        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.remove(panel);
                parent.revalidate();
                validate();
                repaint();
            }
        });

        return panel;
    }

    private Component createRemovablePairInputs(String key, String value, Container parent) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JTextField keyTextField = new JTextField(key);
        keyTextField.setMaximumSize(new Dimension(250, 30));
        panel.add(keyTextField);

        JTextField valueTextField = new JTextField(value);
        valueTextField.setMaximumSize(new Dimension(250, 30));
        panel.add(valueTextField);

        JLabel remove = new JLabel(AllIcons.Actions.Close);
        remove.setBorder(BorderFactory.createEmptyBorder());
        panel.add(remove);

        remove.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parent.remove(panel);
                parent.revalidate();
                validate();
                repaint();
            }
        });

        return panel;
    }

    public GraphEntity getUpdatedEntity() {
        return new GraphEntity() {
            @Override
            public String getRepresentation() {
                return EditEntityDialog.this.node.getRepresentation();
            }

            @Override
            public String getId() {
                return EditEntityDialog.this.node.getId();
            }

            @Override
            public GraphPropertyContainer getPropertyContainer() {
                return () -> Stream.of(EditEntityDialog.this.propertyContainer.getComponents())
                        .map(Container.class::cast)
                        .map(cont -> new AbstractMap.SimpleEntry<>(
                                ((JTextField) cont.getComponent(0)).getText(),
                                ((JTextField) cont.getComponent(1)).getText()))
                        .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
            }

            @Override
            public List<String> getTypes() {
                return Stream.of(EditEntityDialog.this.labelContainer.getComponents())
                        .map(Container.class::cast)
                        .map(cont -> cont.getComponent(0))
                        .map(JTextField.class::cast)
                        .map(JTextField::getText)
                        .collect(toList());
            }

            @Override
            public String getTypesName() {
                return EditEntityDialog.this.node.getTypesName();
            }

            @Override
            public boolean isTypesSingle() {
                return EditEntityDialog.this.node.isTypesSingle();
            }
        };
    }
}
