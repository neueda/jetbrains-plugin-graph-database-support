package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParametersService {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
            .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
            .configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);

    private static final JsonFactory FACTORY = MAPPER.getFactory();

    private ParametersProvider parametersProvider;

    public void registerParametersProvider(final ParametersProvider parametersProvider) {
        if (this.parametersProvider != null) {
            throw new IllegalStateException("Parameters provider already registered");
        }
        this.parametersProvider = parametersProvider;
    }

    public Map<String, Object> getParameters(String queryContent) throws Exception {
        if (!isValidParametersMap(parametersProvider.getParametersJson())) {
            return Collections.emptyMap();
        }

        Map<String, Object> allParameters = MAPPER
                .readValue(parametersProvider.getParametersJson(), new TypeReference<Map<String, Object>>() { });

        return extractQueryParameters(queryContent, allParameters);
    }

    private Map<String, Object> extractQueryParameters(String query, Map<String, Object> allParameters) {
        if (StringUtils.isBlank(query)) {
            return Collections.emptyMap();
        }

        List<String> parameterNames = extractParameterNames(query);
        if (parameterNames.isEmpty()) {
            return Collections.emptyMap();
        }

        return allParameters.entrySet().stream()
                .filter(entry -> parameterNames.contains(entry.getKey()))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
    }

    private List<String> extractParameterNames(String query) {
        return Stream.of(query.split("\\s")) // split by whitespace character
                .filter(w -> w.startsWith("$"))
                .map(w -> w.substring(1))
                .collect(Collectors.toList());
    }

    private static boolean isValidParametersMap(String parametersJson) {
        try {
            if (parametersJson == null || StringUtils.isBlank(parametersJson)) {
                return false;
            }

            JsonParser parser = FACTORY.createParser(parametersJson);
            JsonNode node = MAPPER.readTree(parser);
            if (node == null) {
                return false;
            }
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }

        return true;
    }

}
