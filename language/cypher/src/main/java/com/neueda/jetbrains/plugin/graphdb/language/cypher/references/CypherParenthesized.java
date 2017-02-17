package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherParenthesizedExpression;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTypePropagator;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTyped;

import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.ANY;

public interface CypherParenthesized extends CypherTyped {

    @Override
    default CypherType getType() {
        return Optional.of(this)
                .filter(CypherParenthesizedExpression.class::isInstance)
                .map(CypherParenthesizedExpression.class::cast)
                .map(CypherParenthesizedExpression::getExpression)
                .map(CypherTypePropagator::getType)
                .orElse(ANY);
    }

}
