package model;

public class Parking extends Estate {
	private boolean inCondo;
	private Integer condoId;

	public Parking(boolean inCondo, Integer condoId,
				   String streetName, String num1, String num2, int townId, int regionId, int landlordId,
				   int propertyTypeId, int size, String rolSII) {
		// ADDRESS and PROPERTY
		super(streetName, num1, num2, townId, regionId, landlordId, propertyTypeId, size, rolSII);
		
		// PARKING
		
		this.inCondo = inCondo;
		this.condoId = condoId; // Default value, can be set later
	}

	public Parking() {
		// Default constructor necessary to use `new Parking()`
	}

	// GETTERS

	public boolean isInCondo() {
		return inCondo;
	}

	public Integer getCondoId() {
		return condoId;
	}

	public void setInCondo(boolean inCondo) {
		this.inCondo = inCondo;
	}
	
	public void setCondoId(Integer condoId) {
		this.condoId = condoId;
	}
}
