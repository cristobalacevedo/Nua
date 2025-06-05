package dao;

import model.Landlord;
import db.DBConnection;

import java.sql.*;
import java.util.*;

public class LandlordDAO {

    private Connection conn;

    public LandlordDAO() {
        this.conn = DBConnection.getConnection();
    }

    public boolean save(Landlord landlord) {
        String insertPersonSQL = "INSERT INTO person (rut, name, surname, email, phone, type) VALUES (?, ?, ?, ?, ?, 'landlord')";
        String insertLandlordSQL = "INSERT INTO landlord (person_id, isactive, hasrentals) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement personStmt = conn.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement landlordStmt = conn.prepareStatement(insertLandlordSQL)) {

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
                landlordStmt.setInt(2, landlord.getIsActive()? 1 : 0);
                landlordStmt.setInt(3, landlord.getHasRentals() ? 1 : 0);
                landlordStmt.executeUpdate();

                conn.commit();
                return true;
            } else {
                conn.rollback();
                System.out.println("No se pudo obtener el ID generado para person."); // SPANISH
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

        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
                    rs.getBoolean("hasrentals")
                );
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
	public Landlord getByRut(String rut) {
	    String sql = """
	        SELECT p.rut, p.name, p.surname, p.email, p.phone, p.type, l.isactive, l.hasrentals
	        FROM person p
	        JOIN landlord l ON p.id = l.person_id
	        WHERE p.rut = ?
	    """;

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
	                rs.getBoolean("hasrentals")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}