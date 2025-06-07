package view;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;

import db.DBConnection;

public class Main {
	public static void main(String[] args) {
		// ModFont.setOptionPaneFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 16));
		try { 
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { 
				if ("Nimbus".equals(info.getName())) { 
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// Nimbus no está disponible
		}

		// Después del Look & Feel
		EventQueue.invokeLater(() -> {
			DBConnection.connect();
			
			Menu frame = new Menu();
			frame.setVisible(true);
		});
	}
}

