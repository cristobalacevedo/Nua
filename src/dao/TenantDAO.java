package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.Tenant;
import model.Aval;
import utils.TenantOption;

public class TenantDAO {
	private Connection conn;

	public TenantDAO(Connection conn) {
	        this.conn = conn;
	    }


	public boolean saveTenant(Tenant tenant, int bankId, String accountType, String accountNum) {
	    String insertPersonTenantSQL = "INSERT INTO person (rut, name, surname, email, phone, type) VALUES (?, ?, ?, ?, ?, 'tenant')";
	    String insertTenantSQL = "INSERT INTO tenant (person_id, isactive, hasrentals, aval_id) VALUES (?, ?, ?, ?)";
	    String insertBankAccountSQL = "INSERT INTO bankaccount (bank_id, person_id, type, num) VALUES (?, ?, ?, ?)";
	   // String insertPersonAvalSQL = "INSERT INTO person (rut, name, surname, email, phone, type) VALUES (?, ?, ?, ?, ?, 'aval')";
       // String insertAvalSQL = "INSERT INTO aval (person_id) VALUES (?)";	 
	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement personTenantStmt = conn.prepareStatement(insertPersonTenantSQL, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement tenantStmt = conn.prepareStatement(insertTenantSQL);
	        PreparedStatement bankStmt = conn.prepareStatement(insertBankAccountSQL);
	    	//PreparedStatement personAvalStmt = conn.prepareStatement(insertPersonAvalSQL, Statement.RETURN_GENERATED_KEYS);
	    	//PreparedStatement avalInsertStmt = conn.prepareStatement(insertAvalSQL)
	    			){

	        conn.setAutoCommit(false);

	        // Insert in person table for tenant
	        personTenantStmt.setString(1, tenant.getRut());
	        personTenantStmt.setString(2, tenant.getName());
	        personTenantStmt.setString(3, tenant.getSurname());
	        personTenantStmt.setString(4, tenant.getEmail());
	        personTenantStmt.setString(5, tenant.getPhone());
	        personTenantStmt.executeUpdate();

	        // Obtain the generated ID for the person
	        ResultSet generatedKeysLandlord = personTenantStmt.getGeneratedKeys();
	        if (generatedKeysLandlord.next()) {
	            int personId = generatedKeysLandlord.getInt(1);

	            // Insert in tenant table
	            tenantStmt.setInt(1, personId);
	            tenantStmt.setInt(2, tenant.getIsActive() ? 1 : 0);
	            tenantStmt.setInt(3, tenant.getIsRenting() ? 1 : 0);
	            tenantStmt.setInt(4, tenant.getHasAval() ? 1 : 0); // Assuming aval_id is nullable
	            tenantStmt.executeUpdate();

	            // Insert bank account
	            bankStmt.setInt(1, bankId);
	            bankStmt.setInt(2, personId);
	            bankStmt.setString(3, accountType);
	            bankStmt.setString(4, accountNum);
	            bankStmt.executeUpdate();
	            
//	            // Insert in person table for aval
//	            ResultSet generatedKeysAval = personAvalStmt.getGeneratedKeys();
//				if (generatedKeysAval.next()) {
//					
//					// Obtain the generated ID for the aval person
//					int avalPersonId = generatedKeysAval.getInt(1);
//					
//					// Insert aval person details
//					personAvalStmt.setString(1, tenant.getRut());
//					personAvalStmt.setString(2, tenant.getName());
//					personAvalStmt.setString(3, tenant.getSurname());
//					personAvalStmt.setString(4, tenant.getEmail());
//					personAvalStmt.setString(5, tenant.getPhone());
//					personAvalStmt.executeUpdate();
//
//					// Insertar en tabla aval
//					avalInsertStmt.setInt(1, avalPersonId);
//					avalInsertStmt.executeUpdate();
//					
//					conn.commit();
//					return true;
//				}
	            
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
	
	public boolean saveTenantAval(Tenant tenant, int bankId, String accountType, String accountNum, Aval aval, int bankIdAval, String accountTypeAval, String accountNumAval) {
	    String insertPersonTenantSQL = "INSERT INTO person (rut, name, surname, email, phone, type) VALUES (?, ?, ?, ?, ?, 'tenant')";
	    String insertTenantSQL = "INSERT INTO tenant (person_id, isactive, hasrentals, aval_id) VALUES (?, ?, ?, ?)";
	    String insertBankAccountSQL = "INSERT INTO bankaccount (bank_id, person_id, type, num) VALUES (?, ?, ?, ?)";
	    String insertPersonAvalSQL = "INSERT INTO person (rut, name, surname, email, phone, type) VALUES (?, ?, ?, ?, ?, 'aval')";
        String insertAvalSQL = "INSERT INTO aval (person_id) VALUES (?)";	 
        String insertBankAccountAvalSQL = "INSERT INTO bankaccount (bank_id, person_id, type, num) VALUES (?, ?, ?, ?)";
	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement personTenantStmt = conn.prepareStatement(insertPersonTenantSQL, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement tenantStmt = conn.prepareStatement(insertTenantSQL);
	        PreparedStatement bankStmt = conn.prepareStatement(insertBankAccountSQL);
	    	PreparedStatement personAvalStmt = conn.prepareStatement(insertPersonAvalSQL, Statement.RETURN_GENERATED_KEYS);
	    	PreparedStatement avalStmt = conn.prepareStatement(insertAvalSQL);
	    	PreparedStatement bankAvalStmt = conn.prepareStatement(insertBankAccountAvalSQL);
	    			){

	        conn.setAutoCommit(false);

	        // Insert in person table for aval
	        personAvalStmt.setString(1, aval.getRut());
	        personAvalStmt.setString(2, aval.getName());
	        personAvalStmt.setString(3, aval.getSurname());
	        personAvalStmt.setString(4, aval.getEmail());
	        personAvalStmt.setString(5, aval.getPhone());
	        personAvalStmt.executeUpdate();

	        // Obtain the generated ID for the aval person
	        ResultSet generatedKeysAval = personAvalStmt.getGeneratedKeys();
	        if (generatedKeysAval.next()) {
	            int avalPersonId = generatedKeysAval.getInt(1);

	            // Insert in aval table
	            avalStmt.setInt(1, avalPersonId);
//	            avalStmt.setInt(2, aval.getIsActive() ? 1 : 0);
//	            avalStmt.setInt(3, aval.getIsRenting() ? 1 : 0);
//	            avalStmt.setInt(4, aval.getHasAval() ? 1 : 0); // Assuming aval_id is nullable
	            avalStmt.executeUpdate();

	            // Insert bank account for aval
	            bankAvalStmt.setInt(1, bankId);
	            bankAvalStmt.setInt(2, avalPersonId);
	            bankAvalStmt.setString(3, accountType);
	            bankAvalStmt.setString(4, accountNum);
	            bankAvalStmt.executeUpdate();
	            
	            // Insert in person table for tenant
	            ResultSet generatedKeysTenant = avalStmt.getGeneratedKeys();
				if (generatedKeysTenant.next()) {
					
					// Obtain the generated aval ID for the tenant person
					int avalId = generatedKeysTenant.getInt(1);
					
					// Insert aval person details
					personTenantStmt.setString(1, tenant.getRut());
					personTenantStmt.setString(2, tenant.getName());
					personTenantStmt.setString(3, tenant.getSurname());
					personTenantStmt.setString(4, tenant.getEmail());
					personTenantStmt.setString(5, tenant.getPhone());
					personTenantStmt.executeUpdate();
					
					ResultSet generatedKeysTenantPerson = personTenantStmt.getGeneratedKeys();
					if (generatedKeysTenantPerson.next()) {
						
						// Obtain the generated ID for the tenant person
						
						int tenantPersonId = generatedKeysTenantPerson.getInt(1);
						
						// Insert in tenant table
			            tenantStmt.setInt(1, tenantPersonId);
			            tenantStmt.setInt(2, tenant.getIsActive() ? 1 : 0);
			            avalStmt.setInt(3, tenant.getIsRenting() ? 1 : 0);
			            avalStmt.setInt(4, avalId); // Assuming aval_id is nullable
			            avalStmt.executeUpdate();
						
						conn.commit();
						return true;
					}
				}
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


    public List<Tenant> getAll() {
        List<Tenant> list = new ArrayList<>();
        String sql = """
            SELECT p.id, p.rut, p.name, p.surname, p.phone, p.email, p.type, t.isactive, t.hasrentals, t.aval_id
            FROM person p
            JOIN tenant t ON p.id = t.person_id
        """;
        Connection conn = DBConnection.getConnection();
        try (
        		PreparedStatement stmt = conn.prepareStatement(sql);
        		ResultSet rs = stmt.executeQuery()) {
            	while (rs.next()) {
                Tenant l = new Tenant(
                		rs.getString("rut"),
                		rs.getString("name"),
                		rs.getString("surname"),
                		rs.getString("email"),
                		rs.getString("phone"),
                		rs.getString("type"),
                		rs.getBoolean("isactive"),
                		rs.getBoolean("hasrentals"),
                		rs.getString("aval_id")
                );
                list.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
    public List<String> getAllNamesWithRUT() {
		List<String> LandlordList = new ArrayList<>();
		String sql = "SELECT CONCAT(name, ' ', surname, ' (', rut, ')') AS full_name FROM person p JOIN tenant t on p.id = l.person_id";
		
		try (
				Connection conn = DBConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
				
                LandlordList.add(rs.getString("full_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LandlordList;
	} 
    
    public List<String> getRutByName(String name) {
		List<String> ruts = new ArrayList<>();
		String sql = """
			SELECT p.rut
			FROM person p
			JOIN tenant t ON p.id = l.person_id
			WHERE CONCAT(p.name, ' ', p.surname) = ?
		""";
		Connection conn = DBConnection.getConnection();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				ruts.add(rs.getString("rut"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ruts;
	}
    
	public static Tenant getByRut(String rut) {
	    String sql = """
	        SELECT p.rut, p.name, p.surname, p.email, p.phone, p.type, t.isactive, t.hasrentals, t.aval_id
	        FROM person p
	        JOIN tenant t ON p.id = t.person_id
	        WHERE p.rut = ? AND p.type = 'tenant'
	    """;
	    Connection conn = DBConnection.getConnection();
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, rut);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Tenant(
	            		rs.getString("rut"),
                		rs.getString("name"),
                		rs.getString("surname"),
                		rs.getString("email"),
                		rs.getString("phone"),
                		rs.getString("type"),
                		rs.getBoolean("isactive"),
                		rs.getBoolean("hasrentals"),
                		rs.getString("aval_id")
                );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	// WE ARE USING THIS!!!
	public static Tenant getByRut2(String rut) {
	    String sql = """
	        SELECT p.rut, p.name, p.surname, p.email, p.phone, p.type, t.isactive, t.hasrentals, t.aval_id, ba.num AS accountNum, ba.type AS accountType, b.name AS bankName
	        FROM tenant t
	    	JOIN person p ON t.person_id = p.id
	    	JOIN bankaccount ba ON ba.person_id = p.id 
	    	JOIN bank b ON ba.bank_id = b.id
	    	WHERE p.rut = ? AND p.type = 'tenant'
	    	""";
	    Connection conn = DBConnection.getConnection();
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, rut);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Tenant(
	            		rs.getString("rut"),
                		rs.getString("name"),
                		rs.getString("surname"),
                		rs.getString("email"),
                		rs.getString("phone"),
                		rs.getString("type"),
                		rs.getBoolean("isactive"),
                		rs.getBoolean("hasrentals"),
                		rs.getString("aval_id"),
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
	
	public static Tenant getIdByName(String name) {
	    String sql = """
	        SELECT p.id, p.rut, p.name, p.surname, p.email, p.phone, p.type, t.isactive, t.hasrentals, t.aval_id
	        FROM person p
	        JOIN tenant t ON p.id = t.person_id
	        WHERE CONCAT(p.name, ' ', p.surname) = ?
	    """;
	    Connection conn = DBConnection.getConnection();
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, name);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Tenant(
	            		rs.getString("rut"),
                		rs.getString("name"),
                		rs.getString("surname"),
                		rs.getString("email"),
                		rs.getString("phone"),
                		rs.getString("type"),
                		rs.getBoolean("isactive"),
                		rs.getBoolean("hasrentals"),
                		rs.getString("aval_id")
                );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public List<TenantOption> getAllLandlordOptions() {
	    List<TenantOption> list = new ArrayList<>();
	    String sql = "SELECT person_id, name, surname, rut FROM tenant t JOIN person p ON t.person_id = p.id";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            int id = rs.getInt("person_id");
	            String name = rs.getString("name") + " " + rs.getString("surname");
	            String rut = rs.getString("rut");
	            String displayName = id + " - " + name + " - " + rut;

	            list.add(new TenantOption(id, displayName));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public boolean update(Tenant tenant, int bankId, String accountType, String accountNum) {
	    String updatePersonSQL = "UPDATE person SET name = ?, surname = ?, email = ?, phone = ? WHERE rut = ?";
	    String updateTenantSQL = "UPDATE tenant SET isactive = ?, hasrentals = ? WHERE person_id = (SELECT id FROM person WHERE rut = ?)";
	    String updateBankAccountSQL = "UPDATE bankaccount SET bank_id = ?, type = ?, num = ? WHERE person_id = (SELECT id FROM person WHERE rut = ?)";

	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement personStmt = conn.prepareStatement(updatePersonSQL);
	        PreparedStatement landlordStmt = conn.prepareStatement(updateTenantSQL);
	        PreparedStatement bankStmt = conn.prepareStatement(updateBankAccountSQL)
	    ) {
	        conn.setAutoCommit(false);

	        // Actualizar tabla person
	        personStmt.setString(1, tenant.getName());
	        personStmt.setString(2, tenant.getSurname());
	        personStmt.setString(3, tenant.getEmail());
	        personStmt.setString(4, tenant.getPhone());
	        personStmt.setString(5, tenant.getRut());
	        personStmt.executeUpdate();

	        // Actualizar tabla landlord
	        landlordStmt.setInt(1, tenant.getIsActive() ? 1 : 0);
	        landlordStmt.setInt(2, tenant.getIsRenting() ? 1 : 0);
	        landlordStmt.setString(3, tenant.getRut());
	        landlordStmt.executeUpdate();

	        // Actualizar tabla bankaccount
	        bankStmt.setInt(1, bankId);
	        bankStmt.setString(2, accountType);
	        bankStmt.setString(3, accountNum);
	        bankStmt.setString(4, tenant.getRut());
	        bankStmt.executeUpdate();

	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	public boolean delete(String rut) {
	    String getPersonIdSQL = "SELECT id FROM person WHERE rut = ?";
	    String deleteBankAccountSQL = "DELETE FROM bankaccount WHERE person_id = ?";
	    String deleteAvalSQL = "DELETE FROM aval WHERE person_id = (SELECT id FROM person WHERE rut = ?)";
	    String deleteTenantSQL = "DELETE FROM tenant WHERE person_id = ?";
	    String deletePersonSQL = "DELETE FROM person WHERE id = ?";

	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement getIdStmt = conn.prepareStatement(getPersonIdSQL);
	        PreparedStatement deleteBankStmt = conn.prepareStatement(deleteBankAccountSQL);
	        PreparedStatement deleteTenantStmt = conn.prepareStatement(deleteTenantSQL);
	        PreparedStatement deletePersonStmt = conn.prepareStatement(deletePersonSQL)
	    ) {
	        conn.setAutoCommit(false);

	        getIdStmt.setString(1, rut);
	        ResultSet rs = getIdStmt.executeQuery();

	        if (rs.next()) {
	            int personId = rs.getInt("id");

	            deleteBankStmt.setInt(1, personId);
	            deleteBankStmt.executeUpdate();

	            deleteTenantStmt.setInt(1, personId);
	            deleteTenantStmt.executeUpdate();

	            deletePersonStmt.setInt(1, personId);
	            deletePersonStmt.executeUpdate();

	            conn.commit();
	            return true;
	        } else {
	            System.out.println("No se encontró una persona con RUT: " + rut);
	            conn.rollback();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}


	public static int getLandlordIdByNameWithRUT(String selectedLandlord) {
		// TODO Auto-generated method stub
		return 0;
	}

}
