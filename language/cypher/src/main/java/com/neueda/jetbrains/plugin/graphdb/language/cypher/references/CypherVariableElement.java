package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherNodePattern;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherPatternPart;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherRelationshipDetail;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTyped;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.*;

public interface CypherVariableElement extends CypherNamedElement, CypherTyped {

    @Nullable
    @Override
    default PsiElement getNameIdentifier() {
        return this;
    }

    @Override
    default CypherType getType() {
        return Optional.ofNullable(getReferences())
                .filter(r -> r.length > 0)
                .map(r -> r[0])
                .map(PsiReference::resolve)
                .map(PsiElement::getParent)
                .map(n -> {
                    if (n instanceof CypherNodePattern) {
                        return NODE;
                    } else if (n instanceof CypherPatternPart) {
                        return PATH;
                    } else if (n instanceof CypherRelationshipDetail) {
                        return RELATIONSHIP;
                    }

                    return null;
                })
                .orElse(ANY);
    }
}
