package es.rchavarria.editor;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import es.rchavarria.editor.session.EditorSession;
import es.rchavarria.editor.session.EditorSessionFactory;
import es.rchavarria.editor.tests.util.FilesHelper;
import es.rchavarria.editor.util.BinaryFilesChecker;

public class EditWithoutCommentsTest {

    private FilesHelper helper = new FilesHelper();

    @Test
    public void testOneLine() throws Exception {
        String propertyToChange = "first.prop";
        String newValue = "Expected value";

        File actual = helper.createTmpWithLines(Arrays.asList("first.prop=First value"));
        File expected = helper.createTmpWithLines(Arrays.asList("first.prop=Expected value"));

        updateAndCompare(propertyToChange, newValue, actual, expected);
    }

    private void updateAndCompare(final String propertyToChange, final String newValue, final File actual,
            final File expected) throws Exception {
        EditorSession session = EditorSessionFactory.createFromFile(actual);
        Editor editor = EditorFactory.create(session);

        session.begin();
        editor.update(propertyToChange, newValue);
        session.end();

        boolean areEquals = new BinaryFilesChecker().areFilesEqual(expected, actual);
        assertTrue("Files must be equal", areEquals);
    }

    @Test
    public void testTwoPropertiesModifyOnlyOne() throws Exception {
        String propertyToChange = "second.prop";
        String newValue = "Expected value";

        File actual = helper.createTmpWithLines(Arrays.asList("first.prop=First value", "second.prop=Other value"));
        File expected = helper
                .createTmpWithLines(Arrays.asList("first.prop=First value", "second.prop=Expected value"));

        updateAndCompare(propertyToChange, newValue, actual, expected);
    }

    @Test
    public void testSeveralPropertiesModifyOnlyOne() throws Exception {
        String propertyToChange = "third";
        String newValue = "Expected value";
        String actualProperty = propertyToChange + "=Old value";
        String expectedProperty = propertyToChange + "=" + newValue;

        List<String> actualLines = Arrays.asList("first.prop=First value", "second.prop=Other value", actualProperty,
                "this_is-the.fourth=and THIS is the value");
        File actual = helper.createTmpWithLines(actualLines);

        List<String> expectedLines = Arrays.asList("first.prop=First value", "second.prop=Other value",
                expectedProperty, "this_is-the.fourth=and THIS is the value");
        File expected = helper.createTmpWithLines(expectedLines);

        updateAndCompare(propertyToChange, newValue, actual, expected);
    }
}
