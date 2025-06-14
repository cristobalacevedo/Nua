package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;

import controller.CondoController;
import controller.DoormanController;
import dao.LandlordDAO;
import dao.RegionDAO;
import dao.TownDAO;
import dao.CondoDAO;
import dao.DoormanDAO;
import model.Condo;
import model.Doorman;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;

import utils.CondoOption;
import utils.RUTDocumentFilter;
import utils.RUTValidator;
import utils.TownOption;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;

public class CondoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel CondoFrame;
	private JTextField txtNameCondo;
	private JTextField txtAddress;
	private JComboBox<String> comboRegion;
	private JComboBox<TownOption> comboTown;
	private JComboBox<String> comboPlatform;
	private JComboBox<CondoOption> comboCondo;
	
	private JLabel lblTitle;
	private JLabel lblCondoName;
	private JLabel lblRegion;
	private JLabel lblTown;
	private JLabel lblAddress;
	private JLabel lblCondoEmail;
	private JLabel lblPlatform;
	
	private JLabel lblRut;
	private JLabel lblDoormanPhone;
	private JLabel lblDoormanName;
	private JLabel lblSurname;
	private JLabel lblDoormanEmail;
	private JLabel lblCondo;
	private Doorman actualDoorman;
	
	private JButton btnSaveCondo;
	private JButton btnSaveDoorman;
	private JButton btnExit;
	private JButton btnUpdateCondo;
	private JButton btnUpdateDoorman;
	
	private JTextField txtCondoEmail;
	private JTextField txtNum;
	private JLabel lblNum;
	private JTextField txtRut;
	private JTextField txtDoormanName;
	private JTextField txtSurname;
	private JTextField txtDoormanEmail;
	private JTextField txtPhone;
	private JTextField txtCondoPhone;

	public CondoFrame() {
		setTitle("NUA - Condominios y Conserjes"); // SPANISH for "Condominiums and Concierges"
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 530);
		setName("CondoFrame"); // Set the name of the frame to "CondoFrame"
		CondoFrame = new JPanel();
		CondoFrame.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(CondoFrame);
		CondoFrame.setLayout(null);
		
		lblTitle = new JLabel("Creación de Condominios y Conserjes");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblTitle.setBounds(211, 11, 488, 38);
		CondoFrame.add(lblTitle);
		
		lblCondoName = new JLabel("Nombre: ");
		lblCondoName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCondoName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblCondoName.setBounds(50, 96, 85, 25);
		CondoFrame.add(lblCondoName);
		
	    lblRegion = new JLabel("Región: ");
	    lblRegion.setHorizontalAlignment(SwingConstants.LEFT);
	    lblRegion.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblRegion.setBounds(50, 168, 85, 25);
	    CondoFrame.add(lblRegion);
	    
	    lblTown = new JLabel("Comuna: ");
	    lblTown.setHorizontalAlignment(SwingConstants.LEFT);
	    lblTown.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblTown.setBounds(50, 205, 85, 25);
	    CondoFrame.add(lblTown);
	   
	    lblAddress = new JLabel("Dirección: ");
	    lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblAddress.setBounds(50, 244, 98, 25);
		CondoFrame.add(lblAddress);
		
		lblNum = new JLabel("Nº");
		lblNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblNum.setBounds(325, 244, 30, 25);
		CondoFrame.add(lblNum);
		
		lblCondoEmail = new JLabel("e-mail:");
		lblCondoEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblCondoEmail.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblCondoEmail.setBounds(50, 283, 85, 25);
		CondoFrame.add(lblCondoEmail);
		
		// DOORMAN DATA
		
		lblRut = new JLabel("RUT:");
		lblRut.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRut.setBounds(502, 96, 43, 30);
		CondoFrame.add(lblRut);
		
		lblDoormanName= new JLabel("Nombre:");
		lblDoormanName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblDoormanName.setBounds(502, 137, 92, 30);
		CondoFrame.add(lblDoormanName);
		
		lblSurname = new JLabel("Apellido:");
		lblSurname.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSurname.setBounds(502, 177, 92, 30);
		CondoFrame.add(lblSurname);
		
		lblDoormanEmail = new JLabel("e-mail:");
		lblDoormanEmail.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblDoormanEmail.setBounds(502, 218, 92, 30);
		CondoFrame.add(lblDoormanEmail);
		
		lblDoormanPhone = new JLabel("Teléfono:");
		lblDoormanPhone.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblDoormanPhone.setBounds(502, 260, 92, 30);
		CondoFrame.add(lblDoormanPhone);
		
		lblCondo = new JLabel("Condominio:");
		lblCondo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblCondo.setBounds(502, 302, 110, 30);
		CondoFrame.add(lblCondo);
	    
		txtNameCondo = new JTextField();
		txtNameCondo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		txtNameCondo.setBounds(147, 95, 256, 30);
		CondoFrame.add(txtNameCondo);
		txtNameCondo.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		txtAddress.setBounds(147, 242, 174, 30);
		CondoFrame.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtCondoEmail = new JTextField();
		txtCondoEmail.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		txtCondoEmail.setColumns(10);
		txtCondoEmail.setBounds(147, 281, 256, 30);
		CondoFrame.add(txtCondoEmail);
		
		txtNum = new JTextField();
		txtNum.setBounds(352, 242, 51, 30);
		txtNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		CondoFrame.add(txtNum);
		txtNum.setColumns(10);
		
		txtRut = new JTextField();
		txtRut.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		txtRut.setColumns(10);
		txtRut.setBounds(579, 96, 270, 30);
		CondoFrame.add(txtRut);
		
		((AbstractDocument) txtRut.getDocument()).setDocumentFilter(new RUTDocumentFilter()); // Apply the custom document filter to the RUT field for a better user experience and validation experience
		
		txtRut.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        String text = txtRut.getText();

		        // Solo permitir números, puntos y guiones
		        if (!Character.isDigit(c) && c != '.' && c != '-' && c != 'k' && c != 'K') {
		            e.consume();
		            return;
		        }

		        // Limitar a 12 caracteres
		        if (text.length() >= 12) {
		            e.consume();
		        }
		    }
		});
		txtRut.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyReleased(KeyEvent e) {
		        String inputRut = txtRut.getText().toUpperCase();;
		        //String cleaned = RUTValidator.cleanRUT(input);

		        // No hacer nada si está vacío o es muy corto
		        if (inputRut.length() < 2) return;

		        // Aplicar formato en tiempo real
		        String formattedRut = RUTValidator.formatRUT(inputRut).toUpperCase();;

		        // Evitar bucle infinito al volver a escribir sobre el campo
		        if (!inputRut.equals(formattedRut)) {
		            txtRut.setText(formattedRut);
		            txtRut.setCaretPosition(txtRut.getText().length()); // Mover cursor al final
		        }

		        // Buscar solo si es válido
		        if (RUTValidator.isValid(formattedRut)) {
		            actualDoorman = DoormanDAO.getByRut(formattedRut); // usar RUT limpio para búsqueda

		            if (actualDoorman != null) {
		            	txtDoormanName.setText(actualDoorman.getName());
		                txtSurname.setText(actualDoorman.getSurname());
		                txtDoormanEmail.setText(actualDoorman.getEmail());
		                txtPhone.setText(actualDoorman.getPhone());
		                
//		             // Normalización defensiva por si hay errores de mayúsculas
//		                String bankName = actualLandlord.getBankName();
//		                String accountType = actualLandlord.getAccountType();
//		                String accountNum = actualLandlord.getAccountNum();
//
//		                // Banco
//		                if (bankName != null) {
//		                    for (int i = 0; i < comboBank.getItemCount(); i++) {
//		                        if (comboBank.getItemAt(i).equalsIgnoreCase(bankName)) {
//		                            comboBank.setSelectedIndex(i);
//		                            break;
//		                        }
//		                    }
//		                }
//
//		                // Tipo de cuenta
//		                if (accountType != null) {
//		                    for (int i = 0; i < comboType.getItemCount(); i++) {
//		                        if (comboType.getItemAt(i).equalsIgnoreCase(accountType)) {
//		                            comboType.setSelectedIndex(i);
//		                            break;
//		                        }
//		                    }
//		                }
//
//		                // Número de cuenta
//		                if (accountNum != null) {
//		                    txtNum.setText(accountNum);
//		                }
//		              
		                
		                //comboBank.setSelectedItem(actualLandlord.getBankName()); // Set selected bank
		                //comboType.setSelectedItem(actualLandlord.getAccountType()); // Set selected account type
		               // txtNum.setText(actualDoorman.getAccountNum()); // Set account number
		                showDoormanEditButton();
		                btnSaveDoorman.setVisible(false);
		                System.out.println("\nRUT Found	: " + actualDoorman.getRut());
		                System.out.println("Full name	: " + actualDoorman.getFullName());
		                System.out.println("Condominium	: " + actualDoorman.getCondoId());
		                
		            } else {
		                cleanDoormanFields();
		                hideDoormanEditButtons();
		                btnSaveDoorman.setVisible(true);
		                actualDoorman = null;
		                System.out.println("RUT Not Found: " + formattedRut);
		            }
		        } else {
		            cleanDoormanFields();
		            hideDoormanEditButtons();
		            btnSaveDoorman.setVisible(true);
		        }
		    }
		});
		
		txtRut.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String inputRut = txtRut.getText().trim();
		        String cleanedRut = RUTValidator.cleanRUT(inputRut);
				if (!cleanedRut.isEmpty() && cleanedRut.length() >= 8) {
					String formattedRut = RUTValidator.formatRUT(cleanedRut);
					txtRut.setText(formattedRut); // Format the RUT when focus is lost
				} 		   
			}
		});
		
		txtDoormanName = new JTextField();
		txtDoormanName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		txtDoormanName.setColumns(10);
		txtDoormanName.setBounds(579, 138, 270, 30);
		CondoFrame.add(txtDoormanName);
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtSurname.setColumns(10);
		txtSurname.setBounds(579, 177, 270, 30);
		CondoFrame.add(txtSurname);
		
		txtDoormanEmail = new JTextField();
		txtDoormanEmail.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtDoormanEmail.setColumns(10);
		txtDoormanEmail.setBounds(579, 218, 270, 30);
		CondoFrame.add(txtDoormanEmail);
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtPhone.setColumns(10);
		txtPhone.setBounds(579, 260, 270, 30);
		CondoFrame.add(txtPhone);
		
		comboRegion = new JComboBox<String>();
		comboRegion.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		comboRegion.setEditable(false);
		comboRegion.setBounds(147, 170, 256, 30);
		CondoFrame.add(comboRegion);
		
		String defaultRegion = "Seleccione una Región"; // SPANISH for "Select a Region"
		comboRegion.addItem(defaultRegion); // SPANISH for "Select a Region"
		List<String> regions = RegionDAO.getAllRegion(); // Fetch all regions from the database
		
		for (String region : regions) {
			comboRegion.addItem(region); // Add each region to the combo box
		}
		
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
		
		comboTown = new JComboBox<TownOption>();
		comboTown.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		comboTown.setEditable(false);
		comboTown.setBounds(147, 206, 256, 30);
		CondoFrame.add(comboTown);
		
		comboTown.addItem(new TownOption(0 , "Seleccione una Comuna")); // SPANISH for "Select a Town"
		
		
		
		comboCondo = new JComboBox<CondoOption>();
		String defaultCondo = "Seleccione un Condominio"; // SPANISH for "Select a Condominium"
		comboCondo.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		comboCondo.setEditable(false);
		comboCondo.setBounds(607, 303, 242, 30);
		CondoFrame.add(comboCondo);
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
		
			
		

		comboPlatform = new JComboBox<String>();
		comboPlatform.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		comboPlatform.setEditable(false);
		comboPlatform.setBounds(147, 134, 256, 30);
		CondoFrame.add(comboPlatform);
		
		String defaultPlatform = "Seleccione una Plataforma"; // SPANISH for "Select a Region"
		comboPlatform.addItem(defaultPlatform); // SPANISH for "Select a Region"
		// RegionDAO regionDAO = new RegionDAO(); // Create an instance of RegionDAO to fetch regions
		List<String> platforms = CondoDAO.getAllCondoPlatform(); // Fetch all regions from the database
		
		for (String platform : platforms) {
			comboPlatform.addItem(platform); // Add each region to the combo box
			System.out.println("Platform: " + platform); // For debugging purposes
		}
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(456, 84, 18, 311);
		CondoFrame.add(separator);
		
		btnSaveCondo = new JButton("Guardar");
		btnSaveCondo.setForeground(Color.WHITE);
		btnSaveCondo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 18));
		btnSaveCondo.setBackground(new Color(0, 153, 0));
		btnSaveCondo.setBounds(302, 316, 100, 50);
		CondoFrame.add(btnSaveCondo);
		
		btnSaveDoorman = new JButton("Guardar");
		btnSaveDoorman.setForeground(Color.WHITE);
		btnSaveDoorman.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 18));
		btnSaveDoorman.setBackground(new Color(0, 153, 0));
		btnSaveDoorman.setBounds(749, 345, 100, 50);
		btnSaveDoorman.setVisible(true); // Initially visible for saving a new doorman
		CondoFrame.add(btnSaveDoorman);
		
		btnExit = new JButton("Cerrar");
		btnExit.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		btnExit.setBounds(39, 424, 89, 30);
		CondoFrame.add(btnExit);
		
		btnUpdateCondo = new JButton("Actualizar");
		btnUpdateCondo.setForeground(Color.WHITE);
		btnUpdateCondo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 18));
		btnUpdateCondo.setBackground(new Color(51, 153, 255));
		btnUpdateCondo.setBounds(287, 316, 115, 50);
		btnUpdateCondo.setVisible(false); // Initially hidden until a condo is selected
		CondoFrame.add(btnUpdateCondo);
		
		btnUpdateDoorman = new JButton("Actualizar");
		btnUpdateDoorman.setForeground(Color.WHITE);
		btnUpdateDoorman.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 18));
		btnUpdateDoorman.setBackground(new Color(51, 153, 255));
		btnUpdateDoorman.setBounds(734, 345, 115, 50);
		btnUpdateDoorman.setVisible(false); // Initially hidden until a doorman is found
		CondoFrame.add(btnUpdateDoorman);
		
		lblPlatform = new JLabel("Plataforma:");
		lblPlatform.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPlatform.setBounds(50, 133, 110, 30);
		CondoFrame.add(lblPlatform);
		
		JLabel lblCondoPhone = new JLabel("Teléfono:");
		lblCondoPhone.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblCondoPhone.setBounds(50, 315, 92, 30);
		CondoFrame.add(lblCondoPhone);
		
		txtCondoPhone = new JTextField();
		txtCondoPhone.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtCondoPhone.setColumns(10);
		txtCondoPhone.setBounds(147, 315, 140, 30);
		CondoFrame.add(txtCondoPhone);
		
		btnSaveCondo.addActionListener(e -> {
		    String name = txtNameCondo.getText().trim();
		    String address = txtAddress.getText().trim();
		    String email = txtCondoEmail.getText().trim();
		    String phone = txtCondoPhone.getText().trim();
		    String num = txtNum.getText().trim();
		    String platformName = (String) comboPlatform.getSelectedItem();
		    String regionName = (String) comboRegion.getSelectedItem();
		    TownOption selectedTown = (TownOption) comboTown.getSelectedItem();

		    CondoController controller = new CondoController();
		    boolean success = controller.saveCondo(name, address, email, phone, num, platformName, regionName, selectedTown);

		    if (success) {
		        Popup.showSuccess("Condominio guardado exitosamente.");
		    } else {
		        System.out.println("Fallo al guardar el condominio.");
		    }
		});
		
		btnSaveDoorman.addActionListener(e ->{
			String name = txtDoormanName.getText().trim();
			String surname = txtSurname.getText().trim();
			String email = txtDoormanEmail.getText().trim();
			String phone = txtPhone.getText().trim();
			String rut = txtRut.getText().trim();
			CondoOption selectedCondo = (CondoOption) comboCondo.getSelectedItem();
			
	        //System.out.println("Selected Condo ID: " + condoID);
			DoormanController controller = new DoormanController();
			boolean success = controller.saveDoorman(rut, name, surname, email, phone, selectedCondo);
			
			if (success) {
				Popup.showSuccess("Conserje guardado exitosamente.");
				cleanDoormanFields(); // Clear fields after saving
				hideDoormanEditButtons(); // Hide update button after saving
			} else {
				System.out.println("Fallo al guardar el conserje.");
				Popup.show("Error al guardar el conserje", "error");
			}
		});
		
		
//		btnSaveCondo.addActionListener(e -> {
//			System.out.println("Saving Condo Details...");
//			String name = txtNameCondo.getText().trim();
//			String address_st = txtAddress.getText().trim();
//			String email = txtCondoEmail.getText().trim();
//			String phone = txtCondoPhone.getText().trim();
//			String num = txtNum.getText().trim();
//			String platformName = (String) comboPlatform.getSelectedItem();
//			String regionName = (String) comboRegion.getSelectedItem();
//			TownOption selectedTown = (TownOption) comboTown.getSelectedItem();
//			
//			if (name.isEmpty() 
//				|| address_st.isEmpty() 
//				|| regionName.equals("Seleccione una Región")
//				|| selectedTown.getId() == 0) {
//				System.out.println("Please fill all fields correctly.");
//				return; // Exit if any field is empty or invalid
//			}
//			
//			if (email.isEmpty()) {
//				email = null; // Default value if email is not provided
//			}
//			
//			if (num.isEmpty()) {
//				num = null; // Default value if num is not provided
//			}
//			
//			if (platformName.isEmpty()) {
//				platformName = null; // Default value if platform is not provided
//			}
//			
//			if (phone.isEmpty()) {
//				phone = null; // Default value if phone is not provided
//			}
//			
//			// If the platform is not selected, set it to null
//			Integer platformID = CondoDAO.getPlatformIDByName(platformName);
//			if (platformID != null && platformID == -1) {
//			    platformID = null;
//			}
//			
//			
//			int townID = selectedTown.getId(); // Get the ID of the selected town
//			int regionID = RegionDAO.getRegionIDByName(regionName); // Get the ID of the selected region
//			if (townID == 0 || regionID == -1) {
//				System.out.println("Invalid platform, town, or region selected.");
//				return; // Exit if any ID is invalid
//			}
//			// Create a new Condo object with the provided details
//			Condo newCondo = new Condo();
//			
//			newCondo.setName(name);
//			newCondo.setAddress(address_st);
//			newCondo.setEmail(email);
//			newCondo.setPhone(phone);
//			newCondo.setNum(num);
//			newCondo.setCondoPlatformId(platformID);
//			newCondo.setTownID(townID);
//			newCondo.setRegionID(regionID);
//			System.out.println("(PRESAVE) Condo Details: " + newCondo); // For debugging purposes
//			
//			boolean success = CondoDAO.insertFullCondo(newCondo); // Save the condo to the database
//			
//			if (success) {
//				Popup.showSuccess("Condominio guardado exitosamente.");
//			} else {
//				Popup.show("Error al guardar condominio", "error");
//			}
//		});
		
		
		
		
		btnExit.addActionListener(e -> {
			System.out.println("Closing CondoFrame on Button"); // For debugging purposes
			this.dispose(); // Close the current frame
		});
		
	}
	
	

	private void cleanDoormanFields() {
		txtNameCondo.setText("");
		txtAddress.setText("");
		txtCondoEmail.setText("");
		txtDoormanName.setText("");
		txtSurname.setText("");
		txtDoormanEmail.setText("");
		txtPhone.setText("");
		comboRegion.setSelectedIndex(0);
		comboTown.removeAllItems();
		comboTown.addItem(new TownOption(0, "Seleccione una Comuna")); // SPANISH for "Select a Town"
		//comboCondo.removeAllItems();
	}
	
	private void hideDoormanEditButtons() {
		btnUpdateDoorman.setVisible(false);
		btnSaveDoorman.setVisible(true);
	}
	
	private void showDoormanEditButton() {
		btnUpdateDoorman.setVisible(true);
		btnSaveDoorman.setVisible(false);
		
	}
}
