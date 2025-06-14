package model;

public class Doorman extends Person{
	
	private int condoId; // ID of the condo where the doorman works
	private String condoName; // Name of the condo where the doorman works
	
	public Doorman(String rut, String name, String surname, String email, String phone, String type, int condoId) {
		super(rut, name, surname, email, phone, type);
		this.condoId = condoId; // ID of the condo where the doorman works
	}
	
	public Doorman() {
		
	}
	
	// GETTERS
	
	
	
	public Doorman(String rut, String name, String surname, String email, String phone, String type, int condoId, String condoName) {
		super(rut, name, surname, email, phone, type);
		this.condoId = condoId; // ID of the condo where the doorman works
		this.condoName = condoName; // Name of the condo where the doorman works
		// TODO Auto-generated constructor stub
	}

	public int getCondoId() {
		return condoId;
	}
	
	public String getType() {
		return "doorman"; // Overriding to return a fixed type for Doorman
	}
	
	public String getCondoName() {
		return condoName;
		
	}
	
	// END GETTERS
	
	// SETTERS
	
	public void setCondoId(int condoId) {
		this.condoId = condoId;
	}
	
	public void setCondoName(String condoName) {
		this.condoName = condoName;
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
