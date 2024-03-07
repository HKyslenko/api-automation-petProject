package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.DTOs.Pet;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.BaseSteps.*;

public class PetTests {
    private static Pet pet;
    private final String PET_URL = "pets";
    private final String PET_BY_ID = "petById";

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        pet = new Pet.PetBuilder()
                .name(faker.funnyName().name())
                .category_id(faker.number().numberBetween(0, 10))
                .photoUrls(faker.internet().url())
                .status("available")
                .tags("Wild, Fierce")
                .build();
    }

    @Test
    public void testPostPet() throws IOException {
        Response response = addItem(PET_URL, pet);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Pet created successfully");
    }

    @Test
    public void testGetPet() throws IOException {
        Response response = getItems(PET_URL);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdatePet() throws IOException {
        int lastPetId = getItems(PET_URL).jsonPath().getInt("last().id");
        Response response = updateItemById(PET_BY_ID, pet, lastPetId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Pet updated successfully");
    }

    @Test
    public void testGetPetById() throws IOException {
        int lastPetId = getItems(PET_URL).jsonPath().getInt("last().id");
        Response response = getItemById(PET_BY_ID, lastPetId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastPetId);
    }

    @Test
    public void testDeletePetById() throws IOException {
        int lastPetId = getItems(PET_URL).jsonPath().getInt("last().id");
        Response response = deleteItemById(PET_BY_ID, lastPetId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
