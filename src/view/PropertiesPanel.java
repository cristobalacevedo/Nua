package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.LandlordDAO;
import dao.PropertyDAO;
import db.DBConnection;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

// This class represents the Properties panel in the application.
public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JComboBox<String> comboLandlord; // Text field for landlord information
	private JComboBox<String> comboPropertyType;
	private JLabel lblLandlord;
	private JLabel lblPropertyType;
	private JLabel lblRegion;
	private JLabel lblTown;
	private JLabel lblAddress;
	
	public PropertiesPanel(Container contentPane, Menu menu) {
		setBackground(Color.MAGENTA);
		setForeground(Color.BLACK);
		setName("PropertiesPanel"); // Set the name of the panel
		setLayout(null);
		setBounds(0, 0, 1250, 700);
		setVisible(true);
		DBConnection.getConnection();
		
		// --------------------- //
		
		// --- COMBO BOXES --- //
		
		// -- LANDLORD -- //
		
		comboLandlord = new JComboBox<>();
		comboLandlord.setBounds(302, 164, 270, 30); // Set position and size of the text field
		comboLandlord.setEditable(false); // Make the combo box editable
		add(comboLandlord); // Add the text field to the panel
		
		comboLandlord.addItem("Seleccione un Propietario"); // Add a default item to the combo box
		
		LandlordDAO landlordDAO = new LandlordDAO(null); // Create an instance of LandlordDAO to fetch landlords
		List<String> landlords = landlordDAO.getAllNames(); // Fetch all landlords from the database
		
		for (String landlord : landlords) {
			comboLandlord.addItem(landlord); // Add each landlord to the combo box
		}
		
		// -- END LANDLORD -- //
		
		// -- PROPERTY TYPE -- //
		
		comboPropertyType = new JComboBox<>();
		comboPropertyType.setBounds(302, 214, 270, 30); // Set position and size of the text field
		comboPropertyType.setEditable(false); // Make the combo box editable
		add(comboPropertyType); // Add the text field to the panel
		
		comboPropertyType.addItem("Seleccione un Tipo de Propiedad"); // Add a default item to the combo box
		
		PropertyDAO propertyDAO = new PropertyDAO(); // Create an instance of PropertyDAO to fetch property types
		List<String> propertyTypes = propertyDAO.getAllTypes(); // Fetch all property types from the database
		
		for (String propertyType : propertyTypes) {
			comboPropertyType.addItem(propertyType); // Add each property type to the combo box
		}
		
		
		// -- END PROPERTY TYPE -- //
		
		// --- END COMBO BOXES --- //
		
		// --------------------- //
		
		// --- BUTTONS --- //
		
		// -- BACK BUTTON -- // 
		
		btnBack.setBounds(50, 600, 100, 30);
		add(btnBack);
		
		// -- END BACK BUTTON -- //
		
		// --- END BUTTONS --- //
		
		// ---------------------- //
		
		// --- LABELS --- //		
		
		// -- LANDLORD -- //
		
		lblLandlord = new JLabel("Propietario:"); // SPANISH for "Landlord" or "Owner"
		lblLandlord.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); // Set font size for the label
		lblLandlord.setBounds(191, 164, 94, 22);
		add(lblLandlord);
		
		// -- END LANDLORD -- //
		
		// -- PROPERTY TYPE -- //
		
		lblPropertyType = new JLabel("Tipo:"); // SPANISH for "Property Type"
		lblPropertyType.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); // Set font size for the label
		lblPropertyType.setBounds(191, 214, 88, 22);
		add(lblPropertyType);
		
		// -- END PROPERTY TYPE -- //
		
		// -- REGION, TOWN, ADDRESS -- //
		
		lblRegion = new JLabel("Región:"); // SPANISH for "Region"
		lblRegion.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); // Set font size for the label
		lblRegion.setBounds(191, 277, 67, 30);
		add(lblRegion);
		
		lblTown = new JLabel("Comuna:"); // SPANISH for "Town" or "Community" or "Municipality"
		lblTown.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); // Set font size for the label
		lblTown.setBounds(191, 296, 73, 30);
		add(lblTown);
		
		lblAddress = new JLabel("Dirección:"); // SPANISH for "Address"
		lblAddress.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); // Set font size for the label
		lblAddress.setBounds(191, 325, 94, 30);
		add(lblAddress);

		// -- END REGION, TOWN, ADDRESS -- //
		
		// --- END LABELS --- //
		
		// ---------------------- //
		
		
		// Back Button ActionListener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
				
	}
}
