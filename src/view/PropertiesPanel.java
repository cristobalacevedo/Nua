package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.HousePropertyData;
import dao.LandlordDAO;
import dao.PropertyDAO;
import dao.RegionDAO;
import dao.TownDAO;
import db.DBConnection;
import utils.LandlordOption;
import utils.PropertyTypeOption;
import utils.TownOption;

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
import javax.swing.JSpinner;

// This class represents the Properties panel in the application.
public class PropertiesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JButton btnSave; // Button to save property information
	private JComboBox<LandlordOption> comboLandlord; // Text field for landlord information
	private JComboBox<PropertyTypeOption> comboPropertyType;
	private JComboBox<String> comboRegion;
	private JComboBox<TownOption> comboTown; // Text field for town information
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
	private JCheckBox chckbxInCondo;
	private JTextField txtAddress;
	private JTextField txtNum1;
	private JTextField txtNum2;
	private JTextField txtSize;
	private JSeparator separator1;
	private JSeparator separator2;
	private JSpinner spinnerRoom;
	private JSpinner spinnerBath;
	private JSpinner spinnerFloor;
	private JSpinner spinnerParking;
	private JSpinner spinnerStorage;
	private JCheckBox chckbxGarden;
	private JCheckBox chckbxPatio;
	private JCheckBox chckbxPool;
	private JCheckBox chckbxBalcony;
	private JCheckBox chckbxBBQ;
	private JCheckBox chckbxTerrace;
	private JCheckBox chckbxLaundry;
	private JCheckBox chckbxBldngLift;
	private JCheckBox chckbxBldngPool;
	private JCheckBox chckbxBldngBBQ;
	private JCheckBox chckbxBldngGym;
	private JCheckBox chckbxBldngLaundry;
	private PropertyDAO propertiesDAO = new PropertyDAO(DBConnection.getConnection());
	
	
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
		
		comboLandlord.addItem(new LandlordOption(-1, "Seleccione un propietario")); // SPANISH for "Select a Landlord"
		
		LandlordDAO landlordDAO = new LandlordDAO(null);
		List<LandlordOption> landlords = landlordDAO.getAllLandlordOptions();

		for (LandlordOption option : landlords) {
		    comboLandlord.addItem(option);
		}
		
//		LandlordOption selectedLandlord = null;
//		if (selectedLandlord.getId() == -1) {
//		    Popup.show("Debe seleccionar un propietario válido.", "error");
//		    return;
//		}
		
		// -- END LANDLORD -- //
		
		// -- PROPERTY TYPE -- //
		
		comboPropertyType = new JComboBox<>();
		comboPropertyType.setBounds(791, 29, 180, 30);
		comboPropertyType.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboPropertyType.setEditable(false); // Make the combo box not editable
		add(comboPropertyType);
		
		comboPropertyType.addItem(new PropertyTypeOption("Seleccione un Tipo", "")); // SPANISH for "Select a Property Type"
		
		// PropertyDAO propertyDAO = new PropertyDAO();
		List<String> propertyTypes = PropertyDAO.getAllTypes();

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
		
		// Imprime en consola el tipo de propiedad seleccionado
		comboPropertyType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PropertyTypeOption selectedOption = (PropertyTypeOption) comboPropertyType.getSelectedItem();
				if (selectedOption != null) {
					String propertyType = selectedOption.getValue(); // Get the value of the selected property type
					System.out.println("Property Type: " + propertyType); 
					
					// Show or hide fields based on the selected property type
					switch (propertyType) {
						case "House":
							isHouse();
							break;
						case "Flat":
							isFlat();
							break;
						case "Storage":
							isStorage();
							break;
						case "Parking":
							isParking();
							break;
						case "Land":
							isLand();
							break;
						case "Office":
							isOffice();
							break;
						default:
							cleanFields(); // Reset fields if no valid type is selected
					}
				}
			}
		});
		
		// -- END PROPERTY TYPE -- //
		
		// -- REGION -- //
		
		comboRegion = new JComboBox<>();
		comboRegion.setBounds(222, 192, 310, 30); // Set position and size of the text field
		comboRegion.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the combo box
		comboRegion.setEditable(false); // Make the combo box editable
		add(comboRegion); // Add the text field to the panel
		String defaultRegion = "Seleccione una Región"; // SPANISH for "Select a Region"
		comboRegion.addItem(defaultRegion); // SPANISH for "Select a Region"
		
		// RegionDAO regionDAO = new RegionDAO(); // Create an instance of RegionDAO to fetch regions
		List<String> regions = RegionDAO.getAllRegion(); // Fetch all regions from the database
		
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

					List<TownOption> towns = TownDAO.getAllTownsByRegionID(regionID); // Fetch towns for the selected region
					for (TownOption town : towns) {
						comboTown.addItem(town); // Add each town to the combo box
					}
				} else {
					comboTown.removeAllItems(); // Clear towns if no region is selected
					comboTown.addItem(new TownOption(0 , "Seleccione una Comuna")); // SPANISH for "Select a Town"
				}
			}
		});
		
	
		
		comboTown = new JComboBox<>();
		comboTown.setBounds(222, 233, 310, 30);
		comboTown.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); 
		comboTown.setEditable(false); // Make the combo box not editable
		add(comboTown); 
		
		comboTown.addItem(new TownOption(0 , "Seleccione una Comuna")); // SPANISH for "Select a Town"
		
	// -- END REGION -- //
		
		// -- TOWN -- //
		
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
		lblRegion.setBounds(139, 192, 67, 30);
		add(lblRegion);
		
		lblTown = new JLabel("Comuna:"); // SPANISH for "Town" or "Community" or "Municipality"
		lblTown.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblTown.setBounds(139, 233, 73, 30);
		add(lblTown);
		
		lblAddress = new JLabel("Calle/Avenida/Pasaje:"); // SPANISH for "Address"
		lblAddress.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblAddress.setBounds(35, 274, 186, 30);
		add(lblAddress);
		
		lblAddressNum1 = new JLabel("Nº");
		lblAddressNum1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblAddressNum1.setBounds(442, 274, 37, 30);
		add(lblAddressNum1);
		
		lblAddressNum2 = new JLabel("#2 Nº"); // SPANISH for "Address Number 2 (optional)"
		lblAddressNum2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblAddressNum2.setBounds(411, 315, 54, 30);
		add(lblAddressNum2);
		
		
		lblLocationTitle = new JLabel("Dirección"); // SPANISH for "Address Title"
		lblLocationTitle.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 24));
		lblLocationTitle.setBounds(94, 94, 348, 36);
		add(lblLocationTitle);
		
		// -- END REGION, TOWN, ADDRESS -- //
		
		// -- SIZE -- //
		
		lblSize = new JLabel("Tamaño (m²):"); // SPANISH for "Size (m²)"
		lblSize.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSize.setBounds(680, 75, 125, 30);
		add(lblSize);
		
		// -- END SIZE -- //
		
		// -- ROOM, BATH, FLOOR, PARKING, STORAGE -- //
		
		lblRoomQty = new JLabel("Dormitorios:"); // SPANISH for "Bedrooms"
		lblRoomQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRoomQty.setBounds(680, 99, 125, 31);
		add(lblRoomQty);
		
		lblBathQty = new JLabel("Baños:"); // SPANISH for "Bathrooms"
		lblBathQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblBathQty.setBounds(680, 123, 67, 30);
		add(lblBathQty);
		
		lblFloorQty = new JLabel("Pisos:"); // SPANISH for "Floors"
		lblFloorQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblFloorQty.setBounds(680, 147, 54, 30);
		add(lblFloorQty);
		
		lblParkingQty = new JLabel("Estacionamientos:"); // SPANISH for "Parking Spots"
		lblParkingQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblParkingQty.setBounds(680, 171, 170, 30);
		add(lblParkingQty);
		
		lblHasStorage = new JLabel("Bodega:"); // SPANISH for "Storage"
		lblHasStorage.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasStorage.setBounds(680, 195, 81, 30);
		add(lblHasStorage);
		
		// -- END ROOM, BATH, FLOOR, PARKING, STORAGE -- //
		
		// -- LABELS FOR FEATURES -- //
		
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
	    lblInCondo.setBounds(372, 148, 140, 30);
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
	    
	    // -- END LABELS FOR FEATURES -- //

		// --- END LABELS --- //
		
		// ---------------------- //
		
		// --- SEPARATORS--- //
		
		separator1 = new JSeparator();
		separator1.setBounds(112, 70, 420, 8);
		add(separator1);
		
		separator2 = new JSeparator();
		separator2.setBounds(734, 70, 420, 22);
		add(separator2);
		
		// --- END SEPARATORS --- //
		
		// ---------------------- //
		
		// --- TEXT FIELDS --- //
		
		// -- ADDRESS -- //
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the address text field
		txtAddress.setBounds(222, 274, 220, 30);
		add(txtAddress);
		txtAddress.setColumns(10);
		
		// -- END ADDRESS -- //
		
		// -- ADDRESS NUMBER 1 (OBLIGATORY) -- //
		
		txtNum1 = new JTextField();
		txtNum1.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the address number text field
		txtNum1.setColumns(10);
		txtNum1.setBounds(465, 274, 67, 30);
		add(txtNum1);
		
		// -- END ADDRESS NUMBER 1 -- //
		
		// -- ADDRESS NUMBER 2 (OPTIONAL) -- //
		
		txtNum2 = new JTextField();
		txtNum2.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the address number text field
		txtNum2.setColumns(10);
		txtNum2.setBounds(465, 311, 67, 30);
		txtNum2.setVisible(false); // Initially hidden, it will be shown if needed (e.g., for apartment numbers)
		add(txtNum2);
		
		// -- END ADDRESS NUMBER 2 -- //
		
		// -- SIZE -- //

		txtSize = new JTextField();
		txtSize.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the size text field
		txtSize.setBounds(791, 84, 86, 20);
		add(txtSize);
		txtSize.setColumns(10);
		
		// -- END SIZE -- //
		
		// --- END TEXT FIELDS --- //
		
		// ---------------------- //
			
		// --- SPINNERS --- //
		
		spinnerRoom = new JSpinner();
		spinnerRoom.setBounds(789, 106, 46, 25);
		add(spinnerRoom);
		
		spinnerBath = new JSpinner();
		spinnerBath.setBounds(734, 131, 46, 25);
		add(spinnerBath);
		
		spinnerFloor = new JSpinner();
		spinnerFloor.setBounds(734, 156, 46, 25);
		add(spinnerFloor);
		
		spinnerParking = new JSpinner();
		spinnerParking.setBounds(829, 180, 46, 25);
		add(spinnerParking);
		
		spinnerStorage = new JSpinner();
		spinnerStorage.setBounds(748, 201, 46, 25);
		add(spinnerStorage);
		
		// --- END SPINNERS --- //
		
		// ------------------- //
		
		// --- CHECKBOXES --- //
		
		chckbxInCondo = new JCheckBox("");
		chckbxInCondo.setBounds(502, 147, 30, 38);
		add(chckbxInCondo);
		
		chckbxGarden = new JCheckBox("");
		chckbxGarden.setBounds(775, 226, 30, 23);
		add(chckbxGarden);
		
		chckbxPatio = new JCheckBox("");
		chckbxPatio.setBounds(775, 250, 30, 23);
		add(chckbxPatio);
		
		chckbxPool = new JCheckBox("");
		chckbxPool.setBounds(775, 274, 30, 23);
		add(chckbxPool);
		
		chckbxBalcony = new JCheckBox("");
		chckbxBalcony.setBounds(775, 299, 30, 23);
		add(chckbxBalcony);
		
		chckbxBBQ = new JCheckBox("");
		chckbxBBQ.setBounds(775, 323, 30, 23);
		add(chckbxBBQ);
		
		chckbxTerrace = new JCheckBox("");
		chckbxTerrace.setBounds(775, 347, 30, 23);
		add(chckbxTerrace);
		
		chckbxLaundry = new JCheckBox("");
		chckbxLaundry.setBounds(775, 371, 30, 23);
		add(chckbxLaundry);
		
		chckbxBldngLift = new JCheckBox("");
		chckbxBldngLift.setBounds(875, 418, 30, 23);
		add(chckbxBldngLift);
		
		chckbxBldngPool = new JCheckBox("");
		chckbxBldngPool.setBounds(875, 442, 30, 23);
		add(chckbxBldngPool);
		
		chckbxBldngBBQ = new JCheckBox("");
		chckbxBldngBBQ.setBounds(875, 466, 30, 23);
		add(chckbxBldngBBQ);
		
		chckbxBldngGym = new JCheckBox("");
		chckbxBldngGym.setBounds(875, 490, 30, 23);
		add(chckbxBldngGym);
		
		chckbxBldngLaundry = new JCheckBox("");
		chckbxBldngLaundry.setBounds(875, 514, 30, 23);
		add(chckbxBldngLaundry);
		
		// --- END CHECKBOXES --- //
		
//		JRadioButton rdbtnGardenYes = new JRadioButton("Sí");
//		rdbtnGardenYes.setBounds(1030, 234, 37, 23);
//		add(rdbtnGardenYes);
//		
//		JRadioButton rdbtnGardenNo = new JRadioButton("No");
//		rdbtnGardenNo.setBounds(1080, 234, 44, 23);
//		add(rdbtnGardenNo);
//		
//		JRadioButton rdbtnPatioYes = new JRadioButton("Sí");
//		rdbtnPatioYes.setBounds(1030, 257, 37, 23);
//		add(rdbtnPatioYes);
//		
//		JRadioButton rdbtnPatioNo = new JRadioButton("No");
//		rdbtnPatioNo.setBounds(1080, 257, 44, 23);
//		add(rdbtnPatioNo);
//		
//		JRadioButton rdbtnPoolYes = new JRadioButton("Sí");
//		rdbtnPoolYes.setBounds(1030, 281, 37, 23);
//		add(rdbtnPoolYes);
//		
//		JRadioButton rdbtnPoolNo = new JRadioButton("No");
//		rdbtnPoolNo.setBounds(1080, 281, 44, 23);
//		add(rdbtnPoolNo);
//		
//		JRadioButton rdbtnBalconyYes = new JRadioButton("Sí");
//		rdbtnBalconyYes.setBounds(1030, 298, 37, 23);
//		add(rdbtnBalconyYes);
//		
//		JRadioButton rdbtnBalconyNo = new JRadioButton("No");
//		rdbtnBalconyNo.setBounds(1080, 298, 44, 23);
//		add(rdbtnBalconyNo);
//		
//		JRadioButton rdbtnBBQYes = new JRadioButton("Sí");
//		rdbtnBBQYes.setBounds(1030, 322, 37, 23);
//		add(rdbtnBBQYes);
//		
//		JRadioButton rdbtnBBQNo = new JRadioButton("No");
//		rdbtnBBQNo.setBounds(1080, 322, 44, 23);
//		add(rdbtnBBQNo);
//		
//		JRadioButton rdbtnTerraceYes = new JRadioButton("Sí");
//		rdbtnTerraceYes.setBounds(1030, 346, 37, 23);
//		add(rdbtnTerraceYes);
//		
//		JRadioButton rdbtnTerraceNo = new JRadioButton("No");
//		rdbtnTerraceNo.setBounds(1080, 346, 44, 23);
//		add(rdbtnTerraceNo);
//		
//		JRadioButton rdbtnLaundryYes = new JRadioButton("Sí");
//		rdbtnLaundryYes.setBounds(1030, 370, 37, 23);
//		add(rdbtnLaundryYes);
//		
//		JRadioButton rdbtnLaundryNo = new JRadioButton("No");
//		rdbtnLaundryNo.setBounds(1080, 370, 44, 23);
//		add(rdbtnLaundryNo);
//		
//		JRadioButton rdbtnBldngLiftYes = new JRadioButton("Sí");
//		rdbtnBldngLiftYes.setBounds(1030, 418, 37, 23);
//		add(rdbtnBldngLiftYes);
//		
//		JRadioButton rdbtnBldngLiftNo = new JRadioButton("No");
//		rdbtnBldngLiftNo.setBounds(1080, 418, 44, 23);
//		add(rdbtnBldngLiftNo);
//		
//		JRadioButton rdbtnBldngPoolYes = new JRadioButton("Sí");
//		rdbtnBldngPoolYes.setBounds(1030, 442, 37, 23);
//		add(rdbtnBldngPoolYes);
//		
//		JRadioButton rdbtnBldngPoolNo = new JRadioButton("No");
//		rdbtnBldngPoolNo.setBounds(1080, 442, 44, 23);
//		add(rdbtnBldngPoolNo);
//		
//		JRadioButton rdbtnBldngBBQYes = new JRadioButton("Sí");
//		rdbtnBldngBBQYes.setBounds(1030, 466, 37, 23);
//		add(rdbtnBldngBBQYes);
//		
//		JRadioButton rdbtnBldngBBQNo = new JRadioButton("No");
//		rdbtnBldngBBQNo.setBounds(1080, 466, 44, 23);
//		add(rdbtnBldngBBQNo);
//		
//		JRadioButton rdbtnBldngGymYes = new JRadioButton("Sí");
//		rdbtnBldngGymYes.setBounds(1030, 490, 37, 23);
//		add(rdbtnBldngGymYes);
//		
//		JRadioButton rdbtnBldngGymNo = new JRadioButton("No");
//		rdbtnBldngGymNo.setBounds(1080, 490, 44, 23);
//		add(rdbtnBldngGymNo);
//		
//		JRadioButton rdbtnBldngLaundryYes = new JRadioButton("Sí");
//		rdbtnBldngLaundryYes.setBounds(1030, 514, 37, 23);
//		add(rdbtnBldngLaundryYes);
//		
//		JRadioButton rdbtnBldngLaundryNo = new JRadioButton("No");
//		rdbtnBldngLaundryNo.setBounds(1080, 514, 44, 23);
//		add(rdbtnBldngLaundryNo);
		
		// ---------------------- //
		
		// --- ACTION LISTENERS --- //
		
		// -- BACK BUTTON -- //
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
		
		// -- END BACK BUTTON -- //
		
		// -- SAVE BUTTON -- //	
		
		btnSave.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	LandlordOption selectedLandlord = (LandlordOption) comboLandlord.getSelectedItem();
		    	TownOption selectedTown = (TownOption) comboTown.getSelectedItem();
		    	int landlordId = selectedLandlord.getId();
		    	String nameAndRut = selectedLandlord.toString();
		        int townId = selectedTown.getId();

		        if (selectedLandlord == null || selectedLandlord.equals("Seleccione un Propietario")) {
		        	Popup.show("Debe seleccionar un propietario válido.", "error"); // SPANISH for "You must select a valid landlord."
		            return;
		        }

		        if (selectedTown == null || selectedTown.equals("Seleccione una Comuna")) {
		        	Popup.show("Debe seleccionar una comuna válida.", "error"); // SPANISH for "You must select a valid town."
		            return;
		        }

		        try {
		            //int landlordId = LandlordDAO.getLandlordIdByNameWithRUT(selectedLandlord);
		            //int townId = TownDAO.getTownIdByName(selectedTown);
		            
		            // Extrae el id del tipo seleccionado
		            
		            PropertyTypeOption selectedType = (PropertyTypeOption) comboPropertyType.getSelectedItem();
					if (selectedType == null || selectedType.getValue().isEmpty()) {
						Popup.show("Debe seleccionar un tipo de propiedad válido.", "error"); // SPANISH for "You must select a valid property type."
						return;
					}
					String propertyType = selectedType.getValue();
					if (propertyType.equals("House")) {
		            
						HousePropertyData data = new HousePropertyData();
						data.setLandlordId(landlordId);
						data.setTownId(townId);
						data.setStreetName(txtAddress.getText().trim());
						data.setNum1(txtNum1.getText().trim());
						data.setNum2(txtNum2.isVisible() ? txtNum2.getText().trim() : null);
						data.setSize(Integer.parseInt(txtSize.getText().trim()));

						data.setRoomQty((int) spinnerRoom.getValue());
						data.setBathQty((int) spinnerBath.getValue());
						data.setFloorQty((int) spinnerFloor.getValue());
						data.setHasParking((int) spinnerParking.getValue() > 0);
						data.setHasStorage((int) spinnerStorage.getValue() > 0);

						data.setHasGarden(chckbxGarden.isSelected());
						data.setHasPatio(chckbxPatio.isSelected());
						data.setHasPool(chckbxPool.isSelected());
						data.setHasBBQ(chckbxBBQ.isSelected());
						data.setHasBalcony(chckbxBalcony.isSelected());
						data.setHasTerrace(chckbxTerrace.isSelected());
						data.setHasLaundry(chckbxLaundry.isSelected());
						data.setInCondo(chckbxInCondo.isSelected());

		            // Puedes agregar condicionales si quieres setear condoId o condoPlatformId
						data.setCondoId(null);
						data.setCondoPlatformId(null);

						data.setPropertyTypeId(1); // 1 = house, puedes mapear según necesites

						boolean success = propertiesDAO.insertCompleteHouse(data);
						if (success) {
							Popup.showSuccess("Propiedad insertada correctamente.");
							// Podrías limpiar los campos si quieres
		            } else {
		                Popup.show("Hubo un error al insertar la propiedad.", "error");
		            }
					}
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            Popup.show("Hubo un error al insertar la propiedad. ERROR: "+ ex.getMessage(), "error");
		        }
		    }
		});

		
		
		// --- END ACTION LISTENERS --- //
		
		
	} // END OF  PropertiesPanel constructor
	
	// ---------------------- //
	
	// --- AUXILIARY METHODS --- //
	
	public void cleanFields() {
		comboLandlord.setSelectedIndex(0); // Reset landlord selection
		comboPropertyType.setSelectedIndex(0); // Reset property type selection
		comboRegion.setSelectedIndex(0); // Reset region selection
		comboTown.removeAllItems(); // Clear towns
		//comboTown.addItem("Seleccione una Comuna"); // SPANISH for "Select a Town"
		txtAddress.setText(""); // Clear address text field
		txtNum1.setText(""); // Clear address number 1 text field
		txtNum2.setText(""); // Clear address number 2 text field
		
		// Reset other fields if needed (e.g., room quantity, bath quantity, etc.)
	}
	
	public void isHouse() {
		txtNum2.setVisible(false); // Hide address number 2 field for houses
		lblAddressNum2.setVisible(false); // Hide label for address number 2
		lblLocationTitle.setText("Dirección de la Casa"); // SPANISH for "House Address"
		lblRoomQty.setVisible(true); // Show room quantity label
		lblBathQty.setVisible(true); // Show bath quantity label
		lblFloorQty.setVisible(true); // Show floor quantity label
		lblParkingQty.setVisible(true); // Show parking quantity label
		lblHasStorage.setVisible(true); // Show storage label
		lblHasGarden.setVisible(true); // Show garden label
		lblHasPatio.setVisible(true); // Show patio label
		lblHasPool.setVisible(true); // Show pool label
		lblHasBalcony.setVisible(true); // Show balcony label
		lblHasBBQ.setVisible(true); // Show BBQ label
		lblHasTerrace.setVisible(true); // Show terrace label
		lblHasLaundryRoom.setVisible(true); // Show laundry room label
		
		lblInCondo.setVisible(true); // Show "In Condominium" label for houses
		
		lblBuildingHasLift.setVisible(false); // Hide building lift label for houses
		lblBuildingHasPool.setVisible(false); // Hide building pool label for houses
		lblBuildingHasBBQ.setVisible(false); // Hide building BBQ label for houses
		lblBuildingHasGym.setVisible(false); // Hide building gym label for houses
		lblBuildingHasLaundryRoom.setVisible(false); // Hide building laundry room label for houses
		
		spinnerRoom.setVisible(true); // Show spinner for room quantity for houses
		spinnerBath.setVisible(true); // Show spinner for bath quantity for houses
		spinnerFloor.setVisible(true); // Show spinner for floor quantity for houses
		spinnerParking.setVisible(true); // Show spinner for parking quantity for houses
		spinnerStorage.setVisible(true); // Show spinner for storage quantity for houses
		
		chckbxGarden.setVisible(true); // Show checkbox for garden for houses
		chckbxPatio.setVisible(true); // Show checkbox for patio for houses
		chckbxPool.setVisible(true); // Show checkbox for pool for houses
		chckbxBalcony.setVisible(true); // Show checkbox for balcony for houses
		chckbxBBQ.setVisible(true); // Show checkbox for BBQ for houses
		chckbxTerrace.setVisible(true); // Show checkbox for terrace for houses
		chckbxLaundry.setVisible(true); // Show checkbox for laundry room for houses
		
		chckbxBldngLift.setVisible(false); // Hide checkbox for building lift for houses
		chckbxBldngPool.setVisible(false); // Hide checkbox for building pool for houses
		chckbxBldngBBQ.setVisible(false); // Hide checkbox for building BBQ for houses
		chckbxBldngGym.setVisible(false); // Hide checkbox for building gym for houses
		chckbxBldngLaundry.setVisible(false); // Hide checkbox for building laundry room for houses
	}
	
	public void isFlat() {
		txtNum2.setVisible(true); // Show address number 2 field for flats
		lblAddressNum2.setVisible(true); // Show label for address number 2
		lblLocationTitle.setText("Dirección del Departamento"); // SPANISH for "Apartment Address"
		lblRoomQty.setVisible(true); // Show room quantity label
		lblBathQty.setVisible(true); // Show bath quantity label
		lblFloorQty.setVisible(false); // Hide floor quantity label for flats
		lblParkingQty.setVisible(true); // Hide parking quantity label for flats
		lblHasStorage.setVisible(true); // Hide storage label for flats
		lblHasGarden.setVisible(false); // Hide garden label for flats
		lblHasPatio.setVisible(false); // Hide patio label for flats
		lblHasPool.setVisible(false); // Hide pool label for flats
		lblHasBalcony.setVisible(false); // Hide balcony label for flats
		lblHasBBQ.setVisible(false); // Hide BBQ label for flats
		lblHasTerrace.setVisible(false); // Hide terrace label for flats
		lblHasLaundryRoom.setVisible(false); // Hide laundry room label for flats
		
		lblInCondo.setVisible(true); // Show "In Condominium" label for flats
		
		lblBuildingHasLift.setVisible(true); // Show building lift label for flats
		lblBuildingHasPool.setVisible(true); // Show building pool label for flats
		lblBuildingHasBBQ.setVisible(true); // Show building BBQ label for flats
		lblBuildingHasGym.setVisible(true); // Show building gym label for flats
		lblBuildingHasLaundryRoom.setVisible(true); // Show building laundry room label for flat
				
		spinnerRoom.setVisible(true); // Show spinner for room quantity for flats
		spinnerBath.setVisible(true); // Show spinner for bath quantity for flats
		spinnerFloor.setVisible(false); // Hide spinner for floor quantity for flats	
		spinnerParking.setVisible(true); // Show spinner for parking quantity for flats
		spinnerStorage.setVisible(true); // Show spinner for storage quantity for flats
		
		
		chckbxGarden.setVisible(false); // Hide checkbox for garden for flats
		chckbxPatio.setVisible(false); // Hide checkbox for patio for flats
		chckbxPool.setVisible(false); // Hide checkbox for pool for flats
		chckbxBalcony.setVisible(false); // Hide checkbox for balcony for flats
		chckbxBBQ.setVisible(false); // Hide checkbox for BBQ for flats
		chckbxTerrace.setVisible(false); // Hide checkbox for terrace for flats
		chckbxLaundry.setVisible(false); // Hide checkbox for laundry room for flats
		
		chckbxBldngLift.setVisible(true); // Show checkbox for building lift for flats
		chckbxBldngPool.setVisible(true); // Show checkbox for building pool for flats
		chckbxBldngBBQ.setVisible(true); // Show checkbox for building BBQ for flats
		chckbxBldngGym.setVisible(true); // Show checkbox for building gym for flats
		chckbxBldngLaundry.setVisible(true); // Show checkbox for building laundry room for flats
		
		
	}
	
	public void isStorage() {
		txtNum2.setVisible(false); // Hide address number 2 field for storage
		lblAddressNum2.setVisible(false); // Hide label for address number 2
		lblLocationTitle.setText("Dirección de la Bodega"); // SPANISH for "Storage Address"
		lblRoomQty.setVisible(false); // Hide room quantity label for storage
		lblBathQty.setVisible(false); // Hide bath quantity label for storage
		lblFloorQty.setVisible(false); // Hide floor quantity label for storage
		lblParkingQty.setVisible(false); // Hide parking quantity label for storage
		lblHasStorage.setVisible(false); // Hide storage label for storage
		lblHasGarden.setVisible(false); // Hide garden label for storage
		lblHasPatio.setVisible(false); // Hide patio label for storage
		lblHasPool.setVisible(false); // Hide pool label for storage
		lblHasBalcony.setVisible(false); // Hide balcony label for storage
		lblHasBBQ.setVisible(false); // Hide BBQ label for storage
		lblHasTerrace.setVisible(false); // Hide terrace label for storage
		lblHasLaundryRoom.setVisible(false); // Hide laundry room label for storage
		
		lblInCondo.setVisible(false); // Hide "In Condominium" label for storage
		
		lblBuildingHasLift.setVisible(false); // Hide building lift label for storage
		lblBuildingHasPool.setVisible(false); // Hide building pool label for storage
		lblBuildingHasBBQ.setVisible(false); // Hide building BBQ label for storage
		lblBuildingHasGym.setVisible(false); // Hide building gym label for storage
		lblBuildingHasLaundryRoom.setVisible(false); // Hide building laundry room label for storage
		
		spinnerRoom.setVisible(false); // Hide spinner for room quantity for storage
		spinnerBath.setVisible(false); // Hide spinner for bath quantity for storage
		spinnerFloor.setVisible(false); // Hide spinner for floor quantity for storage
		spinnerParking.setVisible(false); // Hide spinner for parking quantity for storage
        spinnerStorage.setVisible(false); // Hide spinner for storage quantity for storage
        
		chckbxGarden.setVisible(false); // Hide checkbox for garden for storage
		chckbxPatio.setVisible(false); // Hide checkbox for patio for storage
		chckbxPool.setVisible(false); // Hide checkbox for pool for storage
		chckbxBalcony.setVisible(false); // Hide checkbox for balcony for storage
		chckbxBBQ.setVisible(false); // Hide checkbox for BBQ for storage
		chckbxTerrace.setVisible(false); // Hide checkbox for terrace for storage
		
		chckbxLaundry.setVisible(false); // Hide checkbox for laundry room for storage
		chckbxBldngLift.setVisible(false); // Hide checkbox for building lift for storage
		chckbxBldngPool.setVisible(false); // Hide checkbox for building pool for storage
		chckbxBldngBBQ.setVisible(false); // Hide checkbox for building BBQ for storage
		chckbxBldngGym.setVisible(false); // Hide checkbox for building gym for storage
		chckbxBldngLaundry.setVisible(false); // Hide checkbox for building laundry room for storage
		
	}
	
	public void isParking() {
		txtNum2.setVisible(false); // Hide address number 2 field for parking
		lblAddressNum2.setVisible(false); // Hide label for address number 2
		lblLocationTitle.setText("Dirección del Estacionamiento"); // SPANISH for "Parking Address"
		lblRoomQty.setVisible(false); // Hide room quantity label for parking
		lblBathQty.setVisible(false); // Hide bath quantity label for parking
		lblFloorQty.setVisible(false); // Hide floor quantity label for parking
		lblParkingQty.setVisible(false); // Show parking quantity label for parking
		lblHasStorage.setVisible(false); // Hide storage label for parking
		lblHasGarden.setVisible(false); // Hide garden label for parking
		lblHasPatio.setVisible(false); // Hide patio label for parking
		lblHasPool.setVisible(false); // Hide pool label for parking
		lblHasBalcony.setVisible(false); // Hide balcony label for parking
		lblHasBBQ.setVisible(false); // Hide BBQ label for parking
		lblHasTerrace.setVisible(false); // Hide terrace label for parking
		lblHasLaundryRoom.setVisible(false); // Hide laundry room label for parking
		
		lblInCondo.setVisible(false); // Hide "In Condominium" label for parking
		
		lblBuildingHasLift.setVisible(false); // Hide building lift label for parking
		lblBuildingHasPool.setVisible(false); // Hide building pool label for parking
		lblBuildingHasBBQ.setVisible(false); // Hide building BBQ label for parking
		lblBuildingHasGym.setVisible(false); // Hide building gym label for parking
		lblBuildingHasLaundryRoom.setVisible(false); // Hide building laundry room label for parking
		
		spinnerRoom.setVisible(false); // Hide spinner for room quantity for parking
		spinnerBath.setVisible(false); // Hide spinner for bath quantity for parking
		spinnerFloor.setVisible(false); // Hide spinner for floor quantity for parking
		spinnerParking.setVisible(false); // Hide spinner for parking quantity for parking
		spinnerStorage.setVisible(false); // Hide spinner for storage quantity for parking
		
		chckbxGarden.setVisible(false); // Hide checkbox for garden for parking
		chckbxPatio.setVisible(false); // Hide checkbox for patio for parking
		chckbxPool.setVisible(false); // Hide checkbox for pool for parking
		chckbxBalcony.setVisible(false); // Hide checkbox for balcony for parking
		chckbxBBQ.setVisible(false); // Hide checkbox for BBQ for parking
		chckbxTerrace.setVisible(false); // Hide checkbox for terrace for parking
		chckbxLaundry.setVisible(false); // Hide checkbox for laundry room for parking
		
		chckbxBldngLift.setVisible(false); // Hide checkbox for building lift for parking
		chckbxBldngPool.setVisible(false); // Hide checkbox for building pool for parking
		chckbxBldngBBQ.setVisible(false); // Hide checkbox for building BBQ for parking
		chckbxBldngGym.setVisible(false); // Hide checkbox for building gym for parking
        chckbxBldngLaundry.setVisible(false); // Hide checkbox for building laundry room for parking
		
	}
	
	public void isLand() {
		txtNum2.setVisible(false); // Hide address number 2 field for land
		lblAddressNum2.setVisible(false); // Hide label for address number 2
		lblLocationTitle.setText("Dirección de la Parcela/Terreno"); // SPANISH for "Land Address"
		lblRoomQty.setVisible(true); // Show room quantity label for land, only for lands with houses
		lblBathQty.setVisible(true); // Show bath quantity label for land, only for lands with houses
		lblFloorQty.setVisible(true); // Show floor quantity label for land, only for lands with houses
		lblParkingQty.setVisible(true); // Show parking quantity label for land, only for lands with houses
		lblHasStorage.setVisible(true); // Show storage label for land, only for lands with houses
		lblHasGarden.setVisible(true); // Show garden label for land, only for lands with houses
		lblHasPatio.setVisible(true); // Show patio label for land, only for lands with houses
		lblHasPool.setVisible(true); // Show pool label for land, only for lands with houses
		lblHasBalcony.setVisible(true); // Show balcony label for land, only for lands with houses
		lblHasBBQ.setVisible(true); // Show BBQ label for land, only for lands with houses
		lblHasTerrace.setVisible(true); // Show terrace label for land, only for lands with houses
		lblHasLaundryRoom.setVisible(true); // Show laundry room label for land, only for lands with houses
		
		lblInCondo.setVisible(true); // Show "In Condominium" label for land, only for lands with houses
		
		lblBuildingHasLift.setVisible(false); // Hide building lift label for land
		lblBuildingHasPool.setVisible(false); // Hide building pool label for land
		lblBuildingHasBBQ.setVisible(false); // Hide building BBQ label for land
		lblBuildingHasGym.setVisible(false); // Hide building gym label for land
		lblBuildingHasLaundryRoom.setVisible(false); // Hide building laundry room label for land
		
		spinnerRoom.setVisible(true); // Show spinner for room quantity for land, only for lands with houses
		spinnerBath.setVisible(true); // Show spinner for bath quantity for land, only for lands with houses
		spinnerFloor.setVisible(true); // Show spinner for floor quantity for land, only for lands with houses
		spinnerParking.setVisible(true); // Show spinner for parking quantity for land, only for lands with houses
		spinnerStorage.setVisible(true); // Show spinner for storage quantity for land, only for lands with houses
		
		chckbxGarden.setVisible(true); // Show checkbox for garden for land, only for lands with houses
		chckbxPatio.setVisible(true); // Show checkbox for patio for land, only for lands with houses
		chckbxPool.setVisible(true); // Show checkbox for pool for land, only for lands with houses
		chckbxBalcony.setVisible(true); // Show checkbox for balcony for land, only for lands with houses
		chckbxBBQ.setVisible(true); // Show checkbox for BBQ for land, only for lands with houses
		chckbxTerrace.setVisible(true); // Show checkbox for terrace for land, only for lands with houses
		chckbxLaundry.setVisible(true); // Show checkbox for laundry room for land, only for lands with houses
		
		chckbxBldngLift.setVisible(false); // Hide checkbox for building lift for land
		chckbxBldngPool.setVisible(false); // Hide checkbox for building pool for land
		chckbxBldngBBQ.setVisible(false); // Hide checkbox for building BBQ for land
		chckbxBldngGym.setVisible(false); // Hide checkbox for building gym for land
		chckbxBldngLaundry.setVisible(false); // Hide checkbox for building laundry room for land

	}
	
	public void isOffice() {
		txtNum2.setVisible(true); // Show address number 2 field for office
		lblAddressNum2.setVisible(true); // Show label for address number 2
		lblLocationTitle.setText("Dirección de la Oficina"); // SPANISH for "Office Address"
		lblRoomQty.setVisible(false); // Hide room quantity label for office
		lblBathQty.setVisible(true); // Show bath quantity label for office
		lblFloorQty.setVisible(false); // Hide floor quantity label for office
		lblParkingQty.setVisible(true); // Show parking quantity label for office
		lblHasStorage.setVisible(true); // Show storage label for office
		lblHasGarden.setVisible(false); // Hide garden label for office
		lblHasPatio.setVisible(false); // Hide patio label for office
		lblHasPool.setVisible(false); // Hide pool label for office
		lblHasBalcony.setVisible(false); // Hide balcony label for office
		lblHasBBQ.setVisible(false); // Hide BBQ label for office
		lblHasTerrace.setVisible(false); // Hide terrace label for office
		lblHasLaundryRoom.setVisible(false); // Hide laundry room label for office
		lblInCondo.setVisible(false); // Hide "In Condominium" label for office
		lblBuildingHasLift.setVisible(true); // Show building lift label for office
		lblBuildingHasPool.setVisible(false); // Hide building pool label for office
		lblBuildingHasBBQ.setVisible(false); // Hide building BBQ label for office
		lblBuildingHasGym.setVisible(false); // Hide building gym label for office
		lblBuildingHasLaundryRoom.setVisible(false); // Hide building laundry room label for office
		
	}
}
