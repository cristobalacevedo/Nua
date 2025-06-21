package model;

public class Storage extends Estate {
	private boolean inCondo;
	private Integer condoId, flatId, officeId;
	private Integer id;

	public Storage(Integer flatId, boolean inCondo, Integer condoId,
				   String streetName, String num1, String num2, int townId, int regionId, int landlordId,
				   int propertyTypeId, int size, String rolSII) {
		// ADDRESS and PROPERTY
		super(streetName, num1, num2, townId, regionId, landlordId, propertyTypeId, size, rolSII);
		
		// PARKING
		this.flatId = null; // Default value, can be set later
		this.officeId = null; // Default value, can be set later
		this.inCondo = inCondo;
		this.condoId = condoId; 
	}

	public Storage() {
		// Default constructor necessary to use `new Parking()`
	}

	// GETTERS
	
	public int getId() {
		return id;
	}

	public Integer getFlatId() {
		return flatId;
	}
	
	public Integer getOfficeId() {
		return officeId; 
	}

	public boolean isInCondo() {
		return inCondo;
	}

	public Integer getCondoId() {
		return condoId;
	}
	
	// SETTERS
	
	public void setFlatId(Integer flatId) {
		this.flatId = flatId;
	}
	
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
	
	public void setInCondo(boolean inCondo) {
		this.inCondo = inCondo;
	}
	
	public void setCondoId(Integer condoId) {
		this.condoId = condoId;
	}
	
	public void setRol(String rol) {
		// TODO Auto-generated method stub
	}
	
	public void setId(int storagePropertyId) {
		this.id = storagePropertyId; // Assuming id is inherited from Estate
	}
}