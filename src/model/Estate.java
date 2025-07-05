package model;

public abstract class Estate {
	private int townId, regionId, landlordId, propertyTypeId, size;
	private String streetName, num1, num2, rolSII;
	private int priceUF; // Price in UF (Unidad de Fomento), a Chilean currency unit used for real estate transactions
	private int priceCLP; // Price in Chilean Pesos (CLP)
	private int available; // Availability status of the estate (0 for not available, 1 for available)
	// Constructor with all fields
	public Estate(String streetName, String num1, String num2, int townId, int regionId, int landlordId,
			int propertyTypeId, int size, String rolSII, int available){
	}
	
	// Default constructor
	public Estate() {
		
	}
	
	  // GETTERS
	

	public int getTownId() {
		return townId;
	}
	
	public int getRegionId() {
		return regionId;
	}
	
	public int getLandlordId() {
		return landlordId;
	}
	
	public int getPropertyTypeId() {
		return propertyTypeId;
	}
	
	public int getSize() {
		return size;
	}
	
	public String getStreetName() {
		return streetName;
	}
	
	public String getNum1() {
		return num1;
	}
	
	public String getNum2() {
		return num2;
	}
	
	public String getRolSII() {
		return rolSII;
	}
	
	public int getPriceUF() {
		return priceUF;
	}
	
	public int getPriceCLP() {
		return priceCLP;
	}
	
	public int getAvailable() {
		return available; // This method should return the availability status of the estate
	}
	
	// SETTERS
	
	public void setTownId(int townId) {
		this.townId = townId;
	}
	
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	
	public void setLandlordId(int landlordId) {
		this.landlordId = landlordId;
	}
	
	public void setPropertyTypeId(int propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public void setNum1(String num1) {
		this.num1 = num1;
	}
	
	public void setNum2(String num2) {
		this.num2 = num2;
	}
	
	public void setRolSII(String rolSII) {
		this.rolSII = rolSII;
	}
	
	public void setPriceUF(int priceUF) {
		this.priceUF = priceUF;
	}
	
	public void setPriceCLP(int priceCLP) {
        this.priceCLP = priceCLP;
	}
	
	public void setAvailable(int available) {
		this.available = available;
	}
	
}

