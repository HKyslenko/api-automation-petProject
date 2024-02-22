package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.payloads.Tag;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.TagMethods.*;

public class TagTests {
    private static Tag tag;

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        tag = new Tag();
        tag.setName(faker.company().industry());
    }

    @Test
    public void testPostTag() throws IOException {
        Response response = addTag(tag);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Tag created successfully");
    }

    @Test
    public void testGetTag() throws IOException {
        Response response = getTag();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdateTag() throws IOException {
        int lastTagId = getTag().jsonPath().getInt("last().id");
        Response response = updateTagById(tag, lastTagId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Tag updated successfully");
    }

    @Test
    public void testGetTagById() throws IOException {
        int lastTagId = getTag().jsonPath().getInt("last().id");
        Response response = getTagById(lastTagId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastTagId);
    }

    @Test
    public void testDeleteTagById() throws IOException {
        int lastTagId = getTag().jsonPath().getInt("last().id");
        Response response = deleteTagById(lastTagId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
