package utils;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import com.google.gson.Gson;;
import com.google.gson.GsonBuilder;

public class UF {

    private static final String CACHE_FILE = "uf_cache.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Clase para representar el JSON
    private static class UFCache {
        String fecha;
        double valor;
    }

    public static double getUFValue() {
        // 1. Intentar leer desde caché
        Double cachedValue = readCache();
        if (cachedValue != null) {
            return cachedValue;
        }

        // 2. Si no hay caché o está vencido, obtener desde Python
        double valorUF = 0.0;
        try {
            Process p = Runtime.getRuntime().exec("python UF_value/ufclp.py");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Valor UF")) {
                    String[] parts = line.split("\\$");
                    String numberPart = parts[1].trim().split(" ")[0];
                    valorUF = Double.parseDouble(numberPart);
                    break;
                }
            }
            in.close();

            // 3. Guardar en caché
            saveCache(valorUF);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return valorUF;
    }

    private static Double readCache() {
        try {
            if (!Files.exists(Paths.get(CACHE_FILE))) {
                return null; // No existe caché
            }
            String content = Files.readString(Paths.get(CACHE_FILE));
            UFCache cache = gson.fromJson(content, UFCache.class);

            if (cache.fecha.equals(LocalDate.now().toString())) {
                return cache.valor; // Aún válido
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Caché vencido o error
    }

    private static void saveCache(double valor) {
        try {
            UFCache cache = new UFCache();
            cache.fecha = LocalDate.now().toString();
            cache.valor = valor;

            Files.writeString(Paths.get(CACHE_FILE), gson.toJson(cache));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Test
  //  public static void main(String[] args) {
  //      System.out.println("UF hoy: " + getUFValue());
 //   }
//}


//    public static void main(String[] args) {
//        double ufHoy = getUFValue();
//        System.out.println("UF hoy: " + ufHoy);
//    }
}