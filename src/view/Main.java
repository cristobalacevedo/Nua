package view;

import java.awt.EventQueue;

import model.DBConnection;

public class Main {

	public static void main(String[] args) {
		DBConnection.connect(); // Initialize the database connection
		
		// Launch the GUI application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

