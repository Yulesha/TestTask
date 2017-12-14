import com.google.gson.JsonParser;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * Created by user on 14.12.17.
 */
class RequestMethods {

    @Step("Send request")
    static HttpURLConnection sendRequest() {
        HttpURLConnection connection = null;
        try {
            URL obj = new URL("https://jsonplaceholder.typicode.com/users");
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
        }
        catch (IOException e){
            GeneralMethods.saveErrorLog("Sending request has problem");
        }
        return connection;

    }

    @Step("Check response code is 200 - OK")
    static void checkCode(HttpURLConnection con) {
        try {
            Assert.assertEquals(con.getResponseCode(), 200);
        }
        catch(IOException e){
            GeneralMethods.saveErrorLog("We can not get response");
        }
    }

    @Step("Check response has 10 JSON objects")
    static String checkResponse(HttpURLConnection con) {
        String response = null;
        try {
            response = getResponse(con);
        }
        catch (IOException e){
            GeneralMethods.saveErrorLog("We can not get response");

        }
        JsonParser parser = new JsonParser();
        assert response != null;
        Assert.assertEquals(parser.parse(response).getAsJsonArray().size(),10);
        return response;
    }

    private static String getResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @Step("Make JSON validation")
    static void validationJson(String result) {
        MatcherAssert.assertThat(result, matchesJsonSchemaInClasspath("json-schema.json"));
    }
}
