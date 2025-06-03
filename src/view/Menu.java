package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnLandlords = new JButton("Propietarios"); // SPANISH
	private JButton btnProperties = new JButton("Propiedades"); // SPANISH
	private JButton btnTenants = new JButton("Arrendatarios"); // SPANISH
	private JButton btnRentals = new JButton("Arriendos"); // SPANISH
	private JButton btnReports = new JButton("Informes"); // SPANISH;
	private JButton btnSettings = new JButton("Configuración"); // SPANISH;
	private JButton btnExit = new JButton("Salir"); // SPANISH

	
	

	/**
	 * Create the frame.
	 */
	
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1080, 700);
		setLocationRelativeTo(null); // Center the window on screen
		setTitle("Nua - Menú Principal"); // Set the title of the window");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Landlords Button
		btnLandlords.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnLandlords.setBounds(63, 120, 176, 56);
		contentPane.add(btnLandlords);
		
		// Landlords Button Action
		btnLandlords.addActionListener(e -> {
			LandlordsPanel landlordsPanel = new LandlordsPanel();
			
			contentPane.removeAll(); // Remove all components from the content pane
			
			contentPane.revalidate(); // Revalidate the content pane to refresh the UI
			contentPane.repaint(); // Repaint the content pane to reflect changes
			
			
			});
		
		// Exit Button
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnExit.setBounds(63, 563, 89, 31);
		contentPane.add(btnExit);
		
		
		
		// Properties Button
		btnProperties.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnProperties.setBounds(63, 200, 176, 56);
		contentPane.add(btnProperties);
		
		// Rentals Button		
		btnRentals.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnRentals.setBounds(63, 280, 176, 56);
		contentPane.add(btnRentals);
		
		
		
		// Properties Button Action
		btnProperties.addActionListener(e -> {
			contentPane.removeAll(); // Remove all components from the content pane
			PropertiesPanel propertiesPanel = new PropertiesPanel(); // Create the properties panel
			propertiesPanel.setSize(1080, 680);
			propertiesPanel.setLocation(594, 268);
			contentPane.add(propertiesPanel, BorderLayout.CENTER);
			
			

			contentPane.revalidate(); // Revalidate the content pane to refresh the UI
			contentPane.repaint(); // Repaint the content pane to reflect changes
		});
		
		
		
		// Rentals Button Action
		btnRentals.addActionListener(e -> {
			contentPane.removeAll(); // Remove all components from the content pane
			//RentalsPanel rentalsPanel = new RentalsPanel(); // Create the rentals panel
			//rentalsPanel.setSize(1080, 680);
			//rentalsPanel.setLocation(594, 268);
			//contentPane.add(rentalsPanel, BorderLayout.CENTER);
			
			contentPane.revalidate(); // Revalidate the content pane to refresh the UI
			contentPane.repaint(); // Repaint the content pane to reflect changes
		});
		
		// Exit Button Action
		btnExit.addActionListener(exitEvent -> {
		System.exit(0); // Exit the application
				});
	}
}
