package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;


import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionArguments;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureInvocation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface CypherInvocation extends PsiElement {

    default List<? extends PsiElement> arguments() {
        if (this instanceof CypherFunctionInvocation) {
            return Optional.ofNullable(((CypherFunctionInvocation) this).getFunctionArguments())
                    .map(CypherFunctionArguments::getExpressionList)
                    .orElse(Collections.emptyList());
        }
        if (this instanceof CypherProcedureInvocation) {
            return ((CypherProcedureInvocation) this).getProcedureArguments()
                    .getExpressionList();
        } else {
            return Collections.emptyList();
        }
    }

    default PsiElement argumentsToken() {
        if (this instanceof CypherFunctionInvocation) {
            return PsiTreeUtil.getChildOfType(this, CypherArgumentList.class);
        }
        if (this instanceof CypherProcedureInvocation) {
            return PsiTreeUtil.getChildOfType(this, CypherArgumentList.class);
        } else {
            return this;
        }
    }

    String getFullName();

}
