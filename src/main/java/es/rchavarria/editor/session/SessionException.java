package es.rchavarria.editor.session;

public class SessionException extends Exception {
    private static final long serialVersionUID = -6161920224778076964L;

    public SessionException(final String msg, final Exception e) {
        super(msg, e);
    }

}
