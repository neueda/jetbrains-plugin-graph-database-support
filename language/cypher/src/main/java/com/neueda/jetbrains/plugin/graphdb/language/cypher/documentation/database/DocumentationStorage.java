package com.neueda.jetbrains.plugin.graphdb.language.cypher.documentation.database;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.intellij.openapi.diagnostic.Logger;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentationStorage {

    private static final Logger LOG = Logger.getInstance(DocumentationStorage.class);
    private final String documentationDir;
    private final List<String> names;
    private Map<String, String> cache;

    public DocumentationStorage(String documentationDir, List<String> names) {
        this.documentationDir = documentationDir;
        this.names = names;

    }

    public Optional<String> lookup(String name) {
        initialize();
        return Optional.ofNullable(cache.get(name.toLowerCase()));
    }

    private synchronized void initialize() {
        if (cache != null) {
            return;
        }

        cache = new ConcurrentHashMap<>();
        for (String name : names) {
            String filePath = documentationDir + "/" + name + ".html";
            try {
                URL documentationFile = DocumentationStorage.class.getResource(filePath);
                String documentation = Resources.toString(documentationFile, Charsets.UTF_8);

                cache.put(name.toLowerCase(), documentation);
            } catch (Exception e) {
                LOG.error("Unable to load " + filePath + " documentation", e);
            }
        }
    }
}
