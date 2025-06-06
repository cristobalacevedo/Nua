package dao;

import model.Landlord;
import db.DBConnection;

import java.sql.*;
import java.util.*;

public class LandlordDAO {

	private Connection conn;

	public LandlordDAO(Connection conn) {
	        this.conn = conn;
	    }


	public boolean save(Landlord landlord, int bankId, String accountType, String accountNum) {
	    String insertPersonSQL = "INSERT INTO person (rut, name, surname, email, phone, type) VALUES (?, ?, ?, ?, ?, 'landlord')";
	    String insertLandlordSQL = "INSERT INTO landlord (person_id, isactive, hasrentals) VALUES (?, ?, ?)";
	    String insertBankAccountSQL = "INSERT INTO bankaccount (bank_id, person_id, type, num) VALUES (?, ?, ?, ?)";

	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement personStmt = conn.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement landlordStmt = conn.prepareStatement(insertLandlordSQL);
	        PreparedStatement bankStmt = conn.prepareStatement(insertBankAccountSQL)) {

	        conn.setAutoCommit(false);

	        // Insertar en tabla person
	        personStmt.setString(1, landlord.getRut());
	        personStmt.setString(2, landlord.getName());
	        personStmt.setString(3, landlord.getSurname());
	        personStmt.setString(4, landlord.getEmail());
	        personStmt.setString(5, landlord.getPhone());
	        personStmt.executeUpdate();

	        ResultSet generatedKeys = personStmt.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            int personId = generatedKeys.getInt(1);

	            // Insertar en tabla landlord
	            landlordStmt.setInt(1, personId);
	            landlordStmt.setInt(2, landlord.getIsActive() ? 1 : 0);
	            landlordStmt.setInt(3, landlord.getHasRentals() ? 1 : 0);
	            landlordStmt.executeUpdate();

	            // Insertar en tabla bankaccount
	            bankStmt.setInt(1, bankId);
	            bankStmt.setInt(2, personId);
	            bankStmt.setString(3, accountType);
	            bankStmt.setString(4, accountNum);
	            bankStmt.executeUpdate();

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


    public List<Landlord> getAll() {
        List<Landlord> list = new ArrayList<>();
        String sql = """
            SELECT p.id, p.rut, p.name, p.surname, p.phone, p.email, p.type, l.isactive, l.hasrentals
            FROM person p
            JOIN landlord l ON p.id = l.person_id
        """;
        Connection conn = DBConnection.getConnection();
        try (
        		PreparedStatement stmt = conn.prepareStatement(sql);
        		ResultSet rs = stmt.executeQuery()) {
            	while (rs.next()) {
                Landlord l = new Landlord(
                		rs.getString("rut"),
                		rs.getString("name"),
                		rs.getString("surname"),
                		rs.getString("email"),
                		rs.getString("phone"),
                		rs.getString("type"),
                		rs.getBoolean("isactive"),
                		rs.getBoolean("hasrentals"),
                		rs.getString("name"),
                		rs.getString("type"),
                		rs.getString("type")
                );
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public static Landlord getByRut(String rut) {
	    String sql = """
	        SELECT p.rut, p.name, p.surname, p.email, p.phone, p.type, l.isactive, l.hasrentals
	        FROM person p
	        JOIN landlord l ON p.id = l.person_id
	        WHERE p.rut = ?
	    """;
	    Connection conn = DBConnection.getConnection();
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, rut);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Landlord(
	                rs.getString("rut"),
	                rs.getString("name"),
	                rs.getString("surname"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getString("type"),
	                rs.getBoolean("isactive"),
	                rs.getBoolean("hasrentals"),
	                rs.getString("type"),
	                rs.getString("type"),
	                rs.getString("type")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static Landlord getByRut2(String rut) {
	    String sql = """
	        SELECT p.rut, p.name, p.surname, p.email, p.phone, p.type, l.isactive, l.hasrentals, ba.num AS accountNum, ba.type AS accountType, b.name AS bankName
	        FROM landlord l
	    	JOIN person p ON l.person_id = p.id
	    	JOIN bankaccount ba ON ba.person_id = p.id 
	    	JOIN bank b ON ba.bank_id = b.id
	    	WHERE p.rut = ?
	    	""";
	    Connection conn = DBConnection.getConnection();
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, rut);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Landlord(
	                rs.getString("rut"),
	                rs.getString("name"),
	                rs.getString("surname"),
	                rs.getString("email"),
	                rs.getString("phone"),
	                rs.getString("type"),
	                rs.getBoolean("isactive"),
	                rs.getBoolean("hasrentals"),
	                rs.getString("bankName"),
	                rs.getString("accountType"),
	                rs.getString("accountNum")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
}