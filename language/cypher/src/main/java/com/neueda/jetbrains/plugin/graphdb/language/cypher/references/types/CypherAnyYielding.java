package com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types;

import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.ANY;

public interface CypherAnyYielding extends CypherTyped {

    @Override
    default CypherType getType() {
        return ANY;
    }

}
