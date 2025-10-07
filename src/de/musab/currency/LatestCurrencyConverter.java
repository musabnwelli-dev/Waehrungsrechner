package de.musab.currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LatestCurrencyConverter implements CurrencyConverter {

    @Override
    public double convertCurrency(double amount, String sourceCurrency, String targetCurrency) {

        final String apiUrl = "https://api.freecurrencyapi.com/v1/latest";
        final String apikey = "fca_live_FhQOUe20rZbYBU9DwwjCoCRyNztnCRmJjA4V5yla";
        final String stringApi = String.format("%s?apikey=%s&base_currency=%s&currencies=%s", apiUrl, apikey, sourceCurrency, targetCurrency);
        try {

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
            double result = extractExchangeRate(response, targetCurrency);
            return amount * result;

        } catch (Exception e)
        {
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
    /// {"data":{"EUR":0.8571700985}}
    public double extractExchangeRate(String data, String targetCurrency)
    {
        final String target = "\"" + targetCurrency + "\":";
        final int index = data.indexOf(target) + target.length();
        String number = data.substring(index, data.length() - 2);
        return Double.parseDouble(number);
    }
}
