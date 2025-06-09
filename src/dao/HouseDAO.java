package dao;

import java.sql.Statement;
import java.sql.Types;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class HouseDAO {
    private final Connection conn;

    public HouseDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertHouse(int propertyId, HousePropertyData data) throws SQLException {
        String sql = """
            INSERT INTO house (
                property_id, bath_qty, room_qty, floor_qty,
                hasparking, hasgarden, haspatio, haspool, hasstorage, hasbbq,
                incondo, condo_id, condo_platform_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, propertyId);
            stmt.setInt(2, data.getBathQty());
            stmt.setInt(3, data.getRoomQty());
            stmt.setInt(4, data.getFloorQty());
            stmt.setInt(5, data.isHasParking() ? 1 : 0);
            stmt.setInt(6, data.isHasGarden() ? 1 : 0);
            stmt.setInt(7, data.isHasPatio() ? 1 : 0);
            stmt.setInt(8, data.isHasPool() ? 1 : 0);
            stmt.setInt(9, data.isHasStorage() ? 1 : 0);
            stmt.setInt(10, data.isHasBBQ() ? 1 : 0);
            stmt.setInt(11, data.isInCondo() ? 1 : 0);
            stmt.setObject(12, data.getCondoId(), Types.INTEGER);
            stmt.setObject(13, data.getCondoPlatformId(), Types.INTEGER);
            stmt.executeUpdate();
        }
    }
}