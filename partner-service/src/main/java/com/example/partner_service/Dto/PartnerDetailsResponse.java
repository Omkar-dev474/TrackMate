package com.example.partner_service.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PartnerDetailsResponse {

    private String id;

    private String companyName;

    private String contactPerson;

    private String email;

    private String phone;

    private String address;

    private String status;

    private List<DeliveryAgentResponse> deliveryAgents;

    private List<VehicleResponse> vehicles;
}