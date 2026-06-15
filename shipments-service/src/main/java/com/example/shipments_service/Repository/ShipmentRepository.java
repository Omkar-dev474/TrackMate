package com.example.shipments_service.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.shipments_service.Model.Shipment;

@Repository
public interface ShipmentRepository extends MongoRepository<Shipment, String> {

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    Shipment findById(Long id);

    List<Shipment> findBySenderEmail(String email);

  
    
}
