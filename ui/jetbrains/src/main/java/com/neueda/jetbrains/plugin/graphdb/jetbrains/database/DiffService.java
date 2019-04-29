package com.neueda.jetbrains.plugin.graphdb.jetbrains.database;

import com.google.common.collect.ImmutableMap;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.actions.execute.ExecuteQueryPayload;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.datasource.state.DataSourceApi;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class DiffService {

    private final QueryExecutionService service;

    public DiffService(Project project) {
        this.service = new QueryExecutionService(project.getMessageBus());
    }

    public void updateNode(DataSourceApi api, GraphEntity oldNode, GraphEntity newNode) {

        String query = "MATCH (n) WHERE ID(n) = $id " +
                diffLabels(oldNode.getTypes(), newNode.getTypes()) +
                " SET n = $props" +
                " RETURN n";
        service.executeQuery(api, new ExecuteQueryPayload(query,
                ImmutableMap.of(
                        "id", Long.parseLong(oldNode.getId()),
                        "props", newNode.getPropertyContainer().getProperties()),
                null));
    }


    public void updateRelationShip(DataSourceApi api, GraphEntity relationship,
                                   GraphEntity updatedRel) {

        String query = "MATCH ()-[n]->() WHERE ID(n) = $id " +
                " SET n = $props" +
                " RETURN n";
        service.executeQuery(api, new ExecuteQueryPayload(query,
                ImmutableMap.of(
                        "id", Long.parseLong(relationship.getId()),
                        "props", updatedRel.getPropertyContainer().getProperties()),
                null));
    }

    public void saveNewNode(DataSourceApi api, GraphEntity newNode) {
        StringBuilder sb = new StringBuilder("CREATE (n ");
        newNode.getTypes().forEach(newLabel -> sb.append(":").append(newLabel));
        sb.append(" $props) RETURN n");

        service.executeQuery(api, new ExecuteQueryPayload(sb.toString(),
                ImmutableMap.of("props", newNode.getPropertyContainer().getProperties()),
                null));
    }

    private String diffLabels(List<String> originalTypes, List<String> newTypes) {
        StringBuilder sb = new StringBuilder();
        if (!newTypes.isEmpty()) {
            sb.append(" SET n ");
            newTypes.forEach(newLabel -> sb.append(":").append(newLabel));
        }

        List<String> deletedLabels = getDeletedLabels(originalTypes, newTypes);
        if (!deletedLabels.isEmpty()) {
            sb.append(" REMOVE n");
            deletedLabels.forEach(label -> sb.append(":").append(label));
        }

        return sb.toString();
    }

    private List<String> getDeletedLabels(Collection<String> before, Collection<String> after) {
        return before.stream()
                .filter(label -> !after.contains(label))
                .collect(toList());
    }
}
