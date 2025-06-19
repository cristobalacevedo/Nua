package model;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ParkingForm {
    private JTextField txtPrkngRol;
    private JComboBox<String> tipoCombo;
    private JTextField txtPrkngNum;

    public ParkingForm(JTextField txtRol, JComboBox<String> tipoCombo, JTextField txtNum) {
        this.txtPrkngRol = txtRol;
        this.tipoCombo = tipoCombo;
        this.txtPrkngNum = txtNum;
    }

    public String getRol() {
        return txtPrkngRol.getText();
    }

    public String getTipo() {
        return (String) tipoCombo.getSelectedItem();
    }

    public String getNum() {
        return txtPrkngNum.getText();
    }
}