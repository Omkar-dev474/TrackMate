package com.example.shipments_service.kafka;

import java.util.ArrayList;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.shipments_service.Model.Shipment;
import com.example.shipments_service.Repository.ShipmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class TrackingConsumer {

   private final ShipmentRepository shipmentRepository;
   //private List<TrackingResponse> tracking;
    @KafkaListener(
        topics = "Shipment-details",
        groupId = "shipment-details-group"
    )
    public void consume(TrackingResponse event) {

        log.info("Received tracking update: {}", event);

    Shipment shipment = shipmentRepository
            .findByTrackingNumber(event.getTrackingNumber())
            .orElseThrow(() -> new RuntimeException("Shipment not found"));

    if (shipment.getTracking() == null) {
        shipment.setTracking(new ArrayList<>());
    }

    shipment.getTracking().add(event);

    shipmentRepository.save(shipment);

    log.info("Tracking update saved for {}", event.getTrackingNumber());
    }
}
