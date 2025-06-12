package model;

public class Doorman extends Person{
	
	private int condoId; // ID of the condo where the doorman works
	
	public Doorman(String rut, String name, String surname, String email, String phone, String type, int condoId) {
		super(rut, name, surname, email, phone, type);
		this.condoId = condoId; // ID of the condo where the doorman works
	}
	
	// GETTERS
	
	public int getCondoId() {
		return condoId;
	}
	
	public String getType() {
		return "doorman"; // Overriding to return a fixed type for Doorman
	}
	
	// END GETTERS
	
	// SETTERS
	
	public void setCondoId(int condoId) {
		this.condoId = condoId;
	}
	
	@Override
	public String toString() {
		return "Doorman{name='" + getName() + '\'' + ", surname='" + getSurname() + '\'' + ", email='" + getEmail()
				+ '\'' + ", phone='" + getPhone() + '\'' + ", condoId=" + condoId + '}';
	}

	public String getFullName() {
        return getName() + " " + getSurname();
	}
}
