package view;

import model.Landlord;
import utils.RUTDocumentFilter;
import utils.RUTValidator;
import utils.FieldValidator;
import dao.LandlordDAO;
import dao.PersonDAO;
import db.DBConnection;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.text.PlainDocument;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JComboBox;

// This class represents the Landlords Panel in the application.
public class LandlordsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JTextField txtRut;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtNum;
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
	private JComboBox comboBox;
	private JComboBox comboBox_1;

	
	public LandlordsPanel(Container contentPane, Menu menu) {
		setBackground(new Color(153, 153, 153));
		setForeground(Color.BLACK);
		setName("LandlordsPanel"); 
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
		
		txtRut.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c !='-' && c != 'k' && c != 'K' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Avoid invalid input nor letters except 'k' or 'K'
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
		            actualLandlord = LandlordDAO.getByRut(formattedRut); // usar RUT limpio para búsqueda

		            if (actualLandlord != null) {
		                txtName.setText(actualLandlord.getName());
		                txtSurname.setText(actualLandlord.getSurname());
		                txtEmail.setText(actualLandlord.getEmail());
		                txtPhone.setText(actualLandlord.getPhone());
		                showEditButton();
		                btnSave.setVisible(false);
		                System.out.println("RUT Found: " + actualLandlord.getRut());
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
		
		// -- END PHONE -- //
		
		// -- BANK NUMBER -- //
	
		txtNum = new JTextField();
		txtNum.setBounds(689, 249, 270, 30);
		add(txtNum);
		txtNum.setColumns(10);
		
		// -- END BANK NUMBER -- //
		
		// --- END TEXT FIELDS --- //
		
		// --------------------- //
		
		// --- LABELS --- //
		
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
		
		lblEmail = new JLabel("Email:"); // SPANISH for "Email"
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
		
		comboBox = new JComboBox();
		comboBox.setBounds(689, 164, 270, 30);
		add(comboBox);
		
		
		
		
		// --- END COMBO BOXES --- //
		
		// ---------------------
		
		// --- SEPARATORS --- //
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(598, 164, 12, 233);
		add(separator);
		
		// --- END SEPARATORS --- //
		
		// ---------------------
		
		// --- BUTTONS --- //
		
		// Back Button
		btnBack.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		btnBack.setBounds(50, 600, 131, 50);
		add(btnBack);
		
	    // Save Button
		btnSave = new JButton("Guardar"); // SPANISH for "Save"
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(0, 153, 0));
		btnSave.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		btnSave.setBounds(537, 404, 125, 58);
		add(btnSave);
		
		btnDelete = new JButton("Eliminar"); // SPANISH for "Delete"
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBackground(Color.RED);
		btnDelete.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 14));
		btnDelete.setBounds(287, 390, 92, 30);
		btnDelete.setVisible(false); // Initially hidden until a landlord is selected for deletion
		add(btnDelete);
		
		btnUpdate = new JButton("Actualizar"); // SPANISH for "Update"
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(51, 153, 255));
		btnUpdate.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 22));
		btnUpdate.setBounds(531, 404, 142, 58);
		btnUpdate.setVisible(false); // Initially hidden until a landlord is selected for update
		add(btnUpdate);
		
		btnShowLL = new JButton("Ver Listado");
		btnShowLL.setBounds(39, 43, 142, 45);
		btnShowLL.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 20));
		add(btnShowLL);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(689, 206, 270, 30);
		add(comboBox_1);
		
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
		
		// Save Button ActionListener
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Map<String, JTextField> campos = new LinkedHashMap<>();
		        campos.put("RUT", txtRut);
		        campos.put("Nombre", txtName);
		        campos.put("Apellido", txtSurname);
		        campos.put("Email", txtEmail);
		        campos.put("Teléfono", txtPhone);

		        // Validación de campos vacíos
		        if (!FieldValidator.validField(campos)) return;

		        // Obtener valores
		        String rut = txtRut.getText().trim();
		        String name = txtName.getText().trim();
		        String surname = txtSurname.getText().trim();
		        String email = txtEmail.getText().trim();
		        String phone = txtPhone.getText().trim();

				// Clean and validate RUT
				if (!RUTValidator.isValid(rut)) {
				    Popup.show("RUT inválido. Verifica el formato y el dígito verificador.", "error"); // SPANISH for "Invalid RUT. Check the format and the verification digit."
				    System.out.println("RUT inválido. Verifica el formato y el dígito verificador."); // SPANISH for "Invalid RUT. Check the format and the verification digit."
				    return;
				}
				
				// Verificar existencia en la base de datos
				if (PersonDAO.rutExistsInDB(rut)) {
				    Popup.show("El RUT ya está registrado en el sistema.", "error"); // SPANISH for "The RUT is already registered in the system.";
				    return;
				}
				
				// Create a new Landlord object, defaulting to "landlord" type
				Landlord landlord = new Landlord(
					rut,
					name,
					surname,
					email,
					phone,
				    "landlord", // Fixed type for Landlord
					true,       // isActive
					false       // hasRentals
				);

				// Save the landlord using DAO
				LandlordDAO dao = new LandlordDAO(null);
				boolean saved = dao.save(landlord);

				if (saved) {
					Popup.show("Arrendador guardado correctamente.", "success"); // SPANISH for "Landlord saved successfully"
					System.out.println("Arrendador guardado correctamente."); // SPANISH for "Landlord saved successfully"
					cleanFields();
				} else {
					Popup.show("Error al guardar arrendador.", "error"); // SPANISH for "Error saving landlord"
					System.out.println("Error al guardar arrendador."); // SPANISH for "Error saving landlord"
				}
			}
		});

		// Back Button ActionListener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.show(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
		
		btnShowLL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFrame.show(new LandlordsList()); // Show the LandlordsList Frame when the button is clicked
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
