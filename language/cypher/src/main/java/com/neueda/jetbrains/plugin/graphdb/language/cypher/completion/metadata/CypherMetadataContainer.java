package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CypherMetadataContainer {

    private final List<CypherLabelElement> labels;
    private final List<CypherRelationshipTypeElement> relationshipTypes;
    private final List<CypherPropertyKeyElement> propertyKeys;
    private final List<CypherProcedureElement> procedures;
    private final List<CypherUserFunctionElement> userFunctions;

    public CypherMetadataContainer() {
        labels = new ArrayList<>();
        relationshipTypes = new ArrayList<>();
        propertyKeys = new ArrayList<>();
        procedures = new ArrayList<>();
        userFunctions = new ArrayList<>();
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

    public void addProcedure(String procedureName, String procedureSignature, @Nullable String procedureDescription) {
        procedures.add(new CypherProcedureElement(procedureName, procedureSignature, procedureDescription));
    }

    public void addUserFunction(String userFunctionName, String userFunctionSignature, @Nullable String userFunctionDescription) {
        userFunctions.add(new CypherUserFunctionElement(userFunctionName, userFunctionSignature, userFunctionDescription));
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

    public List<CypherUserFunctionElement> getUserFunctions() {
        return userFunctions;
    }
}
