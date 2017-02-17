package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import java.util.regex.Pattern;

public interface CypherElementWithSignature {
    Pattern FULL_SIGNATURE_REGEXP = Pattern.compile("^(\\([^)]*\\)) :: \\(?([^)]*)\\)?$");

    InvokableInformation getInvokableInformation();

}
