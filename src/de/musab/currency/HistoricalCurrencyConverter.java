package de.musab.currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class HistoricalCurrencyConverter implements CurrencyConverter {


    private ICurrencyConverter iCurrencyConverter;

    public HistoricalCurrencyConverter(ICurrencyConverter icurrencyConverter) {
        this.iCurrencyConverter = icurrencyConverter;
    }

    @Override
    public double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {
        final String apiUrl = "https://api.freecurrencyapi.com/v1/historical";
        final String apikey = "fca_live_FhQOUe20rZbYBU9DwwjCoCRyNztnCRmJjA4V5yla";
        LocalDate localDate = iCurrencyConverter.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final String dateString = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        try {
            final String stringApi = String.format("%s?apikey=%s&base_currency=%s&currencies=%s&date=%s", apiUrl, apikey, sourceCurrency, targetCurrency, URLEncoder.encode(dateString, "UTF-8"));

            URL url = new URL(stringApi);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("Fehler bei der API-Anfrage" + httpURLConnection.getResponseCode());
                return -1;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String response = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response += line;
            }
            System.out.println(response);
            bufferedReader.close();
            double exchangeRate = extractExchangeRate(response, targetCurrency);
            return amount * exchangeRate;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
    * Extrahiert den Wechselkurs einer Zielwährung aus einer JSON-Antwort der Currency-API.
     * @param data die komplette JSON-Antwort der API als String
     * @param targetCurrency die Zielwährung
     * @return der extrahierte Wechselkurs als double
    */

    public double extractExchangeRate(String data, String targetCurrency)
    {
        final String target = "\"" + targetCurrency + "\":";
        final int index = data.indexOf(target) + target.length();
        final String number = data.substring(index, data.length() - 3);
        return Double.parseDouble(number);
    }



}
