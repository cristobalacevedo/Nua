package model;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ParkingForm extends Parking {
    public JTextField txtRol;
    public JComboBox<String> tipoCombo;
    public JTextField txtNum;

    public ParkingForm(JTextField txtRol, JComboBox<String> tipoCombo, JTextField txtNum) {
        this.txtRol = txtRol;
        this.tipoCombo = tipoCombo;
        this.txtNum = txtNum;
    }


	public Parking toModel() {
        return new Parking(txtRol.getText(), (String) tipoCombo.getSelectedItem());
    }
}