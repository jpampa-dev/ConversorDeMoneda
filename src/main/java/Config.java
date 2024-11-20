package main.java;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private final String apiKey;

    public Config() {
        Dotenv dotenv = Dotenv.configure().load();
        this.apiKey = dotenv.get("EXCHANGE_API_KEY");
        if (this.apiKey == null || this.apiKey.isEmpty()) {
            throw new IllegalStateException("API Key no encontrada. Verifica el archivo .env.");
        }
    }

    public String getApiKey() {
        return apiKey;
    }
}
