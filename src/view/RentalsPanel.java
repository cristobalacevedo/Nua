package view;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;

import dao.ParkingDAO;
import dao.PropertyDAO;
import dao.StorageDAO;
import model.Parking;
import model.ParkingForm;
import model.Storage;
import utils.PropertyOption;
import utils.PropertyTypeOption;
import utils.RUTDocumentFilter;
import utils.NumberDocumentFilter;
import utils.TenantOption;
import utils.UF;
import utils.ValueDocumentFilter;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.CalendarPanel;
import javax.swing.ImageIcon;
import com.privatejgoodies.forms.layout.FormLayout;


// This class represents the Rentals panel in the application.
public class RentalsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JButton btnShowAA = new JButton("Propiedades Disponibles");
	private JButton btnSave;
	private JLabel lblRentals;
	private JLabel lblPropertyType;
	private JLabel lblPropertiesSelect;
	private JLabel lblTenantSelect;
	private JLabel lblUFValue;
	private JLabel lblValue;
	private JLabel lblCommission;
	private JLabel lblStartDate;
	private JLabel lblEndDate;
	private JTextField txtValue;
	private JTextField txtCommission;
	private JTextField txtNote;
	private JComboBox<PropertyTypeOption> comboType;
	private JComboBox<PropertyOption> comboProperty;
	private JComboBox<TenantOption> comboTenant;
	private JRadioButton rdbtnPesos; // SPANISH for "Pesos ($)"
	private JRadioButton rdbtnUF;
	private JCheckBox chckbxStorage;
	private JCheckBox chckbxParking;
	private JPanel panel_Parking; // Panel for Parking options
	private JPanel panel_Storage; // Panel for Storage options
	private ButtonGroup groupCurrency; // Group for Radio Buttons 
	private DatePicker startDatePicker; // Date Picker for Start Date
	private DatePicker endDatePicker; // Date Picker for End Date

	// Constructor for RentalsPanel
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
		
		lblPropertyType = new JLabel("Tipo:"); // SPANISH for "Property Type:"
		lblPropertyType.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPropertyType.setBounds(382, 43, 96, 22);
		add(lblPropertyType);
		
		lblPropertiesSelect = new JLabel("Propiedad:");
		lblPropertiesSelect.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPropertiesSelect.setBounds(80, 111, 96, 22);
		add(lblPropertiesSelect);
		
		lblTenantSelect = new JLabel("Arrendatario:");
		lblTenantSelect.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblTenantSelect.setBounds(80, 154, 113, 22);
		add(lblTenantSelect);
		
		lblUFValue = new JLabel("Valor UF hoy: $" + UF.getUFValue());  // SPANISH for "Value UF" + today's value);
		lblUFValue.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblUFValue.setBounds(259, 218, 205, 23);
		add(lblUFValue);
		
		lblValue = new JLabel("Valor: "); // SPANISH for "Value: $"
		lblValue.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblValue.setBounds(80, 252, 103, 30);
		add(lblValue);
		
		lblCommission = new JLabel("Comisión: %"); // SPANISH for "Value: $"
		lblCommission.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblCommission.setBounds(80, 280, 105, 32);
		add(lblCommission);
		btnShowAA.setBackground(new Color(51, 153, 0));
		
		lblStartDate = new JLabel("Fecha Inicio:");
        lblStartDate.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
        lblStartDate.setBounds(454, 192, 103, 30);
        add(lblStartDate);
        
        lblEndDate = new JLabel("Fecha Fin:");
        lblEndDate.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
        lblEndDate.setBounds(454, 228, 103, 30);
        add(lblEndDate);
		
		// --- END LABELS --- //
		
		// --- BUTTONS --- //
	
		// Back Button
		
				
		// All Available Button
		btnShowAA.setBounds(80, 40, 225, 30);
		btnShowAA.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		add(btnShowAA);
		
		btnSave = new JButton("Crear Arriendo");
		btnSave.setBounds(300, 499, 187, 48);
		btnSave.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 22));
		btnSave.setBackground(new Color(0, 153, 0)); // Green background
		add(btnSave);
		
		btnBack.setBounds(50, 600, 100, 30);
		btnBack.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		add(btnBack);
		
		// --- END BUTTONS --- //
		
		// --- TEXT FIELDS --- //
		
		txtValue = new JTextField();
		txtValue.setBounds(193, 252, 122, 29);
		txtValue.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		add(txtValue);
		txtValue.setColumns(10);
		
		((AbstractDocument) txtValue.getDocument()).setDocumentFilter(new ValueDocumentFilter(11));
//		String textValue = txtValue.getText();
//		textValue = textValue.replace(".", "");  // elimina los puntos
//		int value = Integer.parseInt(textValue);
		
		txtCommission = new JTextField();
		txtCommission.setColumns(10);
		txtCommission.setBounds(193, 281, 37, 29);
		txtCommission.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		add(txtCommission);
		
		((AbstractDocument) txtCommission.getDocument()).setDocumentFilter(new NumberDocumentFilter(2)); // Limita a 3 dígitos
		
//		String textCommission = txtValue.getText();
//		textCommission = textCommission.replace(".", "");  // elimina los puntos
//		int commission = Integer.parseInt(textCommission);
		
		txtNote = new JTextField();
		txtNote.setBounds(195, 441, 215, 30);
		txtNote.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		add(txtNote);
		txtNote.setColumns(10);
		
		// --- END TEXT FIELDS --- //
		
		
		// --- COMBO BOXES ---
		
		comboType = new JComboBox<>();
		comboType.setBounds(443, 38, 220, 32);
		comboType.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboType.setEditable(false);
		add(comboType);
		
		String defaultType = "Seleccione un Tipo";
		
		comboType.addItem(new PropertyTypeOption(defaultType, ""));
		
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
		    comboType.addItem(new PropertyTypeOption(label, type));
		}
		
		
		comboProperty = new JComboBox<>();
		comboProperty.setBounds(194, 106, 530, 32);
		comboProperty.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboProperty.setEditable(false);
		add(comboProperty);
		String defaultProperty = "Seleccione Tipo de Propiedad";
		comboProperty.addItem(new PropertyOption(defaultProperty, ""));
		
		comboProperty.addItemListener(e -> {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
		        PropertyOption selected = (PropertyOption) comboProperty.getSelectedItem();
		        if (selected != null && !selected.getValue().isEmpty()) {
		        	System.out.println("DEBUG - Property selected: " + selected.getLabel() + " - ID: " + selected.getValue());
		            onPropertySelected(selected);
		        }
		    }
		});
		
		comboType.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PropertyTypeOption selectedType = (PropertyTypeOption) comboType.getSelectedItem();

		        if (selectedType != null) {
		            String propertyType = selectedType.getValue(); // valor en inglés

		            if (!propertyType.isEmpty()) {
		                int typeID = PropertyDAO.getTypeIDByName(propertyType);
		                System.out.println("DEBUG - Name sent: " + propertyType);
		                System.out.println("DEBUG - ID received: " + typeID);

		                comboProperty.removeAllItems();
		                comboProperty.addItem(new PropertyOption("Seleccione una Propiedad", ""));

		                List<PropertyOption> properties = PropertyDAO.getAllAvailableByTypeID(typeID);
		                
		                System.out.println("DEBUG - Propiedades encontradas: " + properties.size());

		                for (PropertyOption property : properties) {
		                	System.out.println("DEBUG - label: " + property.getLabel() + " - ID: " + property.getValue());
		                    comboProperty.addItem(property);
		                }
		            } else {
		                comboProperty.removeAllItems();
		                comboProperty.addItem(new PropertyOption("Seleccione una Propiedad", ""));
		            }
		        }
		    }
		});
		
		comboTenant = new JComboBox<TenantOption>();
		comboTenant.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboTenant.setEditable(false);
		comboTenant.setBounds(194, 149, 530, 32);
		add(comboTenant);
		
		// --- RADIO BUTTONS --- //
		
		rdbtnPesos = new JRadioButton("Pesos ($)");
		rdbtnPesos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		rdbtnPesos.setBounds(195, 192, 110, 23);
		add(rdbtnPesos);
		
		rdbtnUF = new JRadioButton("UF");
		rdbtnUF.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		rdbtnUF.setBounds(195, 218, 58, 23);
		add(rdbtnUF);
		
		groupCurrency = new ButtonGroup();
		groupCurrency.add(rdbtnPesos);
		groupCurrency.add(rdbtnUF);
		
		chckbxParking = new JCheckBox("Con Estacionamiento/s");
		chckbxParking.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxParking.setBounds(750, 111, 200, 23);
		add(chckbxParking);
		
		chckbxStorage = new JCheckBox("Con Bodega/s");
		chckbxStorage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxStorage.setBounds(1004, 111, 139, 23);
		add(chckbxStorage);
		
	    // --- END RADIO BUTTONS --- //
		
		
		// --- DATE PICKER --- //	
		
		startDatePicker = new DatePicker();
	        
		startDatePicker.getComponentToggleCalendarButton().setText("+");
		startDatePicker.getComponentToggleCalendarButton().setFont(new Font("Segoe UI", Font.BOLD, 12));
		startDatePicker.getComponentDateTextField().setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
		startDatePicker.setBounds(559, 192, 165, 30);
	    add(startDatePicker);
	        
	    startDatePicker.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	        
	    endDatePicker = new DatePicker();
	    FormLayout formLayout = (FormLayout) endDatePicker.getLayout();
	    endDatePicker.getComponentToggleCalendarButton().setText("+");
	    endDatePicker.getComponentToggleCalendarButton().setFont(new Font("Segoe UI", Font.BOLD, 12));
	    endDatePicker.getComponentDateTextField().setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 18));
	    endDatePicker.setBounds(560, 229, 164, 30);
	    add(endDatePicker);
	        
	    endDatePicker.getComponentToggleCalendarButton().addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	}
	    });
	
		panel_Parking = new JPanel();
		panel_Parking.setLayout(null); 
		//panelEstacionamientos.setOpaque(false);
		panel_Parking.setBounds(750, 141, 199, 192);
		panel_Parking.setBackground(new Color(187, 187, 187)); 
		panel_Parking.setVisible(false); // Initially hidden, only shown when Flat or Office is selected
		//panelEstacionamientos.setOpaque(true);  // para que el fondo blanco se aplique
		//panelEstacionamientos.setBorder(BorderFactory.createLineBorder(Color.RED));
		add(panel_Parking);
		setComponentZOrder(panel_Parking, 0);
		
		panel_Storage = new JPanel();
		panel_Storage.setLayout(null);
        panel_Storage.setBounds(1004, 141, 200, 192);
        panel_Storage.setBackground(new Color(187, 187, 187)); 
        panel_Storage.setVisible(false); // Initially hidden, only shown when Flat or Office is selected
        add(panel_Storage);
        setComponentZOrder(panel_Storage, 0);
        
       
		

		// ActionListener for Radio Buttons
		
		rdbtnPesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí puedes agregar la lógica que necesites cuando se seleccione "Pesos"
				lblValue.setText("Valor:        $"); // SPANISH for "Value: $"
				System.out.println("DEBUG - Value Selected: Pesos ($)");
			}
		});
		
		rdbtnUF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí puedes agregar la lógica que necesites cuando se seleccione "UF"
				lblValue.setText("Valor:      UF"); // SPANISH for "Value: UF"
				System.out.println("DEBUG - Value Selected: UF");
			}
		});
		// --- END RADIO BUTTONS --- //
		
		
		//List<String> properties = PropertyDAO.getAllTypes();
		
		
		
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
	
	private void drawStorages(List<Storage> storages) {
	    panel_Storage.removeAll();
	    panel_Storage.setVisible(!storages.isEmpty());

	    int i = 0;
	    for (Storage storage : storages) {
	        JPanel miniForm = getStorage(storage, i + 1);
	        miniForm.setBounds(i * 233, 0, 230, 90);
	        panel_Storage.add(miniForm);
	        i++;
	    }

	    panel_Storage.revalidate();
	    panel_Storage.repaint();
	}

	private void drawParkings(List<Parking> parkings) {
	    panel_Parking.removeAll();
	    panel_Parking.setVisible(!parkings.isEmpty());

	    int i = 0;
	    for (Parking parking : parkings) {
	        JPanel miniForm = getParking(parking, i + 1);
	        miniForm.setBounds(i * 233, 0, 230, 90);
	        panel_Parking.add(miniForm);
	        i++;
	    }

	    panel_Parking.revalidate();
	    panel_Parking.repaint();
	}
	
	private void onPropertySelected(PropertyOption propertyOption) {
	    // Aquí deberías tener un ID único del Flat
	    int flatId = propertyOption.getId(); 
	    System.out.println("DEBUG - Flat ID selected: " + flatId);
	    // Solo si es Flat (o si tu sistema también aplica para Office, etc.)
	    if ("Flat".equals(propertyOption.getValue())) {
	    	System.out.println("DEBUG - Loading Storages and Parkings for Flat ID: " + flatId);
	        List<Storage> storages = StorageDAO.getStoragesByFlatId(flatId);
	        System.out.println("DEBUG - Storages found: " + storages.size());
	        List<Parking> parkings = ParkingDAO.getParkingsByFlatId(flatId);
	        System.out.println("DEBUG - Parkings found: " + parkings.size());

	        drawStorages(storages);
	        drawParkings(parkings);
	    } else {
	        panel_Storage.setVisible(false);
	        panel_Parking.setVisible(false);
	    }
	}
	
	private JPanel getParking(Parking parking, int index) {
	    JPanel panelStorage = new JPanel();
	    panelStorage.setBorder(BorderFactory.createTitledBorder("Bodega #" + index));
	    panelStorage.setBackground(Color.LIGHT_GRAY);
	    panelStorage.setLayout(null);

	    JLabel lblRol = new JLabel("ROL: " + parking.getRolSII());
	    lblRol.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblRol.setBounds(20, 25, 200, 16);
	    panelStorage.add(lblRol);

	    JLabel lblNum = new JLabel("Nº: " + parking.getNum1());
	    lblNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblNum.setBounds(20, 45, 200, 16);
	    panelStorage.add(lblNum);

	    JLabel lblSize = new JLabel("Superficie m²: " + parking.getSize());
	    lblSize.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblSize.setBounds(20, 65, 200, 16);
	    panelStorage.add(lblSize);

	    return panelStorage;
	}
	
	private JPanel getStorage(Storage storage, int index) {
	    JPanel panelStorage = new JPanel();
	    panelStorage.setBorder(BorderFactory.createTitledBorder("Bodega #" + index));
	    panelStorage.setBackground(Color.LIGHT_GRAY);
	    panelStorage.setLayout(null);

	    JLabel lblRol = new JLabel("ROL: " + storage.getRolSII());
	    lblRol.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblRol.setBounds(20, 25, 200, 16);
	    panelStorage.add(lblRol);

	    JLabel lblNum = new JLabel("Nº: " + storage.getNum1());
	    lblNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblNum.setBounds(20, 45, 200, 16);
	    panelStorage.add(lblNum);

	    JLabel lblSize = new JLabel("Superficie m²: " + storage.getSize());
	    lblSize.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
	    lblSize.setBounds(20, 65, 200, 16);
	    panelStorage.add(lblSize);

	    return panelStorage;
	}
	
}