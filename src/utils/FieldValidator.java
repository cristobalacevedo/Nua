package utils;

import view.Popup;

import javax.swing.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FieldValidator {

    private static final List<String> INVALID_COMBO_VALUES = Arrays.asList("Seleccione", "-- Seleccione --", "");

    /**
     * Valida un conjunto de campos (JComponent) y retorna true si todos tienen contenido o una selección válida.
     * Muestra un Popup indicando los campos vacíos o inválidos si los hay.
     *
     * @param campos Mapa de nombre descriptivo → JComponent
     * @return true si todos los campos son válidos, false si hay vacíos o inválidos.
     */
    public static boolean validField(Map<String, JComponent> campos) {
        StringBuilder vacios = new StringBuilder();

        for (Map.Entry<String, JComponent> entry : campos.entrySet()) {
            String label = entry.getKey();
            JComponent comp = entry.getValue();

            boolean invalido = false;

            if (comp instanceof JTextField) {
                String text = ((JTextField) comp).getText().trim();
                invalido = text.isEmpty();

            } else if (comp instanceof JComboBox<?>) {
                JComboBox<?> combo = (JComboBox<?>) comp;
                Object selected = combo.getSelectedItem();

                // Validar si es una opción personalizada con método getId()
                if (selected == null) {
                    invalido = true;
                } else {
                    try {
                        // Verificamos si el objeto tiene un método getId()
                        var method = selected.getClass().getMethod("getId");
                        Object id = method.invoke(selected);
                        if (id instanceof Integer && (Integer) id == -1) {
                            invalido = true;
                        }
                    } catch (Exception ex) {
                        // Si no tiene getId(), usar toString como fallback
                        String selectedStr = selected.toString().trim();
                        invalido = selectedStr.isEmpty() || selectedStr.contains("Seleccione");
                    }
                }

            } else if (comp instanceof JSpinner) {
                Object value = ((JSpinner) comp).getValue();
                if (value instanceof Integer) {
                    invalido = (Integer) value <= 0;
                }
            }

            if (invalido) {
                vacios.append("- ").append(label).append("\n");
            }
        }

        if (vacios.length() > 0) {
            Popup.show("ERROR: \nCampos obligatorios vacíos o inválidos:\n" + vacios, "error");
            return false;
        }

        return true;
    }
}