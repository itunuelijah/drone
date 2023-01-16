package com.digicore.drone.mappers;

import com.digicore.drone.data.dtos.MedicationDTO;
import com.digicore.drone.data.models.Medication;
import org.springframework.beans.BeanUtils;

import java.util.Collections;
import java.util.List;

public class MedicationMapper {
    public List<MedicationDTO>toMedicationDTO(Medication medication) {
        List<MedicationDTO> medicationDTO = Collections.singletonList(new MedicationDTO());
        BeanUtils.copyProperties(medication, medicationDTO);
        return medicationDTO;
    }

    public List<Medication> toMedication(MedicationDTO medicationDTO) {
        List<Medication> medication = Collections.singletonList(new Medication());
        BeanUtils.copyProperties(medicationDTO, medication);
        return medication;
    }
}
