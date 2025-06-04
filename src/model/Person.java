package model;

public abstract class Person {
	
	private String rut;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private String type; // "tenant" or "landlord"

	public Person(String rut, String name, String surname, String email, String phone, String type) {
		
		this.rut = rut;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.type = type;
	}

	// GETTERS

	public String getRut() {
		return rut;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	
	public String getType() {
		return type;
	}
	
	// END GETTERS

	// SETTERS

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	// END SETTERS
	
	@Override
	public String toString() {
		return "Person{rut='" + rut + '\'' + ", name='" + name + '\'' + ", surname='" + surname
				+ '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", type='" + type + '\'' + '}';
	}
}
