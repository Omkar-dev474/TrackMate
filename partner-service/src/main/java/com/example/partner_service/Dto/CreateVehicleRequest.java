package com.example.partner_service.Dto;

import lombok.Data;

@Data
public class CreateVehicleRequest {

    private String partnerId;

    private String vehicleNumber;

    private String vehicleType;

    private Double capacity;

    private String registrationNumber;

    private String insuranceNumber;
}
