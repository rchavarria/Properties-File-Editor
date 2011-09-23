package es.rchavarria.editor.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

class FileEditorSession implements EditorSession {

    private File file;
    private File outputFile;
    private InputStream is;
    private OutputStream os;

    public FileEditorSession(final File file) {
        this.file = file;
        String outputFileName = file.getAbsolutePath() + ".out";
        outputFile = new File(outputFileName);
    }

    @Override
    public void begin() throws SessionException {
        try {
            is = new FileInputStream(file);
            os = new FileOutputStream(outputFile);

        } catch (FileNotFoundException e) {
            throw new SessionException("Session can't start", e);
        }
    }

    @Override
    public void end() {
        try {
            is.close();
            os.flush();
            os.close();
        } catch (Exception e) {
        }

        if (!file.delete()) {
            throw new RuntimeException("Can't delete source file to replace");
        }

        if (!outputFile.renameTo(file)) {
            throw new RuntimeException("Can't rename tmp file to source file name");
        }
    }

    @Override
    public InputStream getInputStream() {
        if (is == null) {
            throw new NullPointerException("Session must start before getting input stream");
        }

        return is;
    }

    @Override
    public OutputStream getOutputStream() {
        if (os == null) {
            throw new NullPointerException("Session must start before getting output stream");
        }

        return os;
    }
}
