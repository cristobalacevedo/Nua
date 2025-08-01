package utils;

public class PropertyTypeOption {
    private final String label;   // Español (visible)
    private final String value;   // Inglés (real)

    public PropertyTypeOption(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return label; // Muestra solo el nombre en español en el combo
    }
}
