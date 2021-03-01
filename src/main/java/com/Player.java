package com;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;

public class Player {

    private int id;
    private final String username;
    private final String password;
    private final String email;
    private final String name;
    private final String surname;
    private final String currency;

    public Player() {
        String randomString = getRandomString();
        this.username = randomString;
        this.password = Base64.getEncoder().encodeToString(randomString.getBytes(StandardCharsets.UTF_8));
        this.email = randomString + "@example.com";
        this.name = randomString;
        this.surname = randomString;
        this.currency = "EUR";
    }

    private String getRandomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 6;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCurrency() {
        return currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
