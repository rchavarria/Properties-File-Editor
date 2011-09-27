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

public class EditWithCommentsTest {

    private FilesHelper helper = new FilesHelper();

    @Test
    public void testOnePropertyOneCommentedLine() throws Exception {
        List<String> actualLines = Arrays.asList("# this is a comment", "prop.value=And this is a property");
        List<String> expectedLines = Arrays.asList("# this is a comment", "prop.value=Expected value");

        File actualFile = helper.createTmpWithLines(actualLines);
        File expectedFile = helper.createTmpWithLines(expectedLines);

        updateAndCompare("prop.value", "Expected value", actualFile, expectedFile);
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
}
