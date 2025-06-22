package model;

public class Flat extends Estate {
	private int roomQty, bathQty, floor;
	private int hasParking, hasStorage;
	private boolean hasBalcony;
	private boolean inCondo, buildingHasLift, buildingHasPool, buildingHasGym, buildingHasLaundry, buildingHasBBQ;
    private Integer condoId;

	public Flat(int roomQty, int bathQty, int floor, int hasStorage, int hasParking, boolean hasBalcony,
			boolean buildingHasLift, boolean buildingHasPool,
			boolean buildingHasGym, boolean buildingHasLaundry, boolean buildingHasBBQ,
			String streetName, String num1, String num2, int townId, int regionId, int landlordId,
			int propertyTypeId, int size, String rolSII, boolean inCondo, Integer condoId) {
		// ADDRESS and PROPERTY
		super(streetName, num1, num2, townId, regionId, landlordId, propertyTypeId, size, rolSII);
		
		// FLAT
		this.roomQty = roomQty;
		this.bathQty = bathQty;
		this.floor = floor;
		this.hasStorage = hasStorage;
		this.hasParking = hasParking;
		this.hasBalcony = hasBalcony;
		
		this.buildingHasLift = buildingHasLift;
		this.buildingHasPool = buildingHasPool;
		this.buildingHasGym = buildingHasGym;
		this.buildingHasLaundry = buildingHasLaundry;
		this.buildingHasBBQ = buildingHasBBQ;
		this.inCondo = inCondo;
		this.condoId = condoId; // Default value, can be set later
			
	}
	
	public Flat() {
		// Default constructor necessary to use `new Flat()`
	}

	// GETTERS

	public int getRoomQty() {
        return roomQty;
    }
	
	public int getBathQty() {
        return bathQty;
    }
	
	public int getFloor() {
        return floor;
    }
	
	public int isHasParking() {
        return hasParking;
    }
	
	public boolean isHasBalcony() {
		return hasBalcony;
	}
	
	public int isHasStorage() {
		return hasStorage;
	}
	
	public boolean isInCondo() {
		return inCondo;
	}
	
	public boolean isBuildingHasLift() {
		return buildingHasLift;
	}
	
	public boolean isBuildingHasPool() {
		return buildingHasPool;
	}
	
	public boolean isBuildingHasGym() {
		return buildingHasGym;
	}
	
	public boolean isBuildingHasLaundry() {
		return buildingHasLaundry;
	}
	
	public boolean isBuildingHasBBQ() {
		return buildingHasBBQ;
	}
	
	public Integer getCondoId() {
		return condoId;
	}
	
	// SETTERS
	
	public void setRoomQty(int roomQty) {
		this.roomQty = roomQty;
	}
	
	public void setBathQty(int bathQty) {
		this.bathQty = bathQty;
	}
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public void setHasParking(int hasParking) {
		this.hasParking = hasParking;
	}
	
	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}
	
	public void setHasStorage(int hasStorage) {
		this.hasStorage = hasStorage;
	}
	
	public void setInCondo(boolean inCondo) {
		this.inCondo = inCondo;
	}
	
	public void setCondoId(Integer condoId) {
		this.condoId = condoId;
	}
	
	public void setBuildingHasLift(boolean buildingHasLift) {
		this.buildingHasLift = buildingHasLift;
	}
	
	public void setBuildingHasPool(boolean buildingHasPool) {
		this.buildingHasPool = buildingHasPool;
	}
	
	public void setBuildingHasGym(boolean buildingHasGym) {
		this.buildingHasGym = buildingHasGym;
	}
	
	public void setBuildingHasLaundry(boolean buildingHasLaundry) {
		this.buildingHasLaundry = buildingHasLaundry;
	}
	
	public void setBuildingHasBBQ(boolean buildingHasBBQ) {
		this.buildingHasBBQ = buildingHasBBQ;
	}
	
	@Override
	public String toString() {
		return "Flat{" + "roomQty=" + roomQty + ", bathQty=" + bathQty + ", floorQty=" + floor + ", hasParking="
				+ hasParking + ", hasBalcony=" + hasBalcony + ", hasStorage=" + hasStorage + ", inCondo=" + inCondo
				+ ", buildingHasLift=" + buildingHasLift + ", buildingHasPool=" + buildingHasPool + ", buildingHasGym="
				+ buildingHasGym + ", buildingHasLaundry=" + buildingHasLaundry + ", buildingHasBBQ=" + buildingHasBBQ
				+ ", condoId="	+ condoId + '}';
	}
	
}