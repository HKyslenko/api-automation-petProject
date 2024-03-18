package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.DTOs.Order;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.BaseSteps.*;

public class OrderTests {
    private static Order order;
    private final static Faker faker = new Faker();
    private final String ORDER_URL = "orders";
    private final String ORDER_BY_ID = "orderById";

    @BeforeClass
    public static void setupData() {
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
    public void testNegativePostOrder() throws IOException {
        Order order = new Order.OrderBuilder().build();
        Response response = addItem(ORDER_URL, order);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("message"), "Missing required fields: pet_id, quantity, status, shipDate");
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
    public void testNegativeUpdateOrder() throws IOException {
        int lastOrderId = getItems(ORDER_URL).jsonPath().getInt("last().id");
        Order order = new Order.OrderBuilder().build();
        Response response = updateItemById(ORDER_BY_ID, order, lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("message"), "Invalid quantity format");
    }

    @Test
    public void testGetOrderById() throws IOException {
        int lastOrderId = getItems(ORDER_URL).jsonPath().getInt("last().id");
        Response response = getItemById(ORDER_BY_ID, lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastOrderId);
    }

    @Test
    public void testNegativeGetOrderById() throws IOException {
        Response response = getItemById(ORDER_BY_ID, 0);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("message"), "Order not found");
    }

    @Test
    public void testDeleteOrderById() throws IOException {
        int lastOrderId = getItems(ORDER_URL).jsonPath().getInt("last().id");
        Response response = deleteItemById(ORDER_BY_ID, lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test
    public void testNegativeDeleteOrderById() throws IOException {
        Response response = deleteItemById(ORDER_BY_ID, 0);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("message"), "Order not found");
    }
}
