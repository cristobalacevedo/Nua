package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class showFrame {
	public static void show(JFrame newFrame) {
		newFrame.setResizable(false); // Prevent resizing of the window
		newFrame.setLocationRelativeTo(null); // Center the window on screen
		newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the window without exiting the application
		newFrame.setLayout(new BorderLayout()); // Set layout to BorderLayout
    	newFrame.setVisible(true);
    	newFrame.setBounds(0, 0, 910, 530);
    	newFrame.setLocationRelativeTo(null); // Center the window on screen
    	
    	// If the windows is disposed, print a message
		newFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent windowEvent) {
				System.out.println(newFrame.getName() + " window closed."); // Debug message to console
			}
		});
    }
}
