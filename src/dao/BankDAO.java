package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class BankDAO {
	public List<String> getAllBankNames() {
	    List<String> bankNames = new ArrayList<>();
	    String sql = "SELECT name FROM bank";

	    try (
	    	Connection conn = DBConnection.getConnection();
	    	PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery()) {
	        while (rs.next()) {
	            bankNames.add(rs.getString("name"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return bankNames;
	}

	public String getBankByName(String string) {
		
		String sql = "SELECT name FROM bank WHERE name = ?";
		String bankName = null;

		try (
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, string);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				bankName = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bankName;
	}

	public static int getIdByName(String selectedBankName) {
		String sql = "SELECT id FROM bank WHERE name = ?";
		int bankId = -1; // Default value if not found

		try (
			Connection conn = DBConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, selectedBankName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				bankId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bankId;
	}
}
