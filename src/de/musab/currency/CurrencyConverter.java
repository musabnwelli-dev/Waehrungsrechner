package de.musab.currency;

/**
 * Strategie-Interface für unterschiedliche Umrechnungsarten.
 * Implementierungen: Echtzeit-API, Historische API, Feste Tabelle.
 */
public interface CurrencyConverter {

    /**
     * Rechnet amount von sourceCurrency nach targetCurrency um.
     *
     * @param amount Betrag in Ausgangswährung
     * @param sourceCurrency Ausgangswährung
     * @param targetCurrency Zielwährung
     * @return Ergebnisbetrag in Zielwährung
     */
    public double convertCurrency(double amount, String sourceCurrency, String targetCurrency);
}
