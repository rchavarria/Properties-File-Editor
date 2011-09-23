package es.rchavarria.editor.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileLinesReaderTest {

    private FileLinesReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new FileLinesReader(getClass().getResourceAsStream("/test-FileLinesReader.txt"));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReadLines() throws IOException {
        List<String> lines = reader.readLines();

        assertEquals(6, lines.size());
        assertEquals("line one", lines.get(0));
        assertEquals("line two", lines.get(1));
        assertEquals("#comment line", lines.get(2));
        assertEquals("", lines.get(3));
        assertEquals("last line was empty", lines.get(4));
        assertEquals("line 5th", lines.get(5));
    }
}
