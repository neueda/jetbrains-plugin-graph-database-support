package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion;

import com.intellij.codeInsight.lookup.LookupElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CypherMetadataContainer {

    private final Map<CypherMetadataType, List<LookupElement>> data;

    public CypherMetadataContainer(String sourceId) {
        data = new HashMap<>();

        for (CypherMetadataType cypherMetadataType : CypherMetadataType.values()) {
            data.put(cypherMetadataType, new ArrayList<>());
        }
    }

    public List<LookupElement> getElements(CypherMetadataType metadataType) {
        return data.get(metadataType);
    }

    public void addElements(CypherMetadataType metadataType, List<LookupElement> elements) {
        data.put(metadataType, elements);
    }
}
