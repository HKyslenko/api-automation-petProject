package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.DTOs.Category;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.BaseSteps.*;

public class CategoryTests {
    private static Category category;
    private final String CATEGORY_URL = "categories";
    public final String CATEGORY_BY_ID = "categoryById";

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        category = new Category.CategoryBuilder()
                .name(faker.animal().name())
                .build();
    }

    @Test
    public void testPostCategory() throws IOException {
        Response response = addItem(CATEGORY_URL, category);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Category created successfully");
    }

    @Test
    public void testGetCategory() throws IOException {
        Response response = getItems(CATEGORY_URL);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateCategory() throws IOException {
        int lastCategoryId = getItems(CATEGORY_URL).jsonPath().getInt("last().id");
        Response response = updateItemById(CATEGORY_BY_ID, category, lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Category updated successfully");
    }

    @Test
    public void testGetCategoryById() throws IOException {
        int lastCategoryId = getItems(CATEGORY_URL).jsonPath().getInt("last().id");
        Response response = getItemById(CATEGORY_BY_ID, lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastCategoryId);
    }

    @Test
    public void testDeleteCategoryById() throws IOException {
        int lastCategoryId = getItems(CATEGORY_URL).jsonPath().getInt("last().id");
        Response response = deleteItemById(CATEGORY_BY_ID, lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
