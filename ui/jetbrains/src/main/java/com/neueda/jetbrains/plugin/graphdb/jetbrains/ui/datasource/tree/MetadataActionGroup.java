package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.tree;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.platform.GraphIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.LABELS;
import static com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.metadata.Neo4jBoltCypherDataSourceMetadata.RELATIONSHIP_TYPES;

public class MetadataActionGroup extends ActionGroup {

    private final String type;
    private final String label;
    private final String dataSourceUuid;

    public MetadataActionGroup(String type, String label, String dataSourceUuid){
        this.type = type;
        this.label = label;
        this.dataSourceUuid = dataSourceUuid;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        switch (type) {
            case RELATIONSHIP_TYPES:
                return new AnAction[]{new MetadataLabelAction(label, dataSourceUuid, "Query this relationship", "", GraphIcons.Database.NEO4J)};
            case LABELS:
                return new AnAction[]{new MetadataLabelAction(label, dataSourceUuid, "Query this label", "", GraphIcons.Database.NEO4J)};
            default:
                return new AnAction[]{};
        }
    }

}

