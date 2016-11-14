package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherLabelElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherProcedureElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherPropertyKeyElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherRelationshipTypeElement;

import java.util.ArrayList;
import java.util.List;

public class CypherMetadataContainer {

    private final List<CypherLabelElement> labels;
    private final List<CypherRelationshipTypeElement> relationshipTypes;
    private final List<CypherPropertyKeyElement> propertyKeys;
    private final List<CypherProcedureElement> procedures;

    public CypherMetadataContainer() {
        labels = new ArrayList<>();
        relationshipTypes = new ArrayList<>();
        propertyKeys = new ArrayList<>();
        procedures = new ArrayList<>();
    }

    public void addLabel(String label) {
        labels.add(new CypherLabelElement(label));
    }

    public void addRelationshipType(String relationshipType) {
        relationshipTypes.add(new CypherRelationshipTypeElement(relationshipType));
    }

    public void addPropertyKey(String propertyKey) {
        propertyKeys.add(new CypherPropertyKeyElement(propertyKey));
    }

    public void addProcedure(String procedureName, String procedureSignature) {
        procedures.add(new CypherProcedureElement(procedureName, procedureSignature));
    }

    public List<CypherLabelElement> getLabels() {
        return labels;
    }

    public List<CypherRelationshipTypeElement> getRelationshipTypes() {
        return relationshipTypes;
    }

    public List<CypherPropertyKeyElement> getPropertyKeys() {
        return propertyKeys;
    }

    public List<CypherProcedureElement> getProcedures() {
        return procedures;
    }
}
