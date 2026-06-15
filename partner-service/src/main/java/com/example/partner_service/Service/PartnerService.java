package com.example.partner_service.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.partner_service.Dto.CreateDeliveryAgentRequest;
import com.example.partner_service.Dto.CreatePartnerRequest;
import com.example.partner_service.Dto.CreateVehicleRequest;
import com.example.partner_service.Dto.DeliveryAgentResponse;
import com.example.partner_service.Dto.PartnerResponse;
import com.example.partner_service.Dto.VehicleResponse;
import com.example.partner_service.Model.DeliveryAgent;
import com.example.partner_service.Model.Partner;
import com.example.partner_service.Model.Vehicle;
import com.example.partner_service.Repository.DeliverAgentRepository;
import com.example.partner_service.Repository.PartnerRepository;
import com.example.partner_service.Repository.VechicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final UserServiceValidation userServiceValidation;
    private final DeliverAgentRepository deliverRepository;
    private final VechicleRepository vechicleRepository;

    public PartnerResponse createPartner(CreatePartnerRequest request,String userId) {

        boolean userExists =
                userServiceValidation.checkUser(userId) != null;

        if (!userExists) {
            throw new RuntimeException("User not found");
        }

        Partner partner = Partner.builder()
                .userId(userId)
                .companyName(request.getCompanyName())
                .gstNumber(request.getGstNumber())
                .contactPerson(request.getContactPerson())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .status("ACTIVE")
                .build();

        Partner savedPartner = partnerRepository.save(partner);

        return mapToResponse(savedPartner);
    }

    private PartnerResponse mapToResponse(Partner partner) {

        return PartnerResponse.builder()
                .id(partner.getId())
                .userId(partner.getUserId())
                .companyName(partner.getCompanyName())
                .gstNumber(partner.getGstNumber())
                .contactPerson(partner.getContactPerson())
                .email(partner.getEmail())
                .phone(partner.getPhone())
                .address(partner.getAddress())
                .status(partner.getStatus())
                .createdAt(partner.getCreatedAt())
                .build();
    }

    public DeliveryAgentResponse createDeliveryAgent(CreateDeliveryAgentRequest request) {
       
        boolean exists = partnerRepository.existsById(request.getPartnerId());
        if (!exists) {
            throw new RuntimeException("Partner not found");
        }

        boolean vehicleExists = vechicleRepository.existsById(request.getVehicleId());
        if (!vehicleExists) {
            throw new RuntimeException("Vehicle not found");
        }   
        Optional<Vehicle> vehicle = vechicleRepository.findById(request.getVehicleId());
        Vehicle veh = vehicle.get();

        DeliveryAgent deliveryAgent = DeliveryAgent.builder()
                .partnerId(request.getPartnerId())
                .vehicleNumber(veh.getVehicleNumber())
                .name(request.getName())
                .phone(request.getPhone())
                .status("ACTIVE")
                .build();
        DeliveryAgent savedAgent = deliverRepository.save(deliveryAgent);
        return mapToDeliveryAgentResponse(savedAgent);
    }
    private DeliveryAgentResponse mapToDeliveryAgentResponse(DeliveryAgent deliveryAgent) {
      return DeliveryAgentResponse.builder()
                .id(deliveryAgent.getId())
                .partnerId(deliveryAgent.getPartnerId())
                .name(deliveryAgent.getName())         
                .phone(deliveryAgent.getPhone())
                .status(deliveryAgent.getStatus())
                .vechicleNumber(deliveryAgent.getVehicleNumber())
                .build();
    }

    public VehicleResponse createVechicle(CreateVehicleRequest request) {
       
        boolean exists = partnerRepository.existsById(request.getPartnerId());
        if (!exists) {
            throw new RuntimeException("Partner not found");
        }
        Vehicle vehicle = Vehicle.builder()
                .partnerId(request.getPartnerId())
                .vehicleNumber(request.getVehicleNumber())
                .vehicleType(request.getVehicleType())
                .capacity(request.getCapacity())
                .insuranceNumber(request.getInsuranceNumber())
                .registrationNumber(request.getRegistrationNumber())
                .status("ACTIVE")
                .build();
        Vehicle savedVehicle = vechicleRepository.save(vehicle);
        return mapToVehicleResponse(savedVehicle);
    }

    private VehicleResponse mapToVehicleResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .partnerId(vehicle.getPartnerId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .vehicleType(vehicle.getVehicleType())
                .capacity(vehicle.getCapacity())
                .status(vehicle.getStatus())
                .registrationNumber(vehicle.getRegistrationNumber())
                .insuranceNumber(vehicle.getInsuranceNumber())
                .build();
    }

    public boolean checkPartnerExists(String partnerId) {
        return partnerRepository.existsById(partnerId);
    }

    public PartnerResponse getPartnerById(String partnerId) {
       return partnerRepository.findById(partnerId)
                .map(this::mapToResponse)
                .orElse(null);
    }
}