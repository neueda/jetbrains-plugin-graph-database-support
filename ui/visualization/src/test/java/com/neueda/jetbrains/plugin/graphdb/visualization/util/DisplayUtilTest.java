package com.neueda.jetbrains.plugin.graphdb.visualization.util;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPropertyContainer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.neueda.jetbrains.plugin.graphdb.visualization.util.DisplayUtil.getDisplayProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DisplayUtilTest {

    @Mock
    private GraphNode node;

    @Mock
    private GraphPropertyContainer container;

    private Map<String, Object> properties;
    private List<String> types;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        properties = new HashMap<>();
        types = newArrayList();

        when(node.getPropertyContainer()).thenReturn(container);
        when(container.getProperties()).thenReturn(properties);
        when(node.getTypes()).thenReturn(types);
    }

    @Test
    public void exactMatchName() {
        properties.put("name", "node name");
        properties.put("another", "another");

        assertThat(getDisplayProperty(node))
            .isEqualTo("node name");
    }

    @Test
    public void exactMatchTitle() {
        properties.put("title", "node title");
        properties.put("another", "another");

        assertThat(getDisplayProperty(node))
                .isEqualTo("node title");
    }

    @Test
    public void fuzzyMatchName() {
        properties.put("somename", "node name");

        assertThat(getDisplayProperty(node))
                .isEqualTo("node name");
    }

    @Test
    public void exactOverFuzzy() {
        properties.put("name", "node name");
        properties.put("somename", "another name");

        assertThat(getDisplayProperty(node))
                .isEqualTo("node name");
    }

    @Test
    public void anyString() {
        properties.put("any", "some value");

        assertThat(getDisplayProperty(node))
                .isEqualTo("some value");
    }

    @Test
    public void defaultType() {
        assertThat(DisplayUtil.getDisplayType(node))
                .isEqualTo("");

    }

    @Test
    public void singleType() {
        types.add("type");

        assertThat(DisplayUtil.getDisplayType(node))
                .isEqualTo("type");

    }

    @Test
    public void firstType() {
        types.add("first");
        types.add("second");

        assertThat(DisplayUtil.getDisplayType(node))
                .isEqualTo("first");

    }



}