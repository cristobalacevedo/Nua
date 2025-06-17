package dao;

import java.sql.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.House;

public class HouseDAO {
    private final Connection conn;

    public HouseDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertHouse(int propertyId, House data) throws SQLException {
        String sql = """
            INSERT INTO house (
                property_id, room_qty, bath_qty, floor_qty,
                hasparking, hasgarden, haspatio, haspool, hasstorage, haslaundry, hasbbq,
                incondo, condo_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, propertyId);
            stmt.setInt(2, data.getRoomQty());
            stmt.setInt(3, data.getBathQty());
            stmt.setInt(4, data.getFloorQty());
            stmt.setInt(5, data.isHasParking() ? 1 : 0);
            stmt.setInt(6, data.isHasGarden() ? 1 : 0);
            stmt.setInt(7, data.isHasPatio() ? 1 : 0);
            stmt.setInt(8, data.isHasPool() ? 1 : 0);
            stmt.setInt(9, data.isHasStorage() ? 1 : 0);
            stmt.setInt(10, data.isHasLaundry() ? 1 : 0);
            stmt.setInt(11, data.isHasBBQ() ? 1 : 0);
            stmt.setInt(12, data.isInCondo() ? 1 : 0);
            stmt.setObject(13, data.getCondoId(), Types.INTEGER);
           // stmt.setObject(13, data.getCondoPlatformId(), Types.INTEGER);
            stmt.executeUpdate();
        }
    }
}