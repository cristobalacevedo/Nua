package model;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class StorageForm {
    private JTextField txtStorageRol;
    private JTextField txtStorageNum;
    private JTextField txtStorageSize;

    public StorageForm(JTextField txtRol, JTextField txtNum, JTextField txtSize) {
        this.txtStorageRol = txtRol;
        this.txtStorageNum = txtNum;
        this.txtStorageSize = txtSize;
    }

    public String getRol() {
        return txtStorageRol.getText();
    }

    public String getNum() {
        return txtStorageNum.getText();
    }
    
    public String getSize() {
		return txtStorageSize.getText();
	}
}
