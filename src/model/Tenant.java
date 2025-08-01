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
	
	public Tenant(String rut, String name, String surname, String email, String phone, String type,
			boolean isActive, boolean isRenting, String aval_id) {
		super(rut, name, surname, email, phone, type);
	}
	
	
	public Tenant(String rut, String name, String surname, String email, String phone, String type,
			boolean isActive, boolean isRenting, String aval_id, String bankName, String typeAccount, String numAccount) {
		super(rut, name, surname, email, phone, type, bankName, typeAccount, numAccount);
		this.isActive = isActive;
		this.isRenting = isRenting;
	}
	
	// GETTERS
	

	public Tenant(String rut, String name, String surname, String email, String phone, String type, boolean isActive,
			boolean isRenting, String rutAval, String nameAval, String surnameAval, String emailAval, String phoneAval,
			String bankName, String accountType, String accountNum, String bankNameAval, String accountTypeAval, String accountNumAval) {
		super(rut, name, surname, email, phone, type, bankName, accountType, accountNum);
		this.isActive = isActive;
		this.isRenting = isRenting;
	}
	

	public Tenant(String rut, String name, String surname, String email, String phone, String type, boolean isActive,
			boolean isRenting, String bankName, String accountType, String accountNum) {
		super(rut, name, surname, email, phone, type, bankName, accountType, accountNum);
		this.isActive = isActive;
		this.isRenting = isRenting;
	}

	public boolean getIsActive() {
		return isActive;
	}
	
	public boolean getIsRenting() {
		return isRenting;
	}
	
	public String getType() {
		return "tenant"; // Overriding to return a fixed type for Tenant
	}

	public boolean getHasAval() {
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

