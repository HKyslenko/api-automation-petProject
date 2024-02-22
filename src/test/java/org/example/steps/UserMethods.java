package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.payloads.User;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.example.ApiHelper.getUrlFromFile;

public class UserMethods {

    public static Response addUser(User payload) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrlFromFile("users"));
    }

    public static Response getUser() throws IOException {
        return when().get(getUrlFromFile("users"));
    }

    public static Response updateUserById(User payload, int id) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(getUrlFromFile("userById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response deleteUserById(int id) throws IOException {
        return given().delete(getUrlFromFile("userById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response getUserById(int id) throws IOException {
        return when().get(getUrlFromFile("userById").replaceAll("\\{.*?}", String.valueOf(id)));
    }
}
