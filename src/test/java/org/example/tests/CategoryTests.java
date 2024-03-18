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
    private static final Faker faker = new Faker();
    private final String CATEGORY_URL = "categories";
    public final String CATEGORY_BY_ID = "categoryById";

    @BeforeClass
    public static void setupData() {
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
    public void testNegativePostCategory() throws IOException {
        Category category = new Category.CategoryBuilder().build();
        Response response = addItem(CATEGORY_URL, category);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("message"), "Missing required field: name");
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
    public void testNegativeUpdateCategory() throws IOException {
        int lastCategoryId = getItems(CATEGORY_URL).jsonPath().getInt("last().id");
        Category category = new Category.CategoryBuilder()
                .name(faker.lorem().characters(51))
                .build();

        Response response = updateItemById(CATEGORY_BY_ID, category, lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.jsonPath().get("message"), "Name must be less than 50 characters long");
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
    public void testNegativeGetCategoryById() throws IOException {
        Response response = getItemById(CATEGORY_BY_ID, 0);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("message"), "Category not found");
    }

    @Test
    public void testDeleteCategoryById() throws IOException {
        int lastCategoryId = getItems(CATEGORY_URL).jsonPath().getInt("last().id");
        Response response = deleteItemById(CATEGORY_BY_ID, lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }

    @Test
    public void testNegativeDeleteCategoryById() throws IOException {
        Response response = deleteItemById(CATEGORY_BY_ID, 0);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 404);
        Assert.assertEquals(response.jsonPath().get("message"), "Category not found");
    }
}
