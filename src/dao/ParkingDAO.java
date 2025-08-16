package dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Parking;
import model.Storage;

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
	    
	    public void insertParkingInOffice(List<Parking> parkings) throws SQLException {
	        String sql = """
	        		INSERT INTO parking (
	        		property_id, office_id, incondo, condo_id
	        		) VALUES (?, ?, ?, ?)
	        		""";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            for (Parking parking : parkings) {
	            	stmt.setInt(1, parking.getId()); // ✅ property_id del parking
	                stmt.setInt(2, parking.getOfficeId());
	                stmt.setInt(3, parking.isInCondo() ? 1 : 0); // Estaba mal el índice antes
	                stmt.setObject(4, parking.getCondoId(), Types.INTEGER);
	                stmt.addBatch();
	            }

	            stmt.executeBatch(); // Ejecuta todos los INSERTS
	        }
	    }

	    public static List<Parking> getParkingsByFlatId(int flatId) {
			List<Parking> parkings = new ArrayList<>();
			String sql = "SELECT id, property_id, flat_id FROM storage WHERE flat_id = ?";

			try (Connection conn = DBConnection.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, flatId);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Parking parking = new Parking(
								rs.getInt("id"),
								rs.getInt("property_id"),
								rs.getInt("flat_id")
								);
						parkings.add(parking);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return parkings;
		}
	    
	    public static List<Parking> getParkingsByOfficeId(int flatId) {
			List<Parking> parkings = new ArrayList<>();
			String sql = "SELECT id, property_id, office_id FROM storage WHERE flat_id = ?";

			try (Connection conn = DBConnection.getConnection();
					PreparedStatement ps = conn.prepareStatement(sql)) {

				ps.setInt(1, flatId);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						Parking parking = new Parking(
								rs.getInt("id"),
								rs.getInt("property_id"),
								rs.getInt("office_id")
								);
						parkings.add(parking);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return parkings;
		}
	    
	    
	}