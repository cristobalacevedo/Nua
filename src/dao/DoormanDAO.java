package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import model.Doorman;

public class DoormanDAO {
	public static Doorman getByRut(String rut) {
	    String sql = """
	    	SELECT p.rut as rut, p.name as name, p.surname as surname, p.email as email, p.phone as phone, p.type as type, d.condo_id AS condoId	          
	        FROM doorman d
	    	JOIN person p ON d.person_id = p.id
	    	WHERE p.rut = ?
	    	""";
	    Connection conn = DBConnection.getConnection();
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, rut);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Doorman(
	                rs.getString("rut"),
	                rs.getString("name"),
	                rs.getString("surname"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getString("type"),
	                rs.getInt("condoId")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
