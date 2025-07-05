package model;

public class AvailablePropertyView {
    private String landlord;
    private String type;
    private String rolSII;
    private int size;
    private String town;
    private String fullAddress;
    private String availability;

    // Constructor
    public AvailablePropertyView(String landlord, String type, String rolSII, int size, String town, String fullAddress, String availability) {
    	this.landlord = landlord;
		this.type = type;
		this.rolSII = rolSII;
		this.size = size;
		this.town = town;
		this.fullAddress = fullAddress;
		this.availability = availability;
    }

    // Getters
    public String getLandlord() {
		return landlord;
	}
    
    public String getType() {
		return type;
	}

	public String getRolSII() {
		return rolSII;
	}

	public int getSize() {
		return size;
	}

	public String getTown() {
		return town;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public String getAvailability() {
		return availability;
	}
	
}
