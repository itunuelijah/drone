package com.digicore.drone.data.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class AuditDTO {
    private List<DroneBatteryDTO> drones;
    private int total;
    private LocalDateTime dateTime;
}
