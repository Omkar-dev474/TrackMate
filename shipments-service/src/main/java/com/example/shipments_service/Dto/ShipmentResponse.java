package com.example.shipments_service.Dto;

import lombok.*;
import java.util.List;

import com.example.shipments_service.Config.Address;
import com.example.shipments_service.kafka.TrackingResponse;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponse {
  private String id;
  private String trackingNumber;
private String senderName;
private String senderEmail;
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


private List<TrackingResponse>response;
}