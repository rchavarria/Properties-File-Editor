package es.rchavarria.editor.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This utility class reads the lines of a file and stores them in a List of String
 */
public class FileLinesReader {
    private InputStream is;

    public FileLinesReader(final InputStream is) {
        this.is = is;
    }

    /**
     * @return a List of String containing the lines of a file.
     * @throws IOException
     */
    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }

        return lines;
    }
}
