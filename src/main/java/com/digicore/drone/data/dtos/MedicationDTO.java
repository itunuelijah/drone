package com.digicore.drone.data.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
public class MedicationDTO {
    @JsonProperty(required = false)
    private Long id;

    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9.\\-\\/_ ]*$", message="only letters, numbers, ‘-‘, ‘_’ are allowed")
    private String name;

    @JsonProperty(required = true)
    @Max(value=500, message="Drone maximum weight is 500")
    private Double weight;


    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    @Pattern(regexp = "^[A-Z0-9.\\-\\/_ ]*$", message="Only uppercase letters, underscore and numbers are allowed")
    private String code;

    private String image;
}
