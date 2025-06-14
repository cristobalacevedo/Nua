package model;

public class Land extends Estate {
	 private int bathQty, roomQty, floorQty;
	    private boolean hasParking, hasGarden, hasPatio, hasPool, hasStorage, hasBBQ, hasBalcony, hasTerrace, hasLaundry;
	    private boolean inCondo;
	    private Integer condoId;

	    // Constructor with all fields
		public Land(String streetName, String num1, String num2, int townId, int regionId, int landlordId,
				int propertyTypeId, int size, String rolSII, int roomQty, int bathQty, int floorQty, boolean hasParking, boolean hasGarden,
				boolean hasPatio, boolean hasPool, boolean hasStorage, boolean hasBBQ, boolean hasBalcony, boolean hasTerrace, boolean hasLaundry, boolean inCondo, Integer condoId,
				Integer condoPlatformId) {
			
			// ADDRESS and PROPERTY
			super(streetName, num1, num2, townId, regionId, landlordId, propertyTypeId, size, rolSII);
			
			// HOUSE
			this.roomQty = roomQty;
			this.bathQty = bathQty;
			this.floorQty = floorQty;
			this.hasParking = hasParking;
			this.hasGarden = hasGarden;
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
			
		}

	    // Default constructor
		public Land(){
		    // Default constructor necessary to use `new House()`
		}

		// GETTERS
		
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
		
		// END GETTERS
		
		
		// SETTERS
		
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
		
		@Override
		public String toString() {
			return "House{" + "streetName='" + getStreetName() + '\'' + ", num1='" + getNum1() + '\'' + ", num2='"
					+ getNum2() + '\'' + ", townId=" + getTownId() + ", regionId=" + getRegionId() + ", landlordId="
					+ getLandlordId() + ", propertyTypeId=" + getPropertyTypeId() + ", size=" + getSize() + ", rolSII='"
					+ getRolSII() + '\'' + ", bathQty=" + bathQty + ", roomQty=" + roomQty + ", floorQty=" + floorQty
					+ ", hasParking=" + hasParking + ", hasGarden=" + hasGarden + ", hasPatio=" + hasPatio + ", hasPool="
					+ hasPool + ", hasStorage=" + hasStorage + ", hasBBQ=" + hasBBQ + ", hasBalcony=" + hasBalcony
					+ ", hasTerrace=" + hasTerrace + ", hasLaundry=" + hasLaundry + ", inCondo=" + inCondo + ", condoId="
					+ condoId + '}';
		}
	}
