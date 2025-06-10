package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import utils.LandlordOption;
import utils.TownOption;

public class TownDAO {
	public static List<TownOption> getAllTownsByRegionID(int regionID) {
		List<TownOption> towns = new ArrayList<>();
		String sql = "SELECT id, name FROM town WHERE region_id = ?";

		try (Connection conn = DBConnection.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, regionID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String displayTown = id + " - " + name; // Display format: "ID - Name"
				towns.add(new TownOption(id, displayTown)); // Assuming TownOption is a class that holds the id and name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return towns;
	}

//	public List<LandlordOption> getAllLandlordOptions() {
//	    List<LandlordOption> list = new ArrayList<>();
//	    String sql = "SELECT person_id, name, surname, rut FROM landlord JOIN person ON landlord.person_id = person.id";
//
//	    try (Connection conn = DBConnection.getConnection();
//	         PreparedStatement stmt = conn.prepareStatement(sql);
//	         ResultSet rs = stmt.executeQuery()) {
//
//	        while (rs.next()) {
//	            int id = rs.getInt("person_id");
//	            String name = rs.getString("name") + " " + rs.getString("surname");
//	            String rut = rs.getString("rut");
//	            String displayName = id + " - " + name + " - " + rut;
//
//	            list.add(new LandlordOption(id, displayName));
//	        }
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//
//	    return list;
//	}
	
	
	public static int getTownIdByName(String selectedTown) {
		// TODO Auto-generated method stub
		return 0;
	}
}
