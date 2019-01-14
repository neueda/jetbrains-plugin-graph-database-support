package com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.tests.cypher.parameters;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.intellij.psi.PsiElement;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.component.settings.SettingsComponent;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params.ParametersProvider;
import com.neueda.jetbrains.plugin.graphdb.jetbrains.ui.console.params.ParametersService;
import com.neueda.jetbrains.plugin.graphdb.test.integration.neo4j.util.base.BaseIntegrationTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CypherParametersProviderTest extends BaseIntegrationTest {

    private class TestParametersProvider implements ParametersProvider {

        private String parametersJson, fileSpecificParametersJson;

        @Override
        public String getParametersJson() {
            return parametersJson;
        }

        @Override
        public String getFileSpecificParametersJson() {
            return fileSpecificParametersJson;
        }

        public void setParametersJson(String parametersJson) {
            this.parametersJson = parametersJson;
        }

        public void setFileSpecificParametersJson(String fileSpecificParametersJson) {
            this.fileSpecificParametersJson = fileSpecificParametersJson;
        }
    }

    private ParametersService parametersService;
    private TestParametersProvider parametersProvider = new TestParametersProvider();

    @Override
    public void setUp() throws Exception {
        super.setUp();

        parametersService = new ParametersService();
        parametersService.registerParametersProvider(parametersProvider);
    }

    public void testParsingEmptyJsonObject() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{}");
        parametersProvider.setFileSpecificParametersJson("{\"param\": \"non-empty\"}");
        Map<String, Object> parameters = parametersService.getParameters(getPsiFile("RETURN $param"));
        assertThat(parameters).isEmpty();
    }

    public void testParsingEmptyJsonObjectInLocalParams() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(true);
        parametersProvider.setParametersJson("{\"param\": \"non-empty\"}");
        parametersProvider.setFileSpecificParametersJson("{}");
        // local (file-specific) parameters are returned from getParameters() here, and they must be empty
        Map<String, Object> parameters = parametersService.getParameters(getPsiFile("RETURN $param"));
        assertThat(parameters).isEmpty();
    }

    public void testParsingEmptyParameters() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("");
        parametersProvider.setFileSpecificParametersJson("{\"param\": \"non-empty\"}");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN $param"));
        assertThat(result).isEmpty();
    }

    public void testParsingEmptyFileSpecificParameters() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(true);
        parametersProvider.setParametersJson("{\"param\": \"non-empty\"}");
        parametersProvider.setFileSpecificParametersJson("");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN $param"));
        assertThat(result).isEmpty();
    }

    public void testParsingStringParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"name\": \"Anna\"}");
        Map<String, Object> result = parametersService
                .getParameters(getPsiFile("match (p:Person) where p.name = $name return *"));

        assertThat(result).containsEntry("name", "Anna");
    }

    public void testParsingIntegerParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"p1\": 17}");
        Map<String, Object> result = parametersService
                .getParameters(getPsiFile("match (p:Person) where p.age = $p1 return *"));

        assertThat(result).containsEntry("p1", 17);
    }

    public void testParsingBooleanParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"p2\": false}");
        Map<String, Object> result = parametersService.
                getParameters(getPsiFile("match (p:Person) where p.is_citizen = $p2 return *"));

        assertThat(result).containsEntry("p2", false);
    }

    public void testParsingJsonObjectParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"p3\": {\"name\":\"Alex\"}}");
        Map<String, Object> result = parametersService.
                getParameters(getPsiFile("match (p:Person) where p.father = $p3 return *"));

        Map<String, Object> jsonVal = (Map<String, Object>) result.get("p3");
        assertThat(jsonVal).containsEntry("name", "Alex");
    }

    public void testParsingMultipleParameters() throws Exception {
        parametersProvider.setParametersJson("{\"firstName\": \"Kaleb\", \"lastName\": \"Johnson\"}");
        parametersProvider.setFileSpecificParametersJson(
                "{\"country\": \"France\", \"city\": \"Paris\", \"age\": 90, \"lastName\": \"Green\"}"
        );

        SettingsComponent.getInstance().enableFileSpecificParams(false);
        Map<String, Object> result = parametersService
                .getParameters(getPsiFile("match (p:Person)\n" +
                        "where p.first_name = $firstName " +
                        "   and p.last_name = $lastName return *"));
        assertThat(result)
                .hasSize(2)
                .containsEntry("firstName", "Kaleb")
                .containsEntry("lastName", "Johnson");

        SettingsComponent.getInstance().enableFileSpecificParams(true);
        result = parametersService
                .getParameters(getPsiFile("match (p:Person)\n" +
                        "where p.city = $city and p.age = $age " +
                        "   and p.last_name = $lastName return *"));
        assertThat(result)
                .hasSize(3)
                .containsEntry("city", "Paris")
                .containsEntry("age", 90)
                .containsEntry("lastName", "Green");
    }

    public void testParsingCommentOnly() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("// Provide query parameters in JSON format here:");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN $param"));

        assertThat(result).isEmpty();
    }

    public void testParsingCommentWithParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("// Provide query parameters in JSON format here:\n{\"name\": \"Eva\"}");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN $name"));

        assertThat(result).hasSize(1);
    }

    public void testParsingNumericParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"0\": \"Tom\"}");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN $0"));

        assertThat(result).containsEntry("0", "Tom");
    }

    public void testParsingOldStyleStringParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"name\": \"Ethan\"}");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN {name}"));

        assertThat(result).containsEntry("name", "Ethan");
    }

    public void testParsingOldStyleNumericParameter() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"0\": \"Simon\"}");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN {0}"));

        assertThat(result).containsEntry("0", "Simon");
    }

    public void testFilteringUsedParameters() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        parametersProvider.setParametersJson("{\"firstName\": \"Frodo\", \"lastName\": \"Baggins\"}");
        Map<String, Object> result = parametersService.getParameters(getPsiFile("RETURN $lastName"));

        assertThat(result)
                .hasSize(1)
                .containsEntry("lastName", "Baggins");
    }

    public void testParsingJsonArray() throws Exception {
        try {
            SettingsComponent.getInstance().enableFileSpecificParams(false);
            parametersProvider.setParametersJson("// Provide query parameters in JSON format here:\n[\"item1\",\"item2\"]");
            parametersService.getParameters(getPsiFile("return 1"));
            fail("JsonMappingException expected because of array in parameters json expected");
        } catch (JsonMappingException e) {
            // do nothing, exception was expected
        }
    }

    public void testParsingNumber() throws Exception {
        try {
            SettingsComponent.getInstance().enableFileSpecificParams(false);
            parametersProvider.setParametersJson("1");
            parametersService.getParameters(getPsiFile("return 1"));
            fail("JsonMappingException expected because of number provided instead of parameters map");
        } catch (JsonMappingException e) {
            // do nothing
        }
    }

    public void testParsingString() throws Exception {
        try {
            SettingsComponent.getInstance().enableFileSpecificParams(false);
            parametersProvider.setParametersJson("\"abc\"");
            parametersService.getParameters(getPsiFile("return 1"));
            fail("JsonMappingException expected because of string provided instead of parameters map");
        } catch (JsonMappingException e) {
            // do nothing
        }
    }

    public void testParsingUnwrappedParameter() throws Exception {
        try {
            SettingsComponent.getInstance().enableFileSpecificParams(false);
            parametersProvider.setParametersJson("\"param1\":\"val1\"");
            parametersService.getParameters(getPsiFile("return 1"));
            fail("JsonMappingException expected because of parameter not wrapped in curly braces");
        } catch (JsonMappingException e) {
            // do nothing
        }
    }

    public void testParametersRetrievalWithNoPsiElement() throws Exception {
        SettingsComponent.getInstance().enableFileSpecificParams(false);
        Map<String, Object> result = parametersService.getParameters(null);
        assertThat(result).isEmpty();
    }

    private PsiElement getPsiFile(String queryText) {
        return myFixture.configureByText("test.cypher", queryText);
    }
}
