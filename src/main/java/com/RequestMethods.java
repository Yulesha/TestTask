package com;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import static io.restassured.RestAssured.*;

public class RequestMethods {

    public static Response accessTokenRequest() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("grant_type","client_credentials");
        requestParams.put("scope","guest:default");
        return given().
                header("Content-Type", "application/json").
                header("Authorization", "Basic ZnJvbnRfMmQ2YjBhODM5MTc0MmY1ZDc4OWQ3ZDkxNTc1NWUwOWU6").
                body(requestParams.toJSONString()).
                when().
                post("http://test-api.d6.dev.devcaz.com/v2/oauth2/token");

    }

    public static String getToken(Response resp) {
        return resp.then().extract().path("access_token");

    }

    public static Response login(Player player) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("grant_type","password");
        requestParams.put("username", player.getUsername());
        requestParams.put("password", player.getPassword());
        return given().
                header("Content-Type", "application/json").
                header("Authorization", "Basic ZnJvbnRfMmQ2YjBhODM5MTc0MmY1ZDc4OWQ3ZDkxNTc1NWUwOWU6").
                body(requestParams.toJSONString()).
                when().
                post("http://test-api.d6.dev.devcaz.com/v2/oauth2/token");
    }

    public static Response registerNewPlayer(Player player, String token) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", player.getUsername());
        requestParams.put("password_change", player.getPassword());
        requestParams.put("password_repeat", player.getPassword());
        requestParams.put("email", player.getEmail());
        requestParams.put("name", player.getName());
        requestParams.put("surname", player.getSurname());

        return given().
                header("Content-Type", "application/json").
                header("Authorization", "Bearer " + token).
                body(requestParams.toJSONString()).
                when().
                post("http://test-api.d6.dev.devcaz.com/v2/players");
    }

    public static int getUserId(Response resp) {
        return resp.then().extract().path("id");

    }

    public static Response getProfile(int id, String token) {
        return given().
                header("Authorization", "Bearer " + token).
                when().get("http://test-api.d6.dev.devcaz.com/v2/players/" + id);
    }


}
