package edu.currency.converter.controllers;

import com.google.gson.Gson;
import edu.currency.converter.exceptions.CurrencyNotFoundException;
import edu.currency.converter.models.entities.Currency;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyController {
    /** Get a rate to convert two currencies.
     *
     * @return the rate for conversion.
     */
    public Currency getCurrenciesRate(String endpoint) {
        URI uri = URI.create(endpoint);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Currency.class);
        } catch (Exception e) {
            throw new CurrencyNotFoundException("Par de Moedas não encontrado");
        }
    }
}
