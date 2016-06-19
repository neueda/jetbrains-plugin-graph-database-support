package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import com.intellij.codeInsight.lookup.LookupElement;

import java.util.List;

public interface CypherMetadataProviderService {

    List<LookupElement> getMetadata(CypherMetadataType metadataType);

    void registerMetadata(String sourceId, CypherMetadataType metadataType, List<LookupElement> lookupElements);

    void removeMetadata(String sourceId);
}
