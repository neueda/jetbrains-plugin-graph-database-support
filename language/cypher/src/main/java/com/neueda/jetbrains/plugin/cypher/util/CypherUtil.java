package com.neueda.jetbrains.plugin.cypher.util;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.indexing.FileBasedIndex;
import com.neueda.jetbrains.plugin.cypher.file.CypherFile;
import com.neueda.jetbrains.plugin.cypher.file.CypherFileType;
import com.neueda.jetbrains.plugin.cypher.references.CypherNamedElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Utility method for finding PSI elements.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherUtil {

    private static final Filter INCLUDE_ALL = (element) -> true;

    public static <T extends CypherNamedElement> List<T> findAll(Project project, IElementType elementType) {
        return findAllAndFilter(project, elementType, INCLUDE_ALL);
    }

    public static <T extends CypherNamedElement> List<T> findAll(PsiFile file, IElementType elementType) {
        return findAllAndFilter(file, elementType, INCLUDE_ALL);
    }

    public static <T extends CypherNamedElement> List<T> findAllByName(Project project, IElementType elementType, String name) {
        return findAllAndFilter(project, elementType, (element) -> name.equals(element.getName()));
    }

    public static <T extends CypherNamedElement> List<T> findAllByName(PsiFile file, IElementType elementType, String name) {
        return findAllAndFilter(file, elementType, (element) -> name.equals(element.getName()));
    }

    public static <T extends CypherNamedElement> List<T> findAllAndFilter(Project project, IElementType elementType, Filter filter) {
        Collection<VirtualFile> virtualFiles = FileBasedIndex.getInstance()
                .getContainingFiles(FileTypeIndex.NAME, CypherFileType.INSTANCE, GlobalSearchScope.allScope(project));

        List<T> result = new ArrayList<>();
        for (VirtualFile virtualFile : virtualFiles) {
            CypherFile cypherFile = (CypherFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (cypherFile != null) {
                List<T> found = findAllAndFilter(cypherFile, elementType, filter);
                result.addAll(found);
            }
        }

        return result;
    }

    public static <T extends CypherNamedElement> List<T> findAllAndFilter(PsiFile file, IElementType elementType, Filter filter) {
        List<T> sameElements = null;
        List<T> candidates = TraverseUtil.collectPsiElementsByType(file, elementType);

        for (T candidate : candidates) {
            if (filter.filter(candidate)) {
                if (sameElements == null) {
                    sameElements = new ArrayList<>();
                }
                sameElements.add(candidate);
            }
        }

        return sameElements != null ? sameElements : Collections.emptyList();

    }

    public interface Filter {
        boolean filter(CypherNamedElement element);
    }
}
