package es.rchavarria.editor;

public class PropertyValueTuple {

    private String property;
    private String value;

    public PropertyValueTuple(final String property, final String value) {
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }
}
