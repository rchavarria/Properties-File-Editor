package es.rchavarria.editor.session;

import java.io.InputStream;
import java.io.OutputStream;

public interface EditorSession {

    void begin() throws SessionException;

    void end();

    InputStream getInputStream();

    OutputStream getOutputStream();
}
