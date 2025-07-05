package model;

public class Tenant extends Person {
	private boolean isActive; // true if the tenant is currently active, false otherwise
	private boolean isRenting; // true if the tenant is currently renting, false otherwise
	private boolean hasAval; // true if the tenant has an aval, false otherwise
 
	public Tenant(int id, String rut, String name, String surname, String email, String phone, String type, String bankName, String typeAccount, String numAccount, boolean isActive, boolean isRenting, boolean hasAval) {
		super(rut, name, surname, email, phone, type, bankName, typeAccount, numAccount);
		this.isActive = isActive;
		this.isRenting = isRenting;
		this.hasAval = hasAval; // Default value, can be set later if needed
	}
	
	// GETTERS
	
	public boolean isActive() {
		return isActive;
	}
	
	public boolean isRenting() {
		return isRenting;
	}
	
	public String getType() {
		return "tenant"; // Overriding to return a fixed type for Tenant
	}

	public boolean hasAval() {
		return hasAval;
	}
	
	// END GETTERS
	
	// SETTERS
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setIsRenting(boolean isRenting) {
		this.isRenting = isRenting;
	}
	
	public void setType(String type) {
		type = "tenant"; // Overriding to return a fixed type for Tenant
	}
	
	public void setHasAval(boolean hasAval) {
		this.hasAval = hasAval;
	}
	
	// END SETTERS
	
	@Override
	public String toString() {
		return "Tenant [isActive=" + isActive + ", isRenting=" + isRenting + ", hasAval=" + hasAval + ", " + super.toString() + "]";
	}

}

