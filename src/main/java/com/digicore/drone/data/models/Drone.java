package com.digicore.drone.data.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="drones")
public class Drone {

    @Id
    @Column(name="serial_number", length = 100)
    private String serialNumber;

    @Column(name="model")
    @Enumerated(value = EnumType.STRING)
    private DroneModel droneModel;

    @Column(name="weight_max")
    @DecimalMax(value = "500", message =" Drone maximum weight is {value} grams")
    private Double maxWeight;

    @Column(name="battery_capacity")
    @Max(value=100,message="Battery capacity in %")
    private Integer batteryLife;

    @Column(name = "drone_state")
    @Enumerated(value = EnumType.STRING)
    private DroneState droneState;

    @Column(name = "current_weight")
    private Double currentWeight;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Medication> medicationList;

    public void addMedication(Medication medication){
        if(medicationList == null){
            medicationList = new ArrayList<>();
        }
        medicationList.add(medication);
    }
}
