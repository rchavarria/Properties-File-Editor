package es.rchavarria.editor.session;

import java.io.File;

public class EditorSessionFactory {

    public static EditorSession createFromFile(final File fileFrom) {
        return new FileEditorSession(fileFrom);
    }
}
