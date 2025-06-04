package view;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// This class represents the Rentals panel in the application.
public class RentalsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH

	public RentalsPanel(Container contentPane, Menu menu) {
		setBackground(Color.GREEN);
		setForeground(Color.BLACK);
		setName("RentalsPanel"); // Set the name of the panel
		setLayout(null);
		setBounds(0, 0, 1080, 700);
		setVisible(true);
		
		// Back Button
		btnBack.setBounds(50, 600, 100, 30);
		add(btnBack);
				
		// Back Button ActionListener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.showPanel(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
				
	}
}