package view;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Component;

public class PopupDialog {

    // Opción con ícono estándar de advertencia
    public static int showYesNoWarn(Component parent, String mensaje, String titulo) {
        String[] opciones = {"SÍ", "NO"};
        return JOptionPane.showOptionDialog(
                parent, // Parent component for the dialog
                mensaje, // Message to display
                titulo, // Window title
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE, 
                null,
                opciones,
                opciones[1]
        );
    }

    // Opción con ícono personalizado (pasado como ruta)
    public static int showYesNoIcon(Component parent, String mensaje, String titulo, String iconPath) {
        ImageIcon icono = new ImageIcon(iconPath);
        String[] opciones = {"SÍ", "NO"};
        return JOptionPane.showOptionDialog(
                parent, // Parent component for the dialog
                mensaje, // Message to display
                titulo, // Window title
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icono,
                opciones,
                opciones[1]
        );
    }

    // También puedes añadir más variantes, como SÍ/NO/CANCELAR, mensajes informativos, etc.
}
