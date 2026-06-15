package com.example.partner_service.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Document(collection = "vehicles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    private String id;

    private String partnerId;

    private String vehicleNumber;

    private String vehicleType;

    private Double capacity;

    private String registrationNumber;

    private String insuranceNumber;

    private String status;
}
