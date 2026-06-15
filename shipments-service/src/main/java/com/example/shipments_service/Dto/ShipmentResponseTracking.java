package com.example.shipments_service.Dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponseTracking {
   private String id;
  private String trackingNumber;
}
