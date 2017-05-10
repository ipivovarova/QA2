package backend.backendTesting;

import backend.models.currencyExchange.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Inga on 10/05/2017.
 */
public class CurrencyTest {
    private final static String CCY_USD = "USD";
    private final static String CCY_GBP = "GBP";
    private final static BigDecimal USD_RATE = BigDecimal.valueOf(1.0882);
    private final static BigDecimal GBP_RATE = BigDecimal.valueOf(0.83985);


    @Test
    public  void currencyTest() throws IOException {
        CurrencyRequester requester = new CurrencyRequester();
        //Get Response from server
        Response response = requester.getCurrency();
        //Check response
        Assert.assertEquals("Wrong rate:", USD_RATE, response.getRates().get(CCY_USD));
    }
}
