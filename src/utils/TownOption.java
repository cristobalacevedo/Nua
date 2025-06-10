package utils;

public class TownOption {
	private int id;
    private String displayTown; 
    
    public TownOption(int id, String displayTown) {
    	this.id = id;
    	this.displayTown = displayTown;
    }

	public int getId() {
		return id;
	}
	
	public String toString() {
		return displayTown;
	}


	public String getDisplayTown() {
		return displayTown;
	}
}