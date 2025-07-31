package controller;

import dao.BankDAO;
import dao.TenantDAO;
import dao.PersonDAO;
import model.Tenant;
import utils.RUTValidator;
import view.Popup;
import view.PopupDialog;

public class TenantController {
   
	private final TenantDAO tenantDAO = new TenantDAO(null);
    private final BankDAO bankDAO = new BankDAO();

    public boolean saveTenant(String rut, String name, String surname, String email,
                                  String phone, String aval, String bankName, String accountType, String accountNum) {

        if (!RUTValidator.isValid(rut)) {
            Popup.show("RUT inválido. Verifica el formato y el dígito verificador.", "error"); // SPANISH for "Invalid RUT. Check the format and the verification digit."
            return false;
        }

        if (PersonDAO.rutExistsInDB(rut)) {
            Popup.show("El RUT ya está registrado en el sistema.", "error"); // SPANISH for "The RUT is already registered in the system."
            return false;
        }

        if (bankName == null || bankName.isEmpty() || bankName.equals("Seleccionar banco")) { // SPANISH for "Select a valid bank"
            Popup.show("Debe seleccionar un banco válido.", "error"); // SPANISH for "You must select a valid bank."
            return false;
        }

        if (accountType == null || accountType.isEmpty() || accountType.equals("Seleccionar tipo")) { // SPANISH for "Select a valid account type"
            Popup.show("Debe seleccionar un tipo de cuenta válido.", "error"); // SPANISH for "You must select a valid account type."
            return false;
        }

        int bankId = bankDAO.getIdByName(bankName);

        Tenant tenant = new Tenant(rut, name, surname, email, phone,
            "tenant", true, false, aval,
            bankDAO.getBankByName(bankName), accountType, accountNum);

        return tenantDAO.save(tenant, bankId, accountType, accountNum);
    }
    
    public boolean updateTenant(String rut, String name, String surname, String email,
            String phone, String aval, String bankName, String accountType, String accountNum) {

    	if (!RUTValidator.isValid(rut)) {
    		Popup.show("RUT inválido. Verifica el formato y el dígito verificador.", "error"); // SPANISH for "Invalid RUT. Check the format and the verification digit."
    		return false;
    	}

    	if (!PersonDAO.rutExistsInDB(rut)) {
    		Popup.show("El RUT no existe en el sistema. No se puede actualizar.", "error"); // SPANISH for "The RUT does not exist in the system. Cannot update."
    		return false;
    	}

    	if (bankName == null || bankName.isEmpty() || bankName.equals("Seleccionar banco")) { // SPANISH for "Select a valid bank"
    		Popup.show("Debe seleccionar un banco válido.", "error"); // SPANISH for "You must select a valid bank."
    		return false;
    	}

    	if (accountType == null || accountType.isEmpty() || accountType.equals("Seleccionar tipo")) { // SPANISH for "Select a valid account type"
    		Popup.show("Debe seleccionar un tipo de cuenta válido.", "error"); // SPANISH for "You must select a valid account type."
    		return false;
    	}

//		// Verificar si el banco existe
//		if (!bankDAO.bankExists(bankName)) {
//			Popup.show("El banco seleccionado no existe en el sistema.", "error");
//			return false;
//		}

		// Obtener el ID del banco

		int bankId = bankDAO.getIdByName(bankName);

		Tenant tenant = new Tenant(rut, name, surname, email, phone,
				"tenant", true, false, aval,
				bankDAO.getBankByName(bankName), accountType, accountNum);

		return tenantDAO.update(tenant, bankId, accountType, accountNum);
    }
    
    public boolean deleteTenant(String rut) {
        if (rut == null || rut.trim().isEmpty()) {
            Popup.show("Debe ingresar el RUT del arrendador que desea eliminar.", "error");
            return false;
        }

        rut = rut.trim();

        if (!RUTValidator.isValid(rut)) {
            Popup.show("RUT inválido. Verifique el formato.", "error");
            return false;
        }

        if (!PersonDAO.rutExistsInDB(rut)) {
            Popup.show("No existe un arrendador con ese RUT.", "error");
            return false;
        }

        int confirm = PopupDialog.showYesNoWarn(
	            null,
	            "¿Está seguro que desea eliminar al arrendador con RUT: " + rut + "?",
	            "Confirmar eliminación"
	        );

        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return false; // Usuario canceló
        }

        return tenantDAO.delete(rut);
    }
}
    
