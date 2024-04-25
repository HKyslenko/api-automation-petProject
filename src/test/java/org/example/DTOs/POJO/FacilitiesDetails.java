package org.example.DTOs.POJO;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class FacilitiesDetails {
    @JsonProperty("floors")
    private int floors;

    @JsonProperty("name")
    private String name;

    @JsonProperty("staff")
    private List<String> staff;

    @JsonProperty("equipment")
    private List<String> equipment;
}
