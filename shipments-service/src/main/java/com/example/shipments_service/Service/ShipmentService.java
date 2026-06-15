package com.example.shipments_service.Service;

import java.util.List;
import java.util.Optional;

//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.shipments_service.Config.PartnerFeginClient;
import com.example.shipments_service.Config.PartnerResponse;
import com.example.shipments_service.Config.ResponseUserDto;
import com.example.shipments_service.Config.UserFeignClient;
import com.example.shipments_service.Dto.CreateShipmentRequest;
import com.example.shipments_service.Dto.ShipmentResponse;
import com.example.shipments_service.Dto.ShipmentResponseTracking;
import com.example.shipments_service.Model.Shipment;
import com.example.shipments_service.Repository.ShipmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;

    private final UserFeignClient userFeignClient;

    private final PartnerFeginClient partnerFeginClient;

    // private final KafkaTemplate<String,ShipmentResponse> kafkatemplate;

    public ShipmentResponse createShipment(CreateShipmentRequest request, String userId) {

        ResponseUserDto senderInfo = userFeignClient.getUserById(userId);
        if (senderInfo == null) {
            throw new RuntimeException("Sender not found");
        }

        PartnerResponse partnerInfo = partnerFeginClient.getPartnerById(request.getPartnerId());
        if (partnerInfo == null) {
            throw new RuntimeException("Partner not found");
        }
        System.out.println(partnerInfo.getEmail() + " " + partnerInfo.getCompanyName());
        System.out.println(senderInfo.getEmail() + " " + senderInfo.getAddress());

        Shipment shipment = Shipment.builder()
                .trackingNumber("TM" + System.currentTimeMillis())
                .senderEmail(senderInfo.getEmail())
                .senderName(senderInfo.getFirstName() + " " + senderInfo.getLastName())
                .senderPhone(senderInfo.getPhoneNumber())
                .partnerEmail(partnerInfo.getEmail())
                .deliveryAgentId(request.getDeliveryAgentId())
                .receiverName(request.getReceiverName())
                .receiverPhone(request.getReceiverPhone())
                .pickupAddress(senderInfo.getAddress())
                .deliveryAddress(request.getDeliveryAddress())
                .weight(request.getWeight())
                .shipmentType(request.getShipmentType())
                .paymentStatus(request.getPaymentStatus())
                .totalItems(request.getTotalItems())
                .build();

        Shipment savedShipment = shipmentRepository.save(shipment);

        // try{
        // kafkatemplate.send("Shipment-details",mapToShipmentResponseDto(savedShipment));
        // System.out.println("Kafka details send");
        // }catch(Exception e){
        // e.printStackTrace();
        // }

        return mapToShipmentResponseDto(savedShipment);
    }

    public ShipmentResponse mapToShipmentResponseDto(Shipment shipment) {

        return ShipmentResponse.builder()
                .id(shipment.getId())
                .trackingNumber(shipment.getTrackingNumber())

                .senderName(shipment.getSenderName())
                .senderEmail(shipment.getSenderEmail())
                .senderPhone(shipment.getSenderPhone())

                .partnerEmail(shipment.getPartnerEmail())
                .deliveryAgentId(shipment.getDeliveryAgentId())

                .receiverName(shipment.getReceiverName())
                .receiverPhone(shipment.getReceiverPhone())

                .pickupAddress(shipment.getPickupAddress())
                .deliveryAddress(shipment.getDeliveryAddress())

                .weight(shipment.getWeight())
                .shipmentType(shipment.getShipmentType())

                .response(shipment.getTracking())
                .paymentStatus(shipment.getPaymentStatus())
                .totalItems(shipment.getTotalItems())

                .build();
    }

    public ShipmentResponseTracking getShipmentById(String shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        return mapToShipmentResponseTrackingDto(shipment);
    }

    public ShipmentResponseTracking mapToShipmentResponseTrackingDto(Shipment shipment) {
        return ShipmentResponseTracking.builder()
                .id(shipment.getId())
                .trackingNumber(shipment.getTrackingNumber())
                .build();
    }

    public ShipmentResponse getShipmentByTracking(String trackingNumber) {
        Optional<Shipment> ship = shipmentRepository.findByTrackingNumber(trackingNumber);
        Shipment s = ship.get();
        if (s == null) {
            throw new RuntimeException("Shipment not found");
        }
        return mapToShipmentResponseDto(s);

    }

    public List<ShipmentResponse> getloggedInUserShipments(String email) {

        List<Shipment> shipments = shipmentRepository.findBySenderEmail(email);

        return shipments.stream()
                .map(this::mapToShipmentResponseDto)
                .toList();
    }

}
