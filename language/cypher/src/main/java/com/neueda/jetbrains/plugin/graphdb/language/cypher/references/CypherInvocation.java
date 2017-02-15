package com.neueda.jetbrains.plugin.graphdb.language.cypher.references;


import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherBuiltInFunctions;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherBuiltInFunctionElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherProcedureElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherUserFunctionElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionArguments;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureInvocation;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    default Optional<InvokableInformation> resolve() {
        CypherMetadataProviderService svc = ServiceManager.getService(
                getProject(),
                CypherMetadataProviderService.class);

        if (this instanceof CypherProcedureInvocation) {
            return svc.findProcedure(getFullName())
                    .map(CypherProcedureElement::getInvokableInformation);
        }

        return CypherBuiltInFunctions.FUNCTIONS.stream()
                .filter(f -> Objects.equals(f.getInvokable().getName(), getFullName()))
                .findFirst()
                .map(CypherBuiltInFunctionElement::getInvokable)
                .map(Optional::of)
                .orElseGet(() -> svc.findUserFunction(getFullName())
                        .map(CypherUserFunctionElement::getInvokableInformation));
    }

    String getFullName();

}
