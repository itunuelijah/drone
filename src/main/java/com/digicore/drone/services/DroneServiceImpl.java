package com.digicore.drone.services;

import com.digicore.drone.data.dtos.*;
import com.digicore.drone.data.models.Drone;
import com.digicore.drone.data.models.DroneActivity;
import com.digicore.drone.data.models.DroneState;
import com.digicore.drone.data.models.Medication;
import com.digicore.drone.data.repositories.ActivityRepository;
import com.digicore.drone.data.repositories.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private ActivityRepository activityRepository;


    private int  BATTERY_LIMIT = 25;

    @Override
    public DroneDTO registerDrone(DroneDTO droneDto) {

        Drone drone = new Drone();
        if (drone.getBatteryLife() >= BATTERY_LIMIT) {
            drone.setDroneState(DroneState.LOADING);
        } else {
            drone.setDroneState(DroneState.IDLE);
        }

        drone.setCurrentWeight(0.0);
        Drone savedDrone = droneRepository.save(drone);

        return buildDrone(savedDrone);

    }

    private DroneDTO buildDrone(Drone drone) {
        return DroneDTO.builder()
                .droneState(String.valueOf(drone.getDroneState()))
                .weightLoaded(drone.getCurrentWeight())
                .build();
    }


    @Override
    public Drone getDrone(String serialNumber) {
        return droneRepository.findBySerialNumber(serialNumber);
    }



    @Transactional
    @Override
    public Response loadDrone(LoadDroneDTO loadDroneDTO) {

        Drone drone = droneRepository.findBySerialNumber(loadDroneDTO.getSerialNumber());

        if (drone == null)
            return parseResponse("Drone does not exist. Check the serial number", "error");

        List<Medication> medications = medicationService.createMedicationList(loadDroneDTO.getMedications());
        DroneActivity activity = buildLoadDroneDto(loadDroneDTO, drone);



        if(drone.getDroneState() != DroneState.LOADING)
            return parseResponse("Drone is fully loaded and no longer available, please try another drone","error");

        if(activity.getWeightLoaded()>drone.getMaxWeight())
            return parseResponse("The weight loaded is heavier than the drone capacity","error");

        double availableWeight = drone.getMaxWeight() - drone.getCurrentWeight();

        if(activity.getWeightLoaded()>availableWeight)
            return parseResponse("Weight loaded is greater than available space","error");

        double totalWeight = drone.getCurrentWeight()+activity.getWeightLoaded();

        //Load drone and update status

        drone.setCurrentWeight(totalWeight);
        if(drone.getMaxWeight() == totalWeight) {
            drone.setDroneState(DroneState.LOADED);
        }
        activity.setState(drone.getDroneState());
        activity.setMedications(medications);
        activityRepository.save(activity);
        droneRepository.save(drone);

        return parseResponse(drone.getSerialNumber(),"OK");


    }

    private Response parseResponse(String res,String status) {
        Response resp = new Response();
        resp.setData(res);
        resp.setStatus(status);
        return resp;

    }


    private DroneActivity buildLoadDroneDto(LoadDroneDTO loadDroneDTO, Drone drone) {
        return DroneActivity.builder()
                .weightLoaded(loadDroneDTO.getTotalWeight())
                .state(DroneState.valueOf(loadDroneDTO.getDroneState()))
                .address(loadDroneDTO.getDeliveryAddress())
                .medications(loadDroneDTO.getMedications())
                .quantity(loadDroneDTO.getQuantity())
                .droneId(drone.getSerialNumber())
                .build();
    }

    @Override
    public List<Medication> getLoadedMedicationListByDroneId(String droneId){
        return medicationService.listMedicationByGivenDrone(droneId);
    }


    @Override
    public AuditDTO getDroneAudit() {

        List<Drone> drones = droneRepository.findAll();
        List<DroneBatteryDTO> batteryList = buildBatteries((Drone) drones);

        return  buildAudit(batteryList);
    }


        private AuditDTO buildAudit(List<DroneBatteryDTO> batteryDTOS){
        return  AuditDTO.builder()
                .drones(batteryDTOS)
                .dateTime(LocalDateTime.now())
                .total(batteryDTOS.size())
                .build();
    }

        private  List<DroneBatteryDTO> buildBatteries(Drone drone){
             return Collections.singletonList(DroneBatteryDTO.builder()
                    .serialNumber(drone.getSerialNumber())
                    .model(String.valueOf(drone.getDroneModel()))
                    .batteryLife(drone.getBatteryLife())
                    .build());
    }
    @Override
    public AvailableDronesDTO listAvailableDronesLoading() {
        return droneRepository.findByDroneState(DroneState.LOADING);
    }

    @Override
    public DroneBatteryDTO checkBattery(String serialNumber) {

        Drone drone = droneRepository.findBySerialNumber(serialNumber);
        return buildBattery(drone);
    }
    private DroneBatteryDTO buildBattery(Drone drone){
        return DroneBatteryDTO.builder()
                .serialNumber(drone.getSerialNumber())
                .model(String.valueOf(drone.getDroneModel()))
                .batteryLife(drone.getBatteryLife())
                .build();
    }
}
