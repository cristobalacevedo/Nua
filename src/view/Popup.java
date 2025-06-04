package view;

import javax.swing.*;

public class Popup {
    public static void show(String texto, String tipo) {
        int tipoMsg = switch (tipo.toLowerCase()) {
            case "info" -> JOptionPane.INFORMATION_MESSAGE;
            case "error" -> JOptionPane.ERROR_MESSAGE;
            case "warn" -> JOptionPane.WARNING_MESSAGE;
            case "question" -> JOptionPane.QUESTION_MESSAGE;
            case "success" -> JOptionPane.PLAIN_MESSAGE;
            default -> JOptionPane.PLAIN_MESSAGE;
        };
        JOptionPane.showMessageDialog(null, texto, "Mensaje", tipoMsg);
    }
}
