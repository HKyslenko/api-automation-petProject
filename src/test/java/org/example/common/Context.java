package org.example.common;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.Getter;

import java.io.IOException;

import static org.example.ApiHelper.getUrlFromFile;

@Getter
public class Context {
    private final Response response;

    public Context(String url) throws IOException {
        response = RestAssured.get(getUrlFromFile(url));
    }
}
