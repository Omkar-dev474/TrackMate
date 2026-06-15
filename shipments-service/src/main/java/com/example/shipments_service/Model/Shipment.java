package com.example.shipments_service.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.shipments_service.Config.Address;
import com.example.shipments_service.kafka.TrackingResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;





@Document(collection = "shipments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {

    @Id
    private String id;

    private String trackingNumber;

    private String senderEmail;
    private String senderName;
    private String senderPhone;
    private String partnerEmail;
    private String deliveryAgentId;

    private String receiverName;

    private String receiverPhone;

    private Address pickupAddress;
    private Address deliveryAddress;

    private Double weight;

    private String shipmentType;


    private String paymentStatus;
    private String totalItems;
    private List<TrackingResponse> tracking;


}