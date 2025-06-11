package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.RegionDAO;
import dao.TownDAO;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import utils.TownOption;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Color;

public class CondoFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel CondoFrame;
	private JButton btnExit;
	private JTextField txtNameCondo;
	private JTextField txtAddress;
	private JComboBox<String> comboRegion;
	private JComboBox<TownOption> comboTown;
	private JLabel lblTitle;
	private JLabel lblName;
	private JLabel lblRegion;
	private JLabel lblTown;
	private JLabel lblAddress;
	private JLabel lblEmailCondo;
	private JTextField txtEmail;
	private JTextField textField;
	private JLabel lblNum;
	private JTextField txtRut;
	private JTextField txtNameDoorman;
	private JTextField txtSurname;
	private JTextField textField_5;
	private JTextField textField_6;

	public CondoFrame() {
		setTitle("NUA - Condominios y Conserjes"); // SPANISH for "Condominiums and Concierges"
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 530);
		setName("CondoFrame"); // Set the name of the frame to "CondoFrame"
		CondoFrame = new JPanel();
		CondoFrame.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(CondoFrame);
		CondoFrame.setLayout(null);
		
		lblTitle = new JLabel("Creación de Condominios y Conserjes");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
		lblTitle.setBounds(211, 11, 488, 38);
		CondoFrame.add(lblTitle);
		
		lblName = new JLabel("Nombre: ");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblName.setBounds(50, 96, 85, 25);
		CondoFrame.add(lblName);
		
	    lblRegion = new JLabel("Región: ");
	    lblRegion.setHorizontalAlignment(SwingConstants.LEFT);
	    lblRegion.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblRegion.setBounds(50, 136, 85, 25);
	    CondoFrame.add(lblRegion);
	    
	    lblTown = new JLabel("Comuna: ");
	    lblTown.setHorizontalAlignment(SwingConstants.LEFT);
	    lblTown.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
	    lblTown.setBounds(50, 178, 85, 25);
	    CondoFrame.add(lblTown);
	   
	    lblAddress = new JLabel("Dirección: ");
	    lblAddress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddress.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblAddress.setBounds(50, 223, 98, 25);
		CondoFrame.add(lblAddress);
		
		lblEmailCondo = new JLabel("e-mail:");
		lblEmailCondo.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmailCondo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblEmailCondo.setBounds(50, 259, 85, 25);
		CondoFrame.add(lblEmailCondo);
	    
		txtNameCondo = new JTextField();
		txtNameCondo.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		txtNameCondo.setBounds(147, 95, 256, 30);
		CondoFrame.add(txtNameCondo);
		txtNameCondo.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		txtAddress.setBounds(147, 224, 174, 30);
		CondoFrame.add(txtAddress);
		txtAddress.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(147, 260, 256, 30);
		CondoFrame.add(txtEmail);
		
		comboRegion = new JComboBox<String>();
		comboRegion.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 16));
		comboRegion.setEditable(false);
		comboRegion.setBounds(147, 138, 256, 30);
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
		comboTown.setBounds(147, 179, 256, 30);
		CondoFrame.add(comboTown);
		
		comboTown.addItem(new TownOption(0 , "Seleccione una Comuna")); // SPANISH for "Select a Town"

		btnExit = new JButton("Cerrar");
		btnExit.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		btnExit.setBounds(39, 424, 89, 30);
		CondoFrame.add(btnExit);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(456, 84, 18, 264);
		CondoFrame.add(separator);
		
		textField = new JTextField();
		textField.setBounds(352, 224, 51, 30);
		CondoFrame.add(textField);
		textField.setColumns(10);
		
		lblNum = new JLabel("Nº");
		lblNum.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblNum.setBounds(324, 226, 30, 25);
		CondoFrame.add(lblNum);
		
		txtRut = new JTextField();
		txtRut.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		txtRut.setColumns(10);
		txtRut.setBounds(579, 96, 270, 30);
		CondoFrame.add(txtRut);
		
		txtNameDoorman = new JTextField();
		txtNameDoorman.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		txtNameDoorman.setColumns(10);
		txtNameDoorman.setBounds(579, 138, 270, 30);
		CondoFrame.add(txtNameDoorman);
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		txtSurname.setColumns(10);
		txtSurname.setBounds(579, 177, 270, 30);
		CondoFrame.add(txtSurname);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(579, 218, 270, 30);
		CondoFrame.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		textField_6.setColumns(10);
		textField_6.setBounds(579, 260, 270, 30);
		CondoFrame.add(textField_6);
		
		JLabel lblRut = new JLabel("RUT:");
		lblRut.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblRut.setBounds(502, 96, 43, 30);
		CondoFrame.add(lblRut);
		
		JLabel lblName_1 = new JLabel("Nombre:");
		lblName_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblName_1.setBounds(502, 137, 92, 30);
		CondoFrame.add(lblName_1);
		
		JLabel lblSurname = new JLabel("Apellido:");
		lblSurname.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblSurname.setBounds(502, 177, 92, 30);
		CondoFrame.add(lblSurname);
		
		JLabel lblEmail_1 = new JLabel("e-mail:");
		lblEmail_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblEmail_1.setBounds(502, 218, 92, 30);
		CondoFrame.add(lblEmail_1);
		
		JLabel lblPhone = new JLabel("Teléfono:");
		lblPhone.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblPhone.setBounds(502, 260, 92, 30);
		CondoFrame.add(lblPhone);
		
		JButton btnSaveCondo = new JButton("Guardar");
		btnSaveCondo.setForeground(Color.WHITE);
		btnSaveCondo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 18));
		btnSaveCondo.setBackground(new Color(0, 153, 0));
		btnSaveCondo.setBounds(303, 309, 100, 50);
		CondoFrame.add(btnSaveCondo);
		
		JButton btnSaveCondo_1 = new JButton("Guardar");
		btnSaveCondo_1.setForeground(Color.WHITE);
		btnSaveCondo_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 18));
		btnSaveCondo_1.setBackground(new Color(0, 153, 0));
		btnSaveCondo_1.setBounds(750, 309, 100, 50);
		CondoFrame.add(btnSaveCondo_1);
		
		
		btnExit.addActionListener(e -> {
			System.out.println("Closing CondoFrame on Button"); // For debugging purposes
			this.dispose(); // Close the current frame
		});
		
	}
}
