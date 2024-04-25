package org.example.DTOs.POJO;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DepartmentsDetails {
    @JsonProperty("courses")
    private List<CoursesDetails> courses;

    @JsonProperty("head")
    private String head;

    @JsonProperty("name")
    private String name;
}
