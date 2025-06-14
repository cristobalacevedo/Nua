package model;

public class Condo {
	String name;
	private String email;
	private String phone;
	private String address;
	private int townID;
	private int regionID;
	private Integer condo_platform_id;
	private String num;
	
	public Condo(String name, String address, String num, int townID, int regionID, String email, String phone, Integer condo_platform_id) {
		this.name = name;
		this.address = address;
		this.num = num;
		this.townID = townID;
		this.regionID = regionID;
		this.email = email;
		this.phone = phone;
		this.condo_platform_id = condo_platform_id;
	}
	
	public Condo() {
		
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getNum() {
		return num;
	}
	
	public int getTownID() {
		return townID;
	}
	
	public int getRegionID() {
		return regionID;
	}
	
	public Integer getCondoPlatformId() {
		return condo_platform_id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public void setTownID(int townID) {
		this.townID = townID;
	}
	
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
	
	public void setCondoPlatformId(Integer condo_platform_id) {
		this.condo_platform_id = condo_platform_id;
	}
	
	
	@Override
	public String toString() {
		return "Condo{name='" + name + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", address='"
				+ address + '\'' + ", num='" + num + '\'' + ", condo_platform_id=" + condo_platform_id + '}';
	}
	
}
