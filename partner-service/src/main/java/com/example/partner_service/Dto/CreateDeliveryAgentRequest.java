package com.example.partner_service.Dto;

import lombok.Data;

@Data
public class CreateDeliveryAgentRequest {

    private String partnerId;

    private String name;

    private String phone;

    private String vehicleId;
}