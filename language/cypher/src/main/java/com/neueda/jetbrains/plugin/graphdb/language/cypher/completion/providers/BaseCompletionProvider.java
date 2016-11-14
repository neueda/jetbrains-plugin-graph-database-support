package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.providers;

import com.intellij.codeInsight.completion.CompletionParameters;
import com.intellij.codeInsight.completion.CompletionProvider;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.CypherMetadataProviderService;

public abstract class BaseCompletionProvider extends CompletionProvider<CompletionParameters> {

    protected void withCypherMetadataProvider(CompletionParameters parameters, ProjectRunnable runnable) {
        Project project = parameters.getEditor().getProject();
        if (project != null) {
            CypherMetadataProviderService provider = ServiceManager.getService(project, CypherMetadataProviderService.class);
            runnable.run(provider);
        }
    }

    public interface ProjectRunnable {
        void run(CypherMetadataProviderService metadataProvider);
    }
}
