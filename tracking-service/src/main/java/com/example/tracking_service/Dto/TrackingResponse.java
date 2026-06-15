package com.example.tracking_service.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TrackingResponse {

    private Long id;

    private String shipmentId;
    private String trackingNumber;

    private String status;
    private String location;
    private String remarks;

    private LocalDateTime timestamp;
}