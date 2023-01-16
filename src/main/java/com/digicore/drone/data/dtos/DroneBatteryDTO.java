package com.digicore.drone.data.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DroneBatteryDTO {
    private String serialNumber;
    private String model;
    private int batteryLife;

}
