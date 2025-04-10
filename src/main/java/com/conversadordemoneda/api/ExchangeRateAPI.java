package com.conversadordemoneda.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ExchangeRateAPI {

    private static final String API_KEY = "5b0cde0f30091f38bd22a835";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {

        if (baseCurrency == null || targetCurrency == null || baseCurrency.length() != 3 || targetCurrency.length() != 3) {
            throw new IllegalArgumentException("Las monedas deben ser códigos de 3 letras (ej: USD, EUR)");
        }

        HttpClient client = HttpClient.newHttpClient();
        String requestUrl = BASE_URL + baseCurrency + "/" + targetCurrency;
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            if (!jsonResponse.get("result").getAsString().equals("success")) {
                String errorType = jsonResponse.get("error-type").getAsString();
                throw new RuntimeException("Error en la API: " + errorType);
            }
            
            return jsonResponse.get("conversion_rate").getAsDouble();
            
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }
}
