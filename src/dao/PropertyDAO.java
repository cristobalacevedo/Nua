package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import model.AvailablePropertyView;
import model.Estate;
import model.Flat;
import model.House;
import model.Land;
import model.Office;
import model.Parking;
import model.Storage;

public class PropertyDAO {
    private Connection conn;
    private HouseDAO houseDAO;
    private FlatDAO flatDAO;	    
    private StorageDAO storageDAO;
    private ParkingDAO parkingDAO;
    private LandDAO landDAO;
    private OfficeDAO officeDAO;
       
    public PropertyDAO() {
            this.conn = DBConnection.getConnection();
            this.houseDAO = new HouseDAO(conn);
            this.flatDAO = new FlatDAO(conn);
            this.storageDAO = new StorageDAO(conn);
            this.parkingDAO = new ParkingDAO(conn);
            this.landDAO = new LandDAO(conn);
            this.officeDAO = new OfficeDAO(conn);
      }
    

	public void PropertyDAO1(Connection conn) {
        this.conn = conn;
        this.houseDAO = new HouseDAO(conn);
        this.flatDAO = new FlatDAO(conn);
        this.landDAO = new LandDAO(conn);
//        this.storageDAO = new StorageDAO(conn);
        this.parkingDAO = new ParkingDAO(conn);
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
	
	public List<AvailablePropertyView> getAllAvailable() {
	    List<AvailablePropertyView> list = new ArrayList<>();
	    String sql = "SELECT * FROM all_available_properties";

	    try (
	        Connection conn = DBConnection.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery()
	    ) {
	        while (rs.next()) {
	            AvailablePropertyView apv = new AvailablePropertyView(
	                rs.getString("Dueño"),
	                rs.getString("Tipo"),
	                rs.getString("ROL_SII"),
	                rs.getInt("Tamaño"),
	                rs.getString("Comuna"),
	                rs.getString("Dirección"),
	                rs.getString("Disponibilidad")
	            );
	            list.add(apv);
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }

	    return list;
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
	
	// --- HOUSE METHODS ---
	
	public boolean insertCompleteHouse(House data) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertHouseAddress(data);
	        int propertyId = insertHouseProperty(data, addressId);
	        houseDAO.insertHouse(propertyId, data); 
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}

    private int insertHouseAddress(House data) throws SQLException {
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id, town_region_id, town_region_country_id) VALUES (?, ?, ?, ?, ?, 1)"; // Assuming country_id is always 1 for simplicity (CHILE)
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getStreetName());
            stmt.setString(2, data.getNum1());
            stmt.setString(3, data.getNum2());
            stmt.setInt(4, data.getTownId());
            stmt.setInt(5, data.getRegionId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }

    private int insertHouseProperty(House data, int addressId) throws SQLException {
        String sql = "INSERT INTO property (landlord_id, property_type_id, address_id, size, rol_sii) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, data.getLandlordId());
            stmt.setInt(2, data.getPropertyTypeId());
            stmt.setInt(3, addressId);
            stmt.setInt(4, data.getSize());
            stmt.setString(5, data.getRolSII());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de propiedad.");
                return rs.getInt(1);
            }
        }
    }
    
    // --- END HOUSE METHODS ---
    
    // --- FLAT METHODS ---
    
    public boolean insertCompleteFlatParkingStorage(Flat data, List<Parking> parkings, List<Storage> storages) throws SQLException {
    	try {
	        conn.setAutoCommit(false);

	        int addressId = insertFlatAddress(data);
	        int propertyId = insertFlatProperty(data, addressId);
	        flatDAO.insertFlat(propertyId, data); 
	        for (Parking p : parkings) {
	        	int parkingAddressId = insertParkingAddress(p);
	            int parkingPropertyId = insertParkingProperty(p, parkingAddressId);
	            p.setId(parkingPropertyId); // opcional
	            p.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        System.out.println("Insertando " + parkings.size() + " estacionamientos para Flat ID = " + propertyId);
	        for (Parking p : parkings) {
	            System.out.println(" -> Parking: parkingPropertyId:" + p.getId() + "flatId=" + p.getFlatId() + ", inCondo=" + p.isInCondo() + ", condoId=" + p.getCondoId() + ", size=" + p.getSize() + ", rolSII=" + p.getRolSII() + ", streetName=" + p.getStreetName() + ", num1=" + p.getNum1() + ", num2=" + p.getNum2() + ", townId=" + p.getTownId() + ", regionId=" + p.getRegionId());
	        }
	        parkingDAO.insertParkingInFlat(parkings); // Insertarlos desde tu DAO
	        
	        
	        for (Storage s : storages) {
	        	int storageAddressId = insertStorageAddress(s);
	            int storagePropertyId = insertStorageProperty(s, storageAddressId);
	            s.setId(storagePropertyId); // opcional
	            s.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        System.out.println("Insertando " + storages.size() + " storage para Flat ID = " + propertyId);
	        for (Storage s : storages) {
	            System.out.println(" -> Storage: storagePropertyId:" + s.getId() + "flatId=" + s.getFlatId() + ", inCondo=" + s.isInCondo() + ", condoId=" + s.getCondoId() + ", size=" + s.getSize() + ", rolSII=" + s.getRolSII() + ", streetName=" + s.getStreetName() + ", num1=" + s.getNum1() + ", num2=" + s.getNum2() + ", townId=" + s.getTownId() + ", regionId=" + s.getRegionId());
	        }
	        storageDAO.insertParkingInFlat(storages); // Insertarlos desde tu DAO
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true);  // IMPORTANT: Restore auto-commit to default state
	    }
	}
    
    public boolean insertCompleteFlatStorage(Flat data, List<Storage> storages) throws SQLException {
    	try {
	        conn.setAutoCommit(false);

	        int addressId = insertFlatAddress(data);
	        int propertyId = insertFlatProperty(data, addressId);
	        flatDAO.insertFlat(propertyId, data); 
	        
	        for (Storage s : storages) {
	        	int storageAddressId = insertStorageAddress(s);
	            int storagePropertyId = insertStorageProperty(s, storageAddressId);
	            s.setId(storagePropertyId); // opcional
	            s.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        
	        System.out.println("Insertando " + storages.size() + " storage para Flat ID = " + propertyId);
	        for (Storage s : storages) {
	            System.out.println(" -> Storage: storagePropertyId:" + s.getId() + "flatId=" + s.getFlatId() + ", inCondo=" + s.isInCondo() + ", condoId=" + s.getCondoId() + ", size=" + s.getSize() + ", rolSII=" + s.getRolSII() + ", streetName=" + s.getStreetName() + ", num1=" + s.getNum1() + ", num2=" + s.getNum2() + ", townId=" + s.getTownId() + ", regionId=" + s.getRegionId());
	        }
	        
	        storageDAO.insertParkingInFlat(storages); // Insertarlos desde tu DAO
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true);  // IMPORTANT: Restore auto-commit to default state
	    }
	}
    
    public boolean insertCompleteFlatParking(Flat data, List<Parking> parkings) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertFlatAddress(data);
	        int propertyId = insertFlatProperty(data, addressId);
	        flatDAO.insertFlat(propertyId, data); 
	        
	        for (Parking p : parkings) {
	        	int parkingAddressId = insertParkingAddress(p);
	            int parkingPropertyId = insertParkingProperty(p, parkingAddressId);
	            p.setId(parkingPropertyId); // opcional
	            p.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        
	        System.out.println("Insertando " + parkings.size() + " estacionamientos para Flat ID = " + propertyId);
	        for (Parking p : parkings) {
	            System.out.println(" -> Parking: parkingPropertyId:" + p.getId() + "flatId=" + p.getFlatId() + ", inCondo=" + p.isInCondo() + ", condoId=" + p.getCondoId() + ", size=" + p.getSize() + ", rolSII=" + p.getRolSII() + ", streetName=" + p.getStreetName() + ", num1=" + p.getNum1() + ", num2=" + p.getNum2() + ", townId=" + p.getTownId() + ", regionId=" + p.getRegionId());
	        }
	        
	        parkingDAO.insertParkingInFlat(parkings); // Insertarlos desde tu DAO
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true);  // IMPORTANT: Restore auto-commit to default state
	    }
	}
    
    public boolean insertCompleteFlatNoSP(Flat data) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertFlatAddress(data);
	        int propertyId = insertFlatProperty(data, addressId);
	        flatDAO.insertFlat(propertyId, data); 
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true); // IMPORTANT: Restore auto-commit to default state
	    }
	}
	
	private int insertFlatProperty(Flat data, int addressId) throws SQLException {
        String sql = "INSERT INTO property (landlord_id, property_type_id, address_id, size, rol_sii) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, data.getLandlordId());
            stmt.setInt(2, data.getPropertyTypeId());
            stmt.setInt(3, addressId);
            stmt.setInt(4, data.getSize());
            stmt.setString(5, data.getRolSII());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de propiedad.");
                return rs.getInt(1);
            }
        }
    }
	
	private int insertFlatAddress(Flat data) throws SQLException {
		System.out.println("\nNUM_2 que llega: " + data.getNum2());
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id, town_region_id, town_region_country_id) VALUES (?, ?, ?, ?, ?, 1)"; // Assuming country_id is always 1 for simplicity (CHILE)
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getStreetName());
            stmt.setString(2, data.getNum1());
            stmt.setString(3, data.getNum2());
            stmt.setInt(4, data.getTownId());
            stmt.setInt(5, data.getRegionId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }

	// --- END FLAT METHODS ---

	// --- PARKING METHODS ---
	
	public boolean insertCompleteParking(Parking data) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertParkingAddress(data);
	        int propertyId = insertParkingProperty(data, addressId);
	        parkingDAO.insertParking(propertyId, data); 
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private int insertParkingProperty(Parking data, int addressId) throws SQLException {
        String sql = "INSERT INTO property (landlord_id, property_type_id, address_id, size, rol_sii) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, data.getLandlordId());
            stmt.setInt(2, data.getPropertyTypeId());
            stmt.setInt(3, addressId);
            stmt.setInt(4, data.getSize());
            stmt.setString(5, data.getRolSII());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de propiedad.");
                return rs.getInt(1);
            }
        }
    }
	
	private int insertParkingAddress(Parking data) throws SQLException {
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id, town_region_id, town_region_country_id) VALUES (?, ?, ?, ?, ?, 1)"; // Assuming country_id is always 1 for simplicity (CHILE)
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getStreetName());
            stmt.setString(2, data.getNum1());
            stmt.setString(3, data.getNum2());
            stmt.setInt(4, data.getTownId());
            stmt.setInt(5, data.getRegionId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }

	// --- END PARKING METHODS ---
	
	// --- STORAGE METHODS ---

	public boolean insertCompleteStorage(Storage data) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertStorageAddress(data);
	        int propertyId = insertStorageProperty(data, addressId);
	        storageDAO.insertStorage(propertyId, data); 
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}
	
	private int insertStorageAddress(Storage data) throws SQLException {
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id, town_region_id, town_region_country_id) VALUES (?, ?, ?, ?, ?, 1)"; // Assuming country_id is always 1 for simplicity (CHILE)
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getStreetName());
            stmt.setString(2, data.getNum1());
            stmt.setString(3, data.getNum2());
            stmt.setInt(4, data.getTownId());
            stmt.setInt(5, data.getRegionId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }
	
	private int insertStorageProperty(Storage data, int addressId) throws SQLException {
        String sql = "INSERT INTO property (landlord_id, property_type_id, address_id, size, rol_sii) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, data.getLandlordId());
            stmt.setInt(2, data.getPropertyTypeId());
            stmt.setInt(3, addressId);
            stmt.setInt(4, data.getSize());
            stmt.setString(5, data.getRolSII());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de propiedad.");
                return rs.getInt(1);
            }
        }
    }

	//  --- END STORAGE METHODS ---

	// --- LAND METHODS ---	
	
	public boolean insertCompleteLand(Land data) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertLandAddress(data);
	        int propertyId = insertLandProperty(data, addressId);
	        landDAO.insertLand(propertyId, data); 
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    }
	}

	private int insertLandAddress(Land data) throws SQLException {
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id, town_region_id, town_region_country_id) VALUES (?, ?, ?, ?, ?, 1)"; // Assuming country_id is always 1 for simplicity (CHILE)
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getStreetName());
            stmt.setString(2, data.getNum1());
            stmt.setString(3, data.getNum2());
            stmt.setInt(4, data.getTownId());
            stmt.setInt(5, data.getRegionId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }

    private int insertLandProperty(Land data, int addressId) throws SQLException {
        String sql = "INSERT INTO property (landlord_id, property_type_id, address_id, size, rol_sii) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, data.getLandlordId());
            stmt.setInt(2, data.getPropertyTypeId());
            stmt.setInt(3, addressId);
            stmt.setInt(4, data.getSize());
            stmt.setString(5, data.getRolSII());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de propiedad.");
                return rs.getInt(1);
            }
        }
    }
    
    // --- END LAND METHODS ---

    // --- OFFICE METHODS ---
    
    public boolean insertCompleteOfficeParkingStorage(Office data, List<Parking> parkings, List<Storage> storages) throws SQLException {
    	try {
	        conn.setAutoCommit(false);

	        int addressId = insertOfficeAddress(data);
	        int propertyId = insertOfficeProperty(data, addressId);
	        officeDAO.insertOffice(propertyId, data);
	        
	        for (Parking p : parkings) {
	        	int parkingAddressId = insertParkingAddress(p);
	            int parkingPropertyId = insertParkingProperty(p, parkingAddressId);
	            p.setId(parkingPropertyId); // opcional
	            p.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        System.out.println("Insertando " + parkings.size() + " estacionamientos para Flat ID = " + propertyId);
	        for (Parking p : parkings) {
	            System.out.println(" -> Parking: parkingPropertyId:" + p.getId() + "flatId=" + p.getFlatId() + ", inCondo=" + p.isInCondo() + ", condoId=" + p.getCondoId() + ", size=" + p.getSize() + ", rolSII=" + p.getRolSII() + ", streetName=" + p.getStreetName() + ", num1=" + p.getNum1() + ", num2=" + p.getNum2() + ", townId=" + p.getTownId() + ", regionId=" + p.getRegionId());
	        }
	        parkingDAO.insertParkingInFlat(parkings); // Insertarlos desde tu DAO
	        
	        
	        for (Storage s : storages) {
	        	int storageAddressId = insertStorageAddress(s);
	            int storagePropertyId = insertStorageProperty(s, storageAddressId);
	            s.setId(storagePropertyId); // opcional
	            s.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        System.out.println("Insertando " + storages.size() + " storage para Flat ID = " + propertyId);
	        for (Storage s : storages) {
	            System.out.println(" -> Storage: storagePropertyId:" + s.getId() + "flatId=" + s.getFlatId() + ", inCondo=" + s.isInCondo() + ", condoId=" + s.getCondoId() + ", size=" + s.getSize() + ", rolSII=" + s.getRolSII() + ", streetName=" + s.getStreetName() + ", num1=" + s.getNum1() + ", num2=" + s.getNum2() + ", townId=" + s.getTownId() + ", regionId=" + s.getRegionId());
	        }
	        storageDAO.insertParkingInFlat(storages); // Insertarlos desde tu DAO
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true);  // IMPORTANT: Restore auto-commit to default state
	    }
	}
    
    public boolean insertCompleteOfficeStorage(Office data, List<Storage> storages) throws SQLException {
    	try {
	        conn.setAutoCommit(false);

	        int addressId = insertOfficeAddress(data);
	        int propertyId = insertOfficeProperty(data, addressId);
	        officeDAO.insertOffice(propertyId, data); 
	        
	        for (Storage s : storages) {
	        	int storageAddressId = insertStorageAddress(s);
	            int storagePropertyId = insertStorageProperty(s, storageAddressId);
	            s.setId(storagePropertyId); // opcional
	            s.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        
	        System.out.println("Insertando " + storages.size() + " storage para Flat ID = " + propertyId);
	        for (Storage s : storages) {
	            System.out.println(" -> Storage: storagePropertyId:" + s.getId() + "flatId=" + s.getFlatId() + ", inCondo=" + s.isInCondo() + ", condoId=" + s.getCondoId() + ", size=" + s.getSize() + ", rolSII=" + s.getRolSII() + ", streetName=" + s.getStreetName() + ", num1=" + s.getNum1() + ", num2=" + s.getNum2() + ", townId=" + s.getTownId() + ", regionId=" + s.getRegionId());
	        }
	        
	        storageDAO.insertParkingInFlat(storages); // Insertarlos desde tu DAO
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true);  // IMPORTANT: Restore auto-commit to default state
	    }
	}
    
    public boolean insertCompleteOfficeParking(Office data, List<Parking> parkings) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertOfficeAddress(data);
	        int propertyId = insertOfficeProperty(data, addressId);
	        officeDAO.insertOffice(propertyId, data); 
	        
	        for (Parking p : parkings) {
	        	int parkingAddressId = insertParkingAddress(p);
	            int parkingPropertyId = insertParkingProperty(p, parkingAddressId);
	            p.setId(parkingPropertyId); // opcional
	            p.setFlatId(propertyId); // Relacionar flat con los estacionamientos
	        }
	        
	        System.out.println("Insertando " + parkings.size() + " estacionamientos para Flat ID = " + propertyId);
	        for (Parking p : parkings) {
	            System.out.println(" -> Parking: parkingPropertyId:" + p.getId() + "flatId=" + p.getFlatId() + ", inCondo=" + p.isInCondo() + ", condoId=" + p.getCondoId() + ", size=" + p.getSize() + ", rolSII=" + p.getRolSII() + ", streetName=" + p.getStreetName() + ", num1=" + p.getNum1() + ", num2=" + p.getNum2() + ", townId=" + p.getTownId() + ", regionId=" + p.getRegionId());
	        }
	        
	        parkingDAO.insertParkingInFlat(parkings); // Insertarlos desde tu DAO
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true);  // IMPORTANT: Restore auto-commit to default state
	    }
	}
    
    public boolean insertCompleteOfficeNoSP(Office data) throws SQLException {
	    try {
	        conn.setAutoCommit(false);

	        int addressId = insertOfficeAddress(data);
	        int propertyId = insertOfficeProperty(data, addressId);
	        officeDAO.insertOffice(propertyId, data); 
	        
	        conn.commit();
	        return true;

	    } catch (SQLException e) {
	        conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        conn.setAutoCommit(true); // IMPORTANT: Restore auto-commit to default state
	    }
	}
	
	private int insertOfficeProperty(Office data, int addressId) throws SQLException {
        String sql = "INSERT INTO property (landlord_id, property_type_id, address_id, size, rol_sii) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, data.getLandlordId());
            stmt.setInt(2, data.getPropertyTypeId());
            stmt.setInt(3, addressId);
            stmt.setInt(4, data.getSize());
            stmt.setString(5, data.getRolSII());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de propiedad.");
                return rs.getInt(1);
            }
        }
    }
	
	private int insertOfficeAddress(Office data) throws SQLException {
		System.out.println("\nNUM_2 que llega: " + data.getNum2());
        String sql = "INSERT INTO address (st_name, num_1, num_2, town_id, town_region_id, town_region_country_id) VALUES (?, ?, ?, ?, ?, 1)"; // Assuming country_id is always 1 for simplicity (CHILE)
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, data.getStreetName());
            stmt.setString(2, data.getNum1());
            stmt.setString(3, data.getNum2());
            stmt.setInt(4, data.getTownId());
            stmt.setInt(5, data.getRegionId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (!rs.next()) throw new SQLException("No se pudo obtener ID de dirección.");
                return rs.getInt(1);
            }
        }
    }
	
}

