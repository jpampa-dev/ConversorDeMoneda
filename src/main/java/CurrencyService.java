package main.java;


//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.hc.client5.http.fluent.Request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/";
    private final String apiKey;

    public CurrencyService(String apiKey) {
        this.apiKey = apiKey;
    }

    public double getExchangeRate(String fromCurrencyCode, String toCurrencyCode, Double amount) throws IOException {
        String url_str = API_URL + apiKey + "/pair/" + fromCurrencyCode + "/" + toCurrencyCode + "/" + amount;
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonObject = root.getAsJsonObject();

        String req_result = jsonObject.get("conversion_result").getAsString();
        // Convert to double
        double result = Double.parseDouble(req_result);

        return result;
    }
}
