package com.digicore.drone.services;

import com.digicore.drone.data.dtos.MedicationDTO;
import com.digicore.drone.data.models.Drone;
import com.digicore.drone.data.models.Medication;
import com.digicore.drone.data.repositories.MedicationRepository;
import com.digicore.drone.mappers.MedicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MedicationServiceImpl implements MedicationService{


    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private  DroneService droneService;

    private MedicationMapper mapper;



    public MedicationServiceImpl() {
        this.mapper = mapper;
    }
    @Override
    public void createMedication(MedicationDTO medicationDTO) {

        Medication medication = buildMedication(medicationDTO);
        medicationRepository.save(medication);

    }

    @Override
    public List<Medication> createMedicationList(List<Medication> medicationDTOS) {

        List<Medication> meds = buildMedicationList(medicationDTOS);

        List<Medication> actualList = new ArrayList<>();

        medicationRepository.saveAll(meds).iterator().forEachRemaining(actualList :: add);

        return actualList;

    }




    private Medication buildMedication(MedicationDTO medication) {
        return Medication.builder()
                .name(medication.getName())
                .weight(medication.getWeight())
                .code(medication.getCode())
                .image(medication.getImage())
                .build();
    }

    private List<Medication> buildMedicationList(List<Medication> medication) {
        return Collections.singletonList(Medication.builder().build());
    }


    @Override
    public List<Medication> listMedications() {
        return medicationRepository.findAll();
    }

    @Override
    public List<Medication> listMedicationByGivenDrone(String droneSerialNumber) {
         Drone drone = droneService.getDrone(droneSerialNumber);
        return medicationRepository.findAllByDrone(drone);
    }

}
