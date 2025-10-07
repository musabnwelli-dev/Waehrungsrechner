package de.musab.currency;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Zentrale Vermittler-Klasse zwischen UI und den konkreten Convertern.
 */
public class CurrencyConversionHandler implements ICurrencyConverter {
    private String[] currencies = {"USD", "EUR", "GBP"};
    private Date selectedDate;

    private final Map<String, CurrencyConverter> converters = new HashMap<>();

    /** Erzeugt den Handler und registriert die verfügbaren Strategien. */
    public CurrencyConversionHandler()
    {
        converters.put("Echtzeit", new LatestCurrencyConverter());
        converters.put("Fix", new FixedCurrencyConverter());
        converters.put("Historisch", new HistoricalCurrencyConverter(this));
    }
    @Override
    public String[] getCurrencies() {
        return currencies;
    }

    @Override
    public String[] getConverters() {
        return converters.keySet().toArray(new String[0]);
    }

    @Override
    public double performConversion(double amount, String sourceCurrency, String targetCurrency, String keyCurrency) {
       CurrencyConverter converter = converters.get(keyCurrency);
       if (converter == null)
       {
           throw new UnsupportedOperationException("Ungültiger Converter");
       }
       return converter.convertCurrency(amount, sourceCurrency, targetCurrency);
    }

    @Override
    public void setDate(Date date) {
       this.selectedDate = date;
    }

    @Override
    public Date getDate() {
        return selectedDate;
    }
}
