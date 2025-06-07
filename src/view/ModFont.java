package view;

import javax.swing.*;
import java.awt.*;


public class ModFont {
	
	public static void setOptionPaneFont(Font font) {
        UIManager.put("JOptionPane.messageFont", font);
        UIManager.put("JOptionPane.buttonFont", font);
        UIManager.put("JOptionPane.messageForeground", Color.BLACK); // opcional
    }
	
}
