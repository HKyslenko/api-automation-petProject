package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.payloads.Category;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.example.ApiHelper.getUrlFromFile;

public class CategoryMethods {
    public static Response addCategory(Category payload) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrlFromFile("categories"));
    }

    public static Response getCategory() throws IOException {
        return when().get(getUrlFromFile("categories"));
    }

    public static Response updateCategoryById(Category payload, int id) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(getUrlFromFile("categoryById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response deleteCategoryById(int id) throws IOException {
        return given().delete(getUrlFromFile("categoryById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response getCategoryById(int id) throws IOException {
        return when().get(getUrlFromFile("categoryById").replaceAll("\\{.*?}", String.valueOf(id)));
    }
}
