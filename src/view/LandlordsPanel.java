package view;

import model.Landlord;
import dao.LandlordDAO;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;


// This class represents the Landlords Panel in the application.
public class LandlordsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack = new JButton("Atrás"); // SPANISH
	private JTextField txtRut;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JLabel lblRut;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JButton btnSave;
	
	public LandlordsPanel(Container contentPane, Menu menu) {
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.BLACK);
		setName("LandlordsPanel"); 
		setLayout(null);
		setBounds(0, 0, 1080, 700); 
		setVisible(true);
		
		// --- TEXT FIELDS --- //
		
		txtRut = new JTextField();
		txtRut.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtRut.setBounds(137, 162, 220, 30);
		add(txtRut);
		txtRut.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtName.setBounds(137, 203, 220, 30);
		add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtSurname.setBounds(137, 244, 220, 30);
		add(txtSurname);
		txtSurname.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtEmail.setBounds(137, 285, 220, 30);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtPhone.setBounds(137, 326, 220, 30);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		// --- END TEXT FIELDS --- //
		
		
		
		// --- LABELS --- //
		
		lblRut = new JLabel("RUT:");
		lblRut.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRut.setBounds(35, 158, 43, 30);
		add(lblRut);
		
		lblName = new JLabel("Nombre:");
		lblName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblName.setBounds(35, 192, 92, 45);
		add(lblName);
		
		lblSurname = new JLabel("Apellido:");
		lblSurname.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSurname.setBounds(35, 238, 92, 40);
		add(lblSurname);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblEmail.setBounds(35, 285, 92, 26);
		add(lblEmail);
		
		lblPhone = new JLabel("Teléfono:");
		lblPhone.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPhone.setBounds(35, 322, 92, 31);
		add(lblPhone);
		
		// --- END LABELS --- //
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.GRAY);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(398, 162, 242, 194);
		add(separator);
		
		// --- BUTTONS --- //
		
		// Back Button
		btnBack.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		btnBack.setBounds(50, 600, 100, 30);
		add(btnBack);
		
	    // Save Button
		btnSave = new JButton("Guardar"); // SPANISH for "Save"
		btnSave.setBounds(267, 387, 90, 28);
		add(btnSave);
	
		// --- END BUTTONS --- //
		
		
		
		// --- BUTTON ACTIONS --- //
		
		// Save Button ActionListener
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener los datos del formulario
				String rut = txtRut.getText().trim();
				String name = txtName.getText().trim();
				String surname = txtSurname.getText().trim();
				String email = txtEmail.getText().trim();
				String phone = txtPhone.getText().trim();
				
				// Validación básica
				if (rut.isEmpty() || name.isEmpty() || surname.isEmpty()) {
					System.out.println("Campos obligatorios vacíos.");
					return;
				}

				// Crear el Landlord (por defecto activo y sin propiedades)
				Landlord landlord = new Landlord(
					rut,
					name,
					surname,
					email,
					phone,
				    "landlord", // tipo fijo para arrendador
					true,       // isActive
					false       // hasRentals
				);

				// Guardar en la base de datos
				LandlordDAO dao = new LandlordDAO();
				boolean saved = dao.save(landlord);

				if (saved) {
					System.out.println("Arrendador guardado correctamente.");
					// Aquí podrías limpiar los campos, mostrar un JOptionPane, etc.
					txtRut.setText("");
					txtName.setText("");
					txtSurname.setText("");
					txtEmail.setText("");
					txtPhone.setText("");
				} else {
					System.out.println("Error al guardar arrendador.");
				}
			}
		});

	
		// Back Button ActionListener
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel.showPanel(contentPane, menu.MenuClone());// Show the MenuPanel when back button is clicked
			}
		});
		
	}
}
