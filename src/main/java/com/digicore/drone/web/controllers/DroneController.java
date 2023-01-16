package com.digicore.drone.web.controllers;


import com.digicore.drone.data.dtos.*;
import com.digicore.drone.services.DroneService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/drones")
public class DroneController {


    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }


    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO dto) {

        DroneDTO _dto = droneService.registerDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(_dto);
    }


    @GetMapping(path = "/{serialNumber}")
    public ResponseEntity<DroneBatteryDTO> getDroneBattery(@PathVariable("serialNumber") String serialNumber) {

        DroneBatteryDTO dto = droneService.checkBattery(serialNumber);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else
            return ResponseEntity.notFound().build();

    }


    @PostMapping(value="/load",consumes=APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> loadDrone(@Valid @RequestBody LoadDroneDTO dto){
        Response response = droneService.loadDrone(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/available")
    public ResponseEntity<AvailableDronesDTO> findAvailableDrones() {

        AvailableDronesDTO dto = droneService.listAvailableDronesLoading();
        return ResponseEntity.ok(dto);
    }


    @GetMapping("/audit")
    public ResponseEntity<AuditDTO> getDroneAudit() {
        AuditDTO audit = droneService.getDroneAudit();
        return ResponseEntity.ok(audit);

    }

}

