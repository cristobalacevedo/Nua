package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.TextField;

// This class represents the Rentals panel in the application.
public class RentalsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JButton btnShowAA = new JButton("Propiedades Disponibles");
	private JLabel lblRentals;
	private JTextField textField;

	public RentalsPanel(Container contentPane, Menu menu) {
		setBackground(new Color(170, 170, 170));
		setForeground(Color.BLACK);
		setName("RentalsPanel"); // Set the name of the panel
		setLayout(null);
		setBounds(0, 0, 1250, 700);
		setVisible(true);
		
		// --- LABELS ---
		
		lblRentals = new JLabel("Panel de Arriendos"); // SPANISH for "Landlords Panel"");
		lblRentals.setForeground(Color.GRAY);
		lblRentals.setFont(new Font("Noto Sans JP", Font.PLAIN, 16));
		lblRentals.setHorizontalAlignment(SwingConstants.CENTER);
		lblRentals.setBounds(508, 6, 187, 16);
		add(lblRentals);
		
		
		// Back Button
		btnBack.setBounds(50, 600, 100, 30);
		btnBack.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		add(btnBack);
				
		// All Available Button
		btnShowAA.setBounds(80, 40, 225, 30);
		btnShowAA.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		add(btnShowAA);
		
		textField = new JTextField();
		textField.setBounds(219, 214, 100, 36);
		add(textField);
		textField.setColumns(10);
		
		
		
		// All Available Button ActionListener
		btnShowAA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMiniFrame.show(new AvailablePropertiesList()); // Show the LandlordsList Frame when the button is clicked
			}
		});
		
		
		// Back Button ActionListener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
		
		
				
	}
}