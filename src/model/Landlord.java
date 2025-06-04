package model;

public class Landlord extends Person{
	
	private boolean isActive; // true if the landlord is currently active, false otherwise
	private boolean hasRentals; // true if the landlord has rentals, false otherwise
	
	public Landlord(String rut, String name, String surname, String email, String phone, String type, boolean isActive, boolean hasRentals) {
		super(rut, name, surname, email, phone, type);
	}
	
	// GETTERS
	public boolean isActive() {
		return isActive;
	}
	
	public boolean hasRentals() {
		return hasRentals;
	}
	
	public String getType() {
		return "landlord"; // Overriding to return a fixed type for Landlord
	}
	
	// END GETTERS
	
	// SETTERS
	
	public void setType(String type) {
		type = "landlord";
	}
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setHasRentals(boolean hasRentals) {
		this.hasRentals = hasRentals;
	}
	
	// END SETTERS
		@Override
	public String toString() {
		return "Landlord{name='" + getName() + '\'' + ", surname='" + getSurname() + '\''
				+ ", email='" + getEmail() + '\'' + ", phone='" + getPhone() + '\'' + '}';
	}
}
