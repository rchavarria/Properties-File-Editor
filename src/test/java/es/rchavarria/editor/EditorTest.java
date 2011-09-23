package es.rchavarria.editor;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.rchavarria.editor.session.EditorSession;
import es.rchavarria.editor.session.EditorSessionFactory;
import es.rchavarria.editor.util.BinaryFilesChecker;
import es.rchavarria.editor.util.FileLinesWriter;

public class EditorTest {

    private File actual;
    private File expected;

    @Before
    public void setUp() throws Exception {
        actual = File.createTempFile("actual", ".txt");
        FileLinesWriter writer = new FileLinesWriter(new FileOutputStream(actual));
        writer.writeLines(Arrays.asList("only.one.prop=First value"));

        expected = File.createTempFile("expected", ".txt");
        writer = new FileLinesWriter(new FileOutputStream(expected));
        writer.writeLines(Arrays.asList("only.one.prop=Expected value"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOneLine() throws Exception {
        EditorSession session = EditorSessionFactory.createFromFile(actual);
        Editor editor = EditorFactory.create(session);

        session.begin();
        editor.update("only.one.prop", "Expected value");
        session.end();

        boolean areEquals = new BinaryFilesChecker().areFilesEqual(expected, actual);
        assertTrue("Files must be equal", areEquals);
    }
}
