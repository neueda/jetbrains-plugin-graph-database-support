package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CypherMetadataProviderServiceImpl implements CypherMetadataProviderService {

    private final Map<String, CypherMetadataContainer> sourceData;

    public CypherMetadataProviderServiceImpl() {
        sourceData = new HashMap<>();
    }

    @Override
    public void wipeAll() {
        sourceData.clear();
    }

    @Override
    public void wipeContainer(String sourceId) {
        sourceData.remove(sourceId);
    }

    @Override
    public CypherMetadataContainer getContainer(String sourceId) {
        if (!sourceData.containsKey(sourceId)) {
            sourceData.put(sourceId, new CypherMetadataContainer());
        }
        return sourceData.get(sourceId);
    }

    @Override
    public List<CypherLabelElement> getLabels() {
        List<CypherLabelElement> collect = sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getLabels().stream())
                .collect(Collectors.toList());
//        collect.add(new CypherLabelElement("Testlabel"));
//        collect.add(new CypherLabelElement("Testlabel2"));
        return collect;
    }

    @Override
    public List<CypherRelationshipTypeElement> getRelationshipTypes() {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getRelationshipTypes().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<CypherPropertyKeyElement> getPropertyKeys() {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getPropertyKeys().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<CypherProcedureElement> getProcedures() {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getProcedures().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<CypherUserFunctionElement> getUserFunctions() {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getUserFunctions().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CypherLabelElement> findLabel(String labelName) {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getLabels().stream())
                .filter((labelElement) -> labelElement.getName().equals(labelName))
                .findFirst();
    }

    @Override
    public Optional<CypherRelationshipTypeElement> findRelationshipType(String relationshipTypeName) {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getRelationshipTypes().stream())
                .filter((relationshipTypeElement) -> relationshipTypeElement.getName().equals(relationshipTypeName))
                .findFirst();
    }

    @Override
    public Optional<CypherPropertyKeyElement> findPropertyKey(String propertyKeyName) {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getPropertyKeys().stream())
                .filter((propertyKeyElement) -> propertyKeyElement.getName().equals(propertyKeyName))
                .findFirst();
    }

    @Override
    public Optional<CypherProcedureElement> findProcedure(String name) {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getProcedures().stream())
                .filter((procedureElement) -> procedureElement.getName().equals(name))
                .findFirst();
    }

    @Override
    public Optional<CypherUserFunctionElement> findUserFunction(String name) {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getUserFunctions().stream())
                .filter((userFunctionElement) -> userFunctionElement.getName().equals(name))
                .findFirst();
    }
}
