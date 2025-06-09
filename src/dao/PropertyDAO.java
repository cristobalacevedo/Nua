package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class PropertyDAO {
    private Connection conn;
    private HouseDAO houseDAO;

 
	public PropertyDAO(Connection connection) {
		this.conn = connection;
        this.houseDAO = new HouseDAO(conn);
	}


	public void PropertyDAO1(Connection conn) {
        this.conn = conn;
        this.houseDAO = new HouseDAO(conn);
//        this.flatDAO = new FlatDAO(conn);
//        this.landDAO = new LandDAO(conn);
//        this.storageDAO = new StorageDAO(conn);
//        this.parkingDAO = new ParkingDAO(conn);
    }

	
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

		try (Connection conn = DBConnection.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(sql)) {
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
	
	public boolean insertCompleteHouse(HousePropertyData data) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertAddress(data);
	        int propertyId = insertProperty(data, addressId);
	        houseDAO.insertHouse(propertyId, data); 
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}

    private int insertAddress(HousePropertyData data) throws SQLException {
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getStreetName());
            stmt.setString(2, data.getNum1());
            stmt.setString(3, data.getNum2());
            stmt.setInt(4, data.getTownId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }

    private int insertProperty(HousePropertyData data, int addressId) throws SQLException {
        String sql = "INSERT INTO property (landlord_id, property_type_id, address_id, size) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, data.getLandlordId());
            stmt.setInt(2, data.getPropertyTypeId());
            stmt.setInt(3, addressId);
            stmt.setInt(4, data.getSize());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de propiedad.");
                return rs.getInt(1);
            }
        }
    }
}

