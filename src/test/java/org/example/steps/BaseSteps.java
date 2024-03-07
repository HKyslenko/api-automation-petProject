package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.example.ApiHelper.getUrlFromFile;

public class BaseSteps {
    public static <Payload> Response addItem(String url, Payload payload) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrlFromFile(url));
    }

    public static Response getItems(String url) throws IOException {
        return when().get(getUrlFromFile(url));
    }

    public static <Payload> Response updateItemById(String url, Payload payload, int id) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(getUrlFromFile(url).replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response deleteItemById(String url, int id) throws IOException {
        return given().delete(getUrlFromFile(url).replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response getItemById(String url, int id) throws IOException {
        return when().get(getUrlFromFile(url).replaceAll("\\{.*?}", String.valueOf(id)));
    }
}
