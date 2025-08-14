package utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class UF {
    private static final String UF_FILE = "data/uf.json";
    private static final String UF_API = "https://mindicador.cl/api/uf";

    public static double getUFValue() {
        try {
            // 1. Verificar si existe un archivo con datos
            if (Files.exists(Paths.get(UF_FILE))) {
                String content = new String(Files.readAllBytes(Paths.get(UF_FILE)));
                JSONObject json = new JSONObject(content);

                String fechaGuardada = json.getString("fecha");
                String fechaHoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                // 2. Si la fecha es de hoy, devolver valor guardado
                if (fechaHoy.equals(fechaGuardada)) {
                    return json.getDouble("valor");
                }
            }

            // 3. Si no existe o está desactualizado → consultar API
            URL url = new URL(UF_API);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            JSONObject jsonApi = new JSONObject(sb.toString());
            JSONObject ufHoy = jsonApi.getJSONArray("serie").getJSONObject(0);

            double valor = ufHoy.getDouble("valor");
            String fecha = ufHoy.getString("fecha").substring(0, 10);

            // 4. Guardar en archivo
            JSONObject jsonGuardar = new JSONObject();
            jsonGuardar.put("fecha", fecha);
            jsonGuardar.put("valor", valor);

            Files.write(Paths.get(UF_FILE), jsonGuardar.toString().getBytes());

            return valor;

        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Error
        }
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
