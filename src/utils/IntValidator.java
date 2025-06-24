package utils;

import javax.swing.JTextField;

import view.Popup;

public class IntValidator {

	public static Integer valid(JTextField field, String fieldName) {
	    String text = field.getText().trim();
	    if (text.isEmpty()) {
	        Popup.show("Debes ingresar el campo: " + fieldName, "error");
	        return null;
	    }

	    try {
	        return Integer.parseInt(text);
	    } catch (NumberFormatException e) {
	        Popup.show("El campo \"" + fieldName + "\" debe ser un número entero válido.", "error");
	        return null;
	    }
	}
}
