package model;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ParkingForm {
    private JTextField txtRol;
    private JComboBox<String> tipoCombo;
    private JTextField txtNum;

    public ParkingForm(JTextField txtRol, JComboBox<String> tipoCombo, JTextField txtNum) {
        this.txtRol = txtRol;
        this.tipoCombo = tipoCombo;
        this.txtNum = txtNum;
    }

    public String getRol() {
        return txtRol.getText();
    }

    public String getTipo() {
        return (String) tipoCombo.getSelectedItem();
    }

    public Integer getNumero() {
        try {
            return Integer.parseInt(txtNum.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}