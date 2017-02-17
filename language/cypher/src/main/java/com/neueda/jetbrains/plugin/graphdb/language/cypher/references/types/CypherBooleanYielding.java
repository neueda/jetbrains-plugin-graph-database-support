package com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.BOOLEAN;

public interface CypherBooleanYielding extends CypherTyped {

    @Override
    default CypherType getType() {
        return BOOLEAN;
    }

}
