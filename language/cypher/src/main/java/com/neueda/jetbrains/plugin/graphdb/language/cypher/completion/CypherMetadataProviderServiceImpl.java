package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import com.intellij.codeInsight.lookup.LookupElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CypherMetadataProviderServiceImpl implements CypherMetadataProviderService {

    private final Map<String, CypherMetadataContainer> sourceData;

    public CypherMetadataProviderServiceImpl() {
        sourceData = new HashMap<>();
    }

    @Override
    public List<LookupElement> getMetadata(CypherMetadataType metadataType) {
        return sourceData.entrySet().stream()
                .map(Map.Entry::getValue)
                .flatMap((container) -> container.getElements(metadataType).stream())
                .collect(Collectors.toList());
    }

    @Override
    public void registerMetadata(String sourceId, CypherMetadataType metadataType, List<LookupElement> lookupElements) {
        synchronized (sourceData) {
            CypherMetadataContainer container = sourceData.get(sourceId);
            if (container == null) {
                container = new CypherMetadataContainer(sourceId);
                sourceData.put(sourceId, container);
            }

            container.addElements(metadataType, lookupElements);
        }
    }

    @Override
    public void removeMetadata(String sourceId) {
        synchronized (sourceData) {
            sourceData.remove(sourceId);
        }
    }
}
