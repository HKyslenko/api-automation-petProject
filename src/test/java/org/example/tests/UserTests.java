package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.DTOs.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.BaseSteps.*;

public class UserTests {
    private static User user;
    private static final Faker faker = new Faker();
    private final String USER_URL = "users";
    private final String USER_BY_ID = "userById";

    @BeforeClass
    public static void setupData() {
        user = new User.UserBuilder()
                .address(faker.address().fullAddress())
                .email(faker.internet().emailAddress())
                .username(faker.name().username())
                .phone(faker.number().digits(10))
                .build();
    }

    @Test
    public void testPostUser() throws IOException {
        Response response = addItem(USER_URL, user);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "User created successfully");
    }

    @Test
    public void testNegativePostUser() throws IOException {
        User user = new User.UserBuilder().build();
        Response response = addItem(USER_URL, user);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("message"), "Missing required field: username");
    }

    @Test
    public void testGetUser() throws IOException {
        Response response = getItems(USER_URL);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateUser() throws IOException {
        int lastUserId = getItems(USER_URL).jsonPath().getInt("last().id");
        Response response = updateItemById(USER_BY_ID, user, lastUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "User updated successfully");
    }

    @Test
    public void testNegativeUpdateUser() throws IOException {
        int lastUserId = getItems(USER_URL).jsonPath().getInt("last().id");
        User user = new User.UserBuilder()
                .email(faker.name().name())
                .build();

        Response response = updateItemById(USER_BY_ID, user, lastUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("message"), "Invalid email format");
    }

    @Test
    public void testGetUserById() throws IOException {
        int lastUserId = getItems(USER_URL).jsonPath().getInt("last().id");
        Response response = getItemById(USER_BY_ID, lastUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastUserId);
    }

    @Test
    public void testNegativeGetUserById() throws IOException {
        Response response = getItemById(USER_BY_ID, 0);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("error"), "User not found");
    }

    @Test
    public void testDeleteUserById() throws IOException {
        int lastUserId = getItems(USER_URL).jsonPath().getInt("last().id");
        Response response = deleteItemById(USER_BY_ID, lastUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test
    public void testNegativeDeleteUserById() throws IOException {
        Response response = deleteItemById(USER_BY_ID, 0);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("message"), "User not found");
    }
}
