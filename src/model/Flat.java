package model;

public class Flat extends Estate {
	private int numRooms;
	private int numBathrooms;
	private boolean hasBalcony; // true if the flat has a balcony
	private boolean hasParking; // true if the flat has parking space
	private boolean inCondominium; // true if the flat is part of a condominium
	private boolean hasElevator; // true if the building has an elevator
	private boolean hasPool; // true if the building has a pool
	private boolean hasGym; // true if the building has a gym
	private boolean hasLaundry; // true if the building has a laundry room
	private boolean hasSecurity; // true if the building has security services
	private boolean hasStorage; // true if the flat has a storage room in the building

	public Flat(int id, int area, String address, String city, String region, String description, int numRooms, int numBathrooms, boolean hasBalcony, boolean hasParking, boolean inCondominium, boolean hasElevator, boolean hasPool, boolean hasGym, boolean hasLaundry, boolean hasSecurity, boolean hasStorage) {
		super(id, area, address, city, region, description);
		this.numRooms = numRooms;
		this.numBathrooms = numBathrooms;
		this.hasBalcony = hasBalcony;
		this.hasParking = hasParking;
		this.inCondominium = inCondominium;
		this.hasElevator = hasElevator;
		this.hasPool = hasPool;
		this.hasGym = hasGym;
		this.hasLaundry = hasLaundry;
		this.hasSecurity = hasSecurity;
		this.hasStorage = hasStorage;
			
	}

	// GETTERS

	public int getNumRooms() {
		return numRooms;
	}
	
	public int getNumBathrooms() {
		return numBathrooms;
	}

	public boolean getHasBalcony() {
		return hasBalcony;
	}
	
	public boolean getHasParking() {
		return hasParking;
	}
	
	public boolean getInCondominium() {
		return inCondominium;
	}
	
	public boolean getHasElevator() {
		return hasElevator;
	}
	
	public boolean getHasPool() {
		return hasPool;
	}
	
	public boolean getHasGym() {
		return hasGym;
	}
	
	public boolean getHasLaundry() {
		return hasLaundry;
	}
	
	public boolean getHasSecurity() {
		return hasSecurity;
	}
	
	public boolean getHasStorage() {
		return hasStorage;
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


	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}

}
