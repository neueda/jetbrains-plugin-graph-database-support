package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.DataSourcesView;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.neo4j.bolt.Neo4jBoltDataSourceDialog;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.datasource.interactions.tinkerpop.gremlin.OpenCypherGremlinDataSourceDialog;
import icons.GraphIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NewDataSourceActionGroup extends ActionGroup {

    private final Project project;
    private final DataSourcesView dataSourcesView;

    public NewDataSourceActionGroup(Project project, DataSourcesView dataSourcesView) {
        this.project = project;
        this.dataSourcesView = dataSourcesView;
    }

    @NotNull
    @Override
    public AnAction[] getChildren(@Nullable AnActionEvent e) {
        NewDataSourceAction neo4jBoltDataSource = new NewDataSourceAction(
                dataSourcesView, new Neo4jBoltDataSourceDialog(project, dataSourcesView),
                "Neo4j - Bolt", "Create Neo4j 3.0+ Bolt data source", GraphIcons.Database.NEO4J);

        NewDataSourceAction gremlinDataSource = new NewDataSourceAction(
                dataSourcesView, new OpenCypherGremlinDataSourceDialog(project, dataSourcesView),
                "openCypher - Gremlin", "Connect to Gremlin Data Source and " +
            "translate Cypher to Gremlin", GraphIcons.Database.OPENCYPHER);

        return new AnAction[]{neo4jBoltDataSource, gremlinDataSource};
    }
}
