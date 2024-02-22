package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.payloads.Order;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.example.ApiHelper.getUrlFromFile;

public class OrderMethods {

    public static Response addOrder(Order payload) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrlFromFile("orders"));
    }

    public static Response getOrder() throws IOException {
        return when().get(getUrlFromFile("orders"));
    }

    public static Response updateOrderById(Order payload, int id) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(getUrlFromFile("orderById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response deleteOrderById(int id) throws IOException {
        return given().delete(getUrlFromFile("orderById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response getOrderById(int id) throws IOException {
        return when().get(getUrlFromFile("orderById").replaceAll("\\{.*?}", String.valueOf(id)));
    }
}
