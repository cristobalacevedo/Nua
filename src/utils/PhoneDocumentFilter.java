package utils;

import javax.swing.text.*;

public class PhoneDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;
        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) return;

        Document doc = fb.getDocument();
        String currentText = doc.getText(0, doc.getLength());
        StringBuilder sb = new StringBuilder(currentText);
        sb.replace(offset, offset + length, string);

        // Limpiar dejando solo dígitos y '+'
        String cleaned = sb.toString().replaceAll("[^\\d+]", "");

        // Separar el '+' (si existe) del resto
        String plus = cleaned.startsWith("+") ? "+" : "";
        String digits = cleaned.replaceAll("[^\\d]", "");

        // Limitar a 15 dígitos
        if (digits.length() > 15) return;

        // Aplicar formato: +(XXX) XXXX XXXX XXXX
        String formatted = formatPhone(plus, digits);
        fb.replace(0, doc.getLength(), formatted, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document doc = fb.getDocument();
        String currentText = doc.getText(0, doc.getLength());

        StringBuilder sb = new StringBuilder(currentText);
        sb.delete(offset, offset + length);

        // Limpiar
        String cleaned = sb.toString().replaceAll("[^\\d+]", "");
        String plus = cleaned.startsWith("+") ? "+" : "";
        String digits = cleaned.replaceAll("[^\\d]", "");

        String formatted = formatPhone(plus, digits);
        fb.replace(0, doc.getLength(), formatted, null);
    }

    private String formatPhone(String plus, String digits) {
        StringBuilder formatted = new StringBuilder();

        formatted.append(plus);

        int len = digits.length();
        if (len > 0) {
            formatted.append("").append(digits.substring(0, Math.min(3, len))).append("");
        }

        if (len > 3) {
            formatted.append(" ");
            formatted.append(digits.substring(3, Math.min(7, len)));
        }

        if (len > 7) {
            formatted.append(" ");
            formatted.append(digits.substring(7, Math.min(11, len)));
        }

        if (len > 11) {
            formatted.append(" ");
            formatted.append(digits.substring(11));
        }

        return formatted.toString();
    }
}
