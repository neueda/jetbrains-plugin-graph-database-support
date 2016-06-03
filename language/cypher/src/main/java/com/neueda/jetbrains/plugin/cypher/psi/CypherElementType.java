package com.neueda.jetbrains.plugin.cypher.psi;

import com.intellij.psi.tree.IElementType;
import com.neueda.jetbrains.plugin.cypher.CypherLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * TODO: Description
 *
 * @author dmitry@vrublesvky.me
 */
public class CypherElementType extends IElementType {

    public CypherElementType(@NotNull @NonNls String debugName) {
        super(debugName, CypherLanguage.INSTANCE);
    }
}
