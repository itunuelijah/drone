package com.digicore.drone.data.models;

import com.digicore.drone.data.dtos.MedicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="drone_activity")
@Data
@Builder
public class DroneActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name="drone_id")
    private String droneId;

    @Column(name="activity_state")
    @Enumerated(value = EnumType.STRING)
    private DroneState state;

    @Column(name="loaded_medications")
    @OneToMany
    private List<Medication> medications;

    @Column(name="quantity_loaded")
    private Integer quantity;

    @Column(name="max_weight_loaded")
    private Double weightLoaded;

    @Column(name="destinationm_address",columnDefinition="TEXT")
    private String address;

}