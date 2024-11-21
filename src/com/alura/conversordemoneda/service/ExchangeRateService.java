package com.alura.conversordemoneda.service;

import com.alura.conversordemoneda.config.Config;
import com.alura.conversordemoneda.exception.CurrencyConversionException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRateService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;
    private Map<String, Double> cachedRates = new HashMap<>();

    public ExchangeRateService(Config config) {
        this.apiKey = config.getExchangeApiKey();
    }

    public double getExchangeRate(String fromCurrency, String toCurrency)
            throws CurrencyConversionException {
        try {
            String cacheKey = fromCurrency + "_" + toCurrency;

            // Verificar caché primero
            if (cachedRates.containsKey(cacheKey)) {
                return cachedRates.get(cacheKey);
            }

            String urlStr = API_URL + apiKey + "/pair/" + fromCurrency + "/" + toCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonObject = root.getAsJsonObject();

            double conversionRate = jsonObject.get("conversion_rate").getAsDouble();

            // Guardar en caché
            cachedRates.put(cacheKey, conversionRate);

            return conversionRate;
        } catch (IOException e) {
            throw new CurrencyConversionException("Error al obtener tasa de cambio", e);
        }
    }
}

