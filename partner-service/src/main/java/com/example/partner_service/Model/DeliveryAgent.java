package com.example.partner_service.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "delivery_agents")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAgent {

    @Id
    private String id;

    private String partnerId;

    private String name;

    private String phone;

    private String vehicleNumber;

    private String status;
}