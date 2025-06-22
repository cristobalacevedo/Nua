package model;

public class Office extends Estate{
	private int floor, hasParking, hasStorage, cabinQty, meetingRoomQty;
	private boolean inCondo, buildingHasLift;
    private Integer condoId;

	public Office(int floor, int hasStorage, int hasParking, boolean buildingHasLift,
			String streetName, String num1, String num2, int townId, int regionId, int landlordId,
			int propertyTypeId, int size, String rolSII, boolean inCondo, Integer condoId) {
		// ADDRESS and PROPERTY
		super(streetName, num1, num2, townId, regionId, landlordId, propertyTypeId, size, rolSII);
		
		// OFFICE
		this.floor = floor;
		this.hasStorage = hasStorage;
		this.hasParking = hasParking;
		
		this.buildingHasLift = buildingHasLift;
		this.inCondo = inCondo;
		this.condoId = condoId; // Default value, can be set later
	}
	
	public Office() {
		// Default constructor necessary to use `new Office()`
	}
	
	// GETTERS
	
	public int getFloor() {
		return floor;
	}
	
	public int isHasParking() {
		return hasParking;
	}
	
	public int isHasStorage() {
		return hasStorage;
	}
	
	public int getMeetingRoomQty() {
		return meetingRoomQty;
	}
	
	public boolean isInCondo() {
		return inCondo;
	}
	
	public boolean isBuildingHasLift() {
		return buildingHasLift;
	}
	
	public Integer getCondoId() {
		return condoId;
	}
	
	public int getCabinQty() {
		return cabinQty;
	}
	
	
	// SETTERS
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public void setHasParking(int hasParking) {
		this.hasParking = hasParking;
	}
	
	public void setHasStorage(int hasStorage) {
		this.hasStorage = hasStorage;
	}
	
	public void setMeetingRoomQty(int meetingRoomQty) {
		this.meetingRoomQty = meetingRoomQty;
	}
	
	public void setInCondo(boolean inCondo) {
		this.inCondo = inCondo;
	}
	
	public void setBuildingHasLift(boolean buildingHasLift) {
		this.buildingHasLift = buildingHasLift;
	}
	
	public void setCondoId(Integer condoId) {
		this.condoId = condoId;
	}
	
	public void setCabinQty(int cabinQty) {
		this.cabinQty = cabinQty;
	}
	
	@Override
	public String toString() {
		return "Office [floor=" + floor + ", hasParking=" + hasParking + ", hasStorage=" + hasStorage + ", cabinQty="
				+ cabinQty + ", meetingRoomQty=" + meetingRoomQty + ", inCondo=" + inCondo + ", buildingHasLift="
				+ buildingHasLift + ", condoId=" + condoId + "]";
	}
	
}
