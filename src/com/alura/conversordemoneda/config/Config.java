package com.alura.conversordemoneda.config;
import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private final String exchangeApiKey;

    public Config() {
        Dotenv dotenv = Dotenv.configure().load();
        this.exchangeApiKey = dotenv.get("EXCHANGE_API_KEY");

        if (this.exchangeApiKey == null || this.exchangeApiKey.isEmpty()) {
            throw new IllegalStateException("API Key no encontrada. Verifica el archivo .env.");
        }
    }

    public String getExchangeApiKey() {
        return exchangeApiKey;
    }
}