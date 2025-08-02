package view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;

import controller.TenantController;
import dao.BankDAO;
import dao.TenantDAO;
import db.DBConnection;
import model.Tenant;
import utils.FieldValidator;
import utils.PhoneDocumentFilter;
import utils.RUTDocumentFilter;
import utils.RUTValidator;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

// This class represents the Tenants panel in the application.
public class TenantPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JTextField txtRut;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtNum;
	private JTextField txtNumAval;
	private JLabel lblTenants;;
	private JLabel lblPersonalData;
	private JLabel lblAval; // SPANISH for "Guarantor"
	private JLabel lblRut; 
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblBankData;
	private JLabel lblBank;
	private JLabel lblType;
	private JLabel lblNum;
	private JLabel lblRutAval; 
	private JLabel lblNameAval;
	private JLabel lblSurnameAval;
	private JLabel lblEmailAval;
	private JLabel lblPhoneAval;
	private JLabel lblBankAval;
	private JLabel lblTypeAval;
	private JLabel lblNumAval;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnShowTL;
	private Tenant actualTenant; // Holds the currently selected landlord for updates or deletions
	private JComboBox<String> comboBank;
	private JComboBox<String> comboType;
	private JComboBox<String> comboBankAval;
	private JComboBox<String> comboTypeAval;
	private JCheckBox chckbxAval; // Checkbox to indicate if an aval is provided
	private JTextField txtRutAval;
	private JTextField txtNameAval;
	private JTextField txtEmailAval;
	private JTextField txtPhoneAval;
	private JTextField txtSurnameAval;
	private JSeparator separator_2;
	

	public TenantPanel(Container contentPane, Menu menu) {
		setBackground(new Color(187, 187, 187));
		setForeground(Color.BLACK);
		setName("TenantPanel"); 
		setLayout(null);
		setBounds(0, 0, 1250, 700); 
		setVisible(true);
		DBConnection.getConnection();
		
		// --------------------- //
		
		// --- TEXT FIELDS --- //
		
		// -- RUT -- //
		
		txtRut = new JTextField();
		txtRut.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtRut.setBounds(228, 163, 242, 30);
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
		            actualTenant = TenantDAO.getByRut2(formattedRut); // usar RUT limpio para búsqueda

		            if (actualTenant != null) {
		                txtName.setText(actualTenant.getName());
		                txtSurname.setText(actualTenant.getSurname());
		                txtEmail.setText(actualTenant.getEmail());
		                txtPhone.setText(actualTenant.getPhone());
		                
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
		                
		                comboBank.setSelectedItem(actualTenant.getBankName()); // Set selected bank
		                comboType.setSelectedItem(actualTenant.getAccountType()); // Set selected account type
		                txtNum.setText(actualTenant.getAccountNum()); // Set account number
		                showEditButton();
		                btnSave.setVisible(false);
		                System.out.println("\nRUT Found	: " + actualTenant.getRut());
		                System.out.println("Bank		: " + actualTenant.getBankName());
		                System.out.println("Account Type	: " + actualTenant.getAccountType());
		                System.out.println("N°    		: " + actualTenant.getAccountNum());
		            } else {
		                cleanFields();
		                hideEditButtons();
		                btnSave.setVisible(true);
		                actualTenant = null;
		                System.out.println("RUT Not Found as 'Tenant': " + formattedRut);
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
		txtName.setBounds(228, 205, 242, 30);
		add(txtName);
		txtName.setColumns(10);
		
		// -- END NAME -- //
		
		// -- SURNAME -- //
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtSurname.setBounds(228, 244, 242, 30);
		add(txtSurname);
		txtSurname.setColumns(10);
		
		// -- END SURNAME -- //
		
		// -- EMAIL -- //
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtEmail.setBounds(228, 285, 242, 30);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		// -- END EMAIL -- //
		
		// -- PHONE -- //
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtPhone.setBounds(228, 327, 242, 30);
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
		txtNum.setBounds(557, 248, 270, 30);
		add(txtNum);
		txtNum.setColumns(10);
		
		txtNumAval = new JTextField();
		txtNumAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtNumAval.setColumns(10);
		txtNumAval.setBounds(557, 390, 270, 30);
		txtNumAval.setVisible(false); // Initially hidden until the checkbox is checked
		((AbstractDocument) txtNumAval.getDocument()).setDocumentFilter(new RUTDocumentFilter()); // Apply the custom document filter to the RUT field for a better user experience and validation experience
		add(txtNumAval);
		
		// -- END BANK NUMBER -- //
		
		// -- AVAL TEXT FIELDS -- //
		
		txtRutAval = new JTextField();
	    txtRutAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
	    txtRutAval.setColumns(10);
	    txtRutAval.setBounds(945, 163, 242, 30);
	    txtRutAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(txtRutAval);
	    
	    txtRutAval.addKeyListener(new KeyAdapter() {
			@Override
		    public void keyReleased(KeyEvent e) {
		        String inputRut = txtRutAval.getText().toUpperCase();;
		        //String cleaned = RUTValidator.cleanRUT(input);

		        // No hacer nada si está vacío o es muy corto
		        if (inputRut.length() < 2) return;

		        // Aplicar formato en tiempo real
		        String formattedRut = RUTValidator.formatRUT(inputRut).toUpperCase();;

		        // Evitar bucle infinito al volver a escribir sobre el campo
		        if (!inputRut.equals(formattedRut)) {
		        	txtRutAval.setText(formattedRut);
		        	txtRutAval.setCaretPosition(txtRutAval.getText().length()); // Mover cursor al final
		        }

		        // Buscar solo si es válido
		        if (RUTValidator.isValid(formattedRut)) {
		        	actualTenant = TenantDAO.getByRut2(formattedRut); // usar RUT limpio para búsqueda

		            if (actualTenant != null) {
		                txtNameAval.setText(actualTenant.getName());
		                txtSurnameAval.setText(actualTenant.getSurname());
		                txtEmailAval.setText(actualTenant.getEmail());
		                txtPhoneAval.setText(actualTenant.getPhone());
		                
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
		                
		                comboBank.setSelectedItem(actualTenant.getBankName()); // Set selected bank
		                comboType.setSelectedItem(actualTenant.getAccountType()); // Set selected account type
		                txtNum.setText(actualTenant.getAccountNum()); // Set account number
		                showEditButton();
		                btnSave.setVisible(false);
		                System.out.println("\nRUT Found	: " + actualTenant.getRut());
		                System.out.println("Bank		: " + actualTenant.getBankName());
		                System.out.println("Account Type	: " + actualTenant.getAccountType());
		                System.out.println("N°    		: " + actualTenant.getAccountNum());
		            } else {
		                cleanFields();
		                hideEditButtons();
		                btnSave.setVisible(true);
		                actualTenant = null;
		                System.out.println("RUT Not Found as 'Aval': " + formattedRut);
		            }
		        } else {
		            cleanFields();
		            hideEditButtons();
		            btnSave.setVisible(true);
		        }
		    }
		});
		
		txtRutAval.addFocusListener(new FocusAdapter() {
		    @Override
		    public void focusLost(FocusEvent e) {
		        String inputRut = txtRutAval.getText().trim();
		        String cleanedRut = RUTValidator.cleanRUT(inputRut);
				if (!cleanedRut.isEmpty() && cleanedRut.length() >= 8) {
					String formattedRut = RUTValidator.formatRUT(cleanedRut);
					txtRutAval.setText(formattedRut); // Format the RUT when focus is lost
				} 		   
			}
		});
	    
	    
	    
	    txtNameAval = new JTextField();
	    txtNameAval.setText("");
	    txtNameAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
	    txtNameAval.setColumns(10);
	    txtNameAval.setBounds(945, 205, 242, 30);
	    txtNameAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(txtNameAval);
	    
	    txtSurnameAval = new JTextField();
	    txtSurnameAval.setText("");
	    txtSurnameAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
	    txtSurnameAval.setColumns(10);
	    txtSurnameAval.setBounds(945, 244, 242, 30);
	    txtSurnameAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(txtSurnameAval);
	    
	    txtEmailAval = new JTextField();
	    txtEmailAval.setText("");
	    txtEmailAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
	    txtEmailAval.setColumns(10);
	    txtEmailAval.setBounds(945, 285, 242, 30);
	    txtEmailAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(txtEmailAval);
	    
	    txtPhoneAval = new JTextField();
	    txtPhoneAval.setText("");
	    txtPhoneAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
	    txtPhoneAval.setColumns(10);
	    txtPhoneAval.setBounds(945, 327, 242, 30);
	    txtPhoneAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(txtPhoneAval);
		
	    // -- END AVAL TEXT FIELDS -- //
	    
		// --- END TEXT FIELDS --- //
		
		// --------------------- //
		
		// --- LABELS --- //
		
		// -- TENANTS PANEL TITLE -- //
		
		lblTenants = new JLabel("Panel de Arrendatarios"); // SPANISH for "Landlords Panel"");
		lblTenants.setForeground(Color.GRAY);
		lblTenants.setFont(new Font("Noto Sans JP", Font.PLAIN, 16));
		lblTenants.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenants.setBounds(508, 6, 187, 16);
		add(lblTenants);
		
		// -- PERSONAL DATA TITLE -- //
		
		lblPersonalData = new JLabel("Datos Personales"); // SPANISH for "Personal Data"
		lblPersonalData.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblPersonalData.setHorizontalAlignment(SwingConstants.CENTER);
		lblPersonalData.setBounds(178, 113, 242, 39);
		add(lblPersonalData);
		
		// -- END PERSONAL DATA TITLE --
		
		// -- GUARANTOR TITLE -- //
		
		lblAval = new JLabel("Aval");
	    lblAval.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
	    lblAval.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAval.setBounds(988, 117, 77, 30);
	    lblAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(lblAval);
	    
	    // -- END GUARANTOR TITLE -- //
		
		// -- RUT -- //
		
		lblRut = new JLabel("RUT:"); // SPANISH for "RUT"
		lblRut.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRut.setBounds(137, 163, 43, 30);
		add(lblRut);
		
		// -- END RUT --
		
		// -- NAME --
		
		lblName = new JLabel("Nombre:"); // SPANISH for "Name"
		lblName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblName.setBounds(137, 204, 92, 30);
		add(lblName);
		
		// -- END NAME --
		
		// -- SURNAME --
		
		lblSurname = new JLabel("Apellido:"); // SPANISH for "Surname"
		lblSurname.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSurname.setBounds(137, 244, 92, 30);
		add(lblSurname);
		
		// -- END SURNAME --
		
		// -- EMAIL --
		
		lblEmail = new JLabel("e-mail:"); // SPANISH for "Email"
		lblEmail.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblEmail.setBounds(137, 285, 92, 30);
		add(lblEmail);
		
		// -- END EMAIL --
		
		// -- PHONE --
		
		lblPhone = new JLabel("Teléfono:"); // SPANISH for "Phone"
		lblPhone.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPhone.setBounds(137, 327, 92, 30);
		add(lblPhone);
		
		// -- END PHONE --
		
		// -- BANK DATA -- 
		
		lblBankData = new JLabel("Datos Bancarios"); // SPANISH for "Bank Data"
		lblBankData.setHorizontalAlignment(SwingConstants.CENTER);
		lblBankData.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblBankData.setBounds(583, 113, 242, 39);
		add(lblBankData);
		
		lblBank = new JLabel("Banco:"); // SPANISH for "Bank"
		lblBank.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblBank.setBounds(490, 163, 64, 30);
		add(lblBank);
		
		lblType = new JLabel("Tipo:"); // SPANISH for "Account Type"
		lblType.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblType.setBounds(490, 205, 77, 30);
		add(lblType);
		
		lblNum = new JLabel("Nº : "); // SPANISH for "Account Number"
		lblNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblNum.setBounds(490, 244, 64, 30);
		add(lblNum);
		
		lblBankAval = new JLabel("Banco:");
		lblBankAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblBankAval.setBounds(490, 305, 64, 30);
		lblBankAval.setVisible(false); // Initially hidden until the checkbox is checked
		add(lblBankAval);
		
		lblTypeAval = new JLabel("Tipo:");
		lblTypeAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblTypeAval.setBounds(490, 347, 77, 30);
		lblTypeAval.setVisible(false); // Initially hidden until the checkbox is checked
		add(lblTypeAval);
		
		lblNumAval = new JLabel("Nº : ");
		lblNumAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblNumAval.setBounds(490, 386, 64, 30);
		lblNumAval.setVisible(false); // Initially hidden until the checkbox is checked
		add(lblNumAval);
		
		// -- END BANK DATA -- //
		
		// --- AVAL LABELS --- //
		
	    lblRutAval = new JLabel("RUT:");
	    lblRutAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblRutAval.setBounds(859, 163, 43, 30);
	    lblRutAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(lblRutAval);
	    
	    lblNameAval = new JLabel("Nombre:");
	    lblNameAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblNameAval.setBounds(859, 204, 92, 30);
	    lblNameAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(lblNameAval);
	    
	    lblSurnameAval = new JLabel("Apellido:");
	    lblSurnameAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblSurnameAval.setBounds(859, 244, 92, 30);
	    lblSurnameAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(lblSurnameAval);
	    
	    lblEmailAval = new JLabel("e-mail:");
	    lblEmailAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblEmailAval.setBounds(859, 285, 92, 30);
	    lblEmailAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(lblEmailAval);
	    
	    lblPhoneAval = new JLabel("Teléfono:");
	    lblPhoneAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblPhoneAval.setBounds(859, 327, 92, 30);
	    lblPhoneAval.setVisible(false); // Initially hidden until the checkbox is checked
	    add(lblPhoneAval);
	    
	    // -- END AVAL LABELS -- //
	    
	    
		
		// --- END LABELS --- //
		
		// ---------------------
		
		// --- COMBO BOXES --- //
		
		// -- BANKS -- //
		
		comboBank = new JComboBox<String>();
		comboBank.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboBank.setBounds(557, 163, 270, 30);
		add(comboBank);
		
		comboBank.addItem("Seleccione Banco..."); // SPANISH for "Select..."
		
		BankDAO bankDAO = new BankDAO(); 
		List<String> bankNames = bankDAO.getAllBankNames();

		for (String name : bankNames) {
		    comboBank.addItem(name);
		}
		
		comboBankAval = new JComboBox<String>();
		comboBankAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboBankAval.setBounds(557, 305, 270, 30);
		comboBankAval.setVisible(false); // Initially hidden until the checkbox is checked
		add(comboBankAval);
		
		comboBankAval.addItem("Seleccione Banco..."); // SPANISH for "Select Bank..."
		
		
		for (String name : bankNames) {
		    comboBankAval.addItem(name);
		}
		
		// -- END BANKS -- //
		
		// -- ACCOUNT TYPES -- //
		
		comboType = new JComboBox<String>();
		comboType.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboType.setBounds(557, 205, 270, 30);
		add(comboType);
		
		comboType.addItem("Seleccione Tipo de Cuenta..."); // SPANISH for "Select Account Type..."
		comboType.addItem("Cuenta Corriente");
		comboType.addItem("Cuenta Vista");
		comboType.addItem("Cuenta de Ahorro");		
		
		comboTypeAval = new JComboBox<String>();
		comboTypeAval.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		comboTypeAval.setBounds(557, 347, 270, 30);
		comboTypeAval.setVisible(false); // Initially hidden until the checkbox is checked
		add(comboTypeAval);
		
		comboTypeAval.addItem("Seleccione Tipo de Cuenta..."); // SPANISH for "Select Account Type..."
		comboTypeAval.addItem("Cuenta Corriente");
		comboTypeAval.addItem("Cuenta Vista");
		comboTypeAval.addItem("Cuenta de Ahorro");	

		
		
		// -- END ACCOUNT TYPES -- //
		
		// --- END COMBO BOXES --- //
		
		// --------------------- //
		
		// --- SEPARATORS --- //
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(480, 163, 12, 257);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLUE);
		separator_1.setBackground(Color.GRAY);
		separator_1.setBounds(837, 163, 12, 124);
		add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLUE);
		separator_2.setBounds(480, 285, 358, 16);
		separator_2.setVisible(false); // Initially hidden until the checkbox is checked
		add(separator_2);
		    
		   
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
		btnSave.setBounds(454, 467, 125, 58);
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
		btnUpdate.setBounds(448, 467, 142, 58);
		btnUpdate.setVisible(false); // Initially hidden until a landlord is selected for update
		add(btnUpdate);
		
		// -- END UPDATE BUTTON -- //
		
		// -- SHOW TENANTS LIST BUTTON -- //
		
		btnShowTL = new JButton("Ver Listado");
		btnShowTL.setBounds(39, 43, 142, 45);
		btnShowTL.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 20));
		add(btnShowTL);
		
		// -- END SHOW LANDLORDS LIST BUTTON -- //
		
		// --- END BUTTONS --- //
		
		chckbxAval = new JCheckBox("Aval");
		chckbxAval.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		chckbxAval.setBounds(137, 373, 64, 23);
	    add(chckbxAval);
		
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
		    
		    // Tenant data
		    String rut = txtRut.getText().trim();
		    String name = txtName.getText().trim();
		    String surname = txtSurname.getText().trim();
		    String email = txtEmail.getText().trim();
		    String phone = txtPhone.getText().trim();
		   
		    // Bank and account data
		    String selectedBankName = comboBank.getSelectedItem().toString();
		    String accountType = comboType.getSelectedItem().toString();
		    String num = txtNum.getText().trim();

		    TenantController controller = new TenantController();

		    
		    
		    // If checkbox is selected, validate the aval fields
			if (chckbxAval.isSelected()) {
				Map<String, JComponent> avalFields = new LinkedHashMap<>();
				avalFields.put("Aval - RUT", txtRutAval);
				avalFields.put("Aval - Nombre", txtNameAval);
				avalFields.put("Aval - Apellido", txtSurnameAval);
				avalFields.put("Aval - Email", txtEmailAval);
				avalFields.put("Aval - Teléfono", txtPhoneAval);
				
				

				if (!FieldValidator.validField(avalFields))
					return;
				
				// Get aval data
				String rutAval = txtRutAval.getText().trim();
				String nameAval = txtNameAval.getText().trim();
				String surnameAval = txtSurnameAval.getText().trim();
				String emailAval = txtEmailAval.getText().trim();
				String phoneAval = txtPhoneAval.getText().trim();
				
				// Get bank aval data
				String selectedBankAvalName = comboBankAval.getSelectedItem().toString();
				String accountTypeAval = comboTypeAval.getSelectedItem().toString();
				String numAval = txtNumAval.getText().trim();
				
				// Save tenant with aval
				boolean success = controller.saveTenantAval(rut, name, surname, email, phone, rutAval, nameAval,
						surnameAval, emailAval, phoneAval, selectedBankName, accountType, num, selectedBankAvalName, accountTypeAval, numAval);

				if (success) {
					System.out.println("RUT: " + rut);
					System.out.println("Banco: " + selectedBankName);
					System.out.println("Tipo de Cuenta: " + accountType);
					System.out.println("N°: " + num);

					System.out.println("Arrendatario y aval guardados correctamente."); // SPANISH for "Tenant and
																						// guarantor saved successfully"
					Popup.showSuccess("Arrendatario y aval guardados correctamente."); // SPANISH for "Tenant and
																						// guarantor saved successfully"
					cleanFields();
				} else {
					System.out.println("RUT: " + rut);
					System.out.println("Banco: " + selectedBankName);
					System.out.println("Tipo de Cuenta: " + accountType);
					System.out.println("N°: " + num);

					System.out.println("Error al guardar arrendatario y aval."); // SPANISH for "Error saving tenant and
																					// guarantor"
					Popup.show("Error al guardar arrendatario y aval.", "error"); // SPANISH for "Error saving tenant
																					// and guarantor"
				}
			} else {
				// Save tenant without aval
				boolean success = controller.saveTenant(rut, name, surname, email, phone, selectedBankName, accountType, num);
				
				if (success) {
			    	System.out.println("RUT: " + rut);
			        System.out.println("Banco: " + selectedBankName);
			        System.out.println("Tipo de Cuenta: " + accountType);
			        System.out.println("N°: " + num);
			           
			    	System.out.println("Arrendador guardado correctamente."); // SPANISH for "Landlord saved successfully"
			        Popup.showSuccess("Arrendador guardado correctamente."); // SPANISH for "Landlord saved successfully"
			        cleanFields();
			    } else {
			    	System.out.println("RUT: " + rut);
			        System.out.println("Banco: " + selectedBankName);
			        System.out.println("Tipo de Cuenta: " + accountType);
			        System.out.println("N°: " + num);
			        
			    	System.out.println("Error al guardar arrendador."); // SPANISH for "Error saving landlord"
			        Popup.show("Error al guardar arrendador.", "error"); // SPANISH for "Error saving landlord"
			    }
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
		
//		btnUpdate.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        // Reunir los campos
//		        Map<String, JComponent> campos = new LinkedHashMap<>();
//		        campos.put("RUT", txtRut);
//		        campos.put("Nombre", txtName);
//		        campos.put("Apellido", txtSurname);
//		        campos.put("Email", txtEmail);
//		        campos.put("Teléfono", txtPhone);
//		        campos.put("Banco", comboBank);
//		        campos.put("Tipo de Cuenta", comboType);
//		        campos.put("N° (Número de Cuenta)", txtNum);
//
//		        if (!FieldValidator.validField(campos)) return;
//
//		        // Obtener valores
//		        String rut = txtRut.getText().trim();
//		        String name = txtName.getText().trim();
//		        String surname = txtSurname.getText().trim();
//		        String email = txtEmail.getText().trim();
//		        String phone = txtPhone.getText().trim();
//		        String accountNum = txtNum.getText().trim();
//		        String selectedBankName = comboBank.getSelectedItem().toString();
//		        String accountType = comboType.getSelectedItem().toString();
//
//		        // Llamar al controlador
//		        TenantController controller = new TenantController();
//		        boolean updated = controller.updateTenant(
//		            rut, name, surname, email, phone, selectedBankName, accountType, accountNum
//		        );
//
//		        if (updated) {
//		            Popup.showSuccess("Arrendador actualizado correctamente.");
//		            cleanFields();
//		            hideEditButtons();
//		        } else {
//		            Popup.show("Error al actualizar arrendador.", "error");
//		        }
//		    }
//		});
		
		
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

		    TenantController controller = new TenantController();
		    boolean deleted = controller.deleteTenant(rut);

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
		
		btnShowTL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMiniFrame.show(new TenantsList()); // Show the LandlordsList Frame when the button is clicked
			}
		});
		
		// -- END UPDATE BUTTON ACTION LISTENER -- //
		
		chckbxAval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxAval.isSelected()) {
					showAvalFields();
				} else {
					hideAvalFields();
				}
			}
		});
		
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
	
	private void fillFields(Tenant tenant) {
	    txtName.setText(tenant.getName());
	    txtPhone.setText(tenant.getPhone());
	    txtEmail.setText(tenant.getEmail());
	}
	
	private void hideAvalFields() {
		lblAval.setVisible(false);
		
		lblRutAval.setVisible(false);
		lblNameAval.setVisible(false);
		lblSurnameAval.setVisible(false);
		lblEmailAval.setVisible(false);
		lblPhoneAval.setVisible(false);
		
		lblBankAval.setVisible(false);
		lblTypeAval.setVisible(false);
		lblNumAval.setVisible(false);
		
	    txtRutAval.setVisible(false);
	    txtNameAval.setVisible(false);
	    txtSurnameAval.setVisible(false);
	    txtEmailAval.setVisible(false);
	    txtPhoneAval.setVisible(false);
	    
	    comboBankAval.setVisible(false);
	    comboTypeAval.setVisible(false);
	    txtNumAval.setVisible(false);
	    
	    separator_2.setVisible(false); // Hide the separator when aval fields are hidden
	}
	
	private void showAvalFields() {
		lblAval.setVisible(true);
		
		lblRutAval.setVisible(true);
		lblNameAval.setVisible(true);
		lblSurnameAval.setVisible(true);
		lblEmailAval.setVisible(true);
		lblPhoneAval.setVisible(true);
		
		lblBankAval.setVisible(true);
		lblTypeAval.setVisible(true);
		lblNumAval.setVisible(true);
		
	    txtRutAval.setVisible(true);
	    txtNameAval.setVisible(true);
	    txtSurnameAval.setVisible(true);
	    txtEmailAval.setVisible(true);
	    txtPhoneAval.setVisible(true);
	    
	    comboBankAval.setVisible(true);
	    comboTypeAval.setVisible(true);
	    txtNumAval.setVisible(true);
	    
	    separator_2.setVisible(true); // Show the separator when aval fields are shown
	}
	
	private void searchByRut() {
	    String rut = txtRut.getText().trim();
	    if (!rut.isEmpty()) {
	    	TenantDAO dao = new TenantDAO(null);
	        Tenant tenant = dao.getByRut(rut);
	        if (tenant != null) {
	            fillFields(tenant);
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

	    TenantDAO dao = new TenantDAO(null);
	    Tenant tenant = dao.getByRut(rut); // debes implementar este método

	    if (tenant != null) {
	        // Mostrar datos
	        txtName.setText(tenant.getName());
	        txtSurname.setText(tenant.getSurname());
	        txtEmail.setText(tenant.getEmail());
	        txtPhone.setText(tenant.getPhone());

	        showEditButton(); // btnUpdate & btnDelete visible, btnSave hidden
	    } else {
	    	cleanFields();
	    	hideEditButtons();
	    	btnSave.setVisible(true);
	    }
	}
}
