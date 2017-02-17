package com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types;

import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.ANY;

public interface CypherTypePropagator extends CypherTyped {

    @Override
    default CypherType getType() {
        if (getChildren().length > 1) {
            return ANY;
        }

        PsiElement child = getFirstChild();
        if (child instanceof CypherTyped) {
            return ((CypherTyped) child).getType();
        }
        return ANY;
    }

}
