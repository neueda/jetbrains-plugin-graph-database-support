package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTyped;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.ANY;

public interface CypherParenthesized extends CypherTyped {

    @Override
    default CypherType getType() {
        PsiElement expression = getChildren()[1];
        if (expression instanceof CypherTyped) {
            return ((CypherTyped)expression).getType();
        }

        return ANY;
    }

}
