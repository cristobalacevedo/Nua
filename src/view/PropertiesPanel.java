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
		
		comboLandlord.addItem("Seleccione un Propietario"); // SPANISH for "Select a Landlord"
		
		LandlordDAO landlordDAO = new LandlordDAO(null); // Create an instance of LandlordDAO to fetch landlords
		List<String> landlords = landlordDAO.getAllNames(); // Fetch all landlords from the database
		
		for (String landlord : landlords) {
			comboLandlord.addItem(landlord); // Add each landlord to the combo box
		}
		
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
		comboRegion.setBounds(222, 167, 310, 30); // Set position and size of the text field
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
		comboTown.setBounds(222, 208, 310, 30);
		comboTown.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); 
		comboTown.setEditable(false); // Make the combo box not editable
		add(comboTown); 
		
		comboTown.addItem("Seleccione una Comuna"); // SPANISH for "Select a Town"
		
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
		
		lblAddressNum2 = new JLabel("Nº 2 (opcional)"); // SPANISH for "Address Number 2 (optional)"
		lblAddressNum2.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblAddressNum2.setBounds(341, 290, 125, 30);
		add(lblAddressNum2);
		
		
		lblLocationTitle = new JLabel("Dirección"); // SPANISH for "Address Title"
		lblLocationTitle.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 24));
		lblLocationTitle.setBounds(118, 118, 170, 36);
		add(lblLocationTitle);
		
		// -- END REGION, TOWN, ADDRESS -- //
		
		lblSize = new JLabel("Tamaño (m²):"); // SPANISH for "Size (m²)"
		lblSize.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSize.setBounds(680, 75, 125, 30);
		add(lblSize);
		
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
		txtNum2.setVisible(false); // Initially hidden, it will be shown if needed (e.g., for apartment numbers)
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
		
	
		
	}
	
	// ---------------------- //
	
	// --- AUXILIARY METHODS --- //
	
	public void cleanFields() {
		comboLandlord.setSelectedIndex(0); // Reset landlord selection
		comboPropertyType.setSelectedIndex(0); // Reset property type selection
		comboRegion.setSelectedIndex(0); // Reset region selection
		comboTown.removeAllItems(); // Clear towns
		comboTown.addItem("Seleccione una Comuna"); // SPANISH for "Select a Town"
		txtAddress.setText(""); // Clear address text field
		txtNum1.setText(""); // Clear address number 1 text field
		txtNum2.setText(""); // Clear address number 2 text field
		
		// Reset other fields if needed (e.g., room quantity, bath quantity, etc.)
	}
	
	public void isHouse() {
		// txtNum2.setVisible(false); // Hide address number 2 field for houses
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
		
	}
	
	public void isFlat() {
		txtNum2.setVisible(true); // Show address number 2 field for flats
		lblAddressNum2.setVisible(true); // Show label for address number 2
		lblLocationTitle.setText("Dirección del Departamento"); // SPANISH for "Apartment Address"
		lblRoomQty.setVisible(true); // Show room quantity label
		lblBathQty.setVisible(true); // Show bath quantity label
		lblFloorQty.setVisible(false); // Hide floor quantity label for flats
		lblParkingQty.setVisible(false); // Hide parking quantity label for flats
		lblHasStorage.setVisible(false); // Hide storage label for flats
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
		lblBuildingHasLaundryRoom.setVisible(true); // Show building laundry room label for flats
		
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
