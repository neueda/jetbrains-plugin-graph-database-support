package com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params;

import com.fasterxml.jackson.databind.JsonMappingException;
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
        Map<String, Object> result = parametersService
                .getParameters("match (p:Person) return p;");

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
        Map<String, Object> result = parametersService
                .getParameters("match (p:Person) where p.name = $name return *");

        assertThat(result.keySet().contains("name")).isTrue();
        assertThat(result.values().contains("Anna")).isTrue();
    }

    @Test
    public void testParsingIntegerParameter() throws Exception {
        parametersProvider.setParametersJson("{\"p1\": 17}");
        Map<String, Object> result = parametersService
                .getParameters("match (p:Person) where p.age = $p1 return *");

        assertThat(result.get("p1").toString()).isEqualTo("17");
    }

    @Test
    public void testParsingBooleanParameter() throws Exception {
        parametersProvider.setParametersJson("{\"p2\": false}");
        Map<String, Object> result = parametersService.
                getParameters("match (p:Person) where p.is_citizen = $p2 return *");

        assertThat(result.get("p2").toString()).isEqualTo("false");
    }

    @Test
    public void testParsingJsonObjectParameter() throws Exception {
        parametersProvider.setParametersJson("{\"p3\": {\"name\":\"Alex\"}}");
        Map<String, Object> result = parametersService.
                getParameters("match (p:Person) where p.father = $p3 return *");

        Map<String, Object> jsonVal = (Map<String, Object>) result.get("p3");
        assertThat(jsonVal).containsKey("name");
        assertThat(jsonVal).containsValue("Alex");
    }

    @Test
    public void testParsingMultipleParameters() throws Exception {
        parametersProvider.setParametersJson("{\"firstName\": \"Kaleb\", \"lastName\": \"Johnson\"}");
        Map<String, Object> result = parametersService
                .getParameters("match (p:Person)\n" +
                        "where p.first_name = $firstName " +
                        "   and p.last_name = $lastName return *");

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

    @Test(expected = Exception.class)
    public void testParsingJsonArray() throws Exception {
        parametersProvider.setParametersJson("// Provide query parameters in JSON format here:\n[\"item1\",\"item2\"]");
        parametersService.getParameters();
    }

    @Test(expected = JsonMappingException.class)
    public void testParsingNumber() throws Exception {
        parametersProvider.setParametersJson("1");
        parametersService.getParameters();
    }

    @Test(expected = JsonMappingException.class)
    public void testParsingString() throws Exception {
        parametersProvider.setParametersJson("\"abc\"");
        parametersService.getParameters();
    }

    @Test(expected = JsonMappingException.class)
    public void testParsingUnwrappedParameter() throws Exception {
        parametersProvider.setParametersJson("\"param1\":\"val1\"");
        parametersService.getParameters();
    }

    @Test(expected = Exception.class)
    public void testParsingParamWithObjAccessor() throws Exception {
        parametersProvider.setParametersJson("{\"param1\": person[\"name\"]}");
        parametersService.getParameters();
    }

}
