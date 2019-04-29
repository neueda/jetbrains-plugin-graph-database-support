package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms;

import com.google.common.base.Enums;
import org.jetbrains.annotations.NotNull;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.*;

public interface CypherType {

    static CypherType parse(@NotNull String type) {
        if (type.startsWith("LIST")) {
            if (!type.contains("OF")) {
                return CypherList.of(ANY);
            }
            String param = type.split("OF")[1]
                    .replaceAll("\\?", "")
                    .trim();

            return CypherList.of(Enums.getIfPresent(CypherSimpleType.class, param).or(ANY));
        }

        return Enums.getIfPresent(CypherSimpleType.class, type.replaceAll("\\?", "").trim())
                .or(ANY);
    }

    default boolean isAssignableTo(CypherType t) {
        if (this == NULL || t == ANY || this == ANY || this.equals(t)) {
            return true;
        }

        if ((t == NUMBER || t == FLOAT) && (this == INTEGER || this == FLOAT)) {
            return true;
        }

        return this instanceof CypherList && t instanceof CypherList;
    }

}
