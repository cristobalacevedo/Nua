package model;

public class Aval extends Person {
	private boolean isActive; // true if the aval is currently active, false otherwise
	
	public Aval(String rut, String name, String surname, String email, String phone, String type, boolean isActive,
			String bankName, String accountType, String accountNum) {
		super(rut, name, surname, email, phone, type, bankName, accountType, accountNum);
		this.isActive = isActive;
	}

	// GETTERS
	
	public boolean getIsActive() {
		return isActive;
	}

	// SETTERS
	
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	@Override
	public String toString() {
		return "Aval [rut=" + getRut() + ", name=" + getName() + ", surname=" + getSurname() + ", email=" + getEmail()
				+ ", phone=" + getPhone() + ", type=" + getType() + ", isActive=" + isActive
				+ "]";
	}
}
