package model;

public class Landlord extends Person{
	
	private boolean isActive; // true if the landlord is currently active, false otherwise
	private boolean hasRentals; // true if the landlord has rentals, false otherwise
	
	public Landlord(int id, String name, String surname,String email, String phone, boolean isActive, boolean hasRentals) {
		super(id, name, surname, email, phone);
	}
	
	// GETTERS
	public boolean isActive() {
		return isActive;
	}
	
	public boolean hasRentals() {
		return hasRentals;
	}
	
	// END GETTERS
	
	// SETTERS
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setHasRentals(boolean hasRentals) {
		this.hasRentals = hasRentals;
	}
	
	// END SETTERS
		@Override
	public String toString() {
		return "Landlord{" + "id=" + getId() + ", name='" + getName() + '\'' + ", surname='" + getSurname() + '\''
				+ ", email='" + getEmail() + '\'' + ", phone='" + getPhone() + '\'' + '}';
	}
}
