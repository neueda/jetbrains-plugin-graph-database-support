package com.neueda.jetbrains.plugin.graphdb.language.cypher.completion.metadata.elements;

import com.intellij.openapi.util.text.StringUtil;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface CypherElementWithSignature {
    Pattern FULL_SIGNATURE_REGEXP = Pattern.compile("^(\\([^)]*\\)) :: \\(?([^)]*)\\)?$");

    InvokableInformation getInvokableInformation();

    default InvokableInformation extractInformation(String fullSignature, String name) {
        return new InvokableInformation(fullSignature, name);
    }

    final class InvokableInformation {
        private final String name;
        private final String signature;
        private final String returnType;
        private final boolean hasParameters;

        public InvokableInformation(String fullSignature, String name) {
            String signatureWithoutName = StringUtil.trimStart(fullSignature, name);
            Matcher m = FULL_SIGNATURE_REGEXP.matcher(signatureWithoutName);
            if (m.find()) {
                signature = m.group(1);
                returnType = m.group(2);
            } else {
                // should never happen, unless Neo4j changes signature syntax
                signature = fullSignature;
                returnType = "<?>";
            }

            this.name = name;
            this.hasParameters = !this.signature.startsWith("()");
        }

        public InvokableInformation(String name, String signature, String returnType) {
            this.name = name;
            this.signature = signature;
            this.returnType = returnType;
            this.hasParameters = !Objects.equals(signature, "()");
        }

        public String getName() {
            return name;
        }

        public String getSignature() {
            return signature;
        }

        public String getReturnType() {
            return returnType;
        }

        public boolean hasParameters() {
            return hasParameters;
        }
    }
}
