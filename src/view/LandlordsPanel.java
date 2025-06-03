package view;

import javax.swing.JPanel;

public class LandlordsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public LandlordsPanel() {
		setName("LandlordsPanel"); // Set the name of the panel
		setLayout(null);
		setBounds(0, 0, 1080, 680); // Set the size of the panel
		setVisible(true); // Make the panel visible
		// Additional initialization code can go here
		// For example, adding components to the panel
		// JLabel label = new JLabel("Landlords Panel");
		// label.setBounds(50, 50, 200, 30);
		// add(label);
		
		// You can also set a background color if needed
		// setBackground(Color.LIGHT_GRAY);
		
		// More components can be added as per requirements
	}

}
