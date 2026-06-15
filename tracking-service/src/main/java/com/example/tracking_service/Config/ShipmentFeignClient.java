package com.example.tracking_service.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SHIPMENTS-SERVICE")
public interface ShipmentFeignClient {

    

    @GetMapping("/api/shipments/tracking/{trackingNumber}")
  ShipmentResponse getTrackingnumber(@PathVariable String trackingNumber);
}
