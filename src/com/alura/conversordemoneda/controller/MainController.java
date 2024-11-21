package com.alura.conversordemoneda.controller;

import com.alura.conversordemoneda.config.Config;
import com.alura.conversordemoneda.service.CurrencyConverterService;
import com.alura.conversordemoneda.service.ExchangeRateService;

import java.util.Scanner;

public class MainController {

    private CurrencyConverterService converterService;

    public void init() {

        Scanner scanner = new Scanner(System.in);

        try {
            Config apiConfig = new Config();
            ExchangeRateService exchangeRateService = new ExchangeRateService(apiConfig);
            converterService = new CurrencyConverterService(exchangeRateService);

            System.out.println("=== Conversor de Moneda ===");

            int active = 1;

            while (active == 1) {

                // Solicitar datos al usuario
                System.out.println("""
                        *************************************************
                        Sea bienvenido/a al Conversor de Monedas =]
                        
                        1) Dólar =>> Peso argentino
                        2) Peso argentino =>> Dólar
                        3) Dólar =>> Real brasileño
                        4) Real brasileño =>> Dólar
                        5) Dólar =>> Peso colombiano
                        6) Peso colombiano =>> Dólar
                        7) Salir
                        
                        Elija una opción válida:
                        *************************************************
                        """);
                // Option selection
                int option = scanner.nextInt();

                String fromCurrencyCode = "";
                String toCurrencyCode = "";

                switch (option) {
                    case 1:
                        fromCurrencyCode = "USD";
                        toCurrencyCode = "ARS";
                        break;
                    case 2:
                        fromCurrencyCode = "ARS";
                        toCurrencyCode = "USD";
                        break;
                    case 3:
                        fromCurrencyCode = "USD";
                        toCurrencyCode = "BRL";
                        break;
                    case 4:
                        fromCurrencyCode = "BRL";
                        toCurrencyCode = "USD";
                        break;
                    case 5:
                        fromCurrencyCode = "USD";
                        toCurrencyCode = "COP";
                        break;
                    case 6:
                        fromCurrencyCode = "COP";
                        toCurrencyCode = "USD";
                        break;
                    case 7:
                        active = 0;
                        System.out.println("Saliendo, gracias por usar el Conversor de Monedas =]" + "\n\n\n");
                        break;
                    default:
                        System.out.println("Opción no válida" + "\n\n\n");
                        break;
                }

                // Validar opciones
                if (!(option > 0 && option < 8)) continue;

                // Validar si el estado es activo
                if (active == 0) continue;

                // Amount
                double amount = 0;
                boolean montoValido = false;

                while (!montoValido) {
                    System.out.print("Ingrese el monto que desea convertir: ");
                    try {
                        amount = scanner.nextDouble();
                        montoValido = true; // Si no lanza excepción, el monto es válido
                    } catch (Exception e) {
                        System.err.println("Error: Ingrese un monto válido.");
                        scanner.next(); // Limpiar entrada no válida
                    }
                }

                // Realizar conversión
                double result = converterService.convert(amount, fromCurrencyCode, toCurrencyCode);
                // Con salto de linea
                System.out.println("Resultado: " + result + "\n\n\n");

            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }

    }

}
