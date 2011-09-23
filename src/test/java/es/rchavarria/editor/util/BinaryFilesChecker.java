package es.rchavarria.editor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinaryFilesChecker {
    private static final Logger LOGGER = LoggerFactory.getLogger(BinaryFilesChecker.class);

    public boolean areFilesEqual(final File expected, final File actual) throws IOException {
        boolean haveSameLength = compareLength(expected, actual);
        return haveSameLength ? compareBytesPairWise(expected, actual) : false;
    }

    private boolean compareBytesPairWise(final File expected, final File actual) throws IOException {
        InputStream i1 = new FileInputStream(expected);
        InputStream i2 = new FileInputStream(actual);
        int b1, b2;
        do {
            b1 = i1.read();
            b2 = i2.read();
            LOGGER.debug("{} == {} ?", b1, b2);
        } while (b1 == b2 && b1 != -1);
        i1.close();
        i2.close();

        // true only if end of file is reached
        return b1 == -1 && b2 == -1;
    }

    private boolean compareLength(final File expected, final File actual) {
        return expected.length() == actual.length();
    }
}
