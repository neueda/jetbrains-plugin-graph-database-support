package com.neueda.jetbrains.plugin.graphdb.language.cypher.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceService;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.references.CypherNamedElement;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Description
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherPsiImplUtil {

    /*------------------
     * Common
     */

    public static String getName(CypherNamedElement element) {
        return element.getText();
    }

    public static PsiElement getNameIdentifier(CypherNamedElement element) {
        return element;
    }

    @NotNull
    public static PsiReference[] getReferences(CypherNamedElement element) {
        return PsiReferenceService.getService().getContributedReferences(element);
    }

    /*------------------
     * Specifics
     */

    public static CypherVariable setName(CypherVariable element, String newName) {
        CypherVariable newCypherIdentifier = CypherElementFactory
                .createIdentifierNode(element.getProject(), newName);
        safelyReplaceSymbolicName(element, newCypherIdentifier);
        return element;
    }

    public static CypherLabelName setName(CypherLabelName element, String newName) {
        CypherLabelName newCypherLabelName = CypherElementFactory
                .createLabelNameNode(element.getProject(), newName);
        safelyReplaceSymbolicName(element, newCypherLabelName);
        return element;
    }

    public static CypherRelTypeName setName(CypherRelTypeName element, String newName) {
        CypherRelTypeName newRelTypeNameNode = CypherElementFactory
                .createRelTypeNameNode(element.getProject(), newName);
        safelyReplaceSymbolicName(element, newRelTypeNameNode);
        return element;
    }

    public static CypherPropertyKeyName setName(CypherPropertyKeyName element, String newName) {
        CypherPropertyKeyName newRelTypeNameNode = CypherElementFactory
                .createPropertyKeyNameNode(element.getProject(), newName);
        safelyReplaceSymbolicName(element, newRelTypeNameNode);
        return element;
    }

    public static String getFullName(CypherFunctionInvocation element) {
        return element.getFunctionInvocationBody().getText();
    }

    public static String getFullName(CypherProcedureInvocation element) {
        return element.getProcedureInvocationBody().getText();
    }

    public static String getFullName(CypherExtractFunctionInvocation element) {
        return "extract";
    }

    public static String getFullName(CypherAllFunctionInvocation element) {
        return "all";
    }

    public static String getFullName(CypherAnyFunctionInvocation element) {
        return "any";
    }

    public static String getFullName(CypherFilterFunctionInvocation element) {
        return "filter";
    }

    public static String getFullName(CypherReduceFunctionInvocation element) {
        return "reduce";
    }

    public static String getFullName(CypherNoneFunctionInvocation element) {
        return "none";
    }

    public static String getFullName(CypherSingleFunctionInvocation element) {
        return "single";
    }

    public static String getFullName(CypherExistsFunctionInvocation element) {
        return "exists";
    }

    public static String getParameterName(CypherParameter element) {
        if (element.getNewParameter() != null) {
            CypherNewParameter newParameter = element.getNewParameter();
            if (newParameter.getSymbolicNameString() != null) {
                return newParameter.getSymbolicNameString().getUnescapedSymbolicNameString().getText();
            }
            if (newParameter.getUnsignedInteger() != null) {
                return newParameter.getUnsignedInteger().getText();
            }
        }

        if (element.getOldParameter() != null) {
            CypherOldParameter oldParameter = element.getOldParameter();
            if (oldParameter.getSymbolicNameString() != null) {
                return oldParameter.getSymbolicNameString().getUnescapedSymbolicNameString().getText();
            }
            if (oldParameter.getUnsignedInteger() != null) {
                return oldParameter.getUnsignedInteger().getText();
            }
        }

        return "graphbdb-plugin-invalid-parameter-name-should-never-happen";
    }

    /**
     * Safely replaces symbolic name node with new node.
     * It's possible that nothing happens if newElement is invalid. No error thrown in such case.
     * <p>
     * TODO: handle cases with invalid newElement (e.g. cyrilic name is set)
     */
    private static void safelyReplaceSymbolicName(CypherNamedElement element,
                                                  CypherNamedElement newElement) {
        ASTNode oldNameNode = element.getNode().findChildByType(CypherTypes.SYMBOLIC_NAME_STRING);
        if (oldNameNode != null) {
            if (newElement != null) {
                ASTNode newNameNode = newElement
                        .getNode()
                        .findChildByType(CypherTypes.SYMBOLIC_NAME_STRING);
                if (newNameNode != null) {
                    element.getNode().replaceChild(oldNameNode, newNameNode);
                }
            }
        }
    }
}
