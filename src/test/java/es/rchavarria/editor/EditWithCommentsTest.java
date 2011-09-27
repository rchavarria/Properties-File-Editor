package es.rchavarria.editor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import es.rchavarria.editor.tests.util.FilesHelper;

public class EditWithCommentsTest {

    private EditorTestHelper helper = new EditorTestHelper();
    private FilesHelper filesHelper = new FilesHelper();

    @Test
    public void testOnePropertyOneCommentedLine() throws Exception {
        List<String> actualLines = Arrays.asList("# this is a comment", "prop.value=And this is a property");
        List<String> expectedLines = Arrays.asList("# this is a comment", "prop.value=Expected value");

        File actualFile = filesHelper.createTmpWithLines(actualLines);
        File expectedFile = filesHelper.createTmpWithLines(expectedLines);

        helper.updateAndCompare(new PropertyValueTuple("prop.value", "Expected value"), actualFile, expectedFile);
    }
}
