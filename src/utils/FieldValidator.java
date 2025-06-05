package utils;
import view.Popup;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FieldValidator {

    /**
     * Valida un conjunto de campos (JTextField) y retorna true si todos tienen contenido.
     * Muestra un Popup indicando los campos vacíos si los hay.
     *
     * @param campos Mapa de nombre descriptivo → JTextField
     * @return true si todos los campos están completos, false si hay vacíos.
     */
    public static boolean validField(Map<String, JTextField> campos) {
        StringBuilder vacios = new StringBuilder();

        for (Map.Entry<String, JTextField> entry : campos.entrySet()) {
            if (entry.getValue().getText().trim().isEmpty()) {
                vacios.append("- ").append(entry.getKey()).append("\n");
            }
        }

        if (vacios.length() > 0) {
        	Popup.show("Campos obligatorios vacíos:\n" + vacios, "error");
            return false;
        }

        return true;
    }
}
