package es.rchavarria.editor.tests.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import es.rchavarria.editor.util.FileLinesWriter;

public class FilesHelper {

    public File createTmpWithLines(final List<String> lines) throws IOException {
        File file = File.createTempFile("FilesHelper", ".txt");
        FileLinesWriter writer = new FileLinesWriter(new FileOutputStream(file));
        writer.writeLines(lines);

        return file;
    }
}
