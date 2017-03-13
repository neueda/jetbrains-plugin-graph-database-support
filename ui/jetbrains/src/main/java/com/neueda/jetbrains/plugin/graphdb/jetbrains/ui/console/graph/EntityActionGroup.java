package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.graph;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphRelationship;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityActionGroup extends ActionGroup {

    private AnAction[] actions;

    public EntityActionGroup(DataSourceApi dataSourceApi, GraphEntity entity) {
        if (entity instanceof GraphNode) {
            actions = new AnAction[] {
                    new NodeEditAction("Edit node", "", null, dataSourceApi, (GraphNode) entity),
                    new NodeDeleteAction("Delete node", "", null, dataSourceApi, (GraphNode) entity),
            };
        } else if (entity instanceof GraphRelationship) {
            actions = new AnAction[] {
                    new RelationshipEditAction("Edit relationship", "", null, dataSourceApi, (GraphRelationship) entity),
                    new RelationshipDeleteAction("Delete relationship", "", null, dataSourceApi, (GraphRelationship) entity)
            };
        }
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        return actions;
    }
}
