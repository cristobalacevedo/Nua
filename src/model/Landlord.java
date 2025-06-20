package model;

public class Landlord extends Person{
	
	private boolean isActive; // true if the landlord is currently active, false otherwise
	private boolean hasRentals; // true if the landlord has rentals, false otherwise
	
	public Landlord(String rut, String name, String surname, String email, String phone, String type, boolean isActive, boolean hasRentals, String bankName, String accountType, String accountNum) {
		super(rut, name, surname, email, phone, type, bankName, accountType, accountNum);
		this.isActive = isActive; // true by default
		this.hasRentals = hasRentals; // false by default
	}
	
	public Landlord(String rut, String name, String surname, String email, String phone, String bankName, String accountType, String accountNum) {
		super(rut, name, surname, email, phone, bankName, accountType, accountNum); // type is fixed as "landlord"
	}
	
	// GETTERS
	public boolean getIsActive() {
		return isActive;
	}
	
	public boolean getHasRentals() {
		return hasRentals;
	}
	
	public String getType() {
		return "landlord"; // Overriding to return a fixed type for Landlord
	}
	
	// END GETTERS
	
	// SETTERS
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setHasRentals(boolean hasRentals) {
		this.hasRentals = hasRentals;
	}

	@Override
	public String toString() {
		return "Landlord{name='" + getName() + '\'' + ", surname='" + getSurname() + '\'' + ", email='" + getEmail()
				+ '\'' + ", phone='" + getPhone() + '\'' + '}';
	}
}
