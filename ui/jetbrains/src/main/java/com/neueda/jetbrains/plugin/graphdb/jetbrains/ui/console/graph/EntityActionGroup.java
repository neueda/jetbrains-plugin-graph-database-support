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

    private final AnAction[] actions = new AnAction[1];

    public EntityActionGroup(DataSourceApi dataSourceApi, GraphEntity entity) {
        if (entity instanceof GraphNode) {
            actions[0] = new NodeEditAction("Edit node", "", null, dataSourceApi, (GraphNode) entity);
        } else if (entity instanceof GraphRelationship) {
            actions[0] = new RelationshipEditAction("Edit relationship", "", null, dataSourceApi, (GraphRelationship) entity);
        }
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        return actions;
    }
}
