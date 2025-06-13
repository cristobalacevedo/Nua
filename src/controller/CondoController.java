package controller;

import dao.CondoDAO;
import dao.RegionDAO;
import model.Condo;
import utils.TownOption;
import view.Popup;

public class CondoController {

    public boolean saveCondo(String name, String address, String email, String phone, String num,
                                String platformName, String regionName, TownOption selectedTown) {
        
        if (name == null || name.trim().isEmpty()) {
            Popup.show("El nombre del condominio es obligatorio.", "error"); // SPANISH for "The name of the condominium is required."	
            return false;
        }

        if (address == null || address.trim().isEmpty()) {
            Popup.show("La dirección es obligatoria.", "error"); // SPANISH for "The address is required."
            return false;
        }

        if (regionName == null || regionName.equals("Seleccione una Región")) { 
            Popup.show("Debe seleccionar una región válida.", "error"); // SPANISH for "You must select a valid region."
            return false;
        }

        if (selectedTown == null || selectedTown.getId() == 0) {
            Popup.show("Debe seleccionar una comuna válida.", "error"); // SPANISH for "You must select a valid town."
            return false;
        }

        // Limpieza de campos opcionales
        if (email != null && email.trim().isEmpty()) email = null;
        if (phone != null && phone.trim().isEmpty()) phone = null;
        if (num != null && num.trim().isEmpty()) num = null;
        if (platformName != null && platformName.trim().isEmpty()) platformName = null;

        Integer platformID = CondoDAO.getPlatformIDByName(platformName);
        if (platformID != null && platformID == -1) platformID = null;

        int townID = selectedTown.getId();
        int regionID = RegionDAO.getRegionIDByName(regionName);

        if (regionID == -1 || townID == 0) {
            Popup.show("Región o comuna no válidas.", "error");
            return false;
        }

        Condo newCondo = new Condo();
        newCondo.setName(name.trim());
        newCondo.setAddress(address.trim());
        newCondo.setEmail(email);
        newCondo.setPhone(phone);
        newCondo.setNum(num);
        newCondo.setCondoPlatformId(platformID);
        newCondo.setTownID(townID);
        newCondo.setRegionID(regionID);

        return CondoDAO.insertFullCondo(newCondo);
    }
}