package com.example.shipments_service.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrackingResponse {

    private Long id;

    private String shipmentId;
    private String trackingNumber;

    private String status;
    private String location;
    private String remarks;

    private LocalDateTime timestamp;
}