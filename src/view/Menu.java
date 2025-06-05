package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.UIManager.*;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnLandlords; // SPANISH
	private JButton btnProperties = new JButton("Propiedades"); // SPANISH
	private JButton btnTenants = new JButton("Arrendatarios"); // SPANISH
	private JButton btnRentals = new JButton("Arriendos"); // SPANISH
	private JButton btnReports = new JButton("Informes"); // SPANISH;
	private JButton btnSettings = new JButton("Configuración"); // SPANISH;
	private JButton btnExit = new JButton("Salir"); // SPANISH
	private boolean isMenuActive = true;
	private JPanel clonePanel; // Clone of the menu panel

	// Menu constructor
	public Menu() {
		
		// Parameters for the JFrame
		setResizable(false); // Prevent resizing of the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1250, 700);
		setLocationRelativeTo(null); // Center the window on screen
		setTitle("Nua - Menú Principal"); // Set the title of the window") - SPANISH
		contentPane = new JPanel();
		contentPane.setName("Menu"); // Set the name of the panel to "Menu"
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	
		// --- BUTTONS --- //
		
		// Landlords Button
		btnLandlords = new JButton("Propietarios");
		btnLandlords.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnLandlords.setBounds(100, 60, 176, 56);
		contentPane.add(btnLandlords);	
		
		// Properties Button
		btnProperties.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnProperties.setBounds(100, 140, 176, 56);
		contentPane.add(btnProperties);
		
		// Rentals Button		
		btnRentals.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnRentals.setBounds(100, 220, 176, 56);
		contentPane.add(btnRentals);
		
		// Tenants Button
		btnTenants.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnTenants.setBounds(100, 300, 176, 56);
		contentPane.add(btnTenants);
		
		// Reports Button
		btnReports.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnReports.setBounds(100, 380, 176, 56);
		contentPane.add(btnReports);
		
		// Settings Button
		btnSettings.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		btnSettings.setBounds(100, 460, 176, 56);
		contentPane.add(btnSettings);
		
		// Exit Button
		btnExit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btnExit.setBounds(100, 571, 89, 31);
		contentPane.add(btnExit);
		
		// --- END BUTTONS --- //
		
		
		// --- BUTTON ACTIONS --- //
		
		// Landlords Button Action
		btnLandlords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the LandlordsPanel when button is clicked
				showPanel.showPanel(contentPane, new LandlordsPanel(contentPane, Menu.this)); 
			}
		});
		
		// Properties Button Action
		btnProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the PropertiesPanel when button is clicked
				showPanel.showPanel(contentPane, new PropertiesPanel(contentPane, Menu.this)); 
			}
		});
		
		// Rentals Button Action
		btnRentals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the RentalsPanel when button is clicked
				showPanel.showPanel(contentPane, new RentalsPanel(contentPane, Menu.this)); 
			}
		});
		
		// Tenants Button Action
		btnTenants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the TenantsPanel when button is clicked
				showPanel.showPanel(contentPane, new TenantsPanel(contentPane, Menu.this)); 
			}
		});
		
		// Reports Button Action
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the ReportsPanel when button is clicked
				showPanel.showPanel(contentPane, new ReportsPanel(contentPane, Menu.this)); 
			}
		});
		
		
		// Exit Button Action, or if the ESC key is pressed, exit the application
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // Exit the application
			}
		});
		
		// Este código va en el constructor del JFrame "Menu"
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke("ESCAPE"), "exitApp");

		getRootPane().getActionMap().put("exitApp", new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (isMenuActive) {
				System.exit(0);
			}
		}
		});
		
		// --- END BUTTON ACTIONS --- //
		
		clonePanel = MenuClone(); // Create a clone of the menu panel
		
		
	}
	
	public void setMenuActive(boolean active) {
		this.isMenuActive = active;
	}
	
	// MENU CLONE
	
	public JPanel MenuClone(){
		
		JPanel menuClone = new JPanel();
		menuClone.setName("MenuClone");
		JButton btnLandlordsClone = new JButton(btnLandlords.getText());
		btnLandlordsClone.setFont(btnLandlords.getFont());
		btnLandlordsClone.setBounds(btnLandlords.getBounds());
		btnLandlordsClone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the LandlordsPanel when button is clicked
				showPanel.showPanel(contentPane, new LandlordsPanel(contentPane, Menu.this)); 
			}
		});
		
		JButton btnPropertiesClone = new JButton(btnProperties.getText());
		btnPropertiesClone.setFont(btnProperties.getFont());
		btnPropertiesClone.setBounds(btnProperties.getBounds());
		btnPropertiesClone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the PropertiesPanel when button is clicked
				showPanel.showPanel(contentPane, new PropertiesPanel(contentPane, Menu.this)); 
			}
		});
		
		JButton btnRentalsClone = new JButton(btnRentals.getText());
		btnRentalsClone.setFont(btnRentals.getFont());
		btnRentalsClone.setBounds(btnRentals.getBounds());
		btnRentalsClone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the RentalsPanel when button is clicked
				showPanel.showPanel(contentPane, new RentalsPanel(contentPane, Menu.this)); 
			}
		});
		
		JButton btnTenantsClone = new JButton(btnTenants.getText());
		btnTenantsClone.setFont(btnTenants.getFont());
		btnTenantsClone.setBounds(btnTenants.getBounds());
		btnTenantsClone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the TenantsPanel when button is clicked
				showPanel.showPanel(contentPane, new TenantsPanel(contentPane, Menu.this)); 
			}
		});
		
		JButton btnReportsClone = new JButton(btnReports.getText());
		btnReportsClone.setFont(btnReports.getFont());
		btnReportsClone.setBounds(btnReports.getBounds());
		btnReportsClone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isMenuActive = false;
				// Show the ReportsPanel when button is clicked
				showPanel.showPanel(contentPane, new ReportsPanel(contentPane, Menu.this)); 
			}
		});
		
		JButton btnSettingsClone = new JButton(btnSettings.getText());
		btnSettingsClone.setFont(btnSettings.getFont());
		btnSettingsClone.setBounds(btnSettings.getBounds());
		//btnSettingsClone.addActionListener(e -> showPanel.showPanel(contentPane, new SettingsPanel()));
		
		JButton btnExitClone = new JButton(btnExit.getText());
		btnExitClone.setFont(btnExit.getFont());
		btnExitClone.setBounds(btnExit.getBounds());
		btnExitClone.addActionListener(exitEvent -> System.exit(0)); // Exit the application
		
		menuClone.add(btnLandlordsClone);
		menuClone.add(btnPropertiesClone);
		menuClone.add(btnRentalsClone);
		menuClone.add(btnTenantsClone);
		menuClone.add(btnReportsClone);
		menuClone.add(btnSettingsClone);
		menuClone.add(btnExitClone);
		
		isMenuActive = true; // Set the menu as active
		
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke("ESCAPE"), "exitApp");

		getRootPane().getActionMap().put("exitApp", new AbstractAction() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (isMenuActive) {
				System.exit(0);
			}
		}
		});
		
		return menuClone;
	}
	
}
