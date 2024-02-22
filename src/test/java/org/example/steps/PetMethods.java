package org.example.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.payloads.Pet;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.example.ApiHelper.getUrlFromFile;

public class PetMethods {

    public static Response addPet(Pet payload) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(getUrlFromFile("pets"));
    }

    public static Response getPet() throws IOException {
        return when().get(getUrlFromFile("pets"));
    }

    public static Response updatePetById(Pet payload, int id) throws IOException {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put(getUrlFromFile("petById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response deletePetById(int id) throws IOException {
        return given().delete(getUrlFromFile("petById").replaceAll("\\{.*?}", String.valueOf(id)));
    }

    public static Response getPetById(int id) throws IOException {
        return when().get(getUrlFromFile("petById").replaceAll("\\{.*?}", String.valueOf(id)));
    }
}
