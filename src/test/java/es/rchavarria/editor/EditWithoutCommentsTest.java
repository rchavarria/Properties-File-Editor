package es.rchavarria.editor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import es.rchavarria.editor.tests.util.FilesHelper;

public class EditWithoutCommentsTest {

    private EditorTestHelper helper = new EditorTestHelper();
    private FilesHelper filesHelper = new FilesHelper();

    @Test
    public void testOneLine() throws Exception {
        String propertyToChange = "first.prop";
        String newValue = "Expected value";

        File actual = filesHelper.createTmpWithLines(Arrays.asList("first.prop=First value"));
        File expected = filesHelper.createTmpWithLines(Arrays.asList("first.prop=Expected value"));

        helper.updateAndCompare(new PropertyValueTuple(propertyToChange, newValue), actual, expected);
    }

    @Test
    public void testTwoPropertiesModifyOnlyOne() throws Exception {
        String propertyToChange = "second.prop";
        String newValue = "Expected value";

        File actual = filesHelper
                .createTmpWithLines(Arrays.asList("first.prop=First value", "second.prop=Other value"));
        File expected = filesHelper.createTmpWithLines(Arrays.asList("first.prop=First value",
                "second.prop=Expected value"));

        helper.updateAndCompare(new PropertyValueTuple(propertyToChange, newValue), actual, expected);
    }

    @Test
    public void testSeveralPropertiesModifyOnlyOne() throws Exception {
        String propertyToChange = "third";
        String newValue = "Expected value";
        String actualProperty = propertyToChange + "=Old value";
        String expectedProperty = propertyToChange + "=" + newValue;

        List<String> actualLines = Arrays.asList("first.prop=First value", "second.prop=Other value", actualProperty,
                "this_is-the.fourth=and THIS is the value");
        File actual = filesHelper.createTmpWithLines(actualLines);

        List<String> expectedLines = Arrays.asList("first.prop=First value", "second.prop=Other value",
                expectedProperty, "this_is-the.fourth=and THIS is the value");
        File expected = filesHelper.createTmpWithLines(expectedLines);

        helper.updateAndCompare(new PropertyValueTuple(propertyToChange, newValue), actual, expected);
    }
}
