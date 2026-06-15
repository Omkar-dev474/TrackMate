package com.example.shipments_service.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PARTNER-SERVICE")
public interface PartnerFeginClient {

    @GetMapping("/api/partners/{partnerId}")
    PartnerResponse getPartnerById(@PathVariable String partnerId);
}
