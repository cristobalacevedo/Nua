package utils;

public class PropertyOption {
    private final String label;   // Español (visible)
    private final String value;   // Inglés (real)
    private final int id;         // ID del elemento

    public PropertyOption(String label, String value) {
        this.label = label;
        this.value = value;
		this.id = 0;
    }
    
	public PropertyOption(int id, String label, String value) {
		this.id = id;
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

	public int getId() {
		return id;
	}
}
