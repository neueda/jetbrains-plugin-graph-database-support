package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.util;

import com.intellij.codeInsight.documentation.DocumentationManager;
import com.intellij.lang.documentation.DocumentationProvider;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class BaseDocumentationTest extends BaseGenericTest {

    public void configure(String query) {
        myFixture.configureByText("test.cyp", query);
    }

    public void verify(String expectedDocumentation) {
        assertThat(verify()).isEqualTo(expectedDocumentation);
    }

    public String verify() {
        Editor editor = myFixture.getEditor();
        PsiFile file = myFixture.getFile();

        PsiElement originalElement = file.findElementAt(editor.getCaretModel().getOffset());
        PsiElement element = DocumentationManager.getInstance(getProject()).findTargetElement(editor, file);

        assertThat(originalElement).isNotNull();
        assertThat(element).isNotNull();

        DocumentationProvider documentationProvider = DocumentationManager.getProviderFromElement(originalElement);

        return documentationProvider.generateDoc(element, originalElement);
    }
}
