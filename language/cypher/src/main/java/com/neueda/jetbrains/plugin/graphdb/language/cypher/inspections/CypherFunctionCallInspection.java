package com.neueda.jetbrains.plugin.graphdb.language.cypher.inspections;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.util.Range;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherType;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherExpression;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionArguments;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherFunctionInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureArguments;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherSimpleType.NULL;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation.Argument;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

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
                    List<InvokableInformation> invokableList = ci.resolve();
                    if (invokableList.isEmpty()) {
                        return;
                    }

                    int arity = ci.arguments().size();
                    List<InvokableInformation> invokableByArity = invokableList.stream()
                        .filter(info -> info.getArity().isWithin(arity))
                        .collect(toList());

                    if (invokableByArity.isEmpty()) {
                        // use the first found for notification of error
                        Range<Integer> expectedArity = invokableList.stream().findFirst().get().getArity();
                        String msg = "%s arguments in call to %s";
                        if (arity < expectedArity.getFrom()) {
                            holder.registerProblem(ci.argumentsToken(), String.format(msg, "not enough",
                                ci.getFullName()));
                        } else if (arity > expectedArity.getTo()) {
                            holder.registerProblem(ci.argumentsToken(), String.format(msg, "too many",
                                ci.getFullName()));
                        }
                        return;
                    }
                    validateArgsTypes(invokableByArity, args);
                }
            }

            private void validateArgsTypes(List<InvokableInformation> invokableList, List<CypherExpression> actualArgs) {
                List<InvokableInformation> invokableArgTypeMismatches = newArrayList();
                List<InvokableInformation> invokableArgTypeMatches = newArrayList();
                invokableList.stream()
                    .filter(info -> info.getArguments().size() == actualArgs.size())
                    .forEach(target -> {
                        List<InvokableInformation.Argument> expectedArgs = target.getArguments();
                        boolean hasArgTypesMatch = true;
                        for (int i = 0; i < actualArgs.size(); i++) {
                            Argument expectedArg = expectedArgs.get(Math.min(expectedArgs.size() - 1, i));
                            if (hasArgTypeMismatch(expectedArg, actualArgs.get(i))) {
                                hasArgTypesMatch = false;
                                break;
                            }
                        }
                        if (hasArgTypesMatch) {
                            invokableArgTypeMatches.add(target);
                        } else {
                            invokableArgTypeMismatches.add(target);
                        }
                    });

                // use any found invokable for warning
                if (invokableArgTypeMatches.isEmpty()) {
                    invokableArgTypeMismatches.stream().findFirst()
                        .ifPresent(invokable -> {
                                List<InvokableInformation.Argument> expectedArgs = invokable.getArguments();
                                for (int i = 0; i < actualArgs.size(); i++) {
                                    Argument expectedArg = expectedArgs.get(Math.min(expectedArgs.size() - 1, i));
                                    CypherExpression ce = actualArgs.get(i);
                                    if (hasArgTypeMismatch(expectedArg, actualArgs.get(i))) {
                                        holder.registerProblem(ce, String.format("expected %s, got %s",
                                            expectedArg.typeString, ce.getType().toString()));
                                    }
                                }
                            }
                        );
                }
            }

            private boolean hasArgTypeMismatch(Argument expectedArg, CypherExpression actualExpression) {
                CypherType type = actualExpression.getType();
                return (type == NULL && !expectedArg.nullable) || !type.isAssignableTo(expectedArg.type);
            }
        };
    }
}
