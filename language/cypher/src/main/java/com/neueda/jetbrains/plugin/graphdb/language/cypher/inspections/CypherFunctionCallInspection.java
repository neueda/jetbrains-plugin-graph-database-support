package com.neueda.jetbrains.plugin.graphdb.language.cypher.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.Range;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.NULL;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation.Argument;
import static java.util.Collections.emptyList;

public class CypherFunctionCallInspection extends LocalInspectionTool {

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull ProblemsHolder holder,
                                          boolean isOnTheFly,
                                          @NotNull LocalInspectionToolSession session) {
        return new PsiElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                super.visitElement(element);
                if (element instanceof CypherInvocation) {
                    CypherInvocation ci = (CypherInvocation) element;

                    List<CypherExpression> args;
                    if (ci instanceof CypherFunctionInvocation) {
                        args = Optional.of(ci)
                                .map(CypherFunctionInvocation.class::cast)
                                .map(CypherFunctionInvocation::getFunctionArguments)
                                .map(CypherFunctionArguments::getExpressionList)
                                .orElse(emptyList());
                    } else if (ci instanceof CypherProcedureInvocation) {
                        args = Optional.of(ci)
                                .map(CypherProcedureInvocation.class::cast)
                                .map(CypherProcedureInvocation::getProcedureArguments)
                                .map(CypherProcedureArguments::getExpressionList)
                                .orElse(emptyList());
                    } else {
                        // Skip functions built into grammar, since they are checked by parser
                        return;
                    }

                    // If function can not be resolved, we can not continue inspection
                    InvokableInformation invokable = ci.resolve().orElse(null);
                    if (invokable == null) {
                        return;
                    }

                    int arity = ci.arguments().size();
                    Range<Integer> expectedArity = invokable.getArity();

                    if (!expectedArity.isWithin(arity)) {
                        String tail = " arguments in call to " + ci.getFullName();
                        if (arity < expectedArity.getFrom()) {
                            holder.registerProblem(ci.argumentsToken(), "not enough" + tail);
                        } else if (arity > expectedArity.getTo()) {
                            holder.registerProblem(ci.argumentsToken(), "too many" + tail);
                        }

                        return;
                    }

                    // Check basic types
                    List<InvokableInformation.Argument> expectedArgs = invokable.getArguments();

                    for (int i = 0; i < args.size(); i++) {
                        Argument expectedArg = expectedArgs.get(Math.min(expectedArgs.size() - 1, i));

                        CypherExpression ce = args.get(i);
                        CypherType type = ce.getType();

                        if ((type == NULL && !expectedArg.nullable) || !type.isAssignableTo(expectedArg.type)) {
                            holder.registerProblem(ce, String.format("expected %s, got %s",
                                    expectedArg.typeString, type.toString()));
                        }
                    }

                }
            }

        };
    }
}
