package utils;

import javax.swing.text.*;

public class NumberDocumentFilter extends DocumentFilter {
    private final int maxLength;

    public NumberDocumentFilter(int maxLength) {
        this.maxLength = maxLength;
    }

    private boolean isNumeric(String text) {
        return text.matches("\\d*"); // Solo dígitos
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;

        if (isNumeric(string) && newText.length() <= maxLength) {
            super.insertString(fb, offset, string, attr);
        } // else no insertar nada
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        String oldText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder newText = new StringBuilder(oldText);
        newText.replace(offset, offset + length, text);

        if (isNumeric(text) && newText.length() <= maxLength) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {
        super.remove(fb, offset, length);
    }
}