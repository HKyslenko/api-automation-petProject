package org.example.DTOs.POJO;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CoursesDetails {

    @JsonProperty("code")
    private String code;

    @JsonProperty("instructor")
    private String instructor;

    @JsonProperty("name")
    private String name;

    @JsonProperty("students")
    private List<String> students;
}
