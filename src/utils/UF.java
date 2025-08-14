package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UF {
    public static double getUFValue() {
        double valorUF = 0.0;
        try {
            Process p = Runtime.getRuntime().exec("python UF_value/ufclp.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Valor UF")) {
                    // Extraemos solo los dígitos y punto decimal
                    String[] parts = line.split("\\$");
                    String numberPart = parts[1].trim().split(" ")[0]; // toma solo "39178.72"
                    valorUF = Double.parseDouble(numberPart);
                    break;
                }
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valorUF;
    }

//    public static void main(String[] args) {
//        double ufHoy = getUFValue();
//        System.out.println("UF hoy: " + ufHoy);
//    }
}