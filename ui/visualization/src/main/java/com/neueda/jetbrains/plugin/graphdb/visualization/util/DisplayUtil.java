package com.neueda.jetbrains.plugin.graphdb.visualization.util;

import com.neueda.jetbrains.plugin.graphdb.database.api.data.GraphNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.unmodifiableList;

public class DisplayUtil {

    private static final int TITLE_MAX_LENGTH = 80;
    private static final List<String> TITLE_INDICATORS = unmodifiableList(newArrayList("name", "title"));

    public static String getDisplayProperty(GraphNode node) {
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

    public static String getDisplayType(GraphNode node) {
        return node.getTypes().size() > 0 ? node.getTypes().get(0) : "";
    }

    private static boolean filterLength(String title) {
        return title.length() < TITLE_MAX_LENGTH;
    }
}
