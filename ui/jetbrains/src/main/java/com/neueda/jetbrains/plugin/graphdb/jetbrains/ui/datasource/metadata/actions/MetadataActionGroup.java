package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.metadata.actions;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.*;
import static com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons.Database.NEO4J;

public class MetadataActionGroup extends ActionGroup {

    private final String type;
    private final String data;
    private final String dataSourceUuid;

    public MetadataActionGroup(String type, String data, String dataSourceUuid) {
        this.type = type;
        this.data = data;
        this.dataSourceUuid = dataSourceUuid;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        switch (type) {
            case RELATIONSHIP_TYPES:
                return new AnAction[]{new MetadataRelationshipAction(data, dataSourceUuid, "Query this relationship", "", NEO4J)};
            case LABELS:
                return new AnAction[]{new MetadataLabelAction(data, dataSourceUuid, "Query this label", "", NEO4J)};
            case PROPERTY_KEYS:
                return new AnAction[]{new MetadataPropertyKeyAction(data, dataSourceUuid, "Query this property", "", NEO4J)};
            default:
                return new AnAction[]{};
        }
    }
}
