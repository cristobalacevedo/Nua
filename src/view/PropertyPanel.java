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
import utils.FieldValidator;
import utils.IntValidator;
import utils.LandlordOption;
import utils.NumberDocumentFilter;
import utils.PropertyTypeOption;
import utils.TownOption;

import model.Parking;
import model.ParkingForm;
import model.Storage;
import model.StorageForm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
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
	private JLabel lblCabinQty;
	private JLabel lblMtngRoomQty;
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
	private JSpinner spinnerCabin;
	private JSpinner spinnerMtngRoom;
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
	private List<ParkingForm> parkingForms = new ArrayList<>();
	private List<StorageForm> storageForms = new ArrayList<>();
	private JPanel panel_Parking;
	private JPanel panel_Storage;
	private JTextField txtBldngFloor;
	private JTextField txtPrkngRol;
	private JTextField txtPrkngNum;
	private JTextField txtStorageRol;
	private JTextField txtStorageNum;
	private JTextField txtStorageSize;
	private JSpinner spinner_1;
	
	public String sizeText;
	
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
		String defaultPropertyType = "Seleccione un Tipo"; // SPANISH for "Select a Property Type"
		
		comboPropertyType.addItem(new PropertyTypeOption(defaultPropertyType, "")); // SPANISH for "Select a Property Type"
		
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
		lblParkingQty.setBounds(35, 315, 153, 30);
		add(lblParkingQty);
		
		lblHasStorage = new JLabel("Bodega:"); // SPANISH for "Storage"
		lblHasStorage.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18)); 
		lblHasStorage.setBounds(35, 444, 81, 30);
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
        
        lblCabinQty = new JLabel("Cabinas:");
		lblCabinQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblCabinQty.setBounds(680, 192, 94, 30);
		lblCabinQty.setVisible(false); // Initially hidden, it will be shown if needed (e.g., for buildings with cabins)
		add(lblCabinQty);
		
		lblMtngRoomQty = new JLabel("Salas de Reunión:");
		lblMtngRoomQty.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblMtngRoomQty.setBounds(859, 192, 173, 30);
		lblMtngRoomQty.setVisible(false); // Initially hidden, it will be shown if needed (e.g., for buildings with meeting rooms)
		add(lblMtngRoomQty);
	    
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
		((AbstractDocument) txtSize.getDocument()).setDocumentFilter(new NumberDocumentFilter(5));
		
		// -- END SIZE -- //
		
		// -- FLOOR NUMBER (OPTIONAL) -- //
		
        txtBldngFloor = new JTextField(); // Text field for building floor number
        txtBldngFloor.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18)); // Set font for the building floor text field
        txtBldngFloor.setVisible(true); // Initially hidden, it will be shown if needed (e.g., for buildings with multiple floors)
        txtBldngFloor.setBounds(976, 75, 37, 30);
        add(txtBldngFloor);
        txtBldngFloor.setColumns(10);
        //Limit the text field to accept only numbers
        ((AbstractDocument) txtBldngFloor.getDocument()).setDocumentFilter(new NumberDocumentFilter(2));
  		
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
		spinnerFloor.setBounds(737, 151, 48, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 4
		spinnerFloor.setModel(new javax.swing.SpinnerNumberModel(0, 0, 4, 1)); // Minimum 0, Maximum 4, Step 1
		add(spinnerFloor);
		
		spinnerParking = new JSpinner();
		spinnerParking.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerParking.setBounds(185, 316, 46, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 4
		spinnerParking.setModel(new javax.swing.SpinnerNumberModel(0, 0, 4, 1)); // Minimum 0, Maximum 4, Step 1
		add(spinnerParking);
		
		spinnerStorage = new JSpinner();
		spinnerStorage.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerStorage.setBounds(103, 447, 46, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 2
		spinnerStorage.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2, 1)); // Minimum 0, Maximum 2, Step 1
		add(spinnerStorage);
		
		spinnerCabin = new JSpinner();
		spinnerCabin.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerCabin.setBounds(758, 192, 48, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 20
		spinnerCabin.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1)); // Minimum 0, Maximum 20, Step 1
		spinnerCabin.setVisible(false); // Initially hidden, it will be shown if needed (e.g., for buildings with cabins)
		add(spinnerCabin);
		
		spinnerMtngRoom = new JSpinner();
		spinnerMtngRoom.setFont(new Font("Yu Gothic UI", Font.BOLD, 16));
		spinnerMtngRoom.setBounds(1009, 192, 48, 30);
		// Set the spinner to have a minimum value of 0 and a maximum value of 6
		spinnerMtngRoom.setModel(new javax.swing.SpinnerNumberModel(0, 0, 6, 1)); // Minimum 0, Maximum 6, Step 1
		spinnerMtngRoom.setVisible(false); // Initially hidden, it will be shown if needed (e.g., for buildings with meeting rooms)
		add(spinnerMtngRoom);
		
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
			
			LandlordOption LandlordName = (LandlordOption) comboLandlord.getSelectedItem();
			TownOption TownName = (TownOption) comboTown.getSelectedItem();
			PropertyTypeOption PropertyTypeName = (PropertyTypeOption) comboPropertyType.getSelectedItem();
			CondoOption CondoName = (CondoOption) comboCondo.getSelectedItem();
			
			
			String sizeText = txtSize.getText();
			
			//Integer size = IntValidator.valid(txtSize, "Tamaño");
			//if (size == null) return; // Detener el flujo si no es válido // Get the size from the text field, or null if empty
			String rol = txtRol.getText();
			String address = txtAddress.getText();
			String num1 = txtNum1.getText();
			String num2 = txtNum2.isVisible() ? txtNum2.getText() : null; // Check if txtNum2 is visible before getting its text
			String region = (String) comboRegion.getSelectedItem();
			
			int roomQty = (int) spinnerRoom.getValue(); // Get the value from spinnerRoom
			int bathQty = (int) spinnerBath.getValue(); // Get the value from spinnerBath
			String bldngFloorText = txtBldngFloor.getText();
			int bldngFloor = 0;
			int floorQty = (int) spinnerFloor.getValue(); // Get the value from spinnerFloor
			int storageQty = (int) spinnerStorage.getValue(); // Get the value from spinnerStorage
			int parkingQty = (int) spinnerParking.getValue(); // Get the value from spinnerParking
			int cabinQty = (int) spinnerCabin.getValue(); // Get the value from spinnerCabin
			int mtngRoomQty = (int) spinnerMtngRoom.getValue(); // Get the value from spinnerMtngRoom
			
			boolean hasGarden = chckbxGarden.isSelected(); // Get the value from chckbxGarden
			boolean hasPatio = chckbxPatio.isSelected(); // Get the value from chckbxPatio
			boolean hasPool = chckbxPool.isSelected(); // Get the value from chckbxPool
			boolean hasBBQ = chckbxBBQ.isSelected(); // Get the value from chckbxBBQ
			boolean hasTerrace = chckbxTerrace.isSelected(); // Get the value from chckbxTerrace
			boolean hasLaundry = chckbxLaundry.isSelected(); // Get the value from chckbxLaundry
			boolean hasBalcony = chckbxBalcony.isSelected(); // Get the value from chckbxBalcony
			boolean hasBldngLift = chckbxBldngLift.isSelected(); // Get the value from chckbxBldngLift
			boolean hasBldngPool = chckbxBldngPool.isSelected(); // Get the value from chckbxBldngPool
			boolean hasBldngBBQ = chckbxBldngBBQ.isSelected(); // Get the value from chckbxBldngBBQ
			boolean hasBldngGym = chckbxBldngGym.isSelected(); // Get the value from chckbxBldngGym
			boolean hasBldngLaundry = chckbxBldngLaundry.isSelected(); // Get the value from chckbxBldngLaundry
			boolean inCondo = chckbxInCondo.isSelected(); // Get the value from chckbxInCondo
			
			List<Parking> parkings = getAllParkingsFromForm();
			List<Storage> storages = getAllStoragesFromForm();
			PropertyTypeOption selectedType = (PropertyTypeOption) comboPropertyType.getSelectedItem();
			
			// If Landlord, Town, Property Type, and Condo are selected:
			if (comboLandlord.getSelectedItem() instanceof LandlordOption) {
				if (LandlordName.getId() == -1) { // Check if a landlord is selected
					Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); // Show error if no landlord is selected
					return; // Exit the method if no landlord is selected
				}
			} else {
				Popup.show("ERROR: Debe seleccionar un propietario válido.", "error"); // Show error if no landlord is selected
				return; // Exit the method if no landlord is selected
			}
			
			// If is House:
			if (comboPropertyType.getSelectedItem() instanceof PropertyTypeOption) {
				if (selectedType.getValue().equals("")) {
					Popup.show("ERROR: Debe seleccionar un tipo de propiedad.", "error"); // Show error if no property type is selected
					System.out.println("ERROR: Debe seleccionar un tipo de propiedad."); // Log if no property type is selected
					
					return; // Exit the method if no property type is selected
				
				} else if (selectedType.getValue().equals("House")) {
					contentPane.repaint();
					isHouse(); // Call the method to show house fields
					PropertyController controller = new PropertyController();
					
					   	boolean success = false;
						try {
							success = controller.saveHouse(
									// LANDLORD, TOWN, PROPERTY TYPE
									LandlordName, TownName, PropertyTypeName, CondoName,
								    
								    // ADDRESS
								    rol, address, num1, num2, region,
								    
								    // DATA
								    roomQty, //1
								    bathQty, //2
								    floorQty, //3
								    parkingQty, //4
								    storageQty, //5
								    hasGarden, //6
								    hasPatio, //7
								    hasPool, //8
								    hasBBQ,	 //9
								    hasBalcony, //10
								    hasTerrace, //11
								    hasLaundry, //12
								    inCondo, //13
								    sizeText
							);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

					    if (success) {
					    	Popup.showSuccess("Casa creada con éxito."); 
					    	System.out.println("House created successfully."); // Show success message if saving is successful
					        cleanFields();
					    } else {
					    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
					        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
					    }
				} else if (selectedType.getValue().equals("Flat")) {
					isFlat(); // Call the method to show flat fields
					PropertyController controller = new PropertyController();
	
					if (!bldngFloorText.isEmpty()) {
						try {
							bldngFloor = Integer.parseInt(bldngFloorText); // Convert to integer
						} catch (NumberFormatException e1) {
							Popup.show("ERROR: El número de piso debe ser un número entero.", "error"); // Show error if conversion fails
																									
							return; // Exit the method if conversion fails
						}
					} else {
						bldngFloor = 0; // Default value if the text field is empty
					}
					
					   	// If spinnerParking and spinnerStorage are both more than 0, then it is a flat with parking and storage
						try {
							if (parkingQty > 0 && storageQty > 0) {
								boolean success = controller.saveFlatStorageAndParking(
										LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
										roomQty, //2
										bathQty, //3 
										bldngFloor, //4
										storageQty, //5
										parkingQty, //6
										hasBalcony, //7
										hasBldngLift, //8
										hasBldngPool, //9
										hasBldngBBQ, //10
										hasBldngGym, //11
										hasBldngLaundry, //12
										inCondo, //13
										parkings,
										storages,
										sizeText
										);
								if (success) {
							    	//logFlatDetails(); // Log flat data for debugging
									Popup.showSuccess("Departamento creado con éxito."); 
									System.out.println("Flat w/ Storage & Parking created successfully."); // Show success message if saving is successful
							        System.out.println("Rol: " + txtRol.getText());
							        System.out.println("Tamaño: " + txtSize.getText());
							        System.out.println("Dirección: " + txtAddress.getText());
							        System.out.println("Número 1: " + num1);
							        System.out.println("Número 2: " + num2);
							        cleanFields(); // Set cleanFields to true if saving is successful
							    } else {
							    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
							        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
							    }
						
						// if only spinnerParking is more than 0, then it is a flat with parking
						} else if (parkingQty > 0 && storageQty == 0) {
							
								boolean success = controller.saveFlatParking(
										LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
										roomQty, //2
										bathQty, //3 
										bldngFloor, //4
										storageQty, //5
										parkingQty, //6
										hasBalcony, //7
								    	hasBldngLift, //8
								    	hasBldngPool, //9
								    	hasBldngBBQ, //10
								    	hasBldngGym, //11
								    	hasBldngLaundry, //12
								    	inCondo, //13
								    	parkings, //14
								    	sizeText
										);
								if (success) {
							    	//logFlatDetails(); // Log flat data for debugging
									Popup.showSuccess("Departamento creado con éxito."); 
									System.out.println("Flat w/ Parking created successfully."); // Show success message if saving is successful
							        System.out.println("Rol: " + rol);
							        System.out.println("Tamaño: " + sizeText);
							        System.out.println("Dirección: " + address);
							        System.out.println("Número 1: " + num1);
							        System.out.println("Número 2: " + num2);
							        cleanFields(); // Set cleanFields to true if saving is successful
							    } else {
							    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
							        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
							    }
							} else if (parkingQty == 0 && storageQty > 0) {
								boolean success = controller.saveFlatStorage(
										LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
										roomQty, //2
										bathQty, //3 
										bldngFloor, //4
										storageQty, //5
										parkingQty, //6
										hasBalcony, //7
										hasBldngLift, //8
										hasBldngPool, //9
										hasBldngBBQ, //10
										hasBldngGym, //11
										hasBldngLaundry, //12
										inCondo, //13
										storages,
										sizeText
										);
								if (success) {
							    	//logFlatDetails(); // Log flat data for debugging
									Popup.showSuccess("Departamento creado con éxito."); 
									System.out.println("Flat w/ Storage created successfully."); // Show success message if saving is successful, successfull
							        System.out.println("Rol: " + txtRol.getText());
							        System.out.println("Tamaño: " + txtSize.getText());
							        System.out.println("Dirección: " + txtAddress.getText());
							        System.out.println("Número 1: " + num1);
							        System.out.println("Número 2: " + num2);
							        cleanFields(); // Set cleanFields to true if saving is successful
							    } else {
							    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
							        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
							    }
								
						// If both storage & parking are 0, then it is a flat without parking or storage
						} else if (parkingQty == 0 && storageQty == 0) { // 
							boolean success = controller.saveFlatNoSP(
									LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
									roomQty, //2
									bathQty, //3 
									bldngFloor, //4
									storageQty, //5
									parkingQty, //6
									hasBalcony, //7
									hasBldngLift, //8
									hasBldngPool, //9
									hasBldngBBQ, //10
									hasBldngGym, //11
									hasBldngLaundry, //12
									inCondo, //13
									sizeText
									);
							if (success) {
						    	//logFlatDetails(); // Log flat data for debugging
								Popup.showSuccess("Departamento creado con éxito."); 
								System.out.println("Flat without Storage & Parking created successfully."); // Show success message if saving is successful
						        System.out.println("Rol: " + txtRol.getText());
						        System.out.println("Tamaño: " + txtSize.getText());
						        System.out.println("Dirección: " + txtAddress.getText());
						        System.out.println("Número 1: " + num1);
						        System.out.println("Número 2: " + num2);
						        cleanFields(); // Set cleanFields to true if saving is successful
						    } else {
						    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
						        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
						    }
							
						}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
//					    if (success) {
//					    	//logFlatDetails(); // Log flat data for debugging
//					        Popup.showSuccess("Propiedad insertada correctamente."); // Show success message if saving is successful
//					        System.out.println("Rol: " + txtRol.getText());
//					        System.out.println("Tamaño: " + txtSize.getText());
//					        System.out.println("Dirección: " + txtAddress.getText());
//					        System.out.println("Número 1: " + txtNum1.getText());
//					        System.out.println("Número 2: " + txtNum2.getText());
//					        cleanFields(); // Set cleanFields to true if saving is successful
//					    } else {
//					    	Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
//					        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
//					    }
					    
				} else if (selectedType.getValue().equals("Storage")) {
					PropertyController controller = new PropertyController();
					isStorage(); // Call the method to show storage fields
					
					boolean success = false;
					try {
						success = controller.saveStorage(
						    LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region, inCondo, sizeText
						);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				    if (success) {
				    	Popup.showSuccess("Bodega creada con éxito."); 
				    	System.out.println("Storage created successfully."); // Show success message if saving is successful
				        // DEBUGGING
				        System.out.println("\nSTORAGE CREATED");
				        System.out.println("Rol: " + txtRol.getText());
				        System.out.println("Tamaño: " + txtSize.getText());
				        System.out.println("Dirección: " + txtAddress.getText());
				        System.out.println("Número 1: " + txtNum1.getText());
				        System.out.println("Número 2: " + txtNum2.getText());
				        System.out.println("Región: " + comboRegion.getSelectedItem());
				        System.out.println("Comuna: " + comboTown.getSelectedItem());
				        System.out.println("Tipo de propiedad: " + comboPropertyType.getSelectedItem());
				        System.out.println("En Condominio: " + chckbxInCondo.isSelected());
				        System.out.println("Condominio: " + comboCondo.getSelectedItem());
				         
				       cleanFields();
				    } else {
				    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
				        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
				    }
				} else if (selectedType.getValue().equals("Parking")) {
					PropertyController controller = new PropertyController();
					isParking(); // Call the method to show parking fields
					
					boolean success = false;
					try {
						success = controller.saveParking(
						    LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region, inCondo, sizeText
						);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				    if (success) {
				    	Popup.showSuccess("Estacionamiento creado con éxito."); 
				    	System.out.println("Parking created successfully."); // Show success message if saving is successful
				        // DEBUGGING
				        System.out.println("\nPARKING CREATED");
				        System.out.println("Rol: " + txtRol.getText());
				        System.out.println("Tamaño: " + txtSize.getText());
				        System.out.println("Dirección: " + txtAddress.getText());
				        System.out.println("Número 1: " + txtNum1.getText());
				        System.out.println("Número 2: " + txtNum2.getText());
				        System.out.println("Región: " + comboRegion.getSelectedItem());
				        System.out.println("Comuna: " + comboTown.getSelectedItem());
				        System.out.println("Tipo de propiedad: " + comboPropertyType.getSelectedItem());
				        System.out.println("En Condominio: " + chckbxInCondo.isSelected());
				        System.out.println("Condominio: " + comboCondo.getSelectedItem());
				         
				       cleanFields();
				    } else {
				    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
				        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
				    }
				} else if (selectedType.getValue().equals("Land")) {
					isLand(); // Call the method to show land fields
					PropertyController controller = new PropertyController();
					boolean success = false;
					try {
						success = controller.saveLand(
								// LANDLORD, TOWN, PROPERTY TYPE
								LandlordName, TownName, PropertyTypeName, CondoName,
							    
							    // ADDRESS
							    rol, address, num1, num2, region,
							    
							    // DATA
							    roomQty, //1
							    bathQty, //2
							    floorQty, //3
							    parkingQty, //4
							    storageQty, //5
							    hasGarden, //6
							    hasPatio, //7
							    hasPool, //8
							    hasBBQ,	 //9
							    hasBalcony, //10
							    hasTerrace, //11
							    hasLaundry, //12
							    inCondo, //13
							    sizeText
						);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				    if (success) {
				    	Popup.showSuccess("Terreno creado con éxito."); 
				    	System.out.println("Land created successfully."); // Show success message if saving is successful
				        // DEBUGGING
				        System.out.println("\nLAND CREATED");
				        System.out.println("Rol: " + txtRol.getText());
				        System.out.println("Tamaño: " + txtSize.getText());
				        System.out.println("Dirección: " + txtAddress.getText());
				        System.out.println("Número 1: " + txtNum1.getText());
				        System.out.println("Número 2: " + txtNum2.getText());
				        System.out.println("Región: " + comboRegion.getSelectedItem());
				        System.out.println("Comuna: " + comboTown.getSelectedItem());
				        System.out.println("Tipo de propiedad: " + comboPropertyType.getSelectedItem());
				        System.out.println("En Condominio: " + chckbxInCondo.isSelected());
				        System.out.println("Condominio: " + comboCondo.getSelectedItem());
				         
				       cleanFields();
				    } else {
				    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
				        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
				    }
				} else if (selectedType.getValue().equals("Office")) {
					isOffice(); // Call the method to show office fields
					PropertyController controller = new PropertyController();
					
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
					
					   	// If spinnerParking and spinnerStorage are both more than 0, then it is a flat with parking and storage
						try {
							if (parkingQty > 0 && storageQty > 0) {
								boolean success = controller.saveOfficeStorageAndParking(
										LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
										bldngFloor, //4
										cabinQty, //5
										mtngRoomQty, //6
										storageQty, //7
										parkingQty, //8
										hasBldngLift, //9
										inCondo, //10
										parkings,
										storages,
										sizeText
										);
								if (success) {
							    	//logFlatDetails(); // Log flat data for debugging
									Popup.showSuccess("Oficina creada con éxito.");
									System.out.println("Office w/ Storage & Parking created successfully."); // Show success message if saving is successful
							        System.out.println("Rol: " + txtRol.getText());
							        System.out.println("Tamaño: " + txtSize.getText());
							        System.out.println("Dirección: " + txtAddress.getText());
							        System.out.println("Número 1: " + num1);
							        System.out.println("Número 2: " + num2);
							        cleanFields(); // Set cleanFields to true if saving is successful
							    } else {
							    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
							        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
							    }
						
						// if only spinnerParking is more than 0, then it is a flat with parking
						} else if (parkingQty > 0 && storageQty == 0) {
							
								boolean success = controller.saveOfficeParking(
										LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
										bldngFloor, //4
										cabinQty, //5
										mtngRoomQty, //6
										storageQty, //5
										parkingQty, //6
								    	hasBldngLift, //8
								    	inCondo, //13
								    	parkings, //14
								    	sizeText
										);
								if (success) {
							    	//logFlatDetails(); // Log flat data for debugging
									Popup.showSuccess("Oficina creada con éxito.");
									System.out.println("Office w/ Parking created successfully."); // Show success message if saving is successful
							        System.out.println("Rol: " + rol);
							        System.out.println("Tamaño: " + sizeText);
							        System.out.println("Dirección: " + address);
							        System.out.println("Número 1: " + num1);
							        System.out.println("Número 2: " + num2);
							        cleanFields(); // Set cleanFields to true if saving is successful
							    } else {
							    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
							        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
							    }
							} else if (parkingQty == 0 && storageQty > 0) {
								boolean success = controller.saveOfficeStorage(
										LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
										bldngFloor, //4
										cabinQty, //5
										mtngRoomQty, //6
										storageQty, //7
										parkingQty, //6
										hasBldngLift, //8
										inCondo, //13
										storages,
										sizeText
										);
								if (success) {
							    	//logFlatDetails(); // Log flat data for debugging
									Popup.showSuccess("Oficina creada con éxito.");
									System.out.println("Office w/ Storage created successfully."); // Show success message if saving is successful
							        System.out.println("Rol: " + txtRol.getText());
							        System.out.println("Tamaño: " + txtSize.getText());
							        System.out.println("Dirección: " + txtAddress.getText());
							        System.out.println("Número 1: " + num1);
							        System.out.println("Número 2: " + num2);
							        cleanFields(); // Set cleanFields to true if saving is successful
							    } else {
							    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
							        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
							    }
								
						// If both storage & parking are 0, then it is a flat without parking or storage
						} else if (parkingQty == 0 && storageQty == 0) { // 
							boolean success = controller.saveOfficeNoSP(
									LandlordName, TownName, PropertyTypeName, CondoName, rol, address, num1, num2, region,
									bldngFloor, //4
									cabinQty, //5
									mtngRoomQty, //6
									storageQty, //7
									parkingQty, //6
									hasBldngLift, //8
									inCondo, //13
									sizeText
									);
							if (success) {
						    	//logFlatDetails(); // Log flat data for debugging
								Popup.showSuccess("Oficina creada con éxito."); // Show success message if saving is successful
								System.out.println("Office without Storage & Parking created successfully."); // Show success message if saving is successful
						        System.out.println("Rol: " + txtRol.getText());
						        System.out.println("Tamaño: " + txtSize.getText());
						        System.out.println("Dirección: " + txtAddress.getText());
						        System.out.println("Número 1: " + num1);
						        System.out.println("Número 2: " + num2);
						        cleanFields(); // Set cleanFields to true if saving is successful
						    } else {
						    	//Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
						        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
						    }
							
						}
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
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
//				e1.printStackTrace();
//			}
//
//		    if (success) {
//		        Popup.showSuccess("Propiedad insertada correctamente."); // Show success message if saving is successful
//		        cleanFields();
//		    } else {
//		    	Popup.show("Error al guardar la propiedad.", "error"); // Show error message if saving fails
//		        System.out.println("Error al guardar la propiedad."); // Log error if saving fails
//		    
			
		});

		// -- END SAVE BUTTON -- //
		
		// -- BACK BUTTON -- //
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
				
		// -- END BACK BUTTON -- //
	
		panel_Parking = new JPanel();
		panel_Parking.setLayout(null); 
		//panelEstacionamientos.setOpaque(false);
		panel_Parking.setBounds(35, 356, 570, 90);
		panel_Parking.setBackground(new Color(187, 187, 187)); 
		panel_Parking.setVisible(false); // Initially hidden, only shown when Flat or Office is selected
		//panelEstacionamientos.setOpaque(true);  // para que el fondo blanco se aplique
		//panelEstacionamientos.setBorder(BorderFactory.createLineBorder(Color.RED));
		add(panel_Parking);
		setComponentZOrder(panel_Parking, 0);
		
		
		panel_Storage = new JPanel();
		panel_Storage.setLayout(null);
        panel_Storage.setBounds(35, 486, 578, 90);
        panel_Storage.setBackground(new Color(187, 187, 187)); 
        panel_Storage.setVisible(false); // Initially hidden, only shown when Flat or Office is selected
        add(panel_Storage);
        setComponentZOrder(panel_Storage, 0);
       
		
		spinnerParking.addChangeListener(e -> {
		    PropertyTypeOption currentType = (PropertyTypeOption) comboPropertyType.getSelectedItem();

		    if (currentType != null && (currentType.getValue().equals("Flat") || currentType.getValue().equals("Office"))) {
		        int count = (Integer) spinnerParking.getValue();

		        panel_Parking.removeAll();
		        panel_Parking.setBounds(40, 356, 570, 90);
		        panel_Parking.setVisible(true);
		        parkingForms.clear();

		        for (int i = 0; i < count; i++) {
		            JPanel miniForm = createParking(i + 1);
		            miniForm.setBounds(i * 143, 0, 140, 90); // <------ AQUÍ ES
		            panel_Parking.add(miniForm);
		        }

		        panel_Parking.revalidate();
		        panel_Parking.repaint();
		    } else {
		        // Oculta los formularios si no corresponde
		        panel_Parking.setVisible(false); // AÑADIR ESTOS PARA OTROS TIPOS DE PROPIEDADES
		        parkingForms.clear();
		        panel_Parking.removeAll();
		        panel_Parking.repaint();
		    }
		});
        
        spinnerStorage.addChangeListener(e -> {
			PropertyTypeOption currentType = (PropertyTypeOption) comboPropertyType.getSelectedItem();

			if (currentType != null && (currentType.getValue().equals("Flat") || currentType.getValue().equals("Office"))) {
				int count = (Integer) spinnerStorage.getValue();

				panel_Storage.removeAll();
				panel_Storage.setBounds(40, 486, 578, 90);
				panel_Storage.setVisible(true);
				storageForms.clear();

				for (int i = 0; i < count; i++) {
					JPanel miniForm = createStorage(i + 1);
					miniForm.setBounds(i * 233, 0, 230, 90); // <------ AQUÍ ES
					panel_Storage.add(miniForm);
				}

				panel_Storage.revalidate();
				panel_Storage.repaint();
			} else {
				// Oculta los formularios si no corresponde
				panel_Storage.setVisible(false); // AÑADIR ESTOS PARA OTROS TIPOS DE PROPIEDADES
				storageForms.clear();
				panel_Storage.removeAll();
				panel_Storage.repaint();
			}
		});
		
		
	} // END OF  PropertiesPanel constructor
	
	// ---------------------- //
	
	// --- AUXILIARY METHODS --- //
	// This section contains methods to create parking and storage panels, and to retrieve data from the forms. 
	// Also methods to clean fields and log data for debugging.
	
	public JPanel createParking(int parkingQty) {
	    JPanel panelParking = new JPanel();
	    panelParking.setBorder(BorderFactory.createTitledBorder("Estacionamiento #" + parkingQty)); // SPANISH for "Parking #"
	    panelParking.setBackground(Color.LIGHT_GRAY);
	    panelParking.setLayout(null);
	    
	    JComboBox<String> tipoCombo = new JComboBox<>(new String[]{"Cubierto", "Descubierto"}); // SPANISH for "Covered", "Uncovered"
	    tipoCombo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
	    tipoCombo.setBounds(50, 28, 116, 30);
	   // panelParking.add(tipoCombo);
	    
	    txtPrkngRol = new JTextField(10);
	    txtPrkngRol.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
	    txtPrkngRol.setBounds(65, 20, 60, 30);
	    panelParking.add(txtPrkngRol);
	    
	    txtPrkngNum = new JTextField(10);
	    txtPrkngNum.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
	    txtPrkngNum.setBounds(65, 50, 60, 30);
	    panelParking.add(txtPrkngNum);

	    JLabel lblPrkngType = new JLabel("Tipo:");
	    lblPrkngType.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblPrkngType.setBounds(20, 28, 116, 16);
	   // panelParking.add(lblPrkngType);
	   
	    JLabel lblPrkngRol = new JLabel("ROL:");
	    lblPrkngRol.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblPrkngRol.setBounds(20, 25, 116, 16);
	    panelParking.add(lblPrkngRol);
	    
	    JLabel lblPrkngNum = new JLabel("Nº:");
	    lblPrkngNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblPrkngNum.setBounds(20, 55, 116, 16);
	    panelParking.add(lblPrkngNum);
	    
	    
	    ParkingForm form = new ParkingForm(txtPrkngRol, tipoCombo, txtPrkngNum);
	    parkingForms.add(form); // ✅ agregamos el formulario a la lista
	    
	    return panelParking;
	}
	
	public JPanel createStorage(int storageQty) {
		JPanel panelStorage = new JPanel();
		panelStorage.setBorder(BorderFactory.createTitledBorder("Bodega #" + storageQty));
		panelStorage.setBackground(Color.LIGHT_GRAY);
		panelStorage.setLayout(null);
		
		txtStorageRol = new JTextField(10);
		txtStorageRol.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		txtStorageRol.setBounds(55, 20, 160, 30);
		txtStorageRol.setColumns(10);
		panelStorage.add(txtStorageRol);
		
		txtStorageNum = new JTextField(10);
		txtStorageNum.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		txtStorageNum.setBounds(50, 50, 45, 30);
		txtStorageNum.setColumns(10);
		panelStorage.add(txtStorageNum);
		
		txtStorageSize = new JTextField(10);
		txtStorageSize.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		txtStorageSize.setBounds(170, 50, 45, 30);
		txtStorageSize.setColumns(10);
		panelStorage.add(txtStorageSize);
		((AbstractDocument) txtStorageSize.getDocument()).setDocumentFilter(new NumberDocumentFilter(2));
		
		JLabel lblStorageRol = new JLabel("ROL:");
		lblStorageRol.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblStorageRol.setBounds(20, 25, 116, 16);
		panelStorage.add(lblStorageRol);
		
		JLabel lblStorageNum = new JLabel("Nº:");
		lblStorageNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblStorageNum.setBounds(20, 55, 116, 16);
		panelStorage.add(lblStorageNum);
		
		JLabel lblStorageSize = new JLabel("Superficie m²:");
		lblStorageSize.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		lblStorageSize.setBounds(97, 55, 126, 18);
		panelStorage.add(lblStorageSize);
		
		
		StorageForm form = new StorageForm(txtStorageRol, txtStorageNum, txtStorageSize);
		storageForms.add(form); // ✅ agregamos el formulario a la lista
		return panelStorage;
	}
	
	public List<Parking> getAllParkingsFromForm() {
	    List<Parking> parkings = new ArrayList<>();
	    boolean inCondo = chckbxInCondo.isSelected();
	    Integer condoId = comboCondo.getSelectedItem() instanceof CondoOption
	        ? ((CondoOption) comboCondo.getSelectedItem()).getId()
	        : null;

	    for (ParkingForm form : parkingForms) {
	        String rol = form.getRol();
	        String num = form.getNum(); // Si necesitas usarlo
	     //   String tipo = form.getTipo();   // Si lo quieres guardar

	        if (rol != null && !rol.isEmpty()) {
	        	//THIS GOES TO THE PARKING TABLE, AND THEN TO THE DAO (DB)
	            Parking p = new Parking();
	            p.setLandlordId(comboLandlord.getSelectedItem() instanceof LandlordOption
	            		? ((LandlordOption) comboLandlord.getSelectedItem()).getId()
	            		: null);
	            p.setRolSII(rol);
	            p.setPropertyTypeId(4);
	            p.setStreetName(txtAddress.getText());
	            p.setNum1(txtNum1.getText());
	            p.setNum2(num);
				p.setTownId(comboTown.getSelectedItem() instanceof TownOption
						? ((TownOption) comboTown.getSelectedItem()).getId()
						: null);
				p.setRegionId(RegionDAO.getRegionIDByName((String) comboRegion.getSelectedItem()));
	            p.setInCondo(inCondo);
	            p.setCondoId(inCondo ? condoId : null);
	            p.setFlatId(null);
	            // Puedes guardar el tipo o número si tu modelo Parking lo permite
	            parkings.add(p);
	        }
	    }

	    return parkings;
	}

	public List<Storage> getAllStoragesFromForm() {
		List<Storage> storages = new ArrayList<>();
		boolean inCondo = chckbxInCondo.isSelected();
		Integer condoId = comboCondo.getSelectedItem() instanceof CondoOption
				? ((CondoOption) comboCondo.getSelectedItem()).getId()
				: null;
		
		for (StorageForm form : storageForms) {
			String rol = form.getRol();
			String num = form.getNum();
			String sizeTextStorage = form.getSize(); // Get the size from the text field, or 0 if empty
			
			if (rol != null && !rol.isEmpty()) {
				
				int size; // Initialize size
				size = Integer.parseInt(sizeTextStorage.trim());
	    					
				// Ensure rol is not empty and size is greater than 0
				//THIS GOES TO THE STORAGE TABLE, AND THEN TO THE DAO (DB)
				Storage s = new Storage();
				s.setLandlordId(comboLandlord.getSelectedItem() instanceof LandlordOption
	            		? ((LandlordOption) comboLandlord.getSelectedItem()).getId()
	            		: null);
	            s.setRolSII(rol);
	            s.setPropertyTypeId(3);
	            s.setStreetName(txtAddress.getText());
	            s.setNum1(txtNum1.getText());
	            s.setNum2(num);
	            s.setSize(size);
				s.setTownId(comboTown.getSelectedItem() instanceof TownOption
						? ((TownOption) comboTown.getSelectedItem()).getId()
						: null);
				s.setRegionId(RegionDAO.getRegionIDByName((String) comboRegion.getSelectedItem()));
	            s.setInCondo(inCondo);
	            s.setCondoId(inCondo ? condoId : null);
	            s.setFlatId(null);
	            // Puedes guardar el tipo o número si tu modelo Parking lo permite
	            storages.add(s);
			}
		}
		
		return storages;
	}
	
	private void logHouseDetails() {
		System.out.println("Rol: " + txtRol.getText());
		System.out.println("Dirección: " + txtAddress.getText());
		System.out.println("Número 1: " + txtNum1.getText());
		System.out.println("Número 2: " + txtNum2.getText());
		System.out.println("Región: " + comboRegion.getSelectedItem());
		System.out.println("Comuna: " + comboTown.getSelectedItem());
		System.out.println("Tipo de propiedad: " + comboPropertyType.getSelectedItem());
		System.out.println("En Condominio: " + chckbxInCondo.isSelected());
		System.out.println("Condominio: " + comboCondo.getSelectedItem());
		System.out.println("Habitaciones: " + spinnerRoom.getValue());
		System.out.println("Baños: " + spinnerBath.getValue());
		System.out.println("Piso: " + spinnerFloor.getValue());
		System.out.println("Estacionamientos: " + spinnerParking.getValue());
		System.out.println("Bodega: " + spinnerStorage.getValue());
		System.out.println("Jardín: " + chckbxGarden.isSelected());
		System.out.println("Patio: " + chckbxPatio.isSelected());
		System.out.println("Piscina: " + chckbxPool.isSelected());
		System.out.println("BBQ: " + chckbxBBQ.isSelected());
		System.out.println("Terraza: " + chckbxTerrace.isSelected());
		System.out.println("Lavandería: " + chckbxLaundry.isSelected());
		System.out.println("Balcón: " + chckbxBalcony.isSelected());
	}
	
	public void logFlatDetails() {
		 System.out.println("Rol: " + txtRol.getText());
		 System.out.println("Tamaño: " + txtSize.getText());
		 System.out.println("Dirección: " + txtAddress.getText());
		 System.out.println("Número 1: " + txtNum1.getText());
		 System.out.println("Número 2: " + txtNum2.getText());
		 System.out.println("Región: " + comboRegion.getSelectedItem());
		 System.out.println("Comuna: " + comboTown.getSelectedItem());
		 System.out.println("Tipo de propiedad: " + comboPropertyType.getSelectedItem());
		 System.out.println("En Condominio: " + chckbxInCondo.isSelected());
		 System.out.println("Condominio: " + comboCondo.getSelectedItem());
		 System.out.println("Habitaciones: " + spinnerRoom.getValue());
		 System.out.println("Baños: " + spinnerBath.getValue());
		 System.out.println("Piso: " + txtBldngFloor.getText());
		 System.out.println("Bodega: " + spinnerStorage.getValue());
		 System.out.println("Estacionamientos: " + spinnerParking.getValue());
		 System.out.println("Balcony: " + chckbxBalcony.isSelected());
		 System.out.println("BldngLift: " + chckbxBldngLift.isSelected());
		 System.out.println("BldngPool: " + chckbxBldngPool.isSelected());     
		 System.out.println("BldngBBQ: " + chckbxBldngBBQ.isSelected());
		 System.out.println("BldngGym: " + chckbxBldngGym.isSelected());
	     System.out.println("BldngLaundry: " + chckbxBldngLaundry.isSelected());
	}
	
	
	
	
	private void cleanFields() {
//		panel_Parking.setVisible(false); // Hide parking panel
//		parkingForms.clear(); // Clear parking forms
//		panel_Parking.removeAll(); // Remove all components from parking panel
//		panel_Parking.repaint(); // Repaint parking panel
//		panel_Storage.setVisible(false); // Hide parking panel
//		storageForms.clear(); // Clear parking forms
//		panel_Storage.removeAll(); // Remove all components from parking panel
//		panel_Storage.repaint(); // Repaint parking panel
		//comboLandlord.setSelectedIndex(0); // Reset landlord selection
		//comboPropertyType.setSelectedIndex(0); // Reset property type selection
		//comboRegion.setSelectedIndex(0); // Reset region selection
		//comboTown.removeAllItems(); // Clear towns
		//comboTown.addItem("Seleccione una Comuna"); // SPANISH for "Select a Town"
		//txtAddress.setText(""); // Clear address text field
		//txtNum1.setText(""); // Clear address number 1 text field
		//txtNum2.setText(""); // Clear address number 2 text field
		
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
		
		chckbxBldngLift.setVisible(false); // Hide building lift checkbox
		chckbxBldngPool.setVisible(false); // Hide building pool checkbox
		chckbxBldngBBQ.setVisible(false); // Hide building BBQ checkbox
		chckbxBldngGym.setVisible(false); // Hide building gym checkbox
		chckbxBldngLaundry.setVisible(false); // Hide building laundry room checkbox
		txtBldngFloor.setVisible(false); // Hide building floor text field
		//txtRol.setText(""); // Clear ROL text field
		//txtSize.setText(""); // Clear size text field
		
	}
	
	public void isHouse() {
		panel_Parking.setVisible(false); // AÑADIR ESTOS PARA OTROS TIPOS DE PROPIEDADES
	    parkingForms.clear();
	    panel_Parking.removeAll();
	    panel_Parking.repaint();
	    panel_Storage.setVisible(false); // Hide parking panel
		storageForms.clear(); // Clear parking forms
		panel_Storage.removeAll(); // Remove all components from parking panel
		panel_Storage.repaint(); // Repaint parking panel
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
		lblCabinQty.setVisible(false); // Hide cabin quantity label for houses
		lblMtngRoomQty.setVisible(false); // Hide meeting room quantity label for houses
		
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
		spinnerCabin.setVisible(false); // Hide spinner for cabin quantity for houses
		spinnerMtngRoom.setVisible(false); // Hide spinner for meeting room quantity for houses
		
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
		lblAddressNum2.setText("Depto:"); // SPANISH for "Apartment Address"
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
		lblCabinQty.setVisible(false); // Hide cabin quantity label for flats
		lblMtngRoomQty.setVisible(false); // Hide meeting room quantity label for flats
		
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
		spinnerCabin.setVisible(false); // Hide spinner for cabin quantity for flats
		spinnerMtngRoom.setVisible(false); // Hide spinner for meeting room quantity for flats
		
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
		panel_Parking.setVisible(false); // AÑADIR ESTOS PARA OTROS TIPOS DE PROPIEDADES
	    parkingForms.clear();
	    panel_Parking.removeAll();
	    panel_Parking.repaint();
	    panel_Storage.setVisible(false); // Hide parking panel
		storageForms.clear(); // Clear parking forms
		panel_Storage.removeAll(); // Remove all components from parking panel
		panel_Storage.repaint(); // Repaint parking panel
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
		lblCabinQty.setVisible(false); // Hide cabin quantity label for storage
		lblMtngRoomQty.setVisible(false); // Hide meeting room quantity label for storage
		
		lblInCondo.setVisible(true); // Show "In Condominium" label for storage
		
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
        spinnerCabin.setVisible(false); // Hide spinner for cabin quantity for storage
		spinnerMtngRoom.setVisible(false); // Hide spinner for meeting room quantity for storage
		
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
		panel_Parking.setVisible(false); // AÑADIR ESTOS PARA OTROS TIPOS DE PROPIEDADES
	    parkingForms.clear();
	    panel_Parking.removeAll();
	    panel_Parking.repaint();
	    panel_Storage.setVisible(false); // Hide parking panel
		storageForms.clear(); // Clear parking forms
		panel_Storage.removeAll(); // Remove all components from parking panel
		panel_Storage.repaint(); // Repaint parking panel
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
		lblCabinQty.setVisible(false); // Hide cabin quantity label for parking
		lblMtngRoomQty.setVisible(false); // Hide meeting room quantity label for parking
		
		lblInCondo.setVisible(true); // Show "In Condominium" label for parking
		
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
		spinnerCabin.setVisible(false); // Hide spinner for cabin quantity for parking
		spinnerMtngRoom.setVisible(false); // Hide spinner for meeting room quantity for parking
		
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
		panel_Parking.setVisible(false); // AÑADIR ESTOS PARA OTROS TIPOS DE PROPIEDADES
	    parkingForms.clear();
	    panel_Parking.removeAll();
	    panel_Parking.repaint();
	    panel_Storage.setVisible(false); // Hide parking panel
		storageForms.clear(); // Clear parking forms
		panel_Storage.removeAll(); // Remove all components from parking panel
		panel_Storage.repaint(); // Repaint parking panel
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
		lblCabinQty.setVisible(false); // Hide cabin quantity label for land
		lblMtngRoomQty.setVisible(false); // Hide meeting room quantity label for land
		
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
		spinnerCabin.setVisible(false); // Hide spinner for cabin quantity for land
		spinnerMtngRoom.setVisible(false); // Hide spinner for meeting room quantity for land
		
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
		panel_Parking.setVisible(false); // AÑADIR ESTOS PARA OTROS TIPOS DE PROPIEDADES
	    parkingForms.clear();
	    panel_Parking.removeAll();
	    panel_Parking.repaint();
	    panel_Storage.setVisible(false); // Hide parking panel
		storageForms.clear(); // Clear parking forms
		panel_Storage.removeAll(); // Remove all components from parking panel
		panel_Storage.repaint(); // Repaint parking panel
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
		
		lblInCondo.setVisible(true); // Show "In Condominium" label for office
		
		lblBuildingHasLift.setVisible(true); // Show building lift label for office
		lblBuildingHasPool.setVisible(false); // Hide building pool label for office
		lblBuildingHasBBQ.setVisible(false); // Hide building BBQ label for office
		lblBuildingHasGym.setVisible(false); // Hide building gym label for office
		lblBuildingHasLaundryRoom.setVisible(false); // Hide building laundry room label for office
		lblFloorFlat.setVisible(true); // Show floor label for office
		lblCabinQty.setVisible(true); // Show cabin quantity label for office
		lblMtngRoomQty.setVisible(true); // Show meeting room quantity label for office
		
		spinnerRoom.setVisible(false); // Hide spinner for room quantity for office
		spinnerBath.setVisible(true); // Show spinner for bath quantity for office
		spinnerFloor.setVisible(false); // Hide spinner for floor quantity for office
		spinnerParking.setVisible(true); // Show spinner for parking quantity for office
		spinnerStorage.setVisible(true); // Show spinner for storage quantity for office
		spinnerCabin.setVisible(true); // Show spinner for cabin quantity for office
		spinnerMtngRoom.setVisible(true); // Show spinner for meeting room quantity for office
		
		chckbxGarden.setVisible(false); // Hide checkbox for garden for office
		chckbxPatio.setVisible(false); // Hide checkbox for patio for office
		chckbxPool.setVisible(false); // Hide checkbox for pool for office
		chckbxBalcony.setVisible(false); // Hide checkbox for balcony for office
		chckbxBBQ.setVisible(false); // Hide checkbox for BBQ for office
		chckbxTerrace.setVisible(false); // Hide checkbox for terrace for office
		chckbxLaundry.setVisible(false); // Hide checkbox for laundry room for office
		
		chckbxBldngLift.setVisible(true); // Show checkbox for building lift for office
		chckbxBldngPool.setVisible(false); // Hide checkbox for building pool for office
		chckbxBldngBBQ.setVisible(false); // Hide checkbox for building BBQ for office
		chckbxBldngGym.setVisible(false); // Hide checkbox for building gym for office
		chckbxBldngLaundry.setVisible(false); // Hide checkbox for building laundry room for office
		
		
		
	}
}
