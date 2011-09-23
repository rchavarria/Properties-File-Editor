package es.rchavarria.editor;

public class EditorException extends Exception {
    private static final long serialVersionUID = 1547735681426610174L;

    public EditorException(final String msg, final Exception e) {
        super(msg, e);
    }

}
