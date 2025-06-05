package view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JPanel;

/**
 * This class provides a method to show a new panel in the content pane. It
 * removes all existing components from the content pane and adds the new panel.
 */
public class showPanel {
	public static void showPanel(Container contentPane, JPanel newPanel) {
		contentPane.removeAll();
    	contentPane.add(newPanel, BorderLayout.CENTER);
    	contentPane.revalidate();
    	contentPane.repaint(); 
    	newPanel.setVisible(true);
    	newPanel.setBounds(0, 0, 1250, 700);
    	newPanel.setLayout(null);
    }
}
