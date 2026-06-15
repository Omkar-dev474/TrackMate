package com.example.partner_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.partner_service.Dto.CreateDeliveryAgentRequest;
import com.example.partner_service.Dto.CreatePartnerRequest;
import com.example.partner_service.Dto.CreateVehicleRequest;
import com.example.partner_service.Dto.DeliveryAgentResponse;
import com.example.partner_service.Dto.PartnerResponse;
import com.example.partner_service.Dto.VehicleResponse;
import com.example.partner_service.Service.PartnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("/partners")
    public ResponseEntity<?> createPartner(@RequestBody CreatePartnerRequest request, @RequestHeader("X-USER-ID") String userId) {
        try {
            PartnerResponse partner = partnerService.createPartner(request,userId);
            if (partner != null) {
                return ResponseEntity.ok(partner);
            }
            return ResponseEntity.status(400).body("Failed to create partner");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating partner: " + e.getMessage());
        }
    }

    @PostMapping("/delivery-agents")
    public ResponseEntity<?> createDeliveryAgent(@RequestBody CreateDeliveryAgentRequest request) {
        try {
            DeliveryAgentResponse response = partnerService.createDeliveryAgent(request);
            if (response != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(400).body("Failed to create delivery agent");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating delivery agent: " + e.getMessage());
        }
    }

    @PostMapping("/vechicles")
    public ResponseEntity<?> createVechicle(@RequestBody CreateVehicleRequest request) {
        try {
            VehicleResponse response = partnerService.createVechicle(request);
             if (response != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(400).body("Failed to create vehicle");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating delivery agent: " + e.getMessage());
        }
    }


    @GetMapping("/{partnerId}/exists")
    public ResponseEntity<?> checkPartnerExists(@PathVariable String partnerId) {
        try {
            boolean exists = partnerService.checkPartnerExists(partnerId);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error checking partner existence: " + e.getMessage());
        }
    }

    @GetMapping("/{partnerId}")
    public ResponseEntity<?> getPartnerById(@PathVariable String partnerId) {
        try {
            PartnerResponse response = partnerService.getPartnerById(partnerId);
            if (response != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(404).body("Partner not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving partner: " + e.getMessage());
        }
    }
}
