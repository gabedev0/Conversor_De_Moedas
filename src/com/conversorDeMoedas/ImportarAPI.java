package com.conversorDeMoedas;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import com.google.gson.Gson;

public class ImportarAPI {
    public static com.conversorDeMoedas.ResponseMoeda getMoedaResponse(String Moeda1) throws Exception {

        String key = "68d5abdef9aad4d2f29aa68b";
        String url = "https://v6.exchangerate-api.com/v6/" + key + "/latest/" + Moeda1;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            Gson gson = new Gson();
            return gson.fromJson(response.body(), com.conversorDeMoedas.ResponseMoeda.class);
        } else {
            throw new Exception("Falha na API");
        }
    }
}