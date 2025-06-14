package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;
import model.Doorman;

public class DoormanDAO {
	
	private Connection conn;

	public DoormanDAO(Connection conn) {
	        this.conn = conn;
	    }
	
	public static Doorman getByRut(String rut) {
	    String sql = """
	    	SELECT p.rut as rut, p.name as name, p.surname as surname, p.email as email, p.phone as phone, p.type as type, d.condo_id AS condoId, c.name AS condoName          
	        FROM doorman d
	    	JOIN person p ON d.person_id = p.id
	    	JOIN condo c ON d.condo_id = c.id
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
	                rs.getInt("condoId"),
	                rs.getString("condoName") // Assuming condoName is also retrieved
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static boolean save(Doorman doorman) {
		String insertPersonSQL = "INSERT INTO person (rut, name, surname, email, phone, type) VALUES (?, ?, ?, ?, ?, 'doorman')"; 
	    String insertDoormanSQL = "INSERT INTO doorman (person_id, condo_id) VALUES (?, ?)";
	    
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement personStmt = conn.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement doormanStmt = conn.prepareStatement(insertDoormanSQL)){
			conn.setAutoCommit(false);
			
			// Insert into person table
	        personStmt.setString(1, doorman.getRut());
	        personStmt.setString(2, doorman.getName());
	        personStmt.setString(3, doorman.getSurname());
	        personStmt.setString(4, doorman.getEmail());
	        personStmt.setString(5, doorman.getPhone());
	        personStmt.executeUpdate();
	        
	        ResultSet generatedKeys = personStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				int personId = generatedKeys.getInt(1);

				// Insert into doorman table
				doormanStmt.setInt(1, personId);
				doormanStmt.setInt(2, doorman.getCondoId());
				doormanStmt.executeUpdate();

				conn.commit();
				
				return true;
			} else {
	            conn.rollback();
	            System.out.println("No se pudo obtener el ID generado para person.");
			}

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}

	public static boolean delete(String rut) {
			String deleteDoormanSQL = "DELETE FROM doorman WHERE person_id = (SELECT id FROM person WHERE rut = ?)";
			String deletePersonSQL = "DELETE FROM person WHERE rut = ?";
	
			try (Connection conn = DBConnection.getConnection();
					PreparedStatement doormanStmt = conn.prepareStatement(deleteDoormanSQL);
					PreparedStatement personStmt = conn.prepareStatement(deletePersonSQL)) {
		
				conn.setAutoCommit(false);
		
				// Delete from doorman table
				doormanStmt.setString(1, rut);
				int rowsAffectedDoorman = doormanStmt.executeUpdate();
		
				// Delete from person table
				personStmt.setString(1, rut);
				int rowsAffectedPerson = personStmt.executeUpdate();
		
				if (rowsAffectedDoorman > 0 && rowsAffectedPerson > 0) {
					conn.commit();
				return true;
				} else {
			conn.rollback();
			System.out.println("No se pudo eliminar el doorman o la persona.");
			return false;
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
			return false;

	}
}
