package org.example.DTOs.POJO;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UniversityDetails {
    @JsonProperty("name")
    private String name;

    @JsonProperty("departments")
    private List<DepartmentsDetails> departments;

    @JsonProperty("location")
    private LocationDetails location;
}
