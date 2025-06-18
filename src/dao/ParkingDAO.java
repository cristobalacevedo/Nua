package dao;

import java.sql.Types;
import java.util.List;
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
	    
	    public void insertParkingInFlat(List<Parking> parkings) throws SQLException {
	        String sql = """
	        		INSERT INTO parking (
	        		property_id, flat_id, incondo, condo_id
	        		) VALUES (?, ?, ?, ?)
	        		""";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            for (Parking parking : parkings) {
	            	stmt.setInt(1, parking.getId()); // ✅ property_id del parking
	                stmt.setInt(2, parking.getFlatId());
	                stmt.setInt(3, parking.isInCondo() ? 1 : 0); // Estaba mal el índice antes
	                stmt.setObject(4, parking.getCondoId(), Types.INTEGER);
	                stmt.addBatch();
	            }

	            stmt.executeBatch(); // Ejecuta todos los INSERTS
	        }
	    }
	}


