package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.helpers;

import com.intellij.ui.treeStructure.PatchedDefaultMutableTreeNode;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UiHelperTest {

    @Test
    public void nullValueToTreeNode() throws Exception {
        PatchedDefaultMutableTreeNode treeNode = UiHelper.keyValueToTreeNode("key", "VALUE", null, null);
        assertThat(treeNode).isNotNull();
    }
}
