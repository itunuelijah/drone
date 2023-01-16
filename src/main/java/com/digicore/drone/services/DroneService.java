package com.digicore.drone.services;

import com.digicore.drone.data.dtos.*;
import com.digicore.drone.data.models.Drone;
import com.digicore.drone.data.models.Medication;

import javax.transaction.Transactional;
import java.util.List;

public interface DroneService {
    DroneDTO registerDrone(DroneDTO droneDto);

    Drone getDrone(String serialNumber);

    @Transactional
    Response loadDrone(LoadDroneDTO loadDroneDTO);

    List<Medication> getLoadedMedicationListByDroneId(String droneId);

    AuditDTO getDroneAudit();

    AvailableDronesDTO listAvailableDronesLoading();

    DroneBatteryDTO checkBattery(String serialNumber);
}
