package view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.CondoDAO;
import dao.LandlordDAO;
import dao.PropertyDAO;
import dao.RegionDAO;
import dao.TownDAO;
import db.DBConnection;
import utils.CondoOption;
import utils.LandlordOption;
import utils.PropertyTypeOption;
import utils.TownOption;

import model.House;
import model.Parking;
import model.ParkingForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import controller.PropertyController;

import java.awt.Component;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;

// This class represents the Properties panel in the application.
public class PropertyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JButton btnSave; // Button to save property information
	private JButton btnNewCondo;
	private JComboBox<LandlordOption> comboLandlord; // Text field for landlord information
	private JComboBox<PropertyTypeOption> comboPropertyType;
	private JComboBox<String> comboRegion;
	private JComboBox<TownOption> comboTown; // Text field for town information
	private JComboBox<CondoOption> comboCondo;
	private JLabel lblLandlord;
	private JLabel lblPropertyType;
	private JLabel lblRegion;
	private JLabel lblTown;
	private JLabel lblAddress;
	private JLabel lblAddressNum1;
	private JLabel lblAddressNum2;
	private JLabel lblLocationTitle;
	private JLabel lblRol;
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
	private JLabel lblPanelDePropiedades;
	private JLabel lblFloorFlat;
	private JTextField txtRol;
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
	private JCheckBox chckbxInCondo;
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
	private PropertyDAO propertiesDAO = new PropertyDAO();
	
	private JSeparator separator;
	//private JPanel panelParking;
	private List<JPanel> parkingForms = new ArrayList<>();
	private JPanel panelEstacionamientos;
	private JTextField txtBldngFloor;
	public JTextField txtPrkngRol;
	public JTextField txtPrkngNum;
	
	
	public PropertyPanel(Container contentPane, Menu menu) {
		setBackground(new Color(187, 187, 187));
		setForeground(Color.BLACK);
		setName("PropertiesPanel"); // Set the name of the panel
		setLayout(null);
		setBounds(0, 0, 1250, 660);
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
		comboPropertyType.setBounds(737, 29, 180, 30);
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
					System.out.println("Selected Region ID: " + regionID + " - Name: " + selectedRegion); // Debugging output
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
		
		comboTown.addItem(new TownOption(-1 , "Seleccione una Comuna")); // SPANISH for "Select a Town"
		
		// -- END SAVE BUTTON -- //
		
		// -- NEW CONDO BUTTON -- //
		
		btnNewCondo = new JButton("Agregar");
		btnNewCondo.setBackground(new Color(100, 149, 237));
		btnNewCondo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 14));
		btnNewCondo.setBounds(447, 151, 83, 30);
		btnNewCondo.setVisible(false); // Initially hidden, it will be shown if needed (e.g., for apartments)
		add(btnNewCondo);
		
		// -- END COMBO CONDO -- //
		
		// -- NEW CONDO BUTTON -- //
		
		btnNewCondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Show the NewCondoPanel when the button is clicked
				showMiniFrame.show(new CondoFrame());
			}
		});
		
		// -- END REGION -- //
		
		// -- TOWN -- //
		
		// -- END TOWN -- //
		
		// -- CONDO -- //
		
		comboCondo = new JComboBox<CondoOption>();
		String defaultCondo = "Seleccione un Condominio"; // SPANISH for "Select a Condominium"
		comboCondo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		comboCondo.setEditable(false);
		comboCondo.setVisible(false);
		comboCondo.setBounds(235, 151, 216, 30);
		add(comboCondo);
		comboCondo.addItem(new CondoOption(0, defaultCondo)); // SPANISH for "Select a Condominium"
		List<CondoOption> condos = CondoDAO.getAllCondoNames(); // Fetch all condos from the database
		System.out.println("Fetching condos..."); // For debugging purposes
		for (CondoOption condo : condos) {
			comboCondo.addItem(condo); // Add each condo to the combo box
			System.out.println("Condo: " + condo); // For debugging purposes
		}
		
		comboCondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CondoOption selectedCondo = (CondoOption) comboCondo.getSelectedItem(); // Get the selected condo
				if (selectedCondo != null && selectedCondo.getId() != 0) {
					String name = selectedCondo.getName(); // Get the name of the selected condo
					int condoID = selectedCondo.getId();  // Get the ID of the selected condo
	                System.out.println("Selected Condo ID: " + condoID + " - Name: " + name); // Debugging output);

				//	List<TownOption> towns = TownDAO.getAllTownsByRegionID(regionID); // Fetch towns for the selected region
				//	for (TownOption town : towns) {
				//		comboTown.addItem(town); // Add each town to the combo box
					//}
				} else {
					System.out.println("No condo selected or default option selected.");
				}
			}
		});
		
		
		// --- END COMBO BOXES --- //
		
		// --------------------- //
		
		// --- BUTTONS --- //
		
		// -- BACK BUTTON -- // 
		
		btnBack.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		btnBack.setBounds(50, 600, 131, 50);
		add(btnBack);
		
		// -- END BACK BUTTON -- //
		
		// -- SAVE BUTTON -- //
		
		btnSave = new JButton("Guardar"); // SPANISH for "Save"
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(0, 153, 0));
		btnSave.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		btnSave.setBounds(559, 582, 125, 58);
		add(btnSave);
		
		// -- END NEW CONDO BUTTON -- //
		
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
		lblPropertyType.setBounds(680, 33, 46, 22);
		add(lblPropertyType);
		
		// -- END PROPERTY TYPE -- //
		
		// -- REGION, TOWN, ADDRESS -- //
		
		lblRegion = new JLabel("Región:"); // SPANISH for "Region"
		lblRegion.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblRegion.setBounds(149, 192, 67, 30);
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
		lblAddressNum2.setBounds(412, 311, 54, 30);
		add(lblAddressNum2);
		
		
		lblLocationTitle = new JLabel("Dirección"); // SPANISH for "Address Title"
		lblLocationTitle.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 24));
		lblLocationTitle.setBounds(94, 94, 348, 36);
		add(lblLocationTitle);
		
		// -- END REGION, TOWN, ADDRESS -- //
		
		// -- SIZE -- //
		
		lblSize = new JLabel("Superficie (m²):"); // SPANISH for "Size (m²)"
		lblSize.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSize.setBounds(680, 75, 125, 30);
		add(lblSize);
		
		// -- END SIZE -- //
		
		// -- ROOM, BATH, FLOOR, PARKING, STORAGE -- //
		
		lblRoomQty = new JLabel("Dormitorios:"); // SPANISH for "Bedrooms"
		lblRoomQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRoomQty.setBounds(680, 99, 110, 31);
		add(lblRoomQty);
		
		lblBathQty = new JLabel("Baños:"); // SPANISH for "Bathrooms"
		lblBathQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblBathQty.setBounds(680, 123, 67, 30);
		add(lblBathQty);
		
		lblFloorQty = new JLabel("Pisos:"); // SPANISH for "Floors"
		lblFloorQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblFloorQty.setBounds(680, 149, 54, 30);
		add(lblFloorQty);
		
		lblParkingQty = new JLabel("Estacionamientos:"); // SPANISH for "Parking Spots"
		lblParkingQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblParkingQty.setBounds(680, 171, 153, 30);
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
	    lblInCondo.setBounds(79, 150, 140, 30);
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
	    
	    lblFloorFlat = new JLabel("Piso:");
        lblFloorFlat.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
        lblFloorFlat.setBounds(930, 80, 46, 20);
        add(lblFloorFlat);
	    
	    // -- END LABELS FOR FEATURES -- //

		// --- END LABELS --- //
		
		// ---------------------- //
		
		// --- SEPARATORS--- //
		
		separator1 = new JSeparator();
		separator1.setBounds(112, 70, 462, 8);
		add(separator1);
		
		separator2 = new JSeparator();
		separator2.setBounds(680, 70, 474, 22);
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
		txtSize.setBounds(803, 75, 86, 30);
		add(txtSize);
		txtSize.setColumns(10);
		// Limit the text field to accept only numbers
		txtSize.setInputVerifier(new javax.swing.InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField textField = (JTextField) input;
				String text = textField.getText();
				return text.matches("\\d*"); // Allow only digits
			}
		});
		
		// -- END SIZE -- //
		
		// -- FLOOR NUMBER (OPTIONAL) -- //
		
        txtBldngFloor = new JTextField(); // Text field for building floor number
        txtBldngFloor.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the building floor text field
        txtBldngFloor.setVisible(true); // Initially hidden, it will be shown if needed (e.g., for buildings with multiple floors)
        txtBldngFloor.setBounds(976, 75, 37, 30);
        add(txtBldngFloor);
        txtBldngFloor.setColumns(10);
        //Limit the text field to accept only numbers
  		txtBldngFloor.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        String text = txtBldngFloor.getText();

		        // Only allow digits, spaces, and the '+' character
		        if (!Character.isDigit(c) ) {
		            e.consume();
		            return;
		        }

		        // Limit to 2 characters
		        if (text.length() >= 2) {
		            e.consume();
		        }
		    }
		});
  		
  		// -- END FLOOR NUMBER -- //
  		
		// --- END TEXT FIELDS --- //
		
		// ---------------------- //
			
		// --- SPINNERS --- //
		
		spinnerRoom = new JSpinner();
		spinnerRoom.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerRoom.setBounds(782, 102, 48, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 10
		spinnerRoom.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1)); // Minimum 0, Maximum 10, Step 1
		add(spinnerRoom);
		
		spinnerBath = new JSpinner();
		spinnerBath.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerBath.setBounds(737, 125, 48, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 10
		spinnerBath.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1)); // Minimum 0, Maximum 10, Step 1
		add(spinnerBath);
		
		spinnerFloor = new JSpinner();
		spinnerFloor.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerFloor.setBounds(737, 151, 46, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 4
		spinnerFloor.setModel(new javax.swing.SpinnerNumberModel(0, 0, 4, 1)); // Minimum 0, Maximum 4, Step 1
		add(spinnerFloor);
		
		spinnerParking = new JSpinner();
		spinnerParking.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerParking.setBounds(829, 171, 46, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 4
		spinnerParking.setModel(new javax.swing.SpinnerNumberModel(0, 0, 4, 1)); // Minimum 0, Maximum 4, Step 1
		add(spinnerParking);
		
		spinnerStorage = new JSpinner();
		spinnerStorage.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerStorage.setBounds(748, 198, 46, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 3
		spinnerStorage.setModel(new javax.swing.SpinnerNumberModel(0, 0, 3, 1)); // Minimum 0, Maximum 3, Step 1
		add(spinnerStorage);
		
		// --- END SPINNERS --- //
		
		// ------------------- //
		
		// --- CHECKBOXES --- //
		
		chckbxInCondo = new JCheckBox("");
		chckbxInCondo.setBounds(221, 150, 21, 35);
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
		
		lblRol = new JLabel("ROL:");
		lblRol.setForeground(new Color(255, 51, 0));
		lblRol.setHorizontalAlignment(SwingConstants.LEFT);
		lblRol.setFont(new Font("Yu Gothic UI", Font.BOLD, 18));
		lblRol.setBounds(966, 29, 59, 30);
		add(lblRol);
		
		txtRol = new JTextField();
		// RED BORDER
		txtRol.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED));
		txtRol.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtRol.setColumns(10);
		txtRol.setBounds(1009, 29, 145, 30);
		add(txtRol);
		
		lblPanelDePropiedades = new JLabel("Panel de Propiedades");
		lblPanelDePropiedades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPanelDePropiedades.setForeground(Color.GRAY);
		lblPanelDePropiedades.setFont(new Font("Noto Sans JP", Font.PLAIN, 16));
		lblPanelDePropiedades.setBounds(535, 5, 187, 16);
		add(lblPanelDePropiedades);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(625, 75, 10, 453);
		add(separator);
		
		
		// --- END CHECKBOXES --- //
		
		// ---------------------- //
		
		// --- ACTION LISTENERS --- //
		
		// -- COMBO CONDO -- //
		
		chckbxInCondo.addActionListener(new ActionListener() {
	    @Override
	    	public void actionPerformed(ActionEvent e) {
	    		if (chckbxInCondo.isSelected()) {
	    			comboCondo.setVisible(true); // Show the combo box if the checkbox is selected
	    			btnNewCondo.setVisible(true); // Show the "New Condo" button if the checkbox is selected
	    		} else {
	    			comboCondo.setVisible(false); // Hide the combo box if the checkbox is not selected
	    			btnNewCondo.setVisible(false); // Hide the "New Condo" button if the checkbox is not selected
	    		}
	    	}
	    });
		
		// -- END NEW CONDO BUTTON -- //
		
		// -- SAVE BUTTON -- //	
		
		btnSave.addActionListener(e -> {
			// If is House:
			if (comboPropertyType.getSelectedItem() instanceof PropertyTypeOption) {
				PropertyTypeOption selectedType = (PropertyTypeOption) comboPropertyType.getSelectedItem();
				if (selectedType.getValue().equals("House")) {
					isHouse(); // Call the method to show house fields
					PropertyController controller = new PropertyController();

					   	boolean success = false;
						try {
							success = controller.saveHouse(
									// LANDLORD, TOWN, PROPERTY TYPE
									(LandlordOption) comboLandlord.getSelectedItem(),
								    (TownOption) comboTown.getSelectedItem(),
								    (PropertyTypeOption) comboPropertyType.getSelectedItem(),
								    (CondoOption) comboCondo.getSelectedItem(),
								    // ADDRESS
								    
								    txtRol.getText(),
								    txtAddress.getText(),
								    txtNum1.getText(),
								    txtNum2.isVisible() ? txtNum2.getText() : null,	
								    (String) comboRegion.getSelectedItem(),
								    
								    // DATA
								    (int) spinnerRoom.getValue(),
								    (int) spinnerBath.getValue(),
								    (int) spinnerFloor.getValue(),
								    (int) spinnerParking.getValue(),
								    (int) spinnerStorage.getValue(),
								    chckbxGarden.isSelected(),
								    chckbxPatio.isSelected(),
								    chckbxPool.isSelected(),
								    chckbxBBQ.isSelected(),
								    chckbxBalcony.isSelected(),
								    chckbxTerrace.isSelected(),
								    chckbxLaundry.isSelected(),
								    chckbxInCondo.isSelected()
							);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					    if (success) {
					        Popup.showSuccess("Propiedad insertada correctamente."); // Show success message if saving is successful
					     //   cleanFields();
					    } else {
					    	Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
					        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
					    }
				} else if (selectedType.getValue().equals("Flat")) {
					isFlat(); // Call the method to show flat fields
					PropertyController controller = new PropertyController();
					List<Parking> parkings = getAllParkingsFromForm();
					// Converts txtBldngFloor to INTEGER
					String bldngFloorText = txtBldngFloor.getText();
					int bldngFloor = 0;
					if (!bldngFloorText.isEmpty()) {
						try {
							bldngFloor = Integer.parseInt(bldngFloorText); // Convert to integer
						} catch (NumberFormatException e1) {
							Popup.show("El número de piso debe ser un número entero.", "error"); // Show error if conversion fails
																									
							return; // Exit the method if conversion fails
						}
					} else {
						bldngFloor = 0; // Default value if the text field is empty
					}
					
					   	boolean success = false;
						try {
						// if spinnerParking is more than 0, then it is a parking lot
							if ((int) spinnerParking.getValue() > 0) {
							
								success = controller.saveFlatParking(
								    (LandlordOption) comboLandlord.getSelectedItem(),
								    (TownOption) comboTown.getSelectedItem(),
								    (PropertyTypeOption) comboPropertyType.getSelectedItem(),
								    (CondoOption) comboCondo.getSelectedItem(),
								    txtRol.getText(),
								    txtAddress.getText(),
								    txtNum1.getText(),
								    txtNum2.isVisible() ? txtNum2.getText() : null,
								    (String) comboRegion.getSelectedItem(),
								    (int) spinnerRoom.getValue(), //2
								    (int) spinnerBath.getValue(), //3 
								    bldngFloor, //4
								    (int) spinnerStorage.getValue(), //5
								    (int) spinnerParking.getValue(), //6
								    chckbxBalcony.isSelected(), //7
							        chckbxBldngLift.isSelected(), //8
							        chckbxBldngPool.isSelected(), //9
							        chckbxBldngBBQ.isSelected(), //10
							        chckbxBldngGym.isSelected(), //11
							        chckbxBldngLaundry.isSelected(), //12
								    chckbxInCondo.isSelected(), //13
								    parkings //14
							);
						} else {
							success = controller.saveFlatNoParking(
									(LandlordOption) comboLandlord.getSelectedItem(),
								    (TownOption) comboTown.getSelectedItem(),
								    (PropertyTypeOption) comboPropertyType.getSelectedItem(),
								    (CondoOption) comboCondo.getSelectedItem(),
								    txtRol.getText(),
								    txtAddress.getText(),
								    txtNum1.getText(),
								    txtNum2.isVisible() ? txtNum2.getText() : null,
								    (String) comboRegion.getSelectedItem(),
								    (int) spinnerRoom.getValue(), //2
								    (int) spinnerBath.getValue(), //3 
								    bldngFloor, //4
								    (int) spinnerStorage.getValue(), //5
								    (int) spinnerParking.getValue(), //6
								    chckbxBalcony.isSelected(), //7
							        chckbxBldngLift.isSelected(), //8
							        chckbxBldngPool.isSelected(), //9
							        chckbxBldngBBQ.isSelected(), //10
							        chckbxBldngGym.isSelected(), //11
							        chckbxBldngLaundry.isSelected(), //12
								    chckbxInCondo.isSelected() //13
							);
						}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					    if (success) {
					        Popup.showSuccess("Propiedad insertada correctamente."); // Show success message if saving is successful
					        System.out.println("Rol: " + txtRol.getText());
					        System.out.println("Tamaño: " + txtSize.getText());
					        System.out.println("Dirección: " + txtAddress.getText());
					        System.out.println("Número 1: " + txtNum1.getText());
					        System.out.println("Número 2: " + (txtNum2.isVisible() ? txtNum2.getText() : "N/A"));
					        System.out.println("Región: " + comboRegion.getSelectedItem());
					        System.out.println("Comuna: " + comboTown.getSelectedItem());
					        System.out.println("Tipo de propiedad: " + comboPropertyType.getSelectedItem());
					        System.out.println("En Condominio: " + chckbxInCondo.isSelected());
					        System.out.println("Condominio: " + comboCondo.getSelectedItem());
					        System.out.println("Habitaciones: " + spinnerRoom.getValue());
					        System.out.println("Baños: " + spinnerBath.getValue());
					        System.out.println("Piso: " + bldngFloor);
					        System.out.println("Bodega: " + spinnerStorage.getValue());
					        System.out.println("Estacionamientos: " + spinnerParking.getValue());
					        System.out.println("Balcony: " + chckbxBalcony.isSelected());
					        System.out.println("BldngLift: " + chckbxBldngLift.isSelected());
					        System.out.println("BldngPool: " + chckbxBldngPool.isSelected());
					        System.out.println("BldngBBQ: " + chckbxBldngBBQ.isSelected());
					        System.out.println("BldngGym: " + chckbxBldngGym.isSelected());
					        System.out.println("BldngLaundry: " + chckbxBldngLaundry.isSelected());
					        
					       cleanFields();
					    } else {
					    	Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
					        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
					    }
				} else if (selectedType.getValue().equals("Storage")) {
					isStorage(); // Call the method to show storage fields
					
				} else if (selectedType.getValue().equals("Parking")) {
					PropertyController controller = new PropertyController();
					isParking(); // Call the method to show parking fields
					
					boolean success = false;
					try {
						success = controller.saveParking(
						    (LandlordOption) comboLandlord.getSelectedItem(),
						    (TownOption) comboTown.getSelectedItem(),
						    (PropertyTypeOption) comboPropertyType.getSelectedItem(),
						    (CondoOption) comboCondo.getSelectedItem(),
						    txtRol.getText(),
						    txtAddress.getText(),
						    txtNum1.getText(),
						    txtNum2.isVisible() ? txtNum2.getText() : null,
						    (String) comboRegion.getSelectedItem(),			
						    chckbxInCondo.isSelected()
						);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				    if (success) {
				        Popup.showSuccess("Propiedad insertada correctamente."); // Show success message if saving is successful
				        System.out.println("Rol: " + txtRol.getText());
				        System.out.println("Tamaño: " + txtSize.getText());
				        System.out.println("Dirección: " + txtAddress.getText());
				        System.out.println("Número 1: " + txtNum1.getText());
				        System.out.println("Número 2: " + (txtNum2.isVisible() ? txtNum2.getText() : "N/A"));
				        System.out.println("Región: " + comboRegion.getSelectedItem());
				        System.out.println("Comuna: " + comboTown.getSelectedItem());
				        System.out.println("Tipo de propiedad: " + comboPropertyType.getSelectedItem());
				        System.out.println("En Condominio: " + chckbxInCondo.isSelected());
				        System.out.println("Condominio: " + comboCondo.getSelectedItem());
				        
				       cleanFields();
				    } else {
				    	Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
				        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
				    }
				} else if (selectedType.getValue().equals("Land")) {
					isLand(); // Call the method to show land fields
				} else if (selectedType.getValue().equals("Office")) {
					isOffice(); // Call the method to show office fields
				}
			}
//		    PropertyController controller = new PropertyController();
//
//		    boolean success = false;
//			try {
//				success = controller.saveHouse(
//				    (LandlordOption) comboLandlord.getSelectedItem(),
//				    (TownOption) comboTown.getSelectedItem(),
//				    (PropertyTypeOption) comboPropertyType.getSelectedItem(),
//				    (CondoOption) comboCondo.getSelectedItem(),
//				    txtRol.getText(),
//				    txtAddress.getText(),
//				    txtNum1.getText(),
//				    txtNum2.isVisible() ? txtNum2.getText() : null,
//				    (String) comboRegion.getSelectedItem(),
//				    (int) spinnerRoom.getValue(),
//				    (int) spinnerBath.getValue(),
//				    (int) spinnerFloor.getValue(),
//				    (int) spinnerParking.getValue(),
//				    (int) spinnerStorage.getValue(),
//				    chckbxGarden.isSelected(),
//				    chckbxPatio.isSelected(),
//				    chckbxPool.isSelected(),
//				    chckbxBBQ.isSelected(),
//				    chckbxBalcony.isSelected(),
//				    chckbxTerrace.isSelected(),
//				    chckbxLaundry.isSelected(),
//				    chckbxInCondo.isSelected()
//				);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//		    if (success) {
//		        Popup.showSuccess("Propiedad insertada correctamente."); // Show success message if saving is successful
//		        cleanFields();
//		    } else {
//		    	Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
//		        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
//		    }
		});

		// -- END SAVE BUTTON -- //
		
		// -- BACK BUTTON -- //
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
				
		// -- END BACK BUTTON -- //
	
		panelEstacionamientos = new JPanel();
		panelEstacionamientos.setLayout(null); // uno debajo del otro
		//panelEstacionamientos.setOpaque(false); // para que no se vea gris si no quieres
		panelEstacionamientos.setBounds(922, 190, 270, 110); // o lo que necesites
		panelEstacionamientos.setBackground(new Color(187, 187, 187)); // Fondo blanco para el panel de estacionamientos
		panelEstacionamientos.setVisible(false); // por si acaso se agregó oculto
		//panelEstacionamientos.setOpaque(true);  // para que el fondo blanco se aplique
		//panelEstacionamientos.setBorder(BorderFactory.createLineBorder(Color.RED));
		add(panelEstacionamientos);
		setComponentZOrder(panelEstacionamientos, 0);
		
		spinnerParking.addChangeListener(e -> {  
		    int count = (Integer) spinnerParking.getValue();  

		    panelEstacionamientos.removeAll(); // 💡 solo vacía los formularios, no todo el contentPane
		    panelEstacionamientos.setBounds(922, 110, 270, 150 * count); 
		    panelEstacionamientos.setVisible(true);
		    parkingForms.clear();
		    for (int i = 0; i < count; i++) {
		        JPanel miniForm = createParking(i + 1);
		        miniForm.setBounds(0, i * 137, 270, 135); // Adjust the position of each mini form when adding it
		        System.out.println("Adding parking form " + (i + 1)); // Debugging output
		        panelEstacionamientos.add(miniForm);
		        
		    }

		    panelEstacionamientos.revalidate();
		    panelEstacionamientos.repaint();
		});
		
		// --- END ACTION LISTENERS --- //

	} // END OF  PropertiesPanel constructor
	
	// ---------------------- //
	
	// --- AUXILIARY METHODS --- //
	
	public JPanel createParking(int numero) {
	    JPanel panelParking = new JPanel();
	    panelParking.setBorder(BorderFactory.createTitledBorder("Estacionamiento #" + numero));
	    panelParking.setBackground(Color.WHITE);
	    panelParking.setLayout(null);
	    
	    JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Cubierto", "Descubierto"}); // SPANISH for "Covered", "Uncovered"
	    tipoCombo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
	    tipoCombo.setBounds(140, 28, 116, 30);
	    panelParking.add(tipoCombo);
	    
	    txtPrkngRol = new JTextField(10);
	    txtPrkngRol.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
	    txtPrkngRol.setBounds(140, 64, 116, 30);
	    panelParking.add(txtPrkngRol);
	    
	    txtPrkngNum = new JTextField(10);
	    txtPrkngNum.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
	    txtPrkngNum.setBounds(140, 100, 116, 30);
	    panelParking.add(txtPrkngNum);

	    JLabel lblPrkngType = new JLabel("Tipo:");
	    lblPrkngType.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblPrkngType.setBounds(14, 28, 116, 16);
	    panelParking.add(lblPrkngType);
	   
	    JLabel lblPrkngRol = new JLabel("ROL:");
	    lblPrkngRol.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblPrkngRol.setBounds(14, 64, 116, 16);
	    panelParking.add(lblPrkngRol);
	    
	    JLabel lblPrkngNum = new JLabel("Número:");
	    lblPrkngNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblPrkngNum.setBounds(14, 100, 116, 16);
	    panelParking.add(lblPrkngNum);
	    
	    
	    ParkingForm form = new ParkingForm(txtPrkngRol, tipoCombo, txtPrkngNum);
	    return panelParking;
	}
	
	public List<Parking> getAllParkingsFromForm() {
	    List<Parking> parkings = new ArrayList<>();

	    int qty = (int) spinnerParking.getValue(); // cantidad indicada por el usuario
	    boolean inCondo = chckbxInCondo.isSelected();
	    String rol = txtPrkngRol.getText();
	    Integer condoId = null;
	    Integer parkingNum = txtPrkngNum.getText().isEmpty() ? null : Integer.parseInt(txtPrkngNum.getText());

	    if (comboCondo.getSelectedItem() instanceof CondoOption) {
	        condoId = ((CondoOption) comboCondo.getSelectedItem()).getId();
	    }

	    for (int i = 0; i < qty; i++) {
	        Parking p = new Parking();
	        p.setRolSII(rol);
	        p.setInCondo(inCondo);
	        p.setCondoId(inCondo ? condoId : null); // si no está en condominio, deja null
	        parkings.add(p);
	    }

	    return parkings;
	}
	
	
	public void cleanFields() {
		//comboLandlord.setSelectedIndex(0); // Reset landlord selection
		//comboPropertyType.setSelectedIndex(0); // Reset property type selection
		//comboRegion.setSelectedIndex(0); // Reset region selection
		//comboTown.removeAllItems(); // Clear towns
		//comboTown.addItem("Seleccione una Comuna"); // SPANISH for "Select a Town"
		//txtAddress.setText(""); // Clear address text field
		//txtNum1.setText(""); // Clear address number 1 text field
		txtNum2.setText(""); // Clear address number 2 text field
		
		// Reset other fields if needed (e.g., room quantity, bath quantity, etc.)
		//spinnerRoom.setValue(0); // Reset room quantity spinner
		//spinnerBath.setValue(0); // Reset bath quantity spinner
		//spinnerFloor.setValue(0); // Reset floor quantity spinner
		//spinnerParking.setValue(0); // Reset parking quantity spinner
		//spinnerStorage.setValue(0); // Reset storage quantity spinner
//		chckbxGarden.setSelected(false); // Uncheck garden checkbox
//		chckbxPatio.setSelected(false); // Uncheck patio checkbox
//		chckbxPool.setSelected(false); // Uncheck pool checkbox
//		chckbxBalcony.setSelected(false); // Uncheck balcony checkbox
//		chckbxBBQ.setSelected(false); // Uncheck BBQ checkbox
//		chckbxTerrace.setSelected(false); // Uncheck terrace checkbox
//		chckbxLaundry.setSelected(false); // Uncheck laundry room checkbox
//		chckbxInCondo.setSelected(false); // Uncheck "In Condominium" checkbox
		comboCondo.setVisible(false); // Hide condo combo box
		btnNewCondo.setVisible(false); // Hide "New Condo" button
		lblLocationTitle.setText("Dirección"); // Reset address title label
		lblAddressNum2.setVisible(false); // Hide address number 2 label
		txtNum2.setVisible(false); // Hide address number 2 text field
		lblRoomQty.setVisible(false); // Hide room quantity label
		lblBathQty.setVisible(false); // Hide bath quantity label
		lblFloorQty.setVisible(false); // Hide floor quantity label
		lblParkingQty.setVisible(false); // Hide parking quantity label
		lblHasStorage.setVisible(false); // Hide storage label
		lblHasGarden.setVisible(false); // Hide garden label
		lblHasPatio.setVisible(false); // Hide patio label
		lblHasPool.setVisible(false); // Hide pool label
		lblHasBalcony.setVisible(false); // Hide balcony label
		lblHasBBQ.setVisible(false); // Hide BBQ label
		lblHasTerrace.setVisible(false); // Hide terrace label
		lblHasLaundryRoom.setVisible(false); // Hide laundry room label
		lblInCondo.setVisible(false); // Hide "In Condominium" label
		lblBuildingHasLift.setVisible(false); // Hide building lift label
		lblBuildingHasPool.setVisible(false); // Hide building pool label
		lblBuildingHasBBQ.setVisible(false); // Hide building BBQ label
		lblBuildingHasGym.setVisible(false); // Hide building gym label
		lblBuildingHasLaundryRoom.setVisible(false); // Hide building laundry room label
		spinnerRoom.setVisible(false); // Hide room quantity spinner
		spinnerBath.setVisible(false); // Hide bath quantity spinner
		spinnerFloor.setVisible(false); // Hide floor quantity spinner
		spinnerParking.setVisible(false); // Hide parking quantity spinner
		spinnerStorage.setVisible(false); // Hide storage quantity spinner
		chckbxGarden.setVisible(false); // Hide garden checkbox
		chckbxPatio.setVisible(false); // Hide patio checkbox
		chckbxPool.setVisible(false); // Hide pool checkbox
		chckbxBalcony.setVisible(false); // Hide balcony checkbox
		chckbxBBQ.setVisible(false); // Hide BBQ checkbox
		chckbxTerrace.setVisible(false); // Hide terrace checkbox
		chckbxLaundry.setVisible(false); // Hide laundry room checkbox
		//txtRol.setText(""); // Clear ROL text field
		//txtSize.setText(""); // Clear size text field
		
	}
	
	public void isHouse() {
		cleanFields(); // Clear all fields before setting house-specific fields
		txtNum2.setVisible(false); // Hide address number 2 field for houses
		txtBldngFloor.setVisible(false); // Hide building floor text field for houses
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
		lblFloorFlat.setVisible(false); // Hide floor label for houses
		
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
		cleanFields(); // Clear all fields before setting flat-specific fields
		txtNum2.setVisible(true); // Show address number 2 field for flats
		txtBldngFloor.setVisible(true); // Show building floor text field for flats
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
		lblHasBalcony.setVisible(true); // Hide balcony label for flats
		lblHasBBQ.setVisible(false); // Hide BBQ label for flats
		lblHasTerrace.setVisible(false); // Hide terrace label for flats
		lblHasLaundryRoom.setVisible(false); // Hide laundry room label for flats
		lblFloorFlat.setVisible(true); // Show floor label for flats
		
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
		chckbxBalcony.setVisible(true); // Hide checkbox for balcony for flats
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
		cleanFields(); // Clear all fields before setting storage-specific fields
		txtNum2.setVisible(false); // Hide address number 2 field for storage
		txtBldngFloor.setVisible(false); // Hide building floor text field for storage
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
		lblFloorFlat.setVisible(false); // Hide floor label for storage
		
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
		cleanFields(); // Clear all fields before setting parking-specific fields
		txtNum2.setVisible(false); // Hide address number 2 field for parking
		txtBldngFloor.setVisible(false); // Hide building floor text field for parking
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
		lblFloorFlat.setVisible(false); // Hide floor label for parking
		
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
		cleanFields(); // Clear all fields before setting land-specific fields
		txtNum2.setVisible(false); // Hide address number 2 field for land
		txtBldngFloor.setVisible(false); // Hide building floor text field for land
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
		lblFloorFlat.setVisible(false); // Hide floor label for land
		
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
		cleanFields(); // Clear all fields before setting office-specific fields
		txtBldngFloor.setVisible(true); // Show building floor text field for office
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
		lblFloorFlat.setVisible(true); // Show floor label for office
		
	}
}
