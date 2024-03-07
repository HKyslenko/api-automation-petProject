package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.internal.RestAssuredResponseImpl;
import io.restassured.response.Response;
import org.example.DTOs.Order;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.example.steps.BaseSteps.*;

public class OrderTests {
    private static Order order;
    private final String ORDER_URL = "orders";
    private final String ORDER_BY_ID = "orderById";

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        order = new Order.OrderBuilder()
                .quantity(faker.number().numberBetween(1, 10))
                .complete(false)
                .pet_id(faker.number().numberBetween(1, 10))
                .status("approved")
                .shipDate(faker.date().toString())
                .build();
        // TODO
        // parse dateTime
    }

    @Test
    public void testPostOrder() throws IOException {
        Response response = addItem(ORDER_URL, order);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Order created successfully");
    }

    @Test
    public void testGetOrder() throws IOException {
        Response response = getItems(ORDER_URL);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateOrder() throws IOException {
        int lastOrderId = getItems(ORDER_URL).jsonPath().getInt("last().id");
        Response response = updateItemById(ORDER_BY_ID, order, lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Order updated successfully");
    }

    @Test
    public void testGetOrderById() throws IOException {
        int lastOrderId = getItems(ORDER_URL).jsonPath().getInt("last().id");
        Response response = response = getItemById(ORDER_BY_ID, lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastOrderId);
    }

    @Test
    public void testDeleteOrderById() throws IOException {
        int lastOrderId = getItems(ORDER_URL).jsonPath().getInt("last().id");
        Response response = deleteItemById(ORDER_BY_ID, lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
