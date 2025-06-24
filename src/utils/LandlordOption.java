package utils;

public class LandlordOption {
    private int id;
    private String displayName; // Puede contener "Nombre Apellido - RUT"

    public LandlordOption(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }
    
	public String getDisplayName() {
		return displayName;
	}

    public String toString() {
        return displayName;
    }
}