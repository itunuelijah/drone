package com.digicore.drone.services;


import com.digicore.drone.data.models.Drone;
import com.digicore.drone.data.models.DroneModel;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class DroneServiceImplTest {


    @Autowired
    private DroneServiceImpl droneService;


    private Drone droneDTO;

    @BeforeEach
    void setUp() {
        droneDTO = new Drone();
        droneDTO.setBatteryLife(100);
        droneDTO.setDroneModel(DroneModel.HEAVYWEIGHT);
        droneDTO.setMaxWeight(500.0);
        droneDTO.setSerialNumber("110110TEST");

    }


//    @Test
//    void test_whenregisterdrone_returnsdto() {
//
//        Drone _dto = droneService.registerDrone();
//        Assertions.assertNotNull(_dto.getSerialNumber());
//
//    }
}
//
//
////    @Test
////    void whenavailableDrones_returnsList() {
////
////        AvailableDronesDTO availableDrones = droneService.listAvailableDrones();
////        Assertions.assertNotNull(availableDrones);
////
////        Assertions.assertNotNull(availableDrones.getDrones());
////
////        assertThat(availableDrones.getDrones()).isNotEmpty();
////    }
//
//    @Test
//    void when_given_serialnumber_returns_droneDTO() {
//
//        droneDTO = new Drone();
//        droneDTO.setBatteryLife(100);
//        droneDTO.setDroneModel(DroneModel.HEAVYWEIGHT);
//        droneDTO.setMaxWeight(500.0);
//        droneDTO.setSerialNumber("110110TEST2");
//
//
//        droneService.registerDrone(droneDTO);
//        Drone dto = droneService.getDrone("110110TEST2");
//        assertThat(dto.getSerialNumber()).isEqualTo(droneDTO.getSerialNumber());
//    }
//
//
//}
//
//
////    @Test
////    void registerDrone() {
////    }
////
////    @Test
////    void getDrone() {
////    }
////
////    @Test
////    void getDroneAudit() {
////    }
////
////    @Test
////    void listAvailableDronesLoading() {
////    }
////
////    @Test
////    void checkBattery() {
////    }
//}