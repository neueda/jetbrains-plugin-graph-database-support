package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherParenthesizedExpression;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTypePropagator;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTyped;

import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.ANY;

public interface CypherParenthesized extends CypherTyped {

    @Override
    default CypherType getType() {
        if (this instanceof CypherParenthesizedExpression) {
            return ANY;
        }

        return Optional.of(this)
                .map(CypherParenthesizedExpression.class::cast)
                .map(CypherParenthesizedExpression::getExpression)
                .map(CypherTypePropagator::getType)
                .orElse(ANY);
    }

}
