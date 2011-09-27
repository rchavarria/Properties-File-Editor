package es.rchavarria.editor;

import static org.junit.Assert.assertTrue;

import java.io.File;

import es.rchavarria.editor.session.EditorSession;
import es.rchavarria.editor.session.EditorSessionFactory;
import es.rchavarria.editor.util.BinaryFilesChecker;

public class EditorTestHelper {

    public void updateAndCompare(final PropertyValueTuple tuple, final File actual, final File expected)
            throws Exception {
        EditorSession session = EditorSessionFactory.createFromFile(actual);
        Editor editor = EditorFactory.create(session);

        session.begin();
        editor.update(tuple.getProperty(), tuple.getValue());
        session.end();

        boolean areEquals = new BinaryFilesChecker().areFilesEqual(expected, actual);
        assertTrue("Files must be equal", areEquals);
    }
}
