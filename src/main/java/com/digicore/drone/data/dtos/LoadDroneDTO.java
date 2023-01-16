package com.digicore.drone.data.dtos;

import com.digicore.drone.data.models.Medication;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class LoadDroneDTO {

    @Size(min=3,max=100,message="Drone serial number must not be greater than {value} characters")
    @JsonProperty(required = true)

    @NotBlank
    private String serialNumber;

    private String droneModel;

    @JsonProperty(required = false)
    private String droneState;

    @JsonProperty(required = true)
    @NotNull
    private Integer quantity;

    @JsonProperty(required = true)
    @NotNull
    private Double totalWeight;

    @JsonProperty(required = true)
    private String deliveryAddress;

    @JsonProperty(required = true)
    private List<Medication> medications;
}