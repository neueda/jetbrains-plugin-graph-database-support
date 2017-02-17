package com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.FLOAT;

public interface CypherFloatYielding extends CypherTyped {

    @Override
    default CypherType getType() {
        return FLOAT;
    }

}
