package utils;

public class RUTValidator {

    public static boolean isValid(String rut) {
    	if (rut == null) return false;
        
        rut = rut.replace(".", "").replace("-", "").trim().toUpperCase();
        
        if (!rut.matches("\\d{7,8}[0-9K]")) return false;

        String cuerpo = rut.substring(0, rut.length() - 1);
        char dvIngresado = rut.charAt(rut.length() - 1);

        int suma = 0;
        int factor = 2;

        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(cuerpo.charAt(i)) * factor;
            factor = (factor == 7) ? 2 : factor + 1;
        }

        int resto = 11 - (suma % 11);
        char dvEsperado;

        if (resto == 11) dvEsperado = '0';
        else if (resto == 10) dvEsperado = 'K';
        else dvEsperado = (char) (resto + '0');

        return dvIngresado == dvEsperado;
    }

    public static String cleanRUT(String rut) {
        return rut.replace(".", "").replace("-", "").trim();
    }
    
    public static String formatRUT(String rut) {
        rut = cleanRUT(rut);
        if (rut.length() < 2) return rut;

        String cuerpo = rut.substring(0, rut.length() - 1);
        String dv = rut.substring(rut.length() - 1);

        StringBuilder formatted = new StringBuilder();
        int count = 0;
        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            formatted.insert(0, cuerpo.charAt(i));
            count++;
            if (count == 3 && i != 0) {
                formatted.insert(0, ".");
                count = 0;
            }
        }
        formatted.append("-").append(dv);
        return formatted.toString();
    }

}