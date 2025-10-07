package de.musab.currency;

import java.util.Date;

/**
 * Schnittstelle zwischen UI (Fenster) und Geschäftslogik.
 */
public interface ICurrencyConverter {
    /**
     * Verfügbare Währungen z. B. {"USD","EUR","GBP"}.
     *
     * @return Array mit Währungen
     */
    String[] getCurrencies();

    /**
     * Verfügbare Umrechnungsarten z. B. {"Echtzeit","Historisch","Fix"}.
     *
     * @return Array mit Converter-Namen (Keys)
     */
    String[] getConverters();

    /**
     * Führt eine Umrechnung aus.
     *
     * @param amount Betrag in der Ausgangswährung
     * @param sourceCurrency Ausgangswährung
     * @param targetCurrency Zielwährung
     * @param keyCurrency    Name/Key des gewünschten Converters (z. B. "Historisch")
     * @return Ergebnisbetrag in Zielwährung
     */
    public double performConversion(double amount, String sourceCurrency, String targetCurrency, String keyCurrency);

    /**
     * Setzt das Datum, das z. B. für historische Umrechnungen genutzt wird.
     *
     * @param date ausgewähltes Datum aus der UI
     */
    void setDate(Date date);

    /**
     * Liefert das aktuell gespeicherte Datum (z. B. für historische Umrechnung).
     *
     * @return Datum
     */
    Date getDate();

}
