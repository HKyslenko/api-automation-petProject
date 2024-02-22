package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.payloads.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.UserMethods.*;

public class UserTests {
    private static User user;

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        user = new User();
        user.setAddress(faker.address().fullAddress());
        user.setEmail(faker.internet().emailAddress());
        user.setUsername(faker.name().username());
        user.setPhone(faker.number().digits(10));
    }

    @Test
    public void testPostUser() throws IOException {
        Response response = addUser(user);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "User created successfully");
    }

    @Test
    public void testGetUser() throws IOException {
        Response response = getUser();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateUser() throws IOException {
        int lastUserId = getUser().jsonPath().getInt("last().id");
        Response response = updateUserById(user, lastUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "User updated successfully");
    }

    @Test
    public void testGetUserById() throws IOException {
        int lastUserId = getUser().jsonPath().getInt("last().id");
        Response response = getUserById(lastUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastUserId);
    }

    @Test
    public void testDeleteUserById() throws IOException {
        int lastUserId = getUser().jsonPath().getInt("last().id");
        Response response = deleteUserById(lastUserId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
