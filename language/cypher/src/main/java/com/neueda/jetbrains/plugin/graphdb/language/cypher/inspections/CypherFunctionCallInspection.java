package com.neueda.jetbrains.plugin.graphdb.language.cypher.inspections;

import com.google.common.collect.ImmutableMap;
import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.codeInspection.LocalInspectionToolSession;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.Range;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.*;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.InvokableInformation.Argument;
import static com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes.*;
import static java.util.Collections.emptyList;

public class CypherFunctionCallInspection extends LocalInspectionTool {

    private static final Pattern TYPE_CLEAN = Pattern.compile("(K_|_LITERAL|l_|SIGNED_)");

    private static final Map<String, IElementType[]> EXCLUSION_MAP =
            ImmutableMap.<String, IElementType[]>builder()
                    .put("STRING", new IElementType[]{K_NULL, K_TRUE, K_FALSE, DOUBLE_LITERAL, SIGNED_INTEGER_LITERAL})
                    .put("STRING?", new IElementType[]{K_TRUE, K_FALSE, DOUBLE_LITERAL, SIGNED_INTEGER_LITERAL})
                    .put("INTEGER", new IElementType[]{K_NULL, K_TRUE, K_FALSE, DOUBLE_LITERAL, STRING_LITERAL})
                    .put("INTEGER?", new IElementType[]{K_TRUE, K_FALSE, DOUBLE_LITERAL, STRING_LITERAL})
                    .put("NUMBER", new IElementType[]{K_NULL, K_TRUE, K_FALSE, STRING_LITERAL})
                    .put("NUMBER?", new IElementType[]{K_TRUE, K_FALSE, STRING_LITERAL})
                    .put("FLOAT", new IElementType[]{K_NULL, K_TRUE, K_FALSE, STRING_LITERAL, SIGNED_INTEGER_LITERAL})
                    .put("FLOAT?", new IElementType[]{K_TRUE, K_FALSE, STRING_LITERAL, SIGNED_INTEGER_LITERAL})
                    .put("BOOLEAN", new IElementType[]{K_NULL, STRING_LITERAL, DOUBLE_LITERAL, SIGNED_INTEGER_LITERAL})
                    .put("BOOLEAN?", new IElementType[]{STRING_LITERAL, DOUBLE_LITERAL, SIGNED_INTEGER_LITERAL})
                    .build();

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
                        ASTNode[] astNodes = ce.getNode().getChildren(null);
                        if (astNodes.length != 1) {
                            continue;
                        }

                        IElementType type = getElementType(astNodes);
                        IElementType[] exclusions = EXCLUSION_MAP.get(expectedArg.type);
                        if (exclusions == null) {
                            continue;
                        }

                        if (Stream.of(exclusions).anyMatch(t -> Objects.equals(t, type))) {
                            holder.registerProblem(ce,
                                    String.format("expected %s, got %s",
                                            expectedArg.type,
                                            TYPE_CLEAN.matcher(type.toString()).replaceAll("")));
                        }
                    }

                }
            }

            @NotNull
            private IElementType getElementType(ASTNode[] astNodes) {
                IElementType type = astNodes[0].getElementType();
                if (type == NUMBER_LITERAL) {
                    return astNodes[0].getFirstChildNode().getElementType();
                }
                return type;
            }
        };
    }
}
