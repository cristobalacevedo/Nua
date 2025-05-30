package model;

import java.sql.Date;

public class Rent {
	private int id;
	private int priceCLP;
	private Estate estate;
	private Landlord landlord;
	private Tenant tenant;
	private Date startDate;
	private Date endDate;
	private boolean isActive; // true if the rent is currently active, false otherwise
	
	public Rent(int id, int priceCLP, Estate estate, Landlord landlord, Tenant tenant, Date startDate, Date endDate,
			boolean isActive) {
		this.id = id;
		this.priceCLP = priceCLP;
		this.estate = estate;
		this.landlord = landlord;
		this.tenant = tenant;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
	}
}
