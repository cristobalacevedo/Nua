package model;

public class Tenant extends Person {
	private boolean isActive; // true if the tenant is currently active, false otherwise
	private boolean isRenting; // true if the tenant is currently renting, false otherwise

	public Tenant(int id, String rut, String name, String surname, String email, String phone, String type, boolean isActive,
			boolean isRenting) {
		super(rut, name, surname, email, phone, type);
		this.isActive = isActive;
		this.isRenting = isRenting;
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
	
	// END SETTERS
	
	@Override
	public String toString() {
		return "Tenant{name='" + getName() + '\'' + ", surname='" + getSurname() + '\''
				+ ", email='" + getEmail() + '\'' + ", phone='" + getPhone() + '\'' + '}';
	}

}

