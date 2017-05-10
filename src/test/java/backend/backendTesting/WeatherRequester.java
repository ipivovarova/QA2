package backend.backendTesting;

import backend.models.weather.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Inga on 07/05/2017.
 */
public class WeatherRequester {
    private String URL_WEATHER = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1";

    public Response getWeather() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        Response  response = mapper.readValue(new URL(URL_WEATHER), Response.class);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL_WEATHER, String.class);
        String jsonResponse = responseEntity.getBody();
        Response response = mapper.readValue(jsonResponse, Response.class);
        return response;
    }
}
