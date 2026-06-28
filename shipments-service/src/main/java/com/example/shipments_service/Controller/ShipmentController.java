package com.example.shipments_service.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shipments_service.Dto.CreateShipmentRequest;
import com.example.shipments_service.Dto.ShipmentResponse;
import com.example.shipments_service.Dto.ShipmentResponseTracking;
import com.example.shipments_service.Service.ShipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shipments")
@RequiredArgsConstructor
public class ShipmentController {

    private final ShipmentService shipmentService;

    @PostMapping("/shipments")
    public ResponseEntity<?> createShipment(@RequestBody CreateShipmentRequest request,@RequestHeader("X-USER-ID") String userId) {
        try {
            ShipmentResponse response = shipmentService.createShipment(request, userId);
            if (response != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(400).body("Failed to create shipment");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating shipment: " + e.getMessage());
        }
    }

    @GetMapping("/UsersShipments")
    public ResponseEntity<?> getallshipmentsOfLoggedUser(@RequestHeader("X-USER-EMAIL") String email) {
        try {
            List<ShipmentResponse> response = shipmentService.getloggedInUserShipments(email);
            if (response != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(404).body("Shipment not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving shipment: " + e.getMessage());
        }
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<?> getShipmentById(@PathVariable String shipmentId) {
        try {
            ShipmentResponseTracking response = shipmentService.getShipmentById(shipmentId);
            if (response != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(404).body("Shipment not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving shipment: " + e.getMessage());
        }
    }

    @GetMapping("/tracking/{trackingNumber}")
    @Cacheable(value="Shipment",key="#trackingNumber")
    public ResponseEntity<?> getTrackingnumber(@PathVariable String trackingNumber) {
        try {
            
            ShipmentResponse response = shipmentService.getShipmentByTracking(trackingNumber);
            if (response != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(404).body("Shipment not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error getting tracking number: " + e.getMessage());
        }
    }

  

    // @PostMapping("/shipments/test")
    // public ResponseEntity<?> createShipment(
    //         @RequestBody CreateShipmentRequest request,
    //         @RequestHeader("X-USER-ID") String userId,
    //         @RequestHeader("X-USER-EMAIL") String email,
    //         @RequestHeader("X-USER-ROLE") String role) {

    //     return ResponseEntity.ok(
    //             Map.of(
    //                     "userId", userId,
    //                     "email", email,
    //                     "role", role,
    //                     "request", request));

    // }

   
    // @GetMapping("/check")
    // public String check(
    //         @RequestHeader("X-USER-ID") String userId,
    //         @RequestHeader("X-USER-NAME") String username,
    //         @RequestHeader("X-USER-EMAIL") String email,
    //         @RequestHeader("X-USER-ROLE") String role) {

    //     return userId + " | " + username + " | " + email + " | " + role;
    // }

}
