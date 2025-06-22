package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import model.Office;

public class OfficeDAO {
	private final Connection conn;

    public OfficeDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void insertOffice(int propertyId, Office data) throws SQLException {
        String sql = """
            INSERT INTO office (
                property_id, floor, cabin_qty, meeting_room_qty, hasstorage,  hasparking, buildinghaslift
            ) VALUES (?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, propertyId);
            stmt.setInt(2, data.getFloor());
            stmt.setInt(3, data.getCabinQty());
            stmt.setInt(4, data.getMeetingRoomQty());
            stmt.setInt(5, data.isHasStorage());
            stmt.setInt(6, data.isHasParking());
            stmt.setInt(7, data.isBuildingHasLift() ? 1 : 0);
           // stmt.setObject(13, data.getCondoPlatformId(), Types.INTEGER);
            stmt.executeUpdate();
        }
    }
}
