package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import icons.GraphIcons;

public class CypherRelationshipTypeElement implements CypherElement {

    private final String relationshipType;

    public CypherRelationshipTypeElement(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder.create(relationshipType)
                .withIcon(GraphIcons.Nodes.RELATIONSHIP_TYPE)
                .withTypeText("relationship type");
    }

    public String getName() {
        return relationshipType;
    }
}
