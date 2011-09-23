package es.rchavarria.editor;

public interface Editor {

    void update(String property, String newValue) throws EditorException;

}
