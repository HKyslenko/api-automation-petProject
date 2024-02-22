package org.example.tests;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.example.payloads.Pet;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.example.steps.PetMethods.*;

public class PetTests {
    private static Pet pet;

    @BeforeClass
    public static void setupData() {
        Faker faker = new Faker();
        pet = new Pet();
        pet.setName(faker.funnyName().name());
        pet.setCategory_id(faker.number().numberBetween(0, 10));
        pet.setPhotoUrls(faker.internet().url());
        pet.setStatus("available");
        pet.setTags("Wild, Fierce");
    }

    @Test
    public void testPostPet() throws IOException {
        Response response = addPet(pet);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().get("message"), "Pet created successfully");
    }

    @Test
    public void testGetPet() throws IOException {
        Response response = getPet();
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void testUpdatePet() throws IOException {
        int lastPetId = getPet().jsonPath().getInt("last().id");
        Response response = updatePetById(pet, lastPetId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().get("message"), "Pet updated successfully");
    }

    @Test
    public void testGetPetById() throws IOException {
        int lastPetId = getPet().jsonPath().getInt("last().id");
        Response response = getPetById(lastPetId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), lastPetId);
    }

    @Test
    public void testDeletePetById() throws IOException {
        int lastPetId = getPet().jsonPath().getInt("last().id");
        Response response = deletePetById(lastPetId);
        response.then().log().all();
        Assert.assertEquals(response.statusCode(), 204);
    }
}
