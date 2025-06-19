package controller;

import dao.PropertyDAO;
import dao.RegionDAO;
import model.Flat;
import model.House;
import model.Parking;
import utils.CondoOption;
import utils.LandlordOption;
import utils.PropertyTypeOption;
import utils.TownOption;
import view.Popup;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class PropertyController {

    private final PropertyDAO dao;

    public PropertyController() {
        this.dao = new PropertyDAO();
    }

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
            boolean inCondo
          
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

        // Crear objeto House
        		House data = new House();
        		data.setPropertyTypeId(1); // 1 = House
        		data.setRolSII(rolSII.trim());
        		data.setLandlordId(selectedLandlord.getId());
        		data.setSize(0); // Puedes recibirlo como parámetro si usas el tamaño

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
	        int floorQty,
	        int storageQty,
	        int parkingQty,
	        boolean hasBalcony,
	        boolean buildingHasLift,
	        boolean buildingHasPool,
	        boolean buildingHasBBQ,
	        boolean buildingHasGym,
	        boolean buildingHasLaundry,
	        boolean inCondo,
	        List<Parking> parkings
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
		        
		        // Crear objeto Flat
		        Flat data = new Flat();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(0); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim());  // num2 could be null
		        data.setRoomQty(roomQty); // 2
		        data.setBathQty(bathQty); // 3
		        data.setFloorQty(floorQty); // 4
		        data.setHasStorage(storageQty > 0); //5
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
	
	public boolean saveFlatNoParking(
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
	        int storageQty,
	        int parkingQty,
	        boolean hasBalcony,
	        boolean buildingHasLift,
	        boolean buildingHasPool,
	        boolean buildingHasBBQ,
	        boolean buildingHasGym,
	        boolean buildingHasLaundry,
	        boolean inCondo
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
		        
		        // Crear objeto Flat
		        Flat data = new Flat();
		        data.setPropertyTypeId(2); // 2 = Flat
		        data.setRolSII(rolSII.trim());
		        data.setLandlordId(selectedLandlord.getId());
		        data.setSize(0); // Puedes recibirlo como parámetro si usas el tamaño
		        data.setTownId(selectedTown.getId());
		        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
		        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
		        data.setNum2(num2.trim()); // num2 could be null
		        data.setRoomQty(roomQty); // 2
		        data.setBathQty(bathQty); // 3
		        data.setFloorQty(floorQty); // 4
		        data.setHasStorage(storageQty > 0); //5
		        data.setHasParking(parkingQty); //6
		        data.setHasBalcony(hasBalcony); //7
		        data.setBuildingHasLift(buildingHasLift); //8
		        data.setBuildingHasPool(buildingHasPool); //9
		        data.setBuildingHasGym(buildingHasGym); //10
		        data.setBuildingHasLaundry(buildingHasLaundry); //11
		        data.setBuildingHasBBQ(buildingHasBBQ); //12
		        
		        data.setInCondo(inCondo); //13
		        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
		        
		        return dao.insertCompleteFlatNoParking(data);
		}

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
			boolean inCondo
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
	        
	        // Crear objeto Parking
	        Parking data = new Parking();
	        data.setPropertyTypeId(4); // 4 = Parking
	        data.setRolSII(rolSII.trim());
	        data.setLandlordId(selectedLandlord.getId());
	        data.setSize(0); // Puedes recibirlo como parámetro si usas el tamaño
	        data.setTownId(selectedTown.getId());
	        data.setRegionId(RegionDAO.getRegionIDByName(regionName));
	        data.setStreetName(street.trim()); // street could be null, but we trim it to avoid leading/trailing spaces
	        data.setNum1(num1.trim()); // num1 could be null, but we trim it to avoid leading/trailing spaces
	        data.setNum2(num2.trim()); // num2 could be null, but we trim it to avoid leading/trailing spaces
	        data.setInCondo(inCondo); 
	        data.setCondoId(inCondo && selectedCondo != null ? selectedCondo.getId() : null);
	        
	        
	        return dao.insertCompleteParking(data);
	}
}