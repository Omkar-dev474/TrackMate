package com.example.shipments_service.Config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {

  

    private String id;

    private String userId;

    private String companyName;

    private String gstNumber;

    private String contactPerson;

    private String email;

    private String phone;

    private String address;

    private String status;

    private LocalDateTime createdAt;
}
