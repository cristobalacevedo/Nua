package model;

public class Flat extends Estate {
	private int roomQty, bathQty, floorQty;
	private boolean hasParking, hasGarden, hasStorage;
	private boolean inCondo, buildingHasLift, buildingHasPool, buildingHasGym, buildingHasLaundry, buildingHasBBQ;
	

	public Flat(int roomQty, int bathQty, int floorQty, boolean hasParking, boolean hasGarden,
			boolean hasStorage, boolean inCondo, boolean buildingHasLift, boolean buildingHasPool,
			boolean buildingHasGym, boolean buildingHasLaundry, boolean buildingHasBBQ,
			String streetName, String num1, String num2, int townId, int regionId, int landlordId,
			int propertyTypeId, int size, String rolSII) {
		// ADDRESS and PROPERTY
		super(streetName, num1, num2, townId, regionId, landlordId, propertyTypeId, size, rolSII);
		
		// FLAT
		this.roomQty = roomQty;
		this.bathQty = bathQty;
		this.floorQty = floorQty;
		this.hasParking = hasParking;
		this.hasGarden = hasGarden;
		this.hasStorage = hasStorage;
		this.inCondo = inCondo;
		this.buildingHasLift = buildingHasLift;
		this.buildingHasPool = buildingHasPool;
		this.buildingHasGym = buildingHasGym;
		this.buildingHasLaundry = buildingHasLaundry;
		this.buildingHasBBQ = buildingHasBBQ;
			
	}

	// GETTERS

	public int getRoomQty() {
        return roomQty;
    }
	
	public int getBathQty() {
        return bathQty;
    }
	
	public int getFloorQty() {
        return floorQty;
    }
	
	public boolean isHasParking() {
        return hasParking;
    }
	
	public boolean isHasGarden() {
		return hasGarden;
	}
	
	public boolean isHasStorage() {
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
	
	// SETTERS
	
	public void setRoomQty(int roomQty) {
		this.roomQty = roomQty;
	}
	
	public void setBathQty(int bathQty) {
		this.bathQty = bathQty;
	}
	
	public void setFloorQty(int floorQty) {
		this.floorQty = floorQty;
	}
	
	public void setHasParking(boolean hasParking) {
		this.hasParking = hasParking;
	}
	
	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}
	
	public void setHasStorage(boolean hasStorage) {
		this.hasStorage = hasStorage;
	}
	
	public void setInCondo(boolean inCondo) {
		this.inCondo = inCondo;
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
		return "Flat{" + "roomQty=" + roomQty + ", bathQty=" + bathQty + ", floorQty=" + floorQty + ", hasParking="
				+ hasParking + ", hasGarden=" + hasGarden + ", hasStorage=" + hasStorage + ", inCondo=" + inCondo
				+ ", buildingHasLift=" + buildingHasLift + ", buildingHasPool=" + buildingHasPool + ", buildingHasGym="
				+ buildingHasGym + ", buildingHasLaundry=" + buildingHasLaundry + ", buildingHasBBQ=" + buildingHasBBQ
				+ '}';
	}
	
}