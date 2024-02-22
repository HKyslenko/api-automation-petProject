package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.payloads.Tag;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.example.ApiHelper.getUrlFromFile;

public class TagMethods {

    public static Response addTag(Tag payload) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrlFromFile("tags"));
    }

    public static Response getTag() throws IOException {
        return when().get(getUrlFromFile("tags"));
    }

    public static Response updateTagById(Tag payload, int id) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(getUrlFromFile("tagById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response deleteTagById(int id) throws IOException {
        return given().delete(getUrlFromFile("tagById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response getTagById(int id) throws IOException {
        return when().get(getUrlFromFile("tagById").replaceAll("\\{.*?}", String.valueOf(id)));
    }
}
