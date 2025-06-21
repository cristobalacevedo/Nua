package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import model.Storage;

public class StorageDAO {
	 private final Connection conn;

	    public StorageDAO(Connection conn) {
	        this.conn = conn;
	    }

	    public void insertStorage(int propertyId, Storage data) throws SQLException {
	        String sql = """
	            INSERT INTO storage (
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
	    
	    public void insertParkingInFlat(List<Storage> storages) throws SQLException {
	        String sql = """
	        		INSERT INTO storage (
	        		property_id, flat_id, incondo, condo_id
	        		) VALUES (?, ?, ?, ?)
	        		""";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            for (Storage storage : storages) {
	            	stmt.setInt(1, storage.getId()); // ✅ property_id del parking
	                stmt.setInt(2, storage.getFlatId());
	                stmt.setInt(3, storage.isInCondo() ? 1 : 0); // Estaba mal el índice antes
	                stmt.setObject(4, storage.getCondoId(), Types.INTEGER);
	                stmt.addBatch();
	            }

	            stmt.executeBatch(); // Ejecuta todos los INSERTS
	        }
	    }
	    
	    public void insertParkingInOffice(List<Storage> storages) throws SQLException {
	        String sql = """
	        		INSERT INTO storages (
	        		property_id, office_id, incondo, condo_id
	        		) VALUES (?, ?, ?, ?)
	        		""";

	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            for (Storage storage : storages) {
	            	stmt.setInt(1, storage.getId()); // ✅ property_id del parking
	                stmt.setInt(2, storage.getOfficeId());
	                stmt.setInt(3, storage.isInCondo() ? 1 : 0); // Estaba mal el índice antes
	                stmt.setObject(4, storage.getCondoId(), Types.INTEGER);
	                stmt.addBatch();
	            }

	            stmt.executeBatch(); // Ejecuta todos los INSERTS
	        }
	    }
	}