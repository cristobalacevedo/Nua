package utils;

public class PropertyOption {
    private final String label;   
    private final int id;   // ID (oculto)
    private final String type;


    public PropertyOption(String label, int id, String type) {
        this.label = label;
        this.id = id;
        this.type = type;
    }
    
    public String getLabel() {
        return label;
    }

    public int getId() {
		return id;
	}
    
    public Object getType() {
		return type;
	}

    
    @Override
    public String toString() {
        return label; // Muestra solo el nombre en español en el combo
    }

}
