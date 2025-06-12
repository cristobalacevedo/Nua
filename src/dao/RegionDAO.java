package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class RegionDAO {
	public static List<String> getAllRegion() {
		List<String> regions = new ArrayList<>();
		String sql = "SELECT name FROM region";

		try (
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				regions.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return regions;
	}	
	
	
	public static int getRegionIDByName(String regionName) {
		int regionID = -1; // Default value if not found
		String sql = "SELECT id FROM region WHERE name = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, regionName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				regionID = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return regionID;
	}
}
