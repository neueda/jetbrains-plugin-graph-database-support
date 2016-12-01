// This is a generated file. Not intended for manual editing.
package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface CypherPatternElement extends PsiElement {

    @Nullable
    CypherNodePattern getNodePattern();

    @Nullable
    CypherPatternElement getPatternElement();

    @NotNull
    List<CypherPatternElementChain> getPatternElementChainList();
}
