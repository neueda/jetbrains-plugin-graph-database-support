package com.neueda.jetbrains.plugin.graphdb.visualization.util;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphEntity;
import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.unmodifiableList;

public class DisplayUtil {

    private static final int MAX_TOOLTIP_PROPERTIES = 3;
    private static final int LABEL_TEXT_WIDTH = 300;
    private static final int MAX_TITLE_LENGTH = 40;
    private static final int MAX_TEXT_LENGTH = 60;

    private static final List<String> TITLE_INDICATORS = unmodifiableList(newArrayList("name", "title"));
    private static final Predicate<Map.Entry<String, Object>> IS_STRING_VALUE = o -> String.class.isAssignableFrom(o.getValue().getClass());

    public static String getProperty(GraphNode node) {
        Optional<String> backup = Optional.empty();
        Optional<String> fuzzyMatch = Optional.empty();
        for (Map.Entry<String, Object> entry : node.getPropertyContainer().getProperties().entrySet()) {
            Object valueObj = entry.getValue();
            if (valueObj instanceof String) {
                String key = entry.getKey();
                String value = (String) valueObj;

                for (String titleIndicator : TITLE_INDICATORS) {
                    if (titleIndicator.equals(key) && filterLength(value))
                        return value;

                    if (key.contains(titleIndicator) && !fuzzyMatch.isPresent())
                        fuzzyMatch = Optional.of(value)
                                .filter(DisplayUtil::filterLength);
                }

                if (!backup.isPresent())
                    backup = Optional.of(value)
                            .filter(DisplayUtil::filterLength);
            }
        }

        return fuzzyMatch.orElse(backup.orElse(node.getId()));
    }

    public static String getType(GraphNode node) {
        return node.getTypes().size() > 0 ? node.getTypes().get(0) : "";
    }

    private static boolean filterLength(String title) {
        return title.length() < MAX_TITLE_LENGTH;
    }

    public static String getTooltipText(GraphEntity entity) {
        Map<String, Object> properties = entity.getPropertyContainer().getProperties();
        StringBuilder sb = new StringBuilder();


        Stream<Map.Entry<String, Object>> strings = properties.entrySet().stream()
                .filter(IS_STRING_VALUE)
                .limit(MAX_TOOLTIP_PROPERTIES);
        Stream<Map.Entry<String, Object>> other = properties.entrySet().stream()
                .filter(IS_STRING_VALUE.negate())
                .limit(MAX_TOOLTIP_PROPERTIES);

        Stream.concat(strings, other)
                .limit(MAX_TOOLTIP_PROPERTIES)
                .forEach(entry -> sb
                        .append("<p width=\"" + LABEL_TEXT_WIDTH + "px\"><b>")
                        .append(entry.getKey())
                        .append("</b>: ")
                        .append(truncate(entry.getValue().toString(), MAX_TEXT_LENGTH))
                        .append("</p>"));

        return "<html>" + sb.toString() + "</html>";
    }

    private static String truncate(String text, int length) {
        return text.length() > length ? text.substring(0, length - 1) + "..." : text;
    }
}
