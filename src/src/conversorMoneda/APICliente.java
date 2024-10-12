package conversorMoneda;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APICliente {
    private static final String apiKey = "1fb60bf01f1b18c11ec4da0d"; // Coloca tu API Key aquí
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Método para obtener las tasas de cambio
    public JsonObject getExchangeRates(String baseCurrency) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Leer la respuesta
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();

        // Convertir a JSON
        JsonParser jp = new JsonParser();
        return jp.parse(responseBuilder.toString()).getAsJsonObject();
    }
}
