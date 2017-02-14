package com.neueda.jetbrains.plugin.graphdb.language.cypher.editor;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.parameterInfo.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.containers.ContainerUtil;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.atoms.CypherBuiltInFunctions;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherBuiltInFunctionElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherElementWithSignature.InvokableInformation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherProcedureElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.CypherUserFunctionElement;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherProcedureInvocation;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.psi.CypherTypes;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherInvocation;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class CypherParameterInfoHandler
        implements ParameterInfoHandlerWithTabActionSupport<CypherInvocation, CypherInvocation, PsiElement> {

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public PsiElement[] getActualParameters(@NotNull CypherInvocation o) {
        return ArrayUtil.toObjectArray((List) o.arguments(), PsiElement.class);
    }

    @NotNull
    @Override
    public IElementType getActualParameterDelimiterType() {
        return CypherTypes.OP_COMMA;
    }

    @NotNull
    @Override
    public IElementType getActualParametersRBraceType() {
        return CypherTypes.PARENTHESE_CLOSE;
    }

    @NotNull
    @Override
    public Set<Class> getArgumentListAllowedParentClasses() {
        return ContainerUtil.newHashSet();
    }

    @NotNull
    @Override
    public Set<? extends Class> getArgListStopSearchClasses() {
        return ContainerUtil.newHashSet();
    }

    @NotNull
    @Override
    public Class<CypherInvocation> getArgumentListClass() {
        return CypherInvocation.class;
    }

    @Override
    public boolean couldShowInLookup() {
        return true;
    }

    @Nullable
    @Override
    public Object[] getParametersForLookup(LookupElement item, ParameterInfoContext context) {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Nullable
    @Override
    public Object[] getParametersForDocumentation(CypherInvocation p, ParameterInfoContext context) {
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }

    @Nullable
    @Override
    public CypherInvocation findElementForParameterInfo(@NotNull CreateParameterInfoContext context) {
        PsiElement at = context.getFile().findElementAt(context.getOffset());
        return PsiTreeUtil.getParentOfType(at, CypherInvocation.class);
    }

    @Override
    public void showParameterInfo(@NotNull CypherInvocation ci, @NotNull CreateParameterInfoContext context) {
        context.setItemsToShow(new Object[]{ci});
        context.showHint(ci, ci.getTextRange().getStartOffset(), this);
    }

    @Nullable
    @Override
    public CypherInvocation findElementForUpdatingParameterInfo(@NotNull UpdateParameterInfoContext context) {
        PsiElement at = context.getFile().findElementAt(context.getOffset());
        return PsiTreeUtil.getParentOfType(at, CypherInvocation.class);
    }

    @Override
    public void updateParameterInfo(@NotNull CypherInvocation o, @NotNull UpdateParameterInfoContext context) {
        context.setCurrentParameter(
                ParameterInfoUtils.getCurrentParameterIndex(
                        o.argumentsToken().getNode(),
                        context.getOffset(),
                        CypherTypes.OP_COMMA));
    }

    @Nullable
    @Override
    public String getParameterCloseChars() {
        return ",(";
    }

    @Override
    public boolean tracksParameterIndex() {
        return true;
    }


    @Override
    public void updateUI(CypherInvocation ci, @NotNull ParameterInfoUIContext context) {
        getPresentation(ci, context);
    }

    public String getPresentation(CypherInvocation ci, @NotNull ParameterInfoUIContext context) {
        if (ci == null) {
            context.setUIComponentEnabled(false);
            return null;
        }

        String signature;
        if (ci instanceof CypherProcedureInvocation) {
            signature = getMetadataService(ci).findProcedure(ci.getFullName())
                    .map(CypherProcedureElement::getInvokableInformation)
                    .map(InvokableInformation::getSignature)
                    .orElse(null);
        } else {
            signature = CypherBuiltInFunctions.FUNCTIONS.stream()
                    .filter(f -> Objects.equals(f.getInvokable().getName(), ci.getFullName()))
                    .findFirst()
                    .map(CypherBuiltInFunctionElement::getInvokable)
                    .map(Optional::of)
                    .orElseGet(() -> getMetadataService(ci).findUserFunction(ci.getFullName())
                            .map(CypherUserFunctionElement::getInvokableInformation))
                    .map(InvokableInformation::getSignature)
                    .orElse(null);
        }

        int current = context.getCurrentParameterIndex();

        if (signature == null || Objects.equals(signature, "()")) {
            context.setUIComponentEnabled(false);
            return null;
        }

        String stripped = signature.substring(1, signature.length() - 1) + ",";
        int from = StringUtils.ordinalIndexOf(stripped, ",", current) + 2;
        int to = StringUtils.ordinalIndexOf(stripped, ",", current + 1) + 1;

        return context.setupUIComponentPresentation(
                signature,
                from, to,
                false, false, false,
                context.getDefaultParameterColor());
    }

    private CypherMetadataProviderService getMetadataService(PsiElement element) {
        return ServiceManager.getService(element.getProject(), CypherMetadataProviderService.class);
    }

}
