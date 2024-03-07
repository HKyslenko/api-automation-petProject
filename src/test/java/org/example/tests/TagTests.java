package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.DTOs.Tag;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.BaseSteps.*;

public class TagTests {
    private static Tag tag;
    private final String TAG_URL = "tags";
    private final String TAG_BY_ID = "tagById";

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        tag = new Tag.TagBuilder()
                .name(faker.company().industry())
                .build();
    }

    @Test
    public void testPostTag() throws IOException {
        Response response = addItem(TAG_URL, tag);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Tag created successfully");
    }

    @Test
    public void testGetTag() throws IOException {
        Response response = getItems(TAG_URL);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateTag() throws IOException {
        int lastTagId = getItems(TAG_URL).jsonPath().getInt("last().id");
        Response response = updateItemById(TAG_BY_ID, tag, lastTagId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Tag updated successfully");
    }

    @Test
    public void testGetTagById() throws IOException {
        int lastTagId = getItems(TAG_URL).jsonPath().getInt("last().id");
        Response response = getItemById(TAG_BY_ID, lastTagId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastTagId);
    }

    @Test
    public void testDeleteTagById() throws IOException {
        int lastTagId = getItems(TAG_URL).jsonPath().getInt("last().id");
        Response response = deleteItemById(TAG_BY_ID, lastTagId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
