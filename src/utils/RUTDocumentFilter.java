package utils;

import javax.swing.text.*;

public class RUTDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (string == null) return;
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (text == null) return;

        StringBuilder sb = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.replace(offset, offset + length, text);

        String result = sb.toString().toUpperCase();
        result = result.replaceAll("[^0-9Kk.-]", ""); // Solo permite dígitos, puntos, guion y K/k

        if (result.matches("[0-9.\\-Kk]*")) {
            super.replace(fb, offset, length, text.toUpperCase(), attrs);
        }
    }
}