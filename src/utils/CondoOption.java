package utils;

public class CondoOption {
	private int id;
    private String displayCondo; 
    
    public CondoOption(int id, String displayCondo) {
    	this.id = id;
    	this.displayCondo = displayCondo;
    }

	public int getId() {
		return id;
	}
	
	public String toString() {
		return displayCondo;
	}


	public String getDisplayTown() {
		return displayCondo;
	}
}

