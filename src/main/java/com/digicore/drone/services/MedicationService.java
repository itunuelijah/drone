package com.digicore.drone.services;

import com.digicore.drone.data.dtos.MedicationDTO;
import com.digicore.drone.data.models.Medication;

import java.util.List;

public interface MedicationService {


    void createMedication(MedicationDTO medicationDTO);

    List<Medication> createMedicationList(List<Medication> medDtos);

    List<Medication> listMedications();

    List<Medication> listMedicationByGivenDrone(String droneSerialNumber);
}
