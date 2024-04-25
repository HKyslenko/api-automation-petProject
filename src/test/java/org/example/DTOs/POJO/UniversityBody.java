package org.example.DTOs.POJO;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UniversityBody {
    @JsonProperty("university")
    private UniversityDetails university;

}
