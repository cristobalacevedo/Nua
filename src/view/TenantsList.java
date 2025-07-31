package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import dao.TenantDAO;
import model.Tenant;
import javax.swing.JButton;

public class TenantsList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel TLFrame;
	private JButton btnBack;

	public TenantsList() {
		setTitle("NUA - Lista de Arrendatarios"); // SPANISH for "Landlords List"
		setName("TenantsList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 910, 525);
		TLFrame = new JPanel();
		TLFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(TLFrame);
		
		// --- TABLE SETUP ---
		
		String[] columnNames = {"RUT", "Nombre", "Apellido", "Teléfono", "Email", "Activo", "Arrendando"}; // SPANISH for "Active" and "Has Rentals"
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		
		for (Tenant tenant : new TenantDAO(null).getAll()) {
			Object[] row = { tenant.getRut(), tenant.getName(), tenant.getSurname(), tenant.getPhone(), tenant.getEmail(),
					tenant.getIsActive() ? "Sí" : "No", // SPANISH for "Yes" or "No"
					tenant.getIsRenting() ? "Sí" : "No" // SPANISH for "Yes" or "No"
			};
			model.addRow(row);
		}
				
		JTable tableTenants = new JTable(model);
		tableTenants.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		tableTenants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTenants.setRowSelectionAllowed(true);
		tableTenants.setRowHeight(18); // Set row height to 30 pixels
		tableTenants.setColumnSelectionAllowed(true);
		tableTenants.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático

		// Set custom cell renderer for the tabl
		TableColumnModel columnModel = tableTenants.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(110);  // RUT -- SPANISH for "RUT"
		columnModel.getColumn(1).setPreferredWidth(125);  // Nombre -- SPANISH for "Name"
		columnModel.getColumn(2).setPreferredWidth(125);  // Apellido -- SPANISH for "Surname"
		columnModel.getColumn(3).setPreferredWidth(125);  // Teléfono -- SPANISH for "Phone"
		columnModel.getColumn(4).setPreferredWidth(270);  // Email -- SPANISH for "Email"
		columnModel.getColumn(5).setPreferredWidth(55);   // Activo -- SPANISH for "Active"
		columnModel.getColumn(6).setPreferredWidth(60);  // Arrienda -- SPANISH for "Has Rentals"

		// Center the header text
		JTableHeader header = tableTenants.getTableHeader();
		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// Center specific columns
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		columnModel.getColumn(0).setCellRenderer(centerRenderer); // RUT
		columnModel.getColumn(5).setCellRenderer(centerRenderer); // Activo
		columnModel.getColumn(6).setCellRenderer(centerRenderer); // Tiene Arrendamientos

		// Color alternating rows (striped effect like Excel)
		tableTenants.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value,
		            boolean isSelected, boolean hasFocus, int row, int column) {

		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		        if (!isSelected) {
		            c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240)); // rayado tipo Excel
		        } else {
		            c.setBackground(new Color(184, 207, 229)); // celeste de selección
		        }

		        return c;
		    }
		});
		TLFrame.setLayout(null);
		
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane(tableTenants);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 894, 444); 
		getContentPane().add(scrollPane);
		
		// --- END TABLE SETUP ---
		
		// --- BUTTONS SETUP ---
		
		btnBack = new JButton("Atrás");
		btnBack.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 16));
		btnBack.setBounds(37, 447, 79, 39);
		TLFrame.add(btnBack);
		btnBack.addActionListener(e -> {
			System.out.println("Closing LandlordsList on Button"); // For debugging purposes
			this.dispose(); // Close the current frame
		});
		
		
	}
}
