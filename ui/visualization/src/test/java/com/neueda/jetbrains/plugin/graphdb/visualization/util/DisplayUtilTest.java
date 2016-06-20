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

    private static final String FIRST_NAME = "first";
    private static final String SECOND_NAME = "second";
    private static final String THIRD_NAME = "third";
    private static final String FOURTH_NAME = "fourth";
    private static final String FIRST_VALUE = "first value";
    private static final String SECOND_VALUE = "second value";
    private static final String THIRD_VALUE = "third value";
    private static final String FOURTH_VALUE = "fourth value";
    private static final String NAME = "name";
    private static final String TITLE = "title";
    private static final String CONTAINS_NAME = "somename";
    private static final int THIRD_VALUE_INT = 3;

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
        properties.put(SECOND_NAME, SECOND_VALUE);

        assertThat(getProperty(node))
            .isEqualTo(FIRST_VALUE);
    }

    @Test
    public void exactMatchTitle() {
        properties.put(TITLE, FIRST_VALUE);
        properties.put(SECOND_NAME, SECOND_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(FIRST_VALUE);
    }

    @Test
    public void fuzzyMatchName() {
        properties.put(FIRST_NAME, FIRST_VALUE);
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
        properties.put(FIRST_NAME, FIRST_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(FIRST_VALUE);
    }

    @Test
    public void lengthCapFuzzy() {
        properties.put(NAME, LONG_TITLE);
        properties.put(CONTAINS_NAME, SECOND_VALUE);
        properties.put(THIRD_NAME, THIRD_VALUE);

        assertThat(getProperty(node))
                .isEqualTo(SECOND_VALUE);
    }

    @Test
    public void lengthCapDefault() {
        properties.put(NAME, LONG_TITLE);
        properties.put(SECOND_NAME, SECOND_VALUE);

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
        types.add(FIRST_NAME);

        assertThat(DisplayUtil.getType(node))
                .isEqualTo(FIRST_NAME);

    }

    @Test
    public void firstType() {
        types.add(FIRST_NAME);
        types.add(SECOND_NAME);

        assertThat(DisplayUtil.getType(node))
                .isEqualTo(FIRST_NAME);
    }

    @Test
    public void tooltip() {
        properties.put(FIRST_NAME, FIRST_VALUE);

        assertThat(DisplayUtil.getTooltipText(node))
                .containsSequence(FIRST_NAME, FIRST_VALUE);
    }

    @Test
    public void tooltipNoMoreThanThreeProperties() {
        properties.put(FIRST_NAME, FIRST_VALUE);
        properties.put(SECOND_NAME, SECOND_VALUE);
        properties.put(THIRD_NAME, THIRD_VALUE);
        properties.put(FOURTH_NAME, FOURTH_VALUE);

        assertThat(DisplayUtil.getTooltipText(node))
                .containsSequence(FIRST_NAME, FIRST_VALUE,
                        SECOND_NAME, SECOND_VALUE,
                        THIRD_NAME, THIRD_VALUE);
    }

    @Test
    public void tooltipPrioritizeStrings() {
        properties.put(FIRST_NAME, FIRST_VALUE);
        properties.put(SECOND_NAME, SECOND_VALUE);
        properties.put(THIRD_NAME, THIRD_VALUE_INT);
        properties.put(FOURTH_NAME, FOURTH_VALUE);

        assertThat(DisplayUtil.getTooltipText(node))
                .containsSequence(FIRST_NAME, FIRST_VALUE,
                        SECOND_NAME, SECOND_VALUE,
                        FOURTH_NAME, FOURTH_VALUE);
    }

}