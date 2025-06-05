package utils;

import javax.swing.text.*;

//Método para limitar la cantidad de caracteres en un JTextField
public class CharLimit extends PlainDocument {
    private int limit;

    public CharLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}