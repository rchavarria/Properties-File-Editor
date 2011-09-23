package es.rchavarria.editor;

import es.rchavarria.editor.session.EditorSession;

public class EditorFactory {

    public static Editor create(final EditorSession session) {
        EditorImpl editor = new EditorImpl(session);
        return editor;
    }
}
