package main.java;

import java.io.IOException;

public class CurrencyConverter {
    private final CurrencyService currencyService;

    public CurrencyConverter(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public double convert(String fromCurrencyCode, String toCurrencyCode, double amount) throws IOException {
        double exchangeRate = currencyService.getExchangeRate(fromCurrencyCode, toCurrencyCode, amount);
        return exchangeRate;
    }
}
