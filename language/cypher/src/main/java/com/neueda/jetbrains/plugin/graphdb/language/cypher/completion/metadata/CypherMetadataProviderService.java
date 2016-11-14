package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherLabelElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherProcedureElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherPropertyKeyElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherRelationshipTypeElement;

import java.util.List;

public interface CypherMetadataProviderService {

    void wipeContainer(String sourceId);

    CypherMetadataContainer getContainer(String sourceId);

    List<CypherLabelElement> getLabels();

    List<CypherRelationshipTypeElement> getRelationshipTypes();

    List<CypherPropertyKeyElement> getPropertyKeys();

    List<CypherProcedureElement> getProcedures();
}
