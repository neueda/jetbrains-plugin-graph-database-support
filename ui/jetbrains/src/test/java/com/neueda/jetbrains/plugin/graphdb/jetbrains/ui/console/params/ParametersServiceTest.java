package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ParametersServiceTest {

    private class TestParametersProvider implements ParametersProvider {

        String parametersJson;

        @Override
        public String getParametersJson() {
            return parametersJson;
        }

        public void setParametersJson(String parametersJson) {
            this.parametersJson = parametersJson;
        }
    }

    private ParametersService parametersService = new ParametersService();
    private TestParametersProvider parametersProvider = new TestParametersProvider();

    @Before
    public void setup() {
        parametersService.registerParametersProvider(parametersProvider);
    }

    @Test
    public void testParsingEmptyJsonObject() throws Exception {
        parametersProvider.setParametersJson("{}");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void testParsingEmptyParameters() throws Exception {
        parametersProvider.setParametersJson("");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    public void testParsingStringParameter() throws Exception {
        parametersProvider.setParametersJson("{\"name\": \"Anna\"}");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.keySet().contains("name")).isTrue();
        assertThat(result.values().contains("Anna")).isTrue();
    }

    @Test
    public void testParsingIntegerParameter() throws Exception {
        parametersProvider.setParametersJson("{\"p1\": 17}");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.get("p1").toString()).isEqualTo("17");
    }

    @Test
    public void testParsingBooleanParameter() throws Exception {
        parametersProvider.setParametersJson("{\"p2\": false}");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.get("p2").toString()).isEqualTo("false");
    }

    @Test
    public void testParsingJsonObjectParameter() throws Exception {
        parametersProvider.setParametersJson("{\"p3\": {\"name\":\"Alex\"}}");
        Map<String, Object> result = parametersService.getParameters();

        Map<String, Object> jsonVal = (Map<String, Object>) result.get("p3");
        assertThat(jsonVal).containsKey("name");
        assertThat(jsonVal).containsValue("Alex");
    }

    @Test
    public void testParsingMultipleParameters() throws Exception {
        parametersProvider.setParametersJson("{\"firstName\": \"Kaleb\", \"lastName\": \"Johnson\"}");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.get("firstName").toString()).isEqualTo("Kaleb");
        assertThat(result.get("lastName").toString()).isEqualTo("Johnson");
    }

    @Test
    public void testParsingCommentOnly() throws Exception {
        parametersProvider.setParametersJson("// Provide query parameters in JSON format here:");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.isEmpty()).isTrue();
    }


    @Test
    public void testParsingCommentWithParameter() throws Exception {
        parametersProvider.setParametersJson("// Provide query parameters in JSON format here:\n{\"name\": \"Eva\"}");
        Map<String, Object> result = parametersService.getParameters();

        assertThat(result.size() == 1).isTrue();
    }

    @Test
    public void testParsingJsonArray() throws Exception {
        parametersProvider.setParametersJson("// Provide query parameters in JSON format here:\n[\"item1\",\"item2\"]");
        assertThat(parametersService.getParameters().isEmpty()).isTrue();
    }

    @Test
    public void testParsingNumber() throws Exception {
        parametersProvider.setParametersJson("1");
        assertThat(parametersService.getParameters().isEmpty()).isTrue();
    }

    @Test
    public void testParsingString() throws Exception {
        parametersProvider.setParametersJson("\"abc\"");
        assertThat(parametersService.getParameters().isEmpty()).isTrue();
    }

    @Test
    public void testParsingUnwrappedParameter() throws Exception {
        parametersProvider.setParametersJson("\"param1\":\"val1\"");
        assertThat(parametersService.getParameters().isEmpty()).isTrue();
    }

    @Test
    public void testParsingParamWithArraylikeAccessor() throws Exception {
        parametersProvider.setParametersJson("{\"param1\": person[\"name\"]}");
        assertThat(parametersService.getParameters().isEmpty()).isTrue();
    }

}
