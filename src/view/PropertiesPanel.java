package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.LandlordDAO;
import dao.PropertyDAO;
import dao.RegionDAO;
import dao.TownDAO;
import db.DBConnection;

import utils.PropertyTypeOption;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

// This class represents the Properties panel in the application.
public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JButton btnSave; // Button to save property information
	private JComboBox<String> comboLandlord; // Text field for landlord information
	private JComboBox<PropertyTypeOption> comboPropertyType;
	private JComboBox<String> comboRegion;
	private JComboBox<String> comboTown; // Text field for town information
	private JLabel lblLandlord;
	private JLabel lblPropertyType;
	private JLabel lblRegion;
	private JLabel lblTown;
	private JLabel lblAddress;
	private JLabel lblAddressNum1;
	private JLabel lblAddressNum2;
	private JLabel lblLocationTitle;
	private JLabel lblSize;
	private JLabel lblRoomQty;
	private JLabel lblBathQty;
	private JLabel lblFloorQty;
	private JLabel lblParkingQty;
	private JLabel lblHasStorage;
	private JLabel lblHasGarden;
	private JLabel lblHasPatio;
	private JLabel lblHasPool;
	private JLabel lblHasBalcony;
	private JLabel lblHasBBQ;
	private JLabel lblHasTerrace;
	private JLabel lblHasLaundryRoom;
	private JLabel lblInCondo;
	private JLabel lblBuildingHasLift;
	private JLabel lblBuildingHasPool;
	private JLabel lblBuildingHasBBQ;
	private JLabel lblBuildingHasGym;
	private JLabel lblBuildingHasLaundryRoom;
	
	
	
	private JTextField txtAddress;
	private JTextField txtNum1;
	private JTextField txtNum2;
	private JSeparator separator_1;
	
	public PropertiesPanel(Container contentPane, Menu menu) {
		setBackground(new Color(187, 187, 187));
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
		comboLandlord.setBounds(216, 29, 270, 30); // Set position and size of the text field
		comboLandlord.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the combo box
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
		comboPropertyType.setBounds(791, 29, 270, 30); // Set position and size of the text field
		comboPropertyType.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the combo box
		comboPropertyType.setEditable(false); // Make the combo box editable
		add(comboPropertyType); // Add the text field to the panel
		
		comboPropertyType.addItem(new PropertyTypeOption("Seleccione un Tipo", "")); // default //SPANISH for "Select a Property Type"
		
		PropertyDAO propertyDAO = new PropertyDAO();
		List<String> propertyTypes = propertyDAO.getAllTypes();

		Map<String, String> translations = new HashMap<>();
		translations.put("House", "Casa");
		translations.put("Flat", "Departamento");
		translations.put("Storage", "Bodega");
		translations.put("Parking", "Estacionamiento");
		translations.put("Land", "Parcela/Terreno");
		translations.put("Office", "Oficina");

		for (String type : propertyTypes) {
		    String label = translations.getOrDefault(type, type); // fallback al mismo valor si no hay traducción
		    comboPropertyType.addItem(new PropertyTypeOption(label, type));
		}
		
		// -- END PROPERTY TYPE -- //
		
		// -- REGION -- //
		
		comboRegion = new JComboBox<>();
		comboRegion.setBounds(222, 167, 310, 30); // Set position and size of the text field
		comboRegion.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the combo box
		comboRegion.setEditable(false); // Make the combo box editable
		add(comboRegion); // Add the text field to the panel
		String defaultRegion = "Seleccione una Región"; // SPANISH for "Select a Region"
		comboRegion.addItem(defaultRegion); // SPANISH for "Select a Region"
		
		RegionDAO regionDAO = new RegionDAO(); // Create an instance of RegionDAO to fetch regions
		List<String> regions = regionDAO.getAllRegion(); // Fetch all regions from the database
		
		for (String region : regions) {
			comboRegion.addItem(region); // Add each region to the combo box
		}
		
		// Add an ActionListener to the region combo box to update towns based on selected region
		comboRegion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedRegion = (String) comboRegion.getSelectedItem(); // Get the selected region
				if (selectedRegion != null && !selectedRegion.equals(defaultRegion)) {
					int regionID = RegionDAO.getRegionIDByName(selectedRegion); // Get the ID of the selected region
					comboTown.removeAllItems(); // Clear previous towns

					List<String> towns = TownDAO.getAllTownsByRegionID(regionID); // Fetch towns for the selected region
					for (String town : towns) {
						comboTown.addItem(town); // Add each town to the combo box
					}
				} else {
					comboTown.removeAllItems(); // Clear towns if no region is selected
					comboTown.addItem("Seleccione una Comuna"); // SPANISH for "Select a Town"
				}
			}
		});
		
		// -- END REGION -- //
		
		// -- TOWN -- //
		
		comboTown = new JComboBox<>();
		comboTown.setBounds(222, 208, 310, 30); // Set position and size of the text field
		comboTown.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the combo box
		comboTown.setEditable(false); // Make the combo box editable
		add(comboTown); // Add the text field to the panel
		
		comboTown.addItem("Seleccione una Comuna"); // Add default item
		
		// -- END TOWN -- //

		// --- END COMBO BOXES --- //
		
		// --------------------- //
		
		// --- BUTTONS --- //
		
		// -- BACK BUTTON -- // 
		
		btnBack.setBounds(50, 600, 100, 30);
		add(btnBack);
		
		// -- END BACK BUTTON -- //
		
		// -- SAVE BUTTON -- //
		
		btnSave = new JButton("Guardar"); // SPANISH for "Save"
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(0, 153, 0));
		btnSave.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		btnSave.setBounds(515, 600, 125, 58);
		add(btnSave);
		
		// --- END BUTTONS --- //
		
		// ---------------------- //
		
		// --- LABELS --- //		
		
		// -- LANDLORD -- //
		
		lblLandlord = new JLabel("Propietario:"); // SPANISH for "Landlord" or "Owner"
		lblLandlord.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); // Set font size for the label
		lblLandlord.setBounds(112, 33, 94, 22);
		add(lblLandlord);
		
		// -- END LANDLORD -- //
		
		// -- PROPERTY TYPE -- //
		
		lblPropertyType = new JLabel("Tipo:"); // SPANISH for "Property Type"
		lblPropertyType.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); // Set font size for the label
		lblPropertyType.setBounds(734, 33, 46, 22);
		add(lblPropertyType);
		
		// -- END PROPERTY TYPE -- //
		
		// -- REGION, TOWN, ADDRESS -- //
		
		lblRegion = new JLabel("Región:"); // SPANISH for "Region"
		lblRegion.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblRegion.setBounds(139, 167, 67, 30);
		add(lblRegion);
		
		lblTown = new JLabel("Comuna:"); // SPANISH for "Town" or "Community" or "Municipality"
		lblTown.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblTown.setBounds(139, 208, 73, 30);
		add(lblTown);
		
		lblAddress = new JLabel("Calle/Avenida/Pasaje:"); // SPANISH for "Address"
		lblAddress.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblAddress.setBounds(35, 249, 186, 30);
		add(lblAddress);
		
		lblAddressNum1 = new JLabel("Nº");
		lblAddressNum1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblAddressNum1.setBounds(442, 249, 37, 30);
		add(lblAddressNum1);
		
		lblLocationTitle = new JLabel("Dirección"); // SPANISH for "Address Title"
		lblLocationTitle.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 24));
		lblLocationTitle.setBounds(118, 118, 170, 36);
		add(lblLocationTitle);
		
		lblRoomQty = new JLabel("Dormitorios:"); // SPANISH for "Bedrooms"
		lblRoomQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRoomQty.setBounds(680, 99, 125, 30);
		add(lblRoomQty);
		
		lblBathQty = new JLabel("Baños:"); // SPANISH for "Bathrooms"
		lblBathQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblBathQty.setBounds(680, 123, 81, 30);
		add(lblBathQty);
		
		lblFloorQty = new JLabel("Pisos:"); // SPANISH for "Floors"
		lblFloorQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblFloorQty.setBounds(680, 147, 81, 30);
		add(lblFloorQty);
		
		lblParkingQty = new JLabel("Estacionamientos:"); // SPANISH for "Parking Spots"
		lblParkingQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblParkingQty.setBounds(680, 171, 170, 30);
		add(lblParkingQty);
		
		lblHasStorage = new JLabel("Bodega:"); // SPANISH for "Storage"
		lblHasStorage.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasStorage.setBounds(680, 195, 81, 30);
		add(lblHasStorage);
		
		lblHasGarden = new JLabel("Jardín:"); // SPANISH for "Garden"
		lblHasGarden.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasGarden.setBounds(680, 219, 81, 30);
		add(lblHasGarden);
		
		lblHasPatio = new JLabel("Patio:"); // SPANISH for "Patio"
		lblHasPatio.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasPatio.setBounds(680, 243, 81, 30);
		add(lblHasPatio);
		
		lblHasPool = new JLabel("Piscina:"); // SPANISH for "Pool"
		lblHasPool.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasPool.setBounds(680, 267, 81, 30);
		add(lblHasPool);
		
		lblHasBalcony = new JLabel("Balcón:"); // SPANISH for "Balcony"
		lblHasBalcony.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasBalcony.setBounds(680, 291, 81, 30);
		add(lblHasBalcony);
		
		lblHasBBQ = new JLabel("Quincho:"); // SPANISH for "BBQ Area or Grill Area"
		lblHasBBQ.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasBBQ.setBounds(680, 315, 81, 30);
		add(lblHasBBQ);
		
		lblHasTerrace = new JLabel("Terraza:"); // SPANISH for "Terrace"
		lblHasTerrace.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasTerrace.setBounds(680, 339, 81, 30);
		add(lblHasTerrace);
		
		lblHasLaundryRoom = new JLabel("Lavandería:"); // SPANISH for "Laundry Room"
		lblHasLaundryRoom.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
	    lblHasLaundryRoom.setBounds(680, 363, 125, 30);
	    add(lblHasLaundryRoom);
	    
	    lblInCondo = new JLabel("En Condominio:"); // SPANISH for "In Condominium"
	    lblInCondo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
	    lblInCondo.setBounds(326, 123, 140, 30);
	    add(lblInCondo);
	    
	    lblBuildingHasLift = new JLabel("Edificio con Ascensor:"); // SPANISH for "Building with Elevator"
	    lblBuildingHasLift.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblBuildingHasLift.setBounds(680, 411, 195, 30);
	    add(lblBuildingHasLift);
	    
	    lblBuildingHasPool = new JLabel("Edificio con Piscina:"); // SPANISH for "Building with Pool"
	    lblBuildingHasPool.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblBuildingHasPool.setBounds(680, 435, 195, 30);
	    add(lblBuildingHasPool);
	    
	    lblBuildingHasBBQ = new JLabel("Edificio con Quincho:"); // SPANISH for "Building with BBQ Area"
	    lblBuildingHasBBQ.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblBuildingHasBBQ.setBounds(680, 459, 195, 30);
	    add(lblBuildingHasBBQ);
	    
	    lblBuildingHasGym = new JLabel("Edificio con Gimnasio:"); // SPANISH for "Building with Gym"
	    lblBuildingHasGym.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblBuildingHasGym.setBounds(680, 483, 195, 30);
	    add(lblBuildingHasGym);
	    
	    lblBuildingHasLaundryRoom = new JLabel("Edificio con Lavandería:"); // SPANISH for "Building with Laundry Room"
	    lblBuildingHasLaundryRoom.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblBuildingHasLaundryRoom.setBounds(680, 507, 195, 30);
	    add(lblBuildingHasLaundryRoom);
	    
	    // -- END LABELS -- //

		// -- END REGION, TOWN, ADDRESS -- //
		
		// --- END LABELS --- //
		
		// ---------------------- //
		
		// --- SEPARATORS--- //
		
		JSeparator separator = new JSeparator();
		separator.setBounds(112, 70, 420, 22);
		add(separator);
		
		// --- END SEPARATORS --- //
		
		// ---------------------- //
		
		// --- TEXT FIELDS --- //
		
		// -- ADDRESS -- //
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the address text field
		txtAddress.setBounds(222, 249, 220, 30);
		add(txtAddress);
		txtAddress.setColumns(10);
		
		// -- END ADDRESS -- //
		
		// -- ADDRESS NUMBER 1 (OBLIGATORY) -- //
		
		txtNum1 = new JTextField();
		txtNum1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the address number text field
		txtNum1.setColumns(10);
		txtNum1.setBounds(465, 249, 67, 30);
		add(txtNum1);
		
		// -- END ADDRESS NUMBER 1 -- //
		
		// -- ADDRESS NUMBER 2 (OPTIONAL) -- //
		txtNum2 = new JTextField();
		txtNum2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the address number text field
		txtNum2.setColumns(10);
		txtNum2.setBounds(465, 286, 67, 30);
		txtNum2.setVisible(false); // Initially hidden, can be made visible later if needed
		add(txtNum2);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(734, 70, 420, 22);
		add(separator_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBounds(456, 122, 30, 38);
		add(chckbxNewCheckBox);
		
		
		
		
		
		// ---------------------- //
		
		
		// Back Button ActionListener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
		
		// ---------------------- //
		
		// --- AUXILIARY METHODS --- //
		
		
				
	}
}
