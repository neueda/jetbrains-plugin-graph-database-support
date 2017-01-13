package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParametersService {

    private ParametersProvider parametersProvider;
    public static final ObjectMapper MAPPER = configureMapper();
    public static final JsonFactory FACTORY = MAPPER.getJsonFactory();

    public void registerParametersProvider(final ParametersProvider parametersProvider) {
        if (this.parametersProvider != null) {
            throw new IllegalStateException("Parameters provider already registered");
        }
        this.parametersProvider = parametersProvider;
    }

    public Map<String, Object> getParameters() throws Exception {
        if (parametersProvider == null || StringUtils.isBlank(parametersProvider.getParametersJson())) {
            return Collections.emptyMap();
        }

        JsonParser parser = FACTORY.createJsonParser(parametersProvider.getParametersJson());
        JsonNode node = MAPPER.readTree(parser);
        return mapFirstLevelChildren(node);
    }

    private static ObjectMapper configureMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        return mapper;
    }

    private static Map<String, Object> mapFirstLevelChildren(JsonNode node) {
        if (node == null) {
            return Collections.emptyMap();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        Iterator<Map.Entry<String, JsonNode>> iterator = node.getFields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            JsonNode value = entry.getValue();
            result.put(entry.getKey(), value != null && value.isTextual() ? value.getTextValue() : value);
        }
        return result;
    }

}
