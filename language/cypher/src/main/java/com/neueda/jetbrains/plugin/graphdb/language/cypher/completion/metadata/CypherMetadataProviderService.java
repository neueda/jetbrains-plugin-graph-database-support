package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.*;

import java.util.List;
import java.util.Optional;

public interface CypherMetadataProviderService {

    void wipeAll();

    void wipeContainer(String sourceId);

    CypherMetadataContainer getContainer(String sourceId);

    List<CypherLabelElement> getLabels();

    List<CypherRelationshipTypeElement> getRelationshipTypes();

    List<CypherPropertyKeyElement> getPropertyKeys();

    List<CypherProcedureElement> getProcedures();

    List<CypherUserFunctionElement> getUserFunctions();

    Optional<CypherProcedureElement> findProcedure(String fullName);

    Optional<CypherUserFunctionElement> findUserFunction(String fullName);
}
