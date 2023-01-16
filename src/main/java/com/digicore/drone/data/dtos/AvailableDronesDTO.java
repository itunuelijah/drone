package com.digicore.drone.data.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class AvailableDronesDTO {
    private int total;
    private List<DroneDTO> drones;
}
