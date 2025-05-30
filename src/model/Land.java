package model;

public class Land extends Estate {
	private boolean hasConstruction; // true if the land has existing construction
	
	public Land(int id, int area, String address, String city, String region, String description, boolean hasConstruction) {
		super(id, area, address, city, region, description);
		this.hasConstruction = hasConstruction;
	}
	
	// GETTERS
	
	public boolean hasConstruction() {
		return hasConstruction;
	}
	
	public String getAddress() {
		return super.getAddress();
	}
	
	public String getCity() {
		return super.getCity();
	}
	
	public String getRegion() {
		return super.getRegion();
	}
	
	public String getDescription() {
		return super.getDescription();
	}
	
	public int getId() {
		return super.getId();
	}
	
	// END GETTERS
	
	// SETTERS
	
	public void setHasConstruction(boolean hasConstruction) {
		this.hasConstruction = hasConstruction;
	}
	
	// END SETTERS
	
	@Override
	public String toString() {
		return "Land{" + "id=" + getId() + ", area=" + getArea() + ", address='" + getAddress() + '\'' + ", city='"
				+ getCity() + '\'' + ", region='" + getRegion() + '\'' + ", description='" + getDescription() + '\''
				+ ", hasConstruction=" + hasConstruction + '}';
	}
}
