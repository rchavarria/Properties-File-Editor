package es.rchavarria.editor.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileLinesWriter {

    private OutputStream os;

    public FileLinesWriter(final OutputStream os) {
        this.os = os;
    }

    public void writeLines(final List<String> lines) throws IOException {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(os));

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        } finally {
            if (bw != null) {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                }
            }
        }
    }
}