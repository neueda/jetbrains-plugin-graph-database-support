package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import java.util.Arrays;
import java.util.List;

public class FileTypeExtensionUtil {

    public static final List<String> EXTENSIONS = Arrays.asList("cyp", "cql", "cypher");

    public static boolean isCypherFileTypeExtension(String extension) {
        return extension != null && EXTENSIONS.contains(extension);
    }

}
