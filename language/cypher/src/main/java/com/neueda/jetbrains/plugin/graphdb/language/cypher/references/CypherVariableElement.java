package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherList;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherMaybeVariableLength;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherNodePattern;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherPatternPart;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherRelationshipDetail;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.types.CypherTyped;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.*;
import static java.util.Objects.nonNull;

public interface CypherVariableElement extends CypherNamedElement, CypherTyped {

    @Nullable
    @Override
    default PsiElement getNameIdentifier() {
        return this;
    }

    @Override
    default CypherType getType() {
        return Optional.ofNullable(getReferences())
                .filter(reference -> reference.length > 0)
                .map(reference -> reference[0])
                .map(PsiReference::resolve)
                .map(PsiElement::getParent)
                .map(node -> {
                    if (node instanceof CypherNodePattern) {
                        return NODE;
                    } else if (node instanceof CypherPatternPart) {
                        return PATH;
                    } else if (node instanceof CypherRelationshipDetail) {
                        return resolveRelationshipType((CypherRelationshipDetail) node);
                    }

                    return null;
                })
                .orElse(ANY);
    }

    default CypherType resolveRelationshipType(CypherRelationshipDetail relationshipDetail) {
        CypherMaybeVariableLength maybeVariableLength = relationshipDetail.getMaybeVariableLength();
        return nonNull(maybeVariableLength) ? CypherList.of(RELATIONSHIP) : RELATIONSHIP;
    }
}
