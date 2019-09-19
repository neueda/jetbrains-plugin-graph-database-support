package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.tinkerpop.gremlin;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.util.Validation.*;
import static org.apache.tinkerpop.gremlin.driver.ser.Serializers.*;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.EnumComboBoxModel;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.GremlinFlavor;
import com.neueda.jetbrains.plugin.graphdb.database.opencypher.gremlin.OpenCypherGremlinConfiguration;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.LandingPageAction;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourceType;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.DataSourcesComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.DataSourceDialog;

import javax.swing.*;
import org.apache.commons.lang.StringUtils;
import org.apache.tinkerpop.gremlin.driver.ser.Serializers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class OpenCypherGremlinDataSourceDialog extends DataSourceDialog {

    private final DataSourcesComponent dataSourcesComponent;
    private DataSourceApi dataSourceToEdit;

    private String dataSourceName;
    private OpenCypherGremlinConfiguration configuration = new OpenCypherGremlinConfiguration();

    private JPanel content;
    private JBTextField dataSourceNameField;
    private JBTextField hostField;
    private JBTextField userField;
    private JComboBox<GremlinFlavor> flavorField;
    private JComboBox<Serializers> serializerField;
    private JBPasswordField passwordField;
    private JBTextField portField;
    private JButton testConnectionButton;
    private JCheckBox useSSLCheckBox;
    private JCheckBox optimizeTranslatedQueriesCheckBox;

    public OpenCypherGremlinDataSourceDialog(Project project, DataSourcesView dataSourcesView, DataSourceApi dataSourceToEdit) {
        this(project, dataSourcesView);
        this.dataSourceToEdit = dataSourceToEdit;
    }

    public OpenCypherGremlinDataSourceDialog(Project project, DataSourcesView dataSourcesView) {
        super(project, dataSourcesView);
        flavorField.setModel(new EnumComboBoxModel<>(GremlinFlavor.class));
        serializerField.setModel(new EnumComboBoxModel<>(Serializers.class));
        serializerField.setSelectedItem(GRAPHSON_V3D0);
        dataSourcesComponent = dataSourcesView.getComponent();
        testConnectionButton.addActionListener(e -> this.validationPopup());
        optimizeTranslatedQueriesCheckBox.addActionListener(e -> {
            LandingPageAction.open();
            optimizeTranslatedQueriesCheckBox.setSelected(false);
        });
        initValidation();
    }

    @NotNull
    private FocusListener validationListener() {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                validate();
            }
        };
    }

    @NotNull
    @Override
    protected List<ValidationInfo> doValidateAll() {
        List<ValidationInfo> validations = new ArrayList<>();

        if (StringUtils.isBlank(dataSourceNameField.getText())) {
            validations.add(validation("Data source name must not be empty", dataSourceNameField));
        }
        if (StringUtils.isBlank(hostField.getText())) {
            validations.add(validation("Host must not be empty", hostField));
        }
        if (!StringUtils.isNumeric(portField.getText())) {
            validations.add(validation("Port must be numeric", portField));
        }

        if (hostField.getText().endsWith("cosmos.azure.com") || GremlinFlavor.COSMOSDB.equals(flavorField.getSelectedItem())) {
            if (!useSSLCheckBox.isSelected()) {
                validations.add(warning("SSL is recommended for Cosmos DB", useSSLCheckBox));
            }

            if (!"443".equals(portField.getText())) {
                validations.add(warning("443 is recommended for Cosmos DB", portField));
            }

            if (!GRAPHSON_V2D0.equals(serializerField.getSelectedItem())) {
                validations.add(warning("GRAPHSON_V2D0 is recommended for Cosmos DB", serializerField));
            }

            if (!GremlinFlavor.COSMOSDB.equals(flavorField.getSelectedItem())) {
                validations.add(warning("Cosmos DB flavor is recommended for Cosmos DB", flavorField));
            }
        }

        extractData();

        if (dataSourcesComponent.getDataSourceContainer().isDataSourceExists(dataSourceName)) {
            if (!(dataSourceToEdit != null && dataSourceToEdit.getName().equals(dataSourceName))) {
                validations.add(validation(String.format("Data source [%s] already exists", dataSourceName), dataSourceNameField));
            }
        }

        return validations;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        if (dataSourceToEdit != null) {
            OpenCypherGremlinConfiguration config = new OpenCypherGremlinConfiguration(dataSourceToEdit.getConfiguration());

            dataSourceNameField.setText(dataSourceToEdit.getName());
            hostField.setText(config.getHost());
            portField.setText(config.getPort().toString());
            userField.setText(config.getUser());
            passwordField.setText(config.getPassword());
            flavorField.setSelectedItem(config.getFlavor());
            serializerField.setSelectedItem(config.getSerializer());
            useSSLCheckBox.setSelected(config.getSSL());
        }
        return content;
    }

    @Override
    public DataSourceApi constructDataSource() {
        extractData();

        return dataSourcesComponent.getDataSourceContainer().createDataSource(
            dataSourceToEdit,
            DataSourceType.OPENCYPHER_GREMLIN,
            dataSourceName,
            configuration.getConfiguration()
        );
    }

    private void extractData() {
        dataSourceName = dataSourceNameField.getText();
        configuration.setHost(hostField.getText());
        configuration.setPort(portField.getText());
        configuration.setUser(userField.getText());
        configuration.setPassword(String.valueOf(passwordField.getPassword()));
        configuration.setFlavor((GremlinFlavor) flavorField.getSelectedItem());
        configuration.setSerializer((Serializers) serializerField.getSelectedItem());
        configuration.setSSL(useSSLCheckBox.isSelected());
    }
}
