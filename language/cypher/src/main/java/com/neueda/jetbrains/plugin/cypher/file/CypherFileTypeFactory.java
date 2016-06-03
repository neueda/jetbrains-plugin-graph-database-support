package com.neueda.jetbrains.plugin.cypher.file;

import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Associate Cypher file with extension.
 *
 * @author dmitry@vrublevsky.me
 */
public class CypherFileTypeFactory extends FileTypeFactory {

    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(CypherFileType.INSTANCE, "cyp;cypher;cql");
    }
}
