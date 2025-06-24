package controller;

import dao.PropertyDAO;
import dao.RegionDAO;
import model.Flat;
import model.House;
import model.Land;
import model.Office;
import model.Parking;
import model.Storage;
import utils.CondoOption;
//import utils.FieldValidator;
import utils.LandlordOption;
import utils.PropertyTypeOption;
import utils.TownOption;
import view.Popup;

import java.sql.SQLException;
//import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JTextField;

public class PropertyController {

    private final PropertyDAO dao;

    public PropertyController() {
        this.dao = new PropertyDAO();
    }
    
    // --- HOUSE --- //

    public boolean saveHouse(
            LandlordOption selectedLandlord,
            TownOption selectedTown,
            PropertyTypeOption selectedType,
            CondoOption selectedCondo,
            String rolSII,
            String street,
            String num1,
            String num2,
            String regionName,
            int roomQty,
            int bathQty,
            int floorQty,
            int parkingQty,
            int storageQty,
            boolean hasGarden,
            boolean hasPatio,
            boolean hasPool,
            boolean hasBBQ,
            boolean hasBalcony,
            boolean hasTerrace,
            boolean hasLaundry,
            boolean inCondo,
            String sizeText
    		) throws SQLException {

        // Validaciones
        		if (selectedLandlord == null || selectedLandlord.getId() == -1) {
        			Popup.show("Debe seleccionar un propietario válido.", "error"); 
        			return false;
        		}
			
        		if (selectedTown == null || selectedTown.getId() == -1) {
        			Popup.show("Debe seleccionar una comuna válida.", "error");
        			return false;
        		}	

        		if (selectedType == null || selectedType.getValue().isEmpty()) {
        			Popup.show("Debe seleccionar un tipo de propiedad válido.", "error");
        			return false;
        		}

        		if (rolSII == null || rolSII.trim().isEmpty()
        				|| street == null || street.trim().isEmpty()
        				|| num1 == null || num1.trim().isEmpty()) {
        			Popup.show("Complete todos los campos requeridos de dirección y rol SII.", "error");
        			return false;
        		 }
        		
        		int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("El tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("El tamaño debe ser un número válido.", "error");
        		    return false;
        		}
        		// Create House object
        		House data = new House();
        		data.setPropertyTypeId(1); // 1 = House
        		data.setRolSII(rolSII.trim());
        		data.setLandlordId(selectedLandlord.getId());
        		data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño

        		data.setTownId(selectedTown.getId());
        		data.setRegionId(RegionDAO.getRegionIDByName(regionName)); 
        		data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2 != null ? num2.trim() : null); // num2 could be null

		        data.setRoomQty(roomQty);
		        data.setBathQty(bathQty);
		        data.setFloorQty(floorQty);
		        data.setHasParking(parkingQty > 0); // Consider that parkingQty > 0 means there is parking
		        data.setHasStorage(storageQty > 0); // Consider that storageQty > 0 means there is storage
		        data.setHasGarden(hasGarden);
		        data.setHasPatio(hasPatio);
		        data.setHasPool(hasPool);
		        data.setHasBBQ(hasBBQ);
		        data.setHasBalcony(hasBalcony);
		        data.setHasTerrace(hasTerrace);
		        data.setHasLaundry(hasLaundry);
		        
		        data.setInCondo(inCondo);
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);

		        return dao.insertCompleteHouse(data);
    	}
    
    // --- END HOUSE --- //

    // --- FLAT --- //
    
	public boolean saveFlatStorageAndParking(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int roomQty,
	        int bathQty,
	        int floor,
	        int storageQty,
	        int parkingQty,
	        boolean hasBalcony,
	        boolean buildingHasLift,
	        boolean buildingHasPool,
	        boolean buildingHasBBQ,
	        boolean buildingHasGym,
	        boolean buildingHasLaundry,
	        boolean inCondo,
	        List<Parking> parkings,
	        List<Storage> storages,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
		                || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
		        if (parkings == null || parkings.isEmpty()) {
					Popup.show("ERROR: Debe agregar los datos de al menos un estacionamiento.", "error");
					return false;
				}
				
				if (storages == null || storages.isEmpty()) {
					Popup.show("ERROR: Debe agregar los datos de al menos una bodega.", "error");
					return false;
				}
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("El tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("El tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Flat object
		        Flat data = new Flat();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim());  // num2 could be null
		        data.setRoomQty(roomQty); // 2
		        data.setBathQty(bathQty); // 3
		        data.setFloor(floor); // 4
		        data.setHasStorage(storageQty); //5
		        data.setHasParking(parkingQty); //6
		        data.setHasBalcony(hasBalcony); //7
		        data.setBuildingHasLift(buildingHasLift); //8
		        data.setBuildingHasPool(buildingHasPool); //9
		        data.setBuildingHasGym(buildingHasGym); //10
		        data.setBuildingHasLaundry(buildingHasLaundry); //11
		        data.setBuildingHasBBQ(buildingHasBBQ); //12
		        
		        data.setInCondo(inCondo); //13
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteFlatParkingStorage(data, parkings, storages);
		}
    
	public boolean saveFlatStorage(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int roomQty,
	        int bathQty,
	        int floor,
	        int storageQty,
	        int parkingQty,
	        boolean hasBalcony,
	        boolean buildingHasLift,
	        boolean buildingHasPool,
	        boolean buildingHasBBQ,
	        boolean buildingHasGym,
	        boolean buildingHasLaundry,
	        boolean inCondo,
	        List<Storage> storages,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
		                || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
				
				if (storages == null || storages.isEmpty()) {
					Popup.show("ERROR: Debe agregar los datos de al menos una bodega.", "error");
					return false;
				}
		        
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Flat object
		        Flat data = new Flat();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim());  // num2 could be null
		        data.setRoomQty(roomQty); // 2
		        data.setBathQty(bathQty); // 3
		        data.setFloor(floor); // 4
		        data.setHasStorage(storageQty); //5
		        data.setHasParking(parkingQty); //6
		        data.setHasBalcony(hasBalcony); //7
		        data.setBuildingHasLift(buildingHasLift); //8
		        data.setBuildingHasPool(buildingHasPool); //9
		        data.setBuildingHasGym(buildingHasGym); //10
		        data.setBuildingHasLaundry(buildingHasLaundry); //11
		        data.setBuildingHasBBQ(buildingHasBBQ); //12
		        
		        data.setInCondo(inCondo); //13
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteFlatStorage(data, storages);
		}
	
    
	public boolean saveFlatParking(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int roomQty,
	        int bathQty,
	        int floor,
	        int storageQty,
	        int parkingQty,
	        boolean hasBalcony,
	        boolean buildingHasLift,
	        boolean buildingHasPool,
	        boolean buildingHasBBQ,
	        boolean buildingHasGym,
	        boolean buildingHasLaundry,
	        boolean inCondo,
	        List<Parking> parkings,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
				        || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
		        if (parkings == null || parkings.isEmpty()) {
					Popup.show("ERROR: Debe agregar los datos de al menos un estacionamiento.", "error");
					return false;
				}
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Flat object
		        Flat data = new Flat();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim());  // num2 could be null
		        data.setRoomQty(roomQty); // 2
		        data.setBathQty(bathQty); // 3
		        data.setFloor(floor); // 4
		        data.setHasStorage(storageQty); //5
		        data.setHasParking(parkingQty); //6
		        data.setHasBalcony(hasBalcony); //7
		        data.setBuildingHasLift(buildingHasLift); //8
		        data.setBuildingHasPool(buildingHasPool); //9
		        data.setBuildingHasGym(buildingHasGym); //10
		        data.setBuildingHasLaundry(buildingHasLaundry); //11
		        data.setBuildingHasBBQ(buildingHasBBQ); //12
		        
		        data.setInCondo(inCondo); //13
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteFlatParking(data, parkings);
		}
	

	
	public boolean saveFlatNoSP(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int roomQty,
	        int bathQty,
	        int floor,
	        int storageQty,
	        int parkingQty,
	        boolean hasBalcony,
	        boolean buildingHasLift,
	        boolean buildingHasPool,
	        boolean buildingHasBBQ,
	        boolean buildingHasGym,
	        boolean buildingHasLaundry,
	        boolean inCondo,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
				        || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Flat object
		        Flat data = new Flat();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim()); // num2 could be null
		        data.setRoomQty(roomQty); // 2
		        data.setBathQty(bathQty); // 3
		        data.setFloor(floor); // 4
		        data.setHasStorage(storageQty); //5
		        data.setHasParking(parkingQty); //6
		        data.setHasBalcony(hasBalcony); //7
		        data.setBuildingHasLift(buildingHasLift); //8
		        data.setBuildingHasPool(buildingHasPool); //9
		        data.setBuildingHasGym(buildingHasGym); //10
		        data.setBuildingHasLaundry(buildingHasLaundry); //11
		        data.setBuildingHasBBQ(buildingHasBBQ); //12
		        
		        data.setInCondo(inCondo); //13
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteFlatNoSP(data);
		}
	
		// --- END FLAT --- //
	
		// --- STORAGE --- //
	
		public boolean saveStorage(
				LandlordOption selectedLandlord,
		        TownOption selectedTown,
		        PropertyTypeOption selectedType,
		        CondoOption selectedCondo,
		        String rolSII,
		        String street,
		        String num1,
		        String num2,
		        String regionName,
				boolean inCondo,
				String sizeText
				) throws SQLException {
			// Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }

		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }

		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }

		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Storage object
		        Storage data = new Storage();
		        data.setPropertyTypeId(3); // 3 = Storage
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        
		        // NUM 2 COULD BE NULL
		        data.setNum2(num2 != null ? num2.trim() : null); // num2 could be null
		        
		        
		        data.setInCondo(inCondo); 
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteStorage(data);
		}

		// --- END STORAGE --- //
	
		// --- PARKING --- //

	public boolean saveParking(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
			boolean inCondo,
			String sizeText
			) throws SQLException {
        // Validaciones
			if (selectedLandlord == null || selectedLandlord.getId() == -1) {
	            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
	            return false;
	        }

	        if (selectedTown == null || selectedTown.getId() == -1) {
	            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
	            return false;
	        }

	        if (selectedType == null || selectedType.getValue().isEmpty()) {
	            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
	            return false;
	        }

	        if (rolSII == null || rolSII.trim().isEmpty()
	                || street == null || street.trim().isEmpty()
	                || num1 == null || num1.trim().isEmpty()) {
	            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
	            return false;
	        }
	        
	        int size; // Inicializar tamaño
    		try {
    		    size = Integer.parseInt(sizeText.trim());
    		    if (size <= 0) {
    		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
    		        return false;
    		    }
    		} catch (NumberFormatException e) {
    		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
    		    return false;
    		}
	        
	        // Create Parking object
	        Parking data = new Parking();
	        data.setPropertyTypeId(4); // 4 = Parking
	        data.setRolSII(rolSII.trim());
	        data.setLandlordId(selectedLandlord.getId());
	        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
	        data.setTownId(selectedTown.getId());
	        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
	        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
	        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
	        data.setNum2(num2.trim()); // num2 could be null, but we trim it to avoid leading/trailing spaces
	        data.setInCondo(inCondo); 
	        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
	        
	        
	        return dao.insertCompleteParking(data);
	}
	
		// --- END PARKING --- //
	
		// --- LAND --- //
	
	public boolean saveLand(
            LandlordOption selectedLandlord,
            TownOption selectedTown,
            PropertyTypeOption selectedType,
            CondoOption selectedCondo,
            String rolSII,
            String street,
            String num1,
            String num2,
            String regionName,
            int roomQty,
            int bathQty,
            int floorQty,
            int parkingQty,
            int storageQty,
            boolean hasGarden,
            boolean hasPatio,
            boolean hasPool,
            boolean hasBBQ,
            boolean hasBalcony,
            boolean hasTerrace,
            boolean hasLaundry,
            boolean inCondo,
            String sizeText
    		) throws SQLException {
        // Validaciones
        		if (selectedLandlord == null || selectedLandlord.getId() == -1) {
        			Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); // SPANISH for "You must select a valid landlord."
        			return false;
        		}

        		if (selectedTown == null || selectedTown.getId() == -1) {
        			Popup.show("ERROR: Debe seleccionar una comuna válida.", "error"); // SPANISH for "You must select a valid town."
        			return false;
        		}	

        		if (selectedType == null || selectedType.getValue().isEmpty()) {
        			Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error"); // SPANISH for "You must select a valid property type."
        			return false;
        		}

        		if (rolSII == null || rolSII.trim().isEmpty()
        				|| street == null || street.trim().isEmpty()
        				|| num1 == null || num1.trim().isEmpty()) {
        			Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error"); // SPANISH for "Complete all required fields for address and SII role."
        			return false;
        		 }
        		
        		int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}

        		// Create Land object
        		Land data = new Land();
        		data.setPropertyTypeId(5); // 5 = Land
        		data.setRolSII(rolSII.trim());
        		data.setLandlordId(selectedLandlord.getId());
        		data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño

        		data.setTownId(selectedTown.getId());
        		data.setRegionId(RegionDAO.getRegionIDByName(regionName)); 
        		data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2 != null ? num2.trim() : null); // num2 could be null

		        data.setRoomQty(roomQty);
		        data.setBathQty(bathQty);
		        data.setFloorQty(floorQty);
		        data.setHasParking(parkingQty > 0); // Consider that parkingQty > 0 means there is parking
		        data.setHasStorage(storageQty > 0); // Consider that storageQty > 0 means there is storage
		        data.setHasGarden(hasGarden);
		        data.setHasPatio(hasPatio);
		        data.setHasPool(hasPool);
		        data.setHasBBQ(hasBBQ);
		        data.setHasBalcony(hasBalcony);
		        data.setHasTerrace(hasTerrace);
		        data.setHasLaundry(hasLaundry);
		        
		        data.setInCondo(inCondo);
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);

		        return dao.insertCompleteLand(data);
    	}

	
		// --- END LAND --- //
	
	    // --- OFFICE // 
	
	public boolean saveOfficeStorageAndParking(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int floor,
	        int cabinQty,
	        int meetingRoomQty,
	        int storageQty,
	        int parkingQty,
	        boolean buildingHasLift,
	        boolean inCondo,
	        List<Parking> parkings,
	        List<Storage> storages,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
		                || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
				if (parkings == null || parkings.isEmpty()) {
					Popup.show("ERROR: Debe agregar los datos de al menos un estacionamiento.", "error");
					return false;
				}
				
				if (storages == null || storages.isEmpty()) {
					Popup.show("ERROR: Debe agregar los datos de al menos una bodega.", "error");
					return false;
				}
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Office object
		        Office data = new Office();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim());  // num2 could be null
		        data.setFloor(floor); // 4
		        data.setCabinQty(cabinQty); // 5
		        data.setMeetingRoomQty(meetingRoomQty); // 6
		        data.setHasStorage(storageQty); //7
		        data.setHasParking(parkingQty); //8
		        data.setBuildingHasLift(buildingHasLift); //9
		        data.setInCondo(inCondo); //10
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteOfficeParkingStorage(data, parkings, storages);
		}
    
	public boolean saveOfficeStorage(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int floor,
	        int cabinQty,
	        int meetingRoomQty,
	        int storageQty,
	        int parkingQty,
	        boolean buildingHasLift,
	        boolean inCondo,
	        List<Storage> storages,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
		                || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Office object
		        Office data = new Office();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim());  // num2 could be null
		        data.setFloor(floor); // 4
		        data.setCabinQty(cabinQty); // 5
		        data.setMeetingRoomQty(meetingRoomQty); // 6
		        data.setHasStorage(storageQty); //7
		        data.setHasParking(parkingQty); //8
		        data.setBuildingHasLift(buildingHasLift); // 9
		        data.setInCondo(inCondo); //10
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteOfficeStorage(data, storages);
		}
	
    
	public boolean saveOfficeParking(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int floor,
	        int cabinQty,
	        int meetingRoomQty,
	        int storageQty,
	        int parkingQty,
	        boolean buildingHasLift,
	        boolean inCondo,
	        List<Parking> parkings,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
		                || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
        		// Create Office object
		        Office data = new Office();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim());  // num2 could be null
		        data.setFloor(floor); // 4
		        data.setCabinQty(cabinQty); // 5
		        data.setMeetingRoomQty(meetingRoomQty); // 6
		        data.setHasStorage(storageQty); //5
		        data.setHasParking(parkingQty); //6
		        data.setBuildingHasLift(buildingHasLift); //8
		        data.setInCondo(inCondo); //13
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteOfficeParking(data, parkings);
		}
	

	
	public boolean saveOfficeNoSP(
			LandlordOption selectedLandlord,
	        TownOption selectedTown,
	        PropertyTypeOption selectedType,
	        CondoOption selectedCondo,
	        String rolSII,
	        String street,
	        String num1,
	        String num2,
	        String regionName,
	        int floor,
	        int cabinQty,
	        int meetingRoomQty,
	        int storageQty,
	        int parkingQty,
	        boolean buildingHasLift,
	        boolean inCondo,
	        String sizeText
			) throws SQLException {
	        // Validaciones
				if (selectedLandlord == null || selectedLandlord.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); 
		            return false;
		        }
	
		        if (selectedTown == null || selectedTown.getId() == -1) {
		            Popup.show("ERROR: Debe seleccionar una comuna válida.", "error");
		            return false;
		        }
	
		        if (selectedType == null || selectedType.getValue().isEmpty()) {
		            Popup.show("ERROR: Debe seleccionar un tipo de propiedad válido.", "error");
		            return false;
		        }
	
		        if (rolSII == null || rolSII.trim().isEmpty()
		                || street == null || street.trim().isEmpty()
		                || num1 == null || num1.trim().isEmpty()
		                || num2 == null || num2.trim().isEmpty()) {
		            Popup.show("ERROR: Complete todos los campos requeridos de dirección y rol SII.", "error");
		            return false;
		        }
		        
		        int size; // Inicializar tamaño
        		try {
        		    size = Integer.parseInt(sizeText.trim());
        		    if (size <= 0) {
        		        Popup.show("ERROR: Tamaño debe ser mayor a cero.", "error");
        		        return false;
        		    }
        		} catch (NumberFormatException e) {
        		    Popup.show("ERROR: Tamaño debe ser un número válido.", "error");
        		    return false;
        		}
		        
		        // Create Office object
		        Office data = new Office();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(size); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim()); // num2 could be null
		        data.setFloor(floor); // 4
		        data.setCabinQty(cabinQty); // 5
		        data.setMeetingRoomQty(meetingRoomQty); // 6
		        data.setHasStorage(storageQty); //7
		        data.setHasParking(parkingQty); //8
		        data.setBuildingHasLift(buildingHasLift); //9
		        data.setInCondo(inCondo); //10
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteOfficeNoSP(data);
		}
	}