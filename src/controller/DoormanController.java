package controller;

import dao.CondoDAO;
import dao.DoormanDAO;
import dao.RegionDAO;
import model.Condo;
import model.Doorman;
import utils.CondoOption;
import view.Popup;

public class DoormanController {

	public boolean saveDoorman(String rut, String name, String surname, String email, String phone,
			CondoOption selectedCondo) {
		if (rut == null || rut.trim().isEmpty()) {
            Popup.show("El RUT se encuentra vacío.", "error"); // SPANISH for "The name of the condominium is required."	
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            Popup.show("La dirección es obligatoria.", "error"); // SPANISH for "The address is required."
            return false;
        }
        
        if (surname == null || surname.trim().isEmpty()) {
			Popup.show("El apellido es obligatorio.", "error"); // SPANISH for "The surname is required."
			return false;
		}

        if (email != null && !email.trim().isEmpty() && !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
			Popup.show("El email no es válido.", "error"); // SPANISH for "The email is not valid."
			return false;
        }
        
        if (phone != null && !phone.trim().isEmpty() && !phone.matches("^\\+?\\d{7,15}$")) {
        	Popup.show("El teléfono no es válido.", "error"); // SPANISH for "The phone number is not valid."
        	return false;
        }
        
		if (selectedCondo == null || selectedCondo.getId() == 0) {
			Popup.show("Debe seleccionar un condominio válido.", "error"); // SPANISH for "You must select a valid
																			// condominium."
			return false;
		}

        int condoID = selectedCondo.getId();
        System.out.println("Selected Condo ID: " + condoID);
   

        Doorman newDoorman = new Doorman();
        newDoorman.setRut(rut.trim());
        newDoorman.setName(name.trim());
        newDoorman.setSurname(surname.trim());
        newDoorman.setEmail(email);
        newDoorman.setPhone(phone);
        newDoorman.setType("doorman"); // Setting type as "doorman"
        newDoorman.setCondoId(condoID);

        return DoormanDAO.save(newDoorman);
	}

}
