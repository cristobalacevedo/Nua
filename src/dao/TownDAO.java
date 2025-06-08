package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class TownDAO {
	public static List<String> getAllTownsByRegionID(int regionID) {
		List<String> towns = new ArrayList<>();
		String sql = "SELECT name FROM town WHERE region_id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, regionID);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				towns.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return towns;
	}
}
