package com.digicore.drone.data.repositories;

import com.digicore.drone.data.dtos.AvailableDronesDTO;
import com.digicore.drone.data.models.Drone;
import com.digicore.drone.data.models.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, String> {
    Drone findBySerialNumber(String serialNumber);
    List<Drone> findAll();
    AvailableDronesDTO findByDroneState(DroneState state);
}
