package backend.backendTesting;

import backend.models.currencyExchange.Response;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Inga on 10/05/2017.
 */
public class CurrencyRequester {
    private String URL_CURRENCY = "http://api.fixer.io/latest";

    public Response getCurrency() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Response  response;
        response = mapper.readValue(new URL(URL_CURRENCY), Response.class);
        return response;
    }
}
