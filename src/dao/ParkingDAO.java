package dao;

import java.sql.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Parking;

public class ParkingDAO {
	 private final Connection conn;

	    public ParkingDAO(Connection conn) {
	        this.conn = conn;
	    }

	    public void insertParking(int propertyId, Parking data) throws SQLException {
	        String sql = """
	            INSERT INTO parking (
	                property_id, incondo, condo_id
	            ) VALUES (?, ?, ?)
	        """;
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        	
	            stmt.setInt(1, propertyId);
	            stmt.setInt(2, data.isInCondo() ? 1 : 0);
	            stmt.setObject(3, data.getCondoId(), Types.INTEGER);
	           
	            stmt.executeUpdate();
	        }
	    }
	}