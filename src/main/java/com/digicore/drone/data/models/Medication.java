package com.digicore.drone.data.models;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Slf4j
@Builder
@AllArgsConstructor
@Table(name="medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "medication_name")
    @NotNull()
    private String name;

    @Column(name ="weight")
    private Double weight;

    @Column(name="medication_code")
    private String code;

    @Column(name ="picture_url")
    private String image;

}
