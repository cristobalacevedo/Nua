package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class PropertyDAO {
	public static List<String> getAllTypes() {
		List<String> propertyTypes = new ArrayList<>();
		String sql = "SELECT name FROM property_type";

		try (
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				propertyTypes.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return propertyTypes;
	}
	
	public static int getTypeIDByName(String typeName) {
		int typeID = -1; // Default value if not found
		String sql = "SELECT id FROM property_type WHERE name = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, typeName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				typeID = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return typeID;
	}
}
