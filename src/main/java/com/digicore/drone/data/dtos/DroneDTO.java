package com.digicore.drone.data.dtos;



import com.digicore.drone.data.models.DroneModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DroneDTO {

    @Size(min=2,max=100,message="Drone serial number must not be greater than {value} characters")
    @JsonProperty(required = true)
    @NotEmpty
    @NotBlank
    private String serialNumber;


    @JsonProperty(required = true)
    @NotNull(message="Drone model must not be empty")
    private DroneModel droneModel;


    @DecimalMax(value = "500", message =" Drone maximum weight is {value} grams")
    private Double maxWeight;

    @JsonProperty(required = true)
    @NotNull(message="Battery life must not be null")
    private Integer batteryLife;

    @JsonIgnore
    private String droneState;


    private Double weightLoaded;

    @JsonProperty(required = true)
    @NotEmpty(message="Add medications before loading to drone")
    private List<MedicationDTO> medications;
}
