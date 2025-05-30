package model;

public class Tenant extends Person {
	private boolean isActive; // true if the tenant is currently active, false otherwise
	private boolean isRenting; // true if the tenant is currently renting, false otherwise

	public Tenant(int id, String name, String surname, String email, String phone, boolean isActive,
			boolean isRenting) {
		super(id, name, surname, email, phone);
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
	
	// END GETTERS
	
	// SETTERS
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setIsRenting(boolean isRenting) {
		this.isRenting = isRenting;
	}
	
	// END SETTERS
	
	@Override
	public String toString() {
		return "Tenant{" + "id=" + getId() + ", name='" + getName() + '\'' + ", surname='" + getSurname() + '\''
				+ ", email='" + getEmail() + '\'' + ", phone='" + getPhone() + '\'' + ", isActive=" + isActive
				+ ", isRenting=" + isRenting + '}';
	}	

}

