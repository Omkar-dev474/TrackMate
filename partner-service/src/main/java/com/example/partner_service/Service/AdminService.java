package com.example.partner_service.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.partner_service.Dto.DeliveryAgentResponse;
import com.example.partner_service.Dto.PartnerDetailsResponse;
import com.example.partner_service.Dto.VehicleResponse;
import com.example.partner_service.Model.Partner;
import com.example.partner_service.Repository.DeliverAgentRepository;
import com.example.partner_service.Repository.PartnerRepository;
import com.example.partner_service.Repository.VechicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

        private final PartnerRepository pRepo;
        private final DeliverAgentRepository dRepo;
        private final VechicleRepository vRepo;

        public List<PartnerDetailsResponse> getAllPartners(int pageNo, int pageSize, String sortBy, String sortDir,
                        String search) {
                // 1. Setup Sorting
                Sort sort = sortDir.equalsIgnoreCase("asc")
                                ? Sort.by(sortBy).ascending()
                                : Sort.by(sortBy).descending();

                // 2. Setup Pageable (Spring Data uses 0-based indexing for pages)
                PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);

                Page<Partner> partnerPage;

                // 3. Handle Search Logic
                if (search != null && !search.trim().isEmpty()) {
                        partnerPage = pRepo.searchPartners(search.trim(), pageable);
                } else {
                        partnerPage = pRepo.findAll(pageable);
                }

                // 4. Map entities to PartnerDetailsResponse DTOs
                return partnerPage.getContent().stream()
                                .map(this::convertToDto)
                                .collect(Collectors.toList());
        }

        // Helper method to map Partner entity + relationships to DTO
        private PartnerDetailsResponse convertToDto(Partner partner) {
                // Fetch relations if they aren't eagerly loaded or mapped inside the Partner
                // entity
                // Note: If you have @OneToMany in your Partner entity, you can just do
                // partner.getDeliveryAgents()
                List<DeliveryAgentResponse> agents = dRepo.findByPartnerId(partner.getId()).stream()
                                .map(agent -> DeliveryAgentResponse.builder()
                                                .id(agent.getId())
                                                .partnerId(agent.getPartnerId())
                                                .name(agent.getName())
                                                .phone(agent.getPhone())
                                                .vechicleNumber(agent.getVehicleNumber()) // Keep an eye on the 'ch' vs
                                                                                          // 'hc' typo here to match
                                                                                          // your entity
                                                .status(agent.getStatus())
                                                .build())
                                .collect(Collectors.toList());

                List<VehicleResponse> vehicles = vRepo.findByPartnerId(partner.getId()).stream()
                                .map(vehicle -> VehicleResponse.builder()
                                                .id(vehicle.getId())
                                                .partnerId(vehicle.getPartnerId())
                                                .vehicleNumber(vehicle.getVehicleNumber())
                                                .vehicleType(vehicle.getVehicleType())
                                                .capacity(vehicle.getCapacity())
                                                .registrationNumber(vehicle.getRegistrationNumber())
                                                .insuranceNumber(vehicle.getInsuranceNumber())
                                                .status(vehicle.getStatus())
                                                .build())
                                .collect(Collectors.toList());

                return PartnerDetailsResponse.builder()
                                .id(partner.getId())
                                .companyName(partner.getCompanyName())
                                .contactPerson(partner.getContactPerson())
                                .email(partner.getEmail())
                                .phone(partner.getPhone())
                                .address(partner.getAddress())
                                .status(partner.getStatus())
                                .deliveryAgents(agents)
                                .vehicles(vehicles)
                                .build();
        }

        public Boolean delete(int id) {
                if (pRepo.existsById(id)) {
                        pRepo.deleteById(id);
                        if (dRepo.existsByPartnerId(id)) {
                                dRepo.deleteByPartnerId(id);
                        }
                        if (vRepo.existsByPartnerId(id)) {
                                vRepo.deleteByPartnerId(id);
                        }

                        return true;
                }

                return false;
        }
}