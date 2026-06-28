package com.example.admin_service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryAgentResponse {

    private String id;

    private String partnerId;

    private String name;

    private String phone;

    private String vechicleNumber;

    private String status;
}
