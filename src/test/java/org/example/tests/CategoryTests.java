package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.payloads.Category;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.CategoryMethods.*;

public class CategoryTests {
    private static Category category;

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        category = new Category();
        category.setName(faker.animal().name());
    }

    @Test
    public void testPostCategory() throws IOException {
        Response response = addCategory(category);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Category created successfully");
    }

    @Test
    public void testGetCategory() throws IOException {
        Response response = getCategory();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateCategory() throws IOException {
        int lastCategoryId = getCategory().jsonPath().getInt("last().id");
        Response response = updateCategoryById(category, lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Category updated successfully");
    }

    @Test
    public void testGetCategoryById() throws IOException {
        int lastCategoryId = getCategory().jsonPath().getInt("last().id");
        Response response = getCategoryById(lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastCategoryId);
    }

    @Test
    public void testDeleteCategoryById() throws IOException {
        int lastCategoryId = getCategory().jsonPath().getInt("last().id");
        Response response = deleteCategoryById(lastCategoryId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
