package backend.backendTesting;

import backend.models.weather.Response;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Inga on 07/05/2017.
 */
public class WeatherTest {

    @Test
    public void wetherTest() throws IOException {
        WeatherRequester requester = new WeatherRequester();
        //Get Response from server
        Response response = requester.getWeather();
        //Check response
        Assert.assertEquals("Wrong long", BigDecimal.valueOf(-0.13), response.getCoord().getLon());
        Assert.assertEquals("Wrong lat", BigDecimal.valueOf(51.51), response.getCoord().getLat());
    }
}
