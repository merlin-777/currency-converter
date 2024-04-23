import com.google.gson.Gson;
import com.x.models.ErrorResponse;
import com.x.models.ExchangeRate;
import com.x.models.PairConversion;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {
    static final String APIKEY="97b9818f06166490644b4310";
    static final String DOMAIN="https://v6.exchangerate-api.com/v6/";

    public static PairConversion pairConversion(double amount, String baseCode, String targetCode){
        URI url = URI.create( DOMAIN+APIKEY+"/pair/"+baseCode+"/"+targetCode+"/"+amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode >= 400 && statusCode < 600) {
                ErrorResponse errorResponse = new Gson().fromJson(response.body(),ErrorResponse.class);
                throw new RuntimeException(errorResponse.toString());
            }

            return new Gson().fromJson(response.body(), PairConversion.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            client.close();
        }
    }

    public static ExchangeRate exchangeRate(String currencyCode){
        URI url = URI.create( DOMAIN+APIKEY+"/latest/"+currencyCode);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode >= 400 && statusCode < 600) {
                ErrorResponse errorResponse = new Gson().fromJson(response.body(),ErrorResponse.class);
                throw new RuntimeException(errorResponse.toString());
            }

            return new Gson().fromJson(response.body(), ExchangeRate.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            client.close();
        }
    }
}
