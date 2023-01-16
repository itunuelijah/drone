package com.digicore.drone.data.repositories;

import com.digicore.drone.data.models.DroneActivity;
import com.digicore.drone.data.models.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository <DroneActivity,Long>{

        List<DroneActivity> findByDroneIdAndStateIn(String droneId, List<DroneState> states);
}
