import com.Player;
import com.RequestMethods;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

public class ApiTest {

    @Test
    public void test_Get_guest_token() {
        Response resp = RequestMethods.accessTokenRequest();
        resp.then().
                assertThat().
                statusCode(200).
                and().body("$", hasKey("access_token"));
    }

    @Test
    public void test_Registration() {
        Response resp = RequestMethods.accessTokenRequest();
        String token = RequestMethods.getToken(resp);
        Player player = new Player();
        Response response = RequestMethods.registerNewPlayer(player, token);
        response.then().
                assertThat().
                statusCode(201).
                and().body(matchesJsonSchemaInClasspath("createdPlayer.json"));
    }

    @Test
    public void test_Authorization() {
        Response resp = RequestMethods.accessTokenRequest();
        String token = RequestMethods.getToken(resp);
        Player player = new Player();
        RequestMethods.registerNewPlayer(player, token);
        Response response = RequestMethods.login(player);
        response.then().
                assertThat().
                statusCode(200).
                and().body("$", hasKey("access_token"));
    }

    @Test
    public void test_Get_user_profile() {
        Response resp = RequestMethods.accessTokenRequest();
        String token = RequestMethods.getToken(resp);
        Player player = new Player();
        resp = RequestMethods.registerNewPlayer(player, token);
        int userId = RequestMethods.getUserId(resp);
        resp = RequestMethods.login(player);
        String registeredToken = RequestMethods.getToken(resp);
        Response response = RequestMethods.getProfile(userId, registeredToken);
        response.then().
                assertThat().
                statusCode(200).
                and().body(matchesJsonSchemaInClasspath("createdPlayer.json"));
    }

    @Test
    public void test_Get_other_user_profile() {
        Response resp = RequestMethods.accessTokenRequest();
        String token = RequestMethods.getToken(resp);
        Player player = new Player();
        resp = RequestMethods.registerNewPlayer(player, token);
        int userId = RequestMethods.getUserId(resp);
        resp = RequestMethods.login(player);
        String registeredToken = RequestMethods.getToken(resp);
        Response response = RequestMethods.getProfile(userId+1, registeredToken);
        response.then().
                assertThat().
                statusCode(404);
    }
}
