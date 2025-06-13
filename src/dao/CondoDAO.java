package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Condo;
import utils.CondoOption;

public class CondoDAO {
 
    private Connection conn;

    public CondoDAO(Connection conn) {
        this.conn = conn;
    }
	
	public static boolean insertFullCondo(Condo data) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Iniciar transacción

            CondoDAO dao = new CondoDAO(conn); // Usamos DAO con conexión compartida
            int addressId = dao.insertAddress(data); // Insertar dirección y recuperar su ID

            String sql = "INSERT INTO condo (name, email, phone, condo_platform_id, address_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, data.getName());
                stmt.setString(2, data.getEmail());
                stmt.setString(3, data.getPhone());
             
                if (data.getCondoPlatformId() != null) {
                    stmt.setInt(4, data.getCondoPlatformId());
                } else {
                    stmt.setNull(4, java.sql.Types.INTEGER);
                }
                
                stmt.setInt(5, addressId);
                stmt.executeUpdate();
            }

            conn.commit(); // Confirmar transacción
            System.out.println("Condominio insertado correctamente.");
            return true;

        } catch (SQLException e) {
            System.err.println("Error al insertar el condominio: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Error al hacer rollback: " + ex.getMessage());
            }
            return false;
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }



    private int insertAddress(Condo data) throws SQLException {
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id, town_region_id, town_region_country_id) VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getAddress());
            stmt.setString(2, data.getNum());
            stmt.setString(3, null); // Num_2 puede quedar como null
            stmt.setInt(4, data.getTownID());
            stmt.setInt(5, data.getRegionID());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }
	
    public static List<CondoOption> getAllCondoNames() {
        List<CondoOption> condos = new ArrayList<>();
        String sql = "SELECT id, name FROM condo";  // ✅ Ahora traes el id

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                int id = rs.getInt("id");                 // ✅ id desde la base
                String name = rs.getString("name");       // ✅ nombre desde la base
                condos.add(new CondoOption(id, name));    // ✅ constructor correcto
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return condos;
    }
    
    public static int getCondoIDByName(String selectedCondo) {
    	int condoID = -1; // Default value if not found
    	String sql = "SELECT id FROM condo WHERE name = ?";
    	try (Connection conn = DBConnection.getConnection();
    		PreparedStatement stmt = conn.prepareStatement(sql)) {
    		stmt.setString(1, selectedCondo);
    		ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				condoID = rs.getInt("id");
			}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return condoID;
    }
	
	public static List<String> getAllCondoPlatform() {
		List<String> platform = new ArrayList<>();
		String sql = "SELECT name FROM condo_platform";

		try (
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				platform.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return platform;
	}	
	
	public static Integer getPlatformIDByName(String platformName) {
		int platformID = -1; // Default value if not found
		String sql = "SELECT id FROM condo_platform WHERE name = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, platformName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				platformID = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return platformID;
	}

	


	
}
