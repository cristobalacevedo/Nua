package model;

public class House extends Estate {
	private int numRooms;
	private int numBathrooms;
	private int numFloors;
	private String type; // e.g., "Detached", "Semi-detached", "Terraced"
	private boolean hasGarden;
	private boolean hasPatio; // true if the house has a patio
	private boolean inCondominium; // true if the house is part of a condominium
	private boolean hasParking; // true if the house has parking space
	private boolean hasPool; // true if the house has a pool

	public House(int id, int area, String address, String city, String region, String description, String type, int numRooms, boolean hasGarden, boolean hasPatio, boolean inCondominium, boolean hasParking, boolean hasPool, int numBathrooms, int numFloors) {
		super(id, area, address, city, region, description);
		this.numRooms = numRooms;

		this.hasGarden = hasGarden;
	}

	// GETTERS

	public int getNumRooms() {
		return numRooms;
	}
	
	public int getNumBathrooms() {
		return numBathrooms;
	}

	public int getNumFloors() {
		return numFloors;
	}
	
	public String getType() {
		return type;
	}

	public boolean getHasGarden() {
		return hasGarden;
	}

	public boolean getHasPatio() {
		return hasPatio;
	}
	
	public boolean getInCondominium() {
		return inCondominium;
	}
	
	public boolean getHasParking() {
		return hasParking;
	}
	
	public boolean getHasPool() {
		return hasPool;
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

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

}
