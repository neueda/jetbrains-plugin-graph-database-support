package com.neueda.jetbrains.plugin.graphdb.visualization.util;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphPropertyContainer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;
import static com.neueda.jetbrains.plugin.graphdb.visualization.util.DisplayUtil.getProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SuppressWarnings("Duplicates")
public class DisplayUtilTest {

    private static final String LONG_TITLE = "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde"
            + "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde";

    private static final String FIRST_KEY = "first key";
    private static final String SECOND_KEY = "second key";
    private static final String THIRD_KEY = "third key";
    private static final String FOURTH_KEY = "fourth key";
    private static final String FIRST_VALUE = "first value";
    private static final String SECOND_VALUE = "second value";
    private static final String THIRD_VALUE = "third value";
    private static final String FOURTH_VALUE = "fourth value";
    private static final String NAME = "name";
    private static final String TITLE = "title";
    private static final String CONTAINS_NAME = "somename";
    private static final int THIRD_VALUE_INT = 3;
    public static final String FIRST_TYPE = "first type";
    public static final String SECOND_TYPE = "second type";

    @Mock
    private GraphNode node;

    @Mock
    private GraphPropertyContainer container;

    private Map<String, Object> properties;
    private List<String> types;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        properties = newLinkedHashMap();
        types = newArrayList();

        when(node.getPropertyContainer()).thenReturn(container);
        when(container.getProperties()).thenReturn(properties);
        when(node.getTypes()).thenReturn(types);
    }

    @Test
    public void exactMatchName() {
        properties.put(NAME, FIRST_VALUE);
        properties.put(SECOND_KEY, SECOND_VALUE);

        assertThat(getProperty(node))
            .isEqualTo(FIRST_VALUE);
    }

    @Test
    public void exactMatchTitle() {
        properties.put(TITLE, FIRST_VALUE);
        properties.put(SECOND_KEY, SECOND_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(FIRST_VALUE);
    }

    @Test
    public void fuzzyMatchName() {
        properties.put(FIRST_KEY, FIRST_VALUE);
        properties.put(CONTAINS_NAME, SECOND_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(SECOND_VALUE);
    }

    @Test
    public void exactOverFuzzy() {
        properties.put(NAME, FIRST_VALUE);
        properties.put(CONTAINS_NAME, SECOND_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(FIRST_VALUE);
    }

    @Test
    public void anyString() {
        properties.put(FIRST_KEY, FIRST_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(FIRST_VALUE);
    }

    @Test
    public void lengthCapFuzzy() {
        properties.put(NAME, LONG_TITLE);
        properties.put(CONTAINS_NAME, SECOND_VALUE);
        properties.put(THIRD_KEY, THIRD_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(SECOND_VALUE);
    }

    @Test
    public void lengthCapDefault() {
        properties.put(NAME, LONG_TITLE);
        properties.put(SECOND_KEY, SECOND_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(SECOND_VALUE);
    }

    @Test
    public void defaultType() {
        assertThat(DisplayUtil.getType(node))
                .isEqualTo("");

    }

    @Test
    public void singleType() {
        types.add(FIRST_KEY);

        assertThat(DisplayUtil.getType(node))
                .isEqualTo(FIRST_KEY);

    }

    @Test
    public void firstType() {
        types.add(FIRST_KEY);
        types.add(SECOND_KEY);

        assertThat(DisplayUtil.getType(node))
                .isEqualTo(FIRST_KEY);
    }

    @Test
    public void tooltip() {
        properties.put(FIRST_KEY, FIRST_VALUE);

        assertThat(DisplayUtil.getTooltipText(node))
                .containsSubsequence(FIRST_KEY, FIRST_VALUE);
    }

    @Test
    public void tooltipNoMoreThanThreeProperties() {
        properties.put(FIRST_KEY, FIRST_VALUE);
        properties.put(SECOND_KEY, SECOND_VALUE);
        properties.put(THIRD_KEY, THIRD_VALUE);
        properties.put(FOURTH_KEY, FOURTH_VALUE);

        assertThat(DisplayUtil.getTooltipText(node))
                .containsSubsequence(FIRST_KEY, FIRST_VALUE,
                        SECOND_KEY, SECOND_VALUE,
                        THIRD_KEY, THIRD_VALUE);
    }

    @Test
    public void tooltipPrioritizeStrings() {
        properties.put(FIRST_KEY, FIRST_VALUE);
        properties.put(SECOND_KEY, SECOND_VALUE);
        properties.put(THIRD_KEY, THIRD_VALUE_INT);
        properties.put(FOURTH_KEY, FOURTH_VALUE);

        assertThat(DisplayUtil.getTooltipText(node))
                .containsSubsequence(FIRST_KEY, FIRST_VALUE,
                        SECOND_KEY, SECOND_VALUE,
                        FOURTH_KEY, FOURTH_VALUE);
    }

    @Test
    public void tooltipType() {
        types.add(FIRST_TYPE);

        assertThat(DisplayUtil.getTooltipText(node))
                .contains(types.toString());
    }

    @Test
    public void tooltipTypes() {
        types.add(FIRST_TYPE);
        types.add(SECOND_TYPE);

        assertThat(DisplayUtil.getTooltipText(node))
                .contains(types.toString());
    }

    @Test
    public void tooltipTypesAndProperties() {
        types.add(FIRST_TYPE);
        types.add(SECOND_TYPE);
        properties.put(FIRST_KEY, FIRST_VALUE);
        properties.put(SECOND_KEY, SECOND_VALUE);
        properties.put(THIRD_KEY, THIRD_VALUE);
        properties.put(FOURTH_KEY, FOURTH_VALUE);

        assertThat(DisplayUtil.getTooltipText(node))
                .containsSubsequence(types.toString(),
                        FIRST_KEY, FIRST_VALUE,
                        SECOND_KEY, SECOND_VALUE,
                        THIRD_KEY, THIRD_VALUE);
    }
}
