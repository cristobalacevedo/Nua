package view;

import java.awt.Image;
import java.net.URL;

import javax.swing.*;

public class Popup {
    // Método principal sin icono (compatible con uso actual)
    public static void show(String texto, String tipo) {
        show(texto, tipo, null); // Llama al método sobrecargado
    }

    // Método sobrecargado con posibilidad de ícono personalizado
    public static void show(String texto, String tipo, Icon iconoPersonalizado) {
        int tipoMsg = switch (tipo.toLowerCase()) {
            case "info" -> JOptionPane.INFORMATION_MESSAGE;
            case "error" -> JOptionPane.ERROR_MESSAGE;
            case "warn" -> JOptionPane.WARNING_MESSAGE;
            case "question" -> JOptionPane.QUESTION_MESSAGE;
            case "success" -> JOptionPane.PLAIN_MESSAGE;
            default -> JOptionPane.PLAIN_MESSAGE;
        };

        if (iconoPersonalizado != null) {
            JOptionPane.showMessageDialog(null, texto, "Mensaje", tipoMsg, iconoPersonalizado);
        } else {
            JOptionPane.showMessageDialog(null, texto, "Mensaje", tipoMsg);
        }
    }
    

    public static void showSuccess(String texto) {
        URL location = Popup.class.getResource("/ico/success.png");
        if (location != null) {
            ImageIcon originalIcon = new ImageIcon(location);
            Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            Icon scaledIcon = new ImageIcon(scaledImage);
            show(texto, "success", scaledIcon);
        } else {
        	System.err.println("Resource not found at: " + location);
            show(texto, "success");
        }
    }
    
	public static void showUserDeletedSuccess(String texto) {
		URL location = Popup.class.getResource("/ico/user_delete.png");
		if (location != null) {
			ImageIcon originalIcon = new ImageIcon(location);
            Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
            Icon scaledIcon = new ImageIcon(scaledImage);
            show(texto, "success", scaledIcon);
            System.out.println("User deleted successfully.");
		} else {
			System.err.println("Resource not found at: " + location);
			show(texto, "success");
		}
	}
}