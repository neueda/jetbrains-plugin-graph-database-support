package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;

public class CypherKeywordElement implements CypherElement {

    private final String keyword;

    public CypherKeywordElement(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public LookupElement getLookupElement() {
        return LookupElementBuilder
                        .create(keyword)
                        .bold();
    }
}
