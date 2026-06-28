package com.example.partner_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {

    private String id;

    private String partnerId;

    private String vehicleNumber;

    private String vehicleType;

    private Double capacity;

    private String registrationNumber;

    private String insuranceNumber;

    private String status;
}
