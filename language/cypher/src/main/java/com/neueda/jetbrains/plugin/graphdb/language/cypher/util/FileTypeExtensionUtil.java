package com.neueda.jetbrains.plugin.graphdb.language.cypher.util;

import java.util.ArrayList;
import java.util.List;

public class FileTypeExtensionUtil {

    public static final List<String> EXTENSIONS = new ArrayList<String>() {{
        add("cyp");
        add("cql");
        add("cypher");
    }};

    public static boolean isCypherFileTypeExtension(String extension) {
        return extension != null && EXTENSIONS.contains(extension);
    }

}
