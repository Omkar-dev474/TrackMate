package com.example.partner_service.Dto;



import lombok.Data;

@Data
public class CreatePartnerRequest {

    // private String userId;

    private String companyName;

    private String gstNumber;

    private String contactPerson;

    private String email;

    private String phone;

    private String address;
}
