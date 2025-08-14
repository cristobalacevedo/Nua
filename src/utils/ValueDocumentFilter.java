package utils;

import javax.swing.text.*;

public class ValueDocumentFilter extends DocumentFilter {

    private final int maxLength;

    public ValueDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    private String formatText(String text) {
        // eliminar todo lo que no sea dígito
        text = text.replaceAll("\\D", "");

        // limitar longitud
        if (text.length() > maxLength) {
            text = text.substring(0, maxLength);
        }

        // agregar puntos cada 3 dígitos
        StringBuilder sb = new StringBuilder(text);
        int len = sb.length();
        for (int i = len - 3; i > 0; i -= 3) {
            sb.insert(i, '.');
        }

        return sb.toString();
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder newText = new StringBuilder(oldText);
        newText.insert(offset, string);

        String formatted = formatText(newText.toString());
        super.replace(fb, 0, oldText.length(), formatted, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder newText = new StringBuilder(oldText);
        newText.replace(offset, offset + length, text);

        String formatted = formatText(newText.toString());
        super.replace(fb, 0, oldText.length(), formatted, attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder newText = new StringBuilder(oldText);
        newText.delete(offset, offset + length);

        String formatted = formatText(newText.toString());
        super.replace(fb, 0, oldText.length(), formatted, null);
    }
}