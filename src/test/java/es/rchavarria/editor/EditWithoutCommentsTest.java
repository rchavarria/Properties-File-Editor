package es.rchavarria.editor;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import es.rchavarria.editor.session.EditorSession;
import es.rchavarria.editor.session.EditorSessionFactory;
import es.rchavarria.editor.util.BinaryFilesChecker;
import es.rchavarria.editor.util.FileLinesWriter;

public class EditWithoutCommentsTest {

    @Test
    public void testOneLine() throws Exception {
        String propertyToChange = "first.prop";
        String newValue = "Expected value";

        File actual = createFileWithLines(Arrays.asList("first.prop=First value"));
        File expected = createFileWithLines(Arrays.asList("first.prop=Expected value"));

        updateAndCompare(propertyToChange, newValue, actual, expected);
    }

    private File createFileWithLines(final List<String> lines) throws IOException {
        File file = File.createTempFile("EditorTest", ".txt");
        FileLinesWriter writer = new FileLinesWriter(new FileOutputStream(file));
        writer.writeLines(lines);

        return file;
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

        File actual = createFileWithLines(Arrays.asList("first.prop=First value", "second.prop=Other value"));
        File expected = createFileWithLines(Arrays.asList("first.prop=First value", "second.prop=Expected value"));

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
        File actual = createFileWithLines(actualLines);

        List<String> expectedLines = Arrays.asList("first.prop=First value", "second.prop=Other value",
                expectedProperty, "this_is-the.fourth=and THIS is the value");
        File expected = createFileWithLines(expectedLines);

        updateAndCompare(propertyToChange, newValue, actual, expected);
    }
}
