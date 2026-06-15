package com.example.tracking_service.Dto;

import lombok.Data;

@Data
public class CreateTrackingRequest {

  
    private String trackingNumber;
    private String status;
    private String location;
    private String remarks;
}