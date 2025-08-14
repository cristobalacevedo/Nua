package view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;

import dao.PropertyDAO;
import dao.RegionDAO;
import dao.TownDAO;
import utils.PropertyOption;
import utils.PropertyTypeOption;
import utils.RUTDocumentFilter;
import utils.NumberDocumentFilter;
import utils.TenantOption;
import utils.TownOption;
import utils.UF;
import utils.ValueDocumentFilter;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;


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
		lblPropertyType.setBounds(80, 105, 96, 22);
		add(lblPropertyType);
		
		lblPropertiesSelect = new JLabel("Propiedad:");
		lblPropertiesSelect.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPropertiesSelect.setBounds(80, 148, 96, 22);
		add(lblPropertiesSelect);
		
		lblTenantSelect = new JLabel("Arrendatario:");
		lblTenantSelect.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblTenantSelect.setBounds(80, 191, 113, 22);
		add(lblTenantSelect);
		
		lblUFValue = new JLabel("Valor UF hoy: $" + UF.getUFValue());  // SPANISH for "Value UF" + today's value);
		lblUFValue.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblUFValue.setBounds(259, 250, 205, 23);
		add(lblUFValue);
		
		lblValue = new JLabel("Valor: "); // SPANISH for "Value: $"
		lblValue.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblValue.setBounds(90, 276, 103, 30);
		add(lblValue);
		
		lblCommission = new JLabel("Comisión: %"); // SPANISH for "Value: $"
		lblCommission.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblCommission.setBounds(90, 304, 105, 32);
		add(lblCommission);
		btnShowAA.setBackground(new Color(51, 153, 0));
		
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
		txtValue.setBounds(195, 276, 122, 29);
		txtValue.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		add(txtValue);
		txtValue.setColumns(10);
		
		((AbstractDocument) txtValue.getDocument()).setDocumentFilter(new ValueDocumentFilter(11));
//		String textValue = txtValue.getText();
//		textValue = textValue.replace(".", "");  // elimina los puntos
//		int value = Integer.parseInt(textValue);
		
		txtCommission = new JTextField();
		txtCommission.setColumns(10);
		txtCommission.setBounds(195, 305, 37, 29);
		txtCommission.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 20));
		add(txtCommission);
		
		((AbstractDocument) txtCommission.getDocument()).setDocumentFilter(new NumberDocumentFilter(2)); // Limita a 3 dígitos
		
//		String textCommission = txtValue.getText();
//		textCommission = textCommission.replace(".", "");  // elimina los puntos
//		int commission = Integer.parseInt(textCommission);
		
		txtNote = new JTextField();
		txtNote.setBounds(194, 390, 215, 30);
		txtNote.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 20));
		add(txtNote);
		txtNote.setColumns(10);
		
		// --- END TEXT FIELDS --- //
		
		
		// --- COMBO BOXES ---
		
		comboType = new JComboBox<>();
		comboType.setBounds(194, 100, 220, 32);
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
		comboProperty.setBounds(194, 143, 530, 32);
		comboProperty.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboProperty.setEditable(false);
		add(comboProperty);
		String defaultProperty = "Seleccione Tipo de Propiedad";
		comboProperty.addItem(new PropertyOption(defaultProperty, ""));
		
		comboType.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        PropertyTypeOption selectedType = (PropertyTypeOption) comboType.getSelectedItem();

		        if (selectedType != null) {
		            String propertyType = selectedType.getValue(); // valor en inglés

		            if (!propertyType.isEmpty()) {
		                int typeID = PropertyDAO.getTypeIDByName(propertyType);
		                System.out.println("DEBUG - Nombre que mando: " + propertyType);
		                System.out.println("DEBUG - ID que devuelve: " + typeID);

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
		comboTenant.setBounds(194, 186, 530, 32);
		add(comboTenant);
		
		// --- RADIO BUTTONS --- //
		
		rdbtnPesos = new JRadioButton("Pesos ($)");
		rdbtnPesos.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		rdbtnPesos.setBounds(195, 224, 110, 23);
		add(rdbtnPesos);
		
		rdbtnUF = new JRadioButton("UF");
		rdbtnUF.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		rdbtnUF.setBounds(195, 250, 58, 23);
		add(rdbtnUF);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnPesos);
		group.add(rdbtnUF);
		
		chckbxParking = new JCheckBox("Con Estacionamiento/s");
		chckbxParking.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxParking.setBounds(750, 143, 199, 23);
		add(chckbxParking);
		
		chckbxStorage = new JCheckBox("Con Bodega/s");
		chckbxStorage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		chckbxStorage.setBounds(961, 143, 139, 23);
		add(chckbxStorage);
		

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
				System.out.println("DEBUG - Value Selected: Pesos ($)");
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
}