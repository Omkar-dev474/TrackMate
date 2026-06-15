package com.example.partner_service.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryAgentResponse {

    private String id;

    private String partnerId;

    private String name;

    private String phone;

    private String vechicleNumber;

    private String status;
}
