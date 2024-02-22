package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.payloads.Order;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.OrderMethods.*;

public class OrderTests {
    private static Order order;

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        order = new Order();
        order.setQuantity(faker.number().numberBetween(1, 10));
        order.setComplete(false);
        order.setPet_id(faker.number().numberBetween(1, 10));
        order.setStatus("approved");
        order.setShipDate(faker.date().toString());
    }

    @Test
    public void testPostOrder() throws IOException {
        Response response = addOrder(order);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Order created successfully");
    }

    @Test
    public void testGetOrder() throws IOException {
        Response response = getOrder();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateOrder() throws IOException {
        int lastOrderId = getOrder().jsonPath().getInt("last().id");
        Response response = updateOrderById(order, lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Order updated successfully");
    }

    @Test
    public void testGetOrderById() throws IOException {
        int lastOrderId = getOrder().jsonPath().getInt("last().id");
        Response response = getOrderById(lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastOrderId);
    }

    @Test
    public void testDeleteOrderById() throws IOException {
        int lastOrderId = getOrder().jsonPath().getInt("last().id");
        Response response = deleteOrderById(lastOrderId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
