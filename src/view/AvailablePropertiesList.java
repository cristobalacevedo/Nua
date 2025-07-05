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

import dao.PropertyDAO;
import model.AvailablePropertyView;
import model.Estate;
import javax.swing.JButton;

public class AvailablePropertiesList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel APFrame;
	private JButton btnBack;

	public AvailablePropertiesList() {
		setTitle("NUA - Propiedades Disponibles"); // SPANISH for "Landlords List"
		setName("AvailablePropertiesList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 910, 525);
		APFrame = new JPanel();
		APFrame.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(APFrame);
		
		// --- TABLE SETUP ---
		
		String[] columnNames = {"Dueño", "Tipo", "Rol SII", "Tamaño", "Comuna", "Dirección", "Disponibilidad"}; // SPANISH for "Active" and "Has Rentals"
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		
		for (AvailablePropertyView apv : new PropertyDAO().getAllAvailable()) {
			 Object[] row = {
				        apv.getLandlord(),
				        apv.getType(),
				        apv.getRolSII(),
				        apv.getSize(),
				        apv.getTown(),
				        apv.getFullAddress(),
				        apv.getAvailability()
				    };
				    model.addRow(row);
				}
				
		JTable tableAP = new JTable(model);
		tableAP.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		tableAP.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableAP.setName("AvailablePropertiesTable");
		
		// Set non-editable table
		tableAP.setDefaultEditor(Object.class, null); // Makes the table non-editable
		
		
		tableAP.setForeground(Color.BLACK);
		tableAP.setBackground(Color.WHITE);
		tableAP.setSelectionBackground(new Color(184, 207, 229)); // Celeste de selección -- SPANISH for "Selection Blue"
		tableAP.setSelectionForeground(Color.BLACK);
		tableAP.setGridColor(Color.GRAY); // Set grid color to gray
		tableAP.setShowGrid(true); // Show grid lines
		tableAP.setShowHorizontalLines(true); // Show horizontal lines
		tableAP.setShowVerticalLines(true); // Show vertical lines
		tableAP.setIntercellSpacing(new java.awt.Dimension(1, 1)); // Set intercell spacing to 1 pixel
		
		// IF Set colors and Bold for cells with "Disponible" (Available) and Arrendado (Rented)
		if (model.getRowCount() > 0) {
			for (int i = 0; i < model.getRowCount(); i++) {
				String availability = (String) model.getValueAt(i, 6); // Assuming "Disponibilidad" is the 7th column
				if ("Disponible".equals(availability)) { // SPANISH for "Available"
					tableAP.setValueAt("<html><b><font color='green'>" + availability + "</font></b></html>", i, 6);
				} else if ("Arrendado".equals(availability)) { // SPANISH for "Rented"
					tableAP.setValueAt("<html><b><font color='red'>" + availability + "</font></b></html>", i, 6);
				}
			}
		}
		
		tableAP.setRowSelectionAllowed(true);
		tableAP.setRowHeight(18); // Set row height to 18 pixels
		tableAP.setColumnSelectionAllowed(true);
		tableAP.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Desactiva el ajuste automático

		// Set custom cell renderer for the table
		TableColumnModel columnModel = tableAP.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(150);  // Dueño -- SPANISH for "Owner"
		columnModel.getColumn(1).setPreferredWidth(135);  // Tipo -- SPANISH for "Type"
		columnModel.getColumn(2).setPreferredWidth(125);  // Rol SII -- SPANISH for "SII Role"
		columnModel.getColumn(3).setPreferredWidth(58);  // Tamaño -- SPANISH for "Size"
		columnModel.getColumn(4).setPreferredWidth(135);  // Comuna -- SPANISH for "Commune/District/City"
		columnModel.getColumn(5).setPreferredWidth(150);   // Dirección -- SPANISH for "Address"
		columnModel.getColumn(6).setPreferredWidth(115);  // Disponibilidad -- SPANISH for "Availability"

		// Center the header text
		JTableHeader header = tableAP.getTableHeader();
		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// Center specific columns
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		//columnModel.getColumn(0).setCellRenderer(centerRenderer); // RUT
		//columnModel.getColumn(5).setCellRenderer(centerRenderer); // Activo
		//columnModel.getColumn(6).setCellRenderer(centerRenderer); // Tiene Arrendamientos

		// Color alternating rows (striped effect like Excel)
		tableAP.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
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
		APFrame.setLayout(null);
		
		// ScrollPane
		JScrollPane scrollPane = new JScrollPane(tableAP);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 894, 444); 
		getContentPane().add(scrollPane);
		
		// --- END TABLE SETUP ---
		
		// --- BUTTONS SETUP ---
		
		btnBack = new JButton("Atrás");
		btnBack.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 16));
		btnBack.setBounds(37, 447, 79, 39);
		APFrame.add(btnBack);
		btnBack.addActionListener(e -> {
			System.out.println("Closing AvailablePropertiesList on Button"); // For debugging purposes
			this.dispose(); // Close the current frame
		});
		
		
	}
}
