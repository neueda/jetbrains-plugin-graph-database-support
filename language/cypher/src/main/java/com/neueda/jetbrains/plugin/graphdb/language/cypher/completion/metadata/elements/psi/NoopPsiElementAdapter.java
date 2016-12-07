package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements.psi;

import javax.swing.*;

import org.jetbrains.annotations.*;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;

public abstract class NoopPsiElementAdapter implements PsiElement {

    private final String text;
    private final Project project;

    public NoopPsiElementAdapter(String text, Project project) {
        this.text = text;
        this.project = project;
    }

    @NotNull
    @Override
    public Project getProject() throws PsiInvalidElementAccessException {
        return project;
    }

    @NotNull
    @Override
    public Language getLanguage() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiManager getManager() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiElement[] getChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement getParent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement getFirstChild() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement getLastChild() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement getNextSibling() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement getPrevSibling() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextRange getTextRange() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getStartOffsetInParent() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTextLength() {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public PsiElement findElementAt(final int offset) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public PsiReference findReferenceAt(final int offset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTextOffset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getText() {
        return text;
    }

    @NotNull
    @Override
    public char[] textToCharArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement getNavigationElement() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement getOriginalElement() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean textMatches(@NotNull @NonNls final CharSequence text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean textMatches(@NotNull final PsiElement element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean textContains(final char c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void accept(@NotNull final PsiElementVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void acceptChildren(@NotNull final PsiElementVisitor visitor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement copy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement add(@NotNull final PsiElement element) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement addBefore(@NotNull final PsiElement element, @Nullable final PsiElement anchor) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement addAfter(@NotNull final PsiElement element, @Nullable final PsiElement anchor) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkAdd(@NotNull final PsiElement element) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement addRange(final PsiElement first, final PsiElement last) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement addRangeBefore(@NotNull final PsiElement first, @NotNull final PsiElement last, final PsiElement anchor)
               throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement addRangeAfter(final PsiElement first, final PsiElement last, final PsiElement anchor) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete() throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void checkDelete() throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteChildRange(final PsiElement first, final PsiElement last) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public PsiElement replace(@NotNull final PsiElement newElement) throws IncorrectOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean isWritable() {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public PsiReference getReference() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public PsiReference[] getReferences() {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public <T> T getCopyableUserData(final Key<T> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void putCopyableUserData(final Key<T> key, @Nullable final T value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean processDeclarations(@NotNull final PsiScopeProcessor processor, @NotNull final ResolveState state,
                                       @Nullable final PsiElement lastParent,
                                       @NotNull final PsiElement place) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public PsiElement getContext() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPhysical() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public GlobalSearchScope getResolveScope() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public SearchScope getUseScope() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ASTNode getNode() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean isEquivalentTo(final PsiElement another) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Icon getIcon(@IconFlags final int flags) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    @Override
    public <T> T getUserData(@NotNull final Key<T> key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void putUserData(@NotNull final Key<T> key, @Nullable final T value) {
        throw new UnsupportedOperationException();
    }
}
