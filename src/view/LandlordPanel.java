package view;

import model.Landlord;
import utils.RUTValidator;
import utils.FieldValidator;
import utils.PhoneDocumentFilter;
import utils.RUTDocumentFilter;
import dao.BankDAO;
import dao.LandlordDAO;
import dao.PersonDAO;

import db.DBConnection;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;

import controller.LandlordController;

import javax.swing.JComboBox;
import javax.swing.JComponent;

// FALTA VALIDAR :
// CTRL V EN RUT, YA QUE IGUAL LLEGAN CARACTERES NO VÁLIDOS --- READY
// CAMPO NUMERO DE CUENTA VACÍO EN SAVE Y UPDATE, DEBE SER OBLIGATORIO --- READY
// CARACTERES NO VALIDOS EN TELEFONO, SOLO PERMITIRÁ EL "+" Y ESPACIOS, ADEMÁS DE NÚMEROS

// This class represents the Landlords Panel in the application.
public class LandlordPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JTextField txtRut;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtNum;
	private JLabel lblLandlords;;
	private JLabel lblPersonalData;
	private JLabel lblRut;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblBankData;
	private JLabel lblBank;
	private JLabel lblType;
	private JLabel lblNum;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnShowLL;
	private Landlord actualLandlord; // Holds the currently selected landlord for updates or deletions
	private JComboBox<String> comboBank;
	private JComboBox<String> comboType;
	

	
	public LandlordPanel(Container contentPane, Menu menu) {
		setBackground(new Color(187, 187, 187));
		setForeground(Color.BLACK);
		setName("LandlordPanel"); 
		setLayout(null);
		setBounds(0, 0, 1250, 700); 
		setVisible(true);
		DBConnection.getConnection();
		
		// --------------------- //
		
		// --- TEXT FIELDS --- //
		
		// -- RUT -- //
		
		txtRut = new JTextField();
		txtRut.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtRut.setBounds(302, 164, 270, 30);
		add(txtRut);
		txtRut.setColumns(10);
		
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
		            actualLandlord = LandlordDAO.getByRut2(formattedRut); // usar RUT limpio para búsqueda

		            if (actualLandlord != null) {
		                txtName.setText(actualLandlord.getName());
		                txtSurname.setText(actualLandlord.getSurname());
		                txtEmail.setText(actualLandlord.getEmail());
		                txtPhone.setText(actualLandlord.getPhone());
		                
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
		                
		                comboBank.setSelectedItem(actualLandlord.getBankName()); // Set selected bank
		                comboType.setSelectedItem(actualLandlord.getAccountType()); // Set selected account type
		                txtNum.setText(actualLandlord.getAccountNum()); // Set account number
		                showEditButton();
		                btnSave.setVisible(false);
		                System.out.println("\nRUT Found	: " + actualLandlord.getRut());
		                System.out.println("Bank		: " + actualLandlord.getBankName());
		                System.out.println("Account Type	: " + actualLandlord.getAccountType());
		                System.out.println("N°    		: " + actualLandlord.getAccountNum());
		            } else {
		                cleanFields();
		                hideEditButtons();
		                btnSave.setVisible(true);
		                actualLandlord = null;
		                System.out.println("RUT Not Found: " + formattedRut);
		            }
		        } else {
		            cleanFields();
		            hideEditButtons();
		            btnSave.setVisible(true);
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
		
		
	// OJO	
//		PlainDocument doc = (PlainDocument) txtRut.getDocument();
//		doc.setDocumentFilter(new RUTDocumentFilter());
//		
//		txtRut.getDocument().addDocumentListener(new DocumentListener() {
//		    public void insertUpdate(DocumentEvent e) {
//		        SwingUtilities.invokeLater(() -> searchByRut());
//		    }
//		    public void removeUpdate(DocumentEvent e) {
//		        SwingUtilities.invokeLater(() -> searchByRut());
//		    }
//		    public void changedUpdate(DocumentEvent e) {
//		        SwingUtilities.invokeLater(() -> searchByRut());
//		    }
//		});
		
		// -- END RUT -- //
		
		// -- NAME -- //
		
		txtName = new JTextField();
		txtName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtName.setBounds(302, 206, 270, 30);
		add(txtName);
		txtName.setColumns(10);
		
		// -- END NAME -- //
		
		// -- SURNAME -- //
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtSurname.setBounds(302, 245, 270, 30);
		add(txtSurname);
		txtSurname.setColumns(10);
		
		// -- END SURNAME -- //
		
		// -- EMAIL -- //
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtEmail.setBounds(302, 286, 270, 30);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		// -- END EMAIL -- //
		
		// -- PHONE -- //
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtPhone.setBounds(302, 328, 270, 30);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		((AbstractDocument) txtPhone.getDocument()).setDocumentFilter(new PhoneDocumentFilter()); // Apply the custom document filter to the RUT field for a better user experience and validation experience
		
		txtPhone.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        String text = txtPhone.getText();

		        // Only allow digits, spaces, and the '+' character
		        if (!Character.isDigit(c) && c != ' ' && c != '+') {
		            e.consume();
		            return;
		        }

		        // Limit to 15 characters
		        if (text.length() >= 15) {
		            e.consume();
		        }
		    }
		});
		
		// -- END PHONE -- //
		
		// -- BANK NUMBER -- //
	
		txtNum = new JTextField();
		txtNum.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtNum.setBounds(689, 249, 270, 30);
		add(txtNum);
		txtNum.setColumns(10);
		
		// -- END BANK NUMBER -- //
		
		// --- END TEXT FIELDS --- //
		
		// --------------------- //
		
		// --- LABELS --- //
		
		// -- LANDLORDS PANEL TITLE -- //
		
		lblLandlords = new JLabel("Panel de Propietarios"); // SPANISH for "Landlords Panel"");
		lblLandlords.setForeground(Color.GRAY);
		lblLandlords.setFont(new Font("Noto Sans JP", Font.PLAIN, 16));
		lblLandlords.setHorizontalAlignment(SwingConstants.CENTER);
		lblLandlords.setBounds(508, 6, 187, 16);
		add(lblLandlords);
		
		// -- PERSONAL DATA TITLE -- //
		
		lblPersonalData = new JLabel("Datos Personales"); // SPANISH for "Personal Data"
		lblPersonalData.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPersonalData.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonalData.setBounds(267, 113, 242, 39);
		add(lblPersonalData);
		
		// -- END PERSONAL DATA TITLE --
		
		// -- RUT --
		
		lblRut = new JLabel("RUT:"); // SPANISH for "RUT"
		lblRut.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRut.setBounds(211, 164, 43, 30);
		add(lblRut);
		
		// -- END RUT --
		
		// -- NAME --
		
		lblName = new JLabel("Nombre:"); // SPANISH for "Name"
		lblName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblName.setBounds(211, 205, 92, 30);
		add(lblName);
		
		// -- END NAME --
		
		// -- SURNAME --
		
		lblSurname = new JLabel("Apellido:"); // SPANISH for "Surname"
		lblSurname.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSurname.setBounds(211, 245, 92, 30);
		add(lblSurname);
		
		// -- END SURNAME --
		
		// -- EMAIL --
		
		lblEmail = new JLabel("e-mail:"); // SPANISH for "Email"
		lblEmail.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblEmail.setBounds(211, 286, 92, 30);
		add(lblEmail);
		
		// -- END EMAIL --
		
		// -- PHONE --
		
		lblPhone = new JLabel("Teléfono:"); // SPANISH for "Phone"
		lblPhone.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPhone.setBounds(211, 328, 92, 30);
		add(lblPhone);
		
		// -- END PHONE --
		
		// -- BANK DATA -- 
		
		lblBankData = new JLabel("Datos Bancarios"); // SPANISH for "Bank Data"
		lblBankData.setHorizontalAlignment(SwingConstants.CENTER);
		lblBankData.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblBankData.setBounds(670, 113, 242, 39);
		add(lblBankData);
		
		lblBank = new JLabel("Banco:"); // SPANISH for "Bank"
		lblBank.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblBank.setBounds(622, 164, 64, 30);
		add(lblBank);
		
		lblType = new JLabel("Tipo:"); // SPANISH for "Account Type"
		lblType.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblType.setBounds(622, 206, 77, 30);
		add(lblType);
		
		lblNum = new JLabel("Nº : "); // SPANISH for "Account Number"
		lblNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblNum.setBounds(622, 245, 64, 30);
		add(lblNum);
		
		// -- END BANK DATA -- //
		
		// --- END LABELS --- //
		
		// ---------------------
		
		// --- COMBO BOXES --- //
		
		// -- BANKS -- //
		
		comboBank = new JComboBox<String>();
		comboBank.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboBank.setBounds(689, 164, 270, 30);
		add(comboBank);
		
		comboBank.addItem("Seleccione Banco..."); // SPANISH for "Select..."
		
		BankDAO bankDAO = new BankDAO(); 
		List<String> bankNames = bankDAO.getAllBankNames();

		for (String name : bankNames) {
		    comboBank.addItem(name);
		}
		
		// -- END BANKS -- //
		
		// -- ACCOUNT TYPES -- //
		
		comboType = new JComboBox<String>();
		comboType.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboType.setBounds(689, 206, 270, 30);
		add(comboType);
		
		comboType.addItem("Seleccione Tipo de Cuenta..."); // SPANISH for "Select Account Type..."
		comboType.addItem("Cuenta Corriente");
		comboType.addItem("Cuenta Vista");
		comboType.addItem("Cuenta de Ahorro");		
		
		// -- END ACCOUNT TYPES -- //
		
		// --- END COMBO BOXES --- //
		
		// --------------------- //
		
		// --- SEPARATORS --- //
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(598, 164, 12, 233);
		add(separator);
		
		// --- END SEPARATORS --- //
		
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
		btnSave.setBounds(537, 404, 125, 58);
		add(btnSave);
		
		// -- END SAVE BUTTON -- //
		
		// -- DELETE BUTTON -- //
		
		btnDelete = new JButton("Eliminar"); // SPANISH for "Delete"
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		btnDelete.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 14));
		btnDelete.setBounds(287, 390, 92, 30);
		btnDelete.setVisible(false); // Initially hidden until a landlord is selected for deletion
		add(btnDelete);
		
		// -- END DELETE BUTTON -- //
		
		// -- UPDATE BUTTON -- //
		
		btnUpdate = new JButton("Actualizar"); // SPANISH for "Update"
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(51, 153, 255));
		btnUpdate.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		btnUpdate.setBounds(531, 404, 142, 58);
		btnUpdate.setVisible(false); // Initially hidden until a landlord is selected for update
		add(btnUpdate);
		
		// -- END UPDATE BUTTON -- //
		
		// -- SHOW LANDLORDS LIST BUTTON -- //
		
		btnShowLL = new JButton("Ver Listado");
		btnShowLL.setBounds(39, 43, 142, 45);
		btnShowLL.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 20));
		add(btnShowLL);
		
		// -- END SHOW LANDLORDS LIST BUTTON -- //
		
		// --- END BUTTONS --- //
		
		// --------------------- //
		
		// --- TABLE --- //		

//		String[] columnNames = {"RUT", "Nombre", "Apellido", "Teléfono", "Email", "Activo", "Arrienda"}; // SPANISH for "Active" and "Has Rentals"
//		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
//		
//		for (Landlord landlord : new LandlordDAO().getAll()) {
//			Object[] row = { landlord.getRut(), landlord.getName(), landlord.getSurname(), landlord.getPhone(), landlord.getEmail(),
//					landlord.getIsActive() ? "Sí" : "No", // SPANISH for "Yes" or "No"
//					landlord.getHasRentals() ? "Sí" : "No" // SPANISH for "Yes" or "No"
//			};
//			model.addRow(row);
//		}
//				
//		JTable tableLandlords = new JTable(model);
//		tableLandlords.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
//		tableLandlords.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		tableLandlords.setRowSelectionAllowed(true);
//		tableLandlords.setRowHeight(18); // Set row height to 30 pixels
//		tableLandlords.setColumnSelectionAllowed(true);
//		tableLandlords.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático
//
//		// Set custom cell renderer for the tabl
//		TableColumnModel columnModel = tableLandlords.getColumnModel();
//		columnModel.getColumn(0).setPreferredWidth(110);  // RUT -- SPANISH for "RUT"
//		columnModel.getColumn(1).setPreferredWidth(120);  // Nombre -- SPANISH for "Name"
//		columnModel.getColumn(2).setPreferredWidth(120);  // Apellido -- SPANISH for "Surname"
//		columnModel.getColumn(3).setPreferredWidth(120);  // Teléfono -- SPANISH for "Phone"
//		columnModel.getColumn(4).setPreferredWidth(260);  // Email -- SPANISH for "Email"
//		columnModel.getColumn(5).setPreferredWidth(55);   // Activo -- SPANISH for "Active"
//		columnModel.getColumn(6).setPreferredWidth(60);  // Arrienda -- SPANISH for "Has Rentals"
//
//		// Center the header text
//		JTableHeader header = tableLandlords.getTableHeader();
//		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
//		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//
//		// Center specific columns
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//		columnModel.getColumn(0).setCellRenderer(centerRenderer); // RUT
//		columnModel.getColumn(5).setCellRenderer(centerRenderer); // Activo
//		columnModel.getColumn(6).setCellRenderer(centerRenderer); // Tiene Arrendamientos
//
//		// Color alternating rows (striped effect like Excel)
//		tableLandlords.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//		    @Override
//		    public Component getTableCellRendererComponent(JTable table, Object value,
//		            boolean isSelected, boolean hasFocus, int row, int column) {
//
//		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//
//		        if (!isSelected) {
//		            c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240)); // rayado tipo Excel
//		        } else {
//		            c.setBackground(new Color(184, 207, 229)); // celeste de selección
//		        }
//
//		        return c;
//		    }
//		});
//		
//		// ScrollPane
//		JScrollPane scrollPane = new JScrollPane(tableLandlords);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(370, 163, 860, 233); 
//		add(scrollPane);
//		
//		
//		
	
		// --- END TABLE --- //
		
		// --------------------- //
		
		// --- BUTTON ACTIONS --- //
		
		// -- SAVE BUTTON ACTION LISTENER -- //
		
		btnSave.addActionListener(e -> {
		    Map<String, JComponent> fields = new LinkedHashMap<>();
		    fields.put("RUT", txtRut);
		    fields.put("Nombre", txtName);
		    fields.put("Apellido", txtSurname);
		    fields.put("Email", txtEmail);
		    fields.put("Teléfono", txtPhone);
		    fields.put("Banco", comboBank);
		    fields.put("Tipo de Cuenta", comboType);
		    fields.put("N° (Número de Cuenta)", txtNum);

		    if (!FieldValidator.validField(fields)) return;
		    
		    // Landlord data
		    String rut = txtRut.getText().trim();
		    String name = txtName.getText().trim();
		    String surname = txtSurname.getText().trim();
		    String email = txtEmail.getText().trim();
		    String phone = txtPhone.getText().trim();

		    // Bank and account data
		    String selectedBankName = comboBank.getSelectedItem().toString();
		    String accountType = comboType.getSelectedItem().toString();
		    String num = txtNum.getText().trim();

		    LandlordController controller = new LandlordController();

		    boolean success = controller.saveLandlord(
		        rut, name, surname, email, phone, selectedBankName, accountType, num);

		    if (success) {
		    	System.out.println("Arrendador guardado correctamente."); // SPANISH for "Landlord saved successfully"
		        Popup.showSuccess("Arrendador guardado correctamente."); // SPANISH for "Landlord saved successfully"
		        cleanFields();
		    } else {
		    	System.out.println("Error al guardar arrendador."); // SPANISH for "Error saving landlord"
		        Popup.show("Error al guardar arrendador.", "error"); // SPANISH for "Error saving landlord"
		    }
		});
		
//			public void actionPerformed(ActionEvent e) {
//
//				Map<String, JTextField> campos = new LinkedHashMap<>();
//		        campos.put("RUT", txtRut);
//		        campos.put("Nombre", txtName);
//		        campos.put("Apellido", txtSurname);
//		        campos.put("Email", txtEmail);
//		        campos.put("Teléfono", txtPhone);
//		       // campos.put("Banco", comboBank); // Added bank field validation
//		        campos.put("N° (Número de Cuenta)", txtNum); // Added account number field validation
//
//		        // Validación de campos vacíos
//		        if (!FieldValidator.validField(campos)) return;
//
//		        // Obtener valores
//		        String rut = txtRut.getText().trim();
//		        String name = txtName.getText().trim();
//		        String surname = txtSurname.getText().trim();
//		        String email = txtEmail.getText().trim();
//		        String phone = txtPhone.getText().trim();
//		        String num = txtNum.getText().trim();
//
//				// Clean and validate RUT
//				if (!RUTValidator.isValid(rut)) {
//				    Popup.show("RUT inválido. Verifica el formato y el dígito verificador.", "error"); // SPANISH for "Invalid RUT. Check the format and the verification digit."
//				    System.out.println("RUT inválido. Verifica el formato y el dígito verificador."); // SPANISH for "Invalid RUT. Check the format and the verification digit."
//				    return;
//				}
//				
//				// Verificar existencia en la base de datos
//				if (PersonDAO.rutExistsInDB(rut)) {
//				    Popup.show("El RUT ya está registrado en el sistema.", "error"); // SPANISH for "The RUT is already registered in the system.";
//				    return;
//				}
//				
//				if (comboBank.getSelectedIndex() == 0) {
//				    Popup.show("Campos obligatorios vacíos:\n - Banco.", "error"); // SPANISH for "You must select a valid bank."
//				    return;
//				}
//				
//				if (comboType.getSelectedIndex() == 0) {
//					Popup.show("Campos obligatorios vacíos:\n - Tipo de Cuenta.", "error"); // SPANISH for "You must select a valid account type."																	
//					return;
//				}
//			
//				
//				String selectedBankName = comboBank.getSelectedItem().toString();
//				int bankId = BankDAO.getIdByName(selectedBankName); // Asegúrate que este método exista
//				String accountType = comboType.getSelectedItem().toString();
//				String accountNum = txtNum.getText().trim();
//				
//				// Create a new Landlord object, defaulting to "landlord" type
//				Landlord landlord = new Landlord(
//					rut,
//					name,
//					surname,
//					email,
//					phone,
//				    "landlord", // Fixed type for Landlord
//					true,       // isActive
//					false,       // hasRentals
//					bankDAO.getBankByName(comboBank.getSelectedItem().toString()), // Get the selected bank name
//					comboType.getSelectedItem().toString(), // Get the selected account type
//					num // Get the account number
//				);
//
//				// Save the landlord using DAO
//				LandlordDAO dao = new LandlordDAO(null);
//				boolean saved = dao.save(landlord, bankId, accountType, accountNum);
//
//				if (saved) {
//					//ModFont.setOptionPaneFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 16)); // Update the title to reflect the current panel
//					Popup.showSuccess("Arrendador guardado correctamente."); // SPANISH for "Landlord saved successfully"
//					System.out.println("Arrendador guardado correctamente."); // SPANISH for "Landlord saved successfully"
//					cleanFields();
//				} else {
//					Popup.show("Error al guardar arrendador.", "error"); // SPANISH for "Error saving landlord"
//					System.out.println("Error al guardar arrendador."); // SPANISH for "Error saving landlord"
//				}
//			}
//		});

		// -- END SAVE BUTTON ACTION LISTENER -- //
		
		// -- UPDATE BUTTON ACTION LISTENER -- //
		
		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Reunir los campos
		        Map<String, JComponent> campos = new LinkedHashMap<>();
		        campos.put("RUT", txtRut);
		        campos.put("Nombre", txtName);
		        campos.put("Apellido", txtSurname);
		        campos.put("Email", txtEmail);
		        campos.put("Teléfono", txtPhone);
		        campos.put("Banco", comboBank);
		        campos.put("Tipo de Cuenta", comboType);
		        campos.put("N° (Número de Cuenta)", txtNum);

		        if (!FieldValidator.validField(campos)) return;

		        // Obtener valores
		        String rut = txtRut.getText().trim();
		        String name = txtName.getText().trim();
		        String surname = txtSurname.getText().trim();
		        String email = txtEmail.getText().trim();
		        String phone = txtPhone.getText().trim();
		        String accountNum = txtNum.getText().trim();
		        String selectedBankName = comboBank.getSelectedItem().toString();
		        String accountType = comboType.getSelectedItem().toString();

		        // Llamar al controlador
		        LandlordController controller = new LandlordController();
		        boolean updated = controller.updateLandlord(
		            rut, name, surname, email, phone, selectedBankName, accountType, accountNum
		        );

		        if (updated) {
		            Popup.showSuccess("Arrendador actualizado correctamente.");
		            cleanFields();
		            hideEditButtons();
		        } else {
		            Popup.show("Error al actualizar arrendador.", "error");
		        }
		    }
		});
		
		
//		btnUpdate.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//
//		        Map<String, JTextField> campos = new LinkedHashMap<>();
//		        campos.put("RUT", txtRut);
//		        campos.put("Nombre", txtName);
//		        campos.put("Apellido", txtSurname);
//		        campos.put("Email", txtEmail);
//		        campos.put("Teléfono", txtPhone);
//		        campos.put("N° (Número de Cuenta)", txtNum); // Added account number field validation
//
//		        // Validación de campos vacíos
//		        if (!FieldValidator.validField(campos)) return;
//
//		        // Obtener valores
//		        String rut = txtRut.getText().trim();
//		        String name = txtName.getText().trim();
//		        String surname = txtSurname.getText().trim();
//		        String email = txtEmail.getText().trim();
//		        String phone = txtPhone.getText().trim();
//
//		        // Validar RUT
//		        if (!RUTValidator.isValid(rut)) {
//		            Popup.show("RUT inválido. Verifica el formato y el dígito verificador.", "error"); // SPANISH for "Invalid RUT. Check the format and the verification digit."
//		            return;
//		        }
//
//		        // Verificar que el RUT sí exista (para poder actualizar)
//		        if (!PersonDAO.rutExistsInDB(rut)) {
//		            Popup.show("El RUT no existe en el sistema. No se puede actualizar.", "error"); // SPANISH for "The RUT does not exist in the system. Cannot update."
//		            return;
//		        }
//
//		        if (comboBank.getSelectedIndex() == 0) {
//				    Popup.show("Campos obligatorios vacíos:\n - Banco.", "error"); // SPANISH for "You must select a valid bank."
//				    return;
//				}
//				
//				if (comboType.getSelectedIndex() == 0) {
//					Popup.show("Campos obligatorios vacíos:\n - Tipo de Cuenta.", "error"); // SPANISH for "You must select a valid account type."																	
//					return;
//				}
//
//		        String selectedBankName = comboBank.getSelectedItem().toString();
//		        int bankId = BankDAO.getIdByName(selectedBankName);
//		        String accountType = comboType.getSelectedItem().toString();
//		        String accountNum = txtNum.getText().trim();
//
//		        // Crear objeto Landlord actualizado
//		        Landlord landlord = new Landlord(
//		            rut,
//		            name,
//		            surname,
//		            email,
//		            phone,
//		            "landlord",
//		            true,   // isActive (puedes adaptar según tu lógica)
//		            false,  // hasRentals (puedes adaptar si ya tiene arriendos)
//		            selectedBankName,
//		            accountType,
//		            accountNum
//		        );
//
//		        // Actualizar usando DAO
//		        LandlordDAO dao = new LandlordDAO(null);
//		        boolean updated = dao.update(landlord, bankId, accountType, accountNum);
//
//		        if (updated) {
//		        	Popup.showSuccess("Arrendador actualizado correctamente."); // SPANISH for "Landlord updated successfully"
//		            System.out.println("Landlord updated successfully."); 
//		            cleanFields();
//	                hideEditButtons();
//		        } else {
//		            Popup.show("Error al actualizar arrendador.", "error"); // SPANISH for "Error updating landlord"
//		            System.out.println("Error updating landlord.");
//		        }
//		    }
//		});
		
		// -- END UPDATE BUTTON ACTION LISTENER -- //
		
		// -- DELETE BUTTON ACTION LISTENER -- //	
		
		btnDelete.addActionListener(e -> {
		    String rut = txtRut.getText().trim();

		    LandlordController controller = new LandlordController();
		    boolean deleted = controller.deleteLandlord(rut);

		    if (deleted) {
		        Popup.showSuccess("Arrendador eliminado con éxito.");
		        cleanFields();
		    } else {
		        System.out.println("No se eliminó al arrendador (puede haber fallado o cancelado).");
		    }
		});


		
//		btnDelete.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        String rut = txtRut.getText().trim();
//
//		        if (rut.isEmpty()) {
//		            Popup.show("Debe ingresar el RUT del arrendador que desea eliminar.", "error");
//		            return;
//		        }
//
//		        if (!RUTValidator.isValid(rut)) {
//		            Popup.show("RUT inválido. Verifique el formato.", "error");
//		            return;
//		        }
//
//		        if (!PersonDAO.rutExistsInDB(rut)) {
//		            Popup.show("No existe un arrendador con ese RUT.", "error");
//		            return;
//		        }
//
//		        int confirm = PopupDialog.showYesNoWarn(null,
//		        	    "¿Está seguro que desea eliminar al arrendador con RUT: " + rut + "?",
//		        	    "Confirmar eliminación");
//
//		        if (confirm == JOptionPane.YES_OPTION) {
//		            LandlordDAO dao = new LandlordDAO(null);
//		            boolean deleted = dao.delete(rut);
//
//		            if (deleted) {
//		                Popup.showUserDeletedSuccess("Arrendador eliminado con éxito.");
//		                cleanFields();
//		            } else {
//		                Popup.show("Error al eliminar arrendador.", "error");
//		            }
//		        }
//		    }
//		});
//		
		// -- END DELETE BUTTON ACTION LISTENER -- //
		
		// -- BACK BUTTON ACTION LISTENER -- //
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Update the title to reflect the current panel
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
		
		// -- END BACK BUTTON ACTION LISTENER -- //
		
		// -- UPDATE BUTTON ACTION LISTENER -- //
		
		btnShowLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMiniFrame.show(new LandlordsList()); // Show the LandlordsList Frame when the button is clicked
			}
		});
		
		// -- END UPDATE BUTTON ACTION LISTENER -- //
		
		// --- END BUTTON ACTIONS --- //
		
	}
	
	// --------------------- //
	
	// --- AUXILIARY METHODS --- //
	
	// This method clears all text fields in the panel.
	private void cleanFields() {
	    //txtRut.setText("");
	    txtName.setText("");
	    txtSurname.setText("");
	    txtEmail.setText("");
	    txtPhone.setText("");
	    txtNum.setText("");
	    comboBank.setSelectedIndex(0); // Reset to "Seleccione Banco..."
	    comboType.setSelectedIndex(0); // Reset to "Seleccione Tipo de Cuenta..."
	}
	
	private void showEditButton() {
	    btnUpdate.setVisible(true);
	    btnDelete.setVisible(true);
	    btnSave.setVisible(false);
	}

	private void hideEditButtons() {
		btnUpdate.setVisible(false);
		btnDelete.setVisible(false);
		btnSave.setVisible(true);
	}
	
	private void fillFields(Landlord landlord) {
	    txtName.setText(landlord.getName());
	    txtPhone.setText(landlord.getPhone());
	    txtEmail.setText(landlord.getEmail());
	}
	
	private void searchByRut() {
	    String rut = txtRut.getText().trim();
	    if (!rut.isEmpty()) {
	    	LandlordDAO dao = new LandlordDAO(null);
	        Landlord landlord = dao.getByRut(rut);
	        if (landlord != null) {
	            fillFields(landlord);
	        } else {
	            cleanFields(); // Do not clean RUT field
	        }
	    }
	}
	
	private void searchByRut2() {
	    String rut = RUTValidator.cleanRUT(txtRut.getText().trim());

	    // Validar largo mínimo
	    if (rut.length() < 8) return;

	    if (!RUTValidator.isValid(rut)) {
	      
	    	cleanFields();
	        hideEditButtons();
	        return;
	    }

	    LandlordDAO dao = new LandlordDAO(null);
	    Landlord landlord = dao.getByRut(rut); // debes implementar este método

	    if (landlord != null) {
	        // Mostrar datos
	        txtName.setText(landlord.getName());
	        txtSurname.setText(landlord.getSurname());
	        txtEmail.setText(landlord.getEmail());
	        txtPhone.setText(landlord.getPhone());

	        showEditButton(); // btnUpdate & btnDelete visible, btnSave hidden
	    } else {
	    	cleanFields();
	    	hideEditButtons();
	    	btnSave.setVisible(true);
	    }
	}
}
