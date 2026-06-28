package com.example.admin_service.Config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.admin_service.Dto.PartnerDetailsResponse;

@FeignClient("PARTNER-SERVICE")
public interface StreamPartnerConfig {

    @GetMapping("/api/admin/allpartner")
    List<PartnerDetailsResponse> allPartners(
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search);

    @DeleteMapping("/api/admin/delete/{id}")
    public Boolean deletePartner(@PathVariable int id);
}
