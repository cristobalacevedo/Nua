package utils;

import javax.swing.text.*;

public class RUTDocumentFilter extends DocumentFilter {
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

        // Eliminar todo lo que no sea dígito o 'k'/'K'
        String cleaned = sb.toString().replaceAll("[^\\dkK]", "").toUpperCase();

        // Limitar a 9 caracteres sin formato (8 números + dígito verificador)
        if (cleaned.length() > 9) {
            return;
        }

        // Aplicar formato: XX.XXX.XXX-X
        String formatted = formatRUT(cleaned);
        fb.replace(0, doc.getLength(), formatted, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document doc = fb.getDocument();
        String currentText = doc.getText(0, doc.getLength());

        StringBuilder sb = new StringBuilder(currentText);
        sb.delete(offset, offset + length);

        String cleaned = sb.toString().replaceAll("[^\\dkK]", "").toUpperCase();
        String formatted = formatRUT(cleaned);
        fb.replace(0, doc.getLength(), formatted, null);
    }

    private String formatRUT(String rut) {
        if (rut.length() < 2) return rut;

        String numPart = rut.substring(0, rut.length() - 1);
        String dv = rut.substring(rut.length() - 1);

        // Insertar puntos desde el final hacia el inicio
        StringBuilder formatted = new StringBuilder();
        int len = numPart.length();
        for (int i = 0; i < len; i++) {
            if (i > 0 && (len - i) % 3 == 0) {
                formatted.append('.');
            }
            formatted.append(numPart.charAt(i));
        }
        formatted.append('-').append(dv);
        return formatted.toString();
    }
}