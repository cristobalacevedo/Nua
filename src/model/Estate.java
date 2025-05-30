package model;

public abstract class Estate {
	private int id;
	private int area; // Area in square meters
	private String address;
	private String city;
	private String region;
	private String description;
	private int priceUF; // Price in UF (Unidad de Fomento), a Chilean currency unit used for real estate transactions
	private int priceCLP; // Price in Chilean Pesos (CLP)
	
	public Estate(int id, int area, String address, String city, String region, String description) {
		this.id = id;
		this.area = area;
		this.address = address;
		this.city = city;
		this.region = region;
		this.description = description;
	}
	
	// GETTERS
	
	public int getId() {
		return id;
	}
	
	public int getArea() {
        return area;
    }
	
	public String getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getRegion() {
		return region;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getPriceUF() {
		return priceUF;
	}
	
	public int getPriceCLP() {
		return priceCLP;
	}
	
	// END GETTERS 
	
	// SETTERS
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// SETTERS
	
	// toString method for easy display
	@Override
	public String toString() {
		return "Propiedad [id=" + id + ", direccion=" + address + ", ciudad=" + city + ", Región=" + region
				+ ", description=" + description + "]";
	}
	
	
}
