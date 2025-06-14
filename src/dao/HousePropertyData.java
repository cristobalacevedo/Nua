package dao;

public class HousePropertyData {
	private String streetName, num1, num2, rolSII;
    private int townId, regionId, landlordId, propertyTypeId, size;
    private int bathQty, roomQty, floorQty;
    private boolean hasParking, hasGarden, hasPatio, hasPool, hasStorage, hasBBQ, hasBalcony, hasTerrace, hasLaundry;
    private boolean inCondo;
    private Integer condoId;
    
	public HousePropertyData(String streetName, String num1, String num2, int townId, int regionId, int landlordId,
			int propertyTypeId, String rolSII, int size, int bathQty, int roomQty, int floorQty, boolean hasParking, boolean hasGarden,
			boolean hasPatio, boolean hasPool, boolean hasStorage, boolean hasBBQ, boolean hasBalcony, boolean hasTerrace, boolean hasLaundry, boolean inCondo, Integer condoId,
			Integer condoPlatformId) {
		
		// ADDRESS
		this.streetName = streetName;
		this.num1 = num1;
		this.num2 = num2;
		this.townId = townId;
		this.regionId = regionId;
		// ADDRESS
		
		// PROPERTY
		this.landlordId = landlordId;
		this.propertyTypeId = propertyTypeId;
		this.size = size;
		this.rolSII = null; // Asumiendo que rolSII es un campo opcional, inicializado a null
		// PROPERTY
		
		// HOUSE
		this.bathQty = bathQty;
		this.roomQty = roomQty;
		this.floorQty = floorQty;
		this.hasParking = hasParking;
		this.hasGarden = hasGarden;
		this.hasPatio = hasPatio;
		this.hasPool = hasPool;
		this.hasStorage = hasStorage;
		this.hasBBQ = hasBBQ;
		this.hasBalcony = hasBalcony;
		this.hasTerrace = hasTerrace;
		this.hasLaundry = hasLaundry;
		this.inCondo = inCondo;
		this.condoId = condoId;
		// HOUSE
	}
	
	public HousePropertyData() {
	    // Constructor por defecto necesario para poder usar `new HousePropertyData()`
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
	
	public int getBathQty() {
		return bathQty;
	}
	
	public int getRoomQty() {
		return roomQty;
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
	
	public boolean isHasPatio() {
		return hasPatio;
	}
	
	public boolean isHasPool() {
		return hasPool;
	}
	
	public boolean isHasStorage() {
		return hasStorage;
	}
	
	public boolean isHasBBQ() {
		return hasBBQ;
	}
	
	public boolean isHasBalcony() {
		return hasBalcony;
	}
	
	public boolean isHasTerrace() {
		return hasTerrace;
	}

	public boolean isHasLaundry() {
		return hasLaundry;
	}
	
	public boolean isInCondo() {
		return inCondo;
	}

	public Integer getCondoId() {
		return condoId;
	}
	
	public String getRolSII() {
		return rolSII;
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
	
	public void setBathQty(int bathQty) {
		this.bathQty = bathQty;
	}
	
	public void setRoomQty(int roomQty) {
		this.roomQty = roomQty;
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
	
	public void setHasPatio(boolean hasPatio) {
		this.hasPatio = hasPatio;
	}
	
	public void setHasPool(boolean hasPool) {
		this.hasPool = hasPool;
	}
	
	public void setHasStorage(boolean hasStorage) {
		this.hasStorage = hasStorage;
	}
	
	public void setHasBBQ(boolean hasBBQ) {
		this.hasBBQ = hasBBQ;
	}
	
	public void setInCondo(boolean inCondo) {
		this.inCondo = inCondo;
	}
	
	public void setCondoId(Integer condoId) {
		this.condoId = condoId;
	}

	public void setHasBalcony(boolean selected) {
		this.hasBalcony = selected;
	}
	
	public void setHasTerrace(boolean selected) {
		this.hasTerrace = selected;
	}
	
	public void setHasLaundry(boolean selected) {
		this.hasLaundry = selected;
	}

	public void setRolSII(String rolSII) {
		this.rolSII = rolSII;
	}
}
