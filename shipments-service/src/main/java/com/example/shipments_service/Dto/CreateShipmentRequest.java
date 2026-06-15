package com.example.shipments_service.Dto;



import com.example.shipments_service.Config.Address;


import lombok.Data;

@Data
public class CreateShipmentRequest {

    //private String senderId;
    private String partnerId;
    private String deliveryAgentId;

    private String receiverName;
    private String receiverPhone;

    private Address pickupAddress;
    private Address deliveryAddress;

    private Double weight;
    private String shipmentType;

    private String paymentStatus;
    private String totalItems;
    
}