package de.musab.currency;

import java.util.HashMap;
import java.util.Map;
/**
 * Converter mit festen (statischen) Wechselkursen.
 * Eignet sich für Tests oder als Fallback, wenn keine API verfügbar ist.
 */

public class FixedCurrencyConverter implements CurrencyConverter {
    private final Map<String, Double> fixedCurrency = new HashMap<>();

    /** Befüllt die interne Kurstabelle. */
    public FixedCurrencyConverter()
    {
        fixedCurrency.put("USD", 1.0);
        fixedCurrency.put("EUR", 0.85);
        fixedCurrency.put("GBP", 0.73);
    }

    @Override
    public double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {
       final double sourceExchangeRate = fixedCurrency.get(sourceCurrency);
       final double targetExchangeRate = fixedCurrency.get(targetCurrency);


        return amount * (targetExchangeRate / sourceExchangeRate);
    }
}
