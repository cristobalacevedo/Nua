package dao;

import java.sql.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Flat;

public class FlatDAO {
	 private final Connection conn;

	    public FlatDAO(Connection conn) {
	        this.conn = conn;
	    }

	    public void insertFlat(int propertyId, Flat data) throws SQLException {
	        String sql = """
	            INSERT INTO flat (
	                property_id, room_qty, bath_qty, floor,
	                hasstorage, hasparking, hasbalcony, buildinghaslift, buildinghaspool, buildinghasbbq, 
	        		buildinghasgym, buildinghaslaundry,
	                incondo, condo_id
	            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
	        """;
	        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, propertyId);
	            stmt.setInt(2, data.getRoomQty());
	            stmt.setInt(3, data.getBathQty());
	            stmt.setInt(4, data.getFloorQty());
	            stmt.setInt(5, data.isHasStorage() ? 1 : 0);
	            stmt.setInt(6, data.isHasParking());
	            stmt.setInt(7, data.isHasBalcony() ? 1 : 0);
	            stmt.setInt(8, data.isBuildingHasLift() ? 1 : 0);
	            stmt.setInt(9, data.isBuildingHasPool() ? 1 : 0);
	            stmt.setInt(10, data.isBuildingHasBBQ() ? 1 : 0);
	            stmt.setInt(11, data.isBuildingHasGym() ? 1 : 0);
	            stmt.setInt(12, data.isBuildingHasLaundry() ? 1 : 0);
	            stmt.setInt(13, data.isInCondo() ? 1 : 0);
	            stmt.setObject(14, data.getCondoId(), Types.INTEGER);
	           // stmt.setObject(13, data.getCondoPlatformId(), Types.INTEGER);
	            stmt.executeUpdate();
	        }
	    }
	}