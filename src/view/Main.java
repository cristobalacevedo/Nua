package view;

import java.awt.EventQueue;

import javax.swing.UIManager;

import db.DBConnection;

public class Main {
	public static void main(String[] args) {
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

