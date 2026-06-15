package com.example.tracking_service.Config;




import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentResponse {
   private String id;
  private String trackingNumber;
}