package com.example.admin_service.Config;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("SHIPMENTS-SERVICE")
public  interface StreamShipmentConfig {
   

}

