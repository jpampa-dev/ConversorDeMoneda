package com.alura.conversordemoneda.service;

import com.alura.conversordemoneda.exception.CurrencyConversionException;

public class CurrencyConverterService {
    private final ExchangeRateService exchangeRateService;

    public CurrencyConverterService(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public double convert(double amount, String fromCurrency, String toCurrency)
            throws CurrencyConversionException {
        if (amount < 0) {
            throw new CurrencyConversionException("El monto no puede ser negativo");
        }

        double rate = exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
        return amount * rate;
    }
}