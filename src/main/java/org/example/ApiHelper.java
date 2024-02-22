package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ApiHelper {

    private static final String BASE_URL = "http://localhost:8080";

    public static String readFileToString(String filePath) throws IOException {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
        return new String(encodedBytes);
    }

    public static String getUrlFromFile(String url) throws IOException {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, String>>() {}.getType();
        Map<String, String> endpoints = gson
                .fromJson(readFileToString("src/test/resources/json/endpoints.json"), type);
        return BASE_URL + endpoints.get(url);
    }
}
