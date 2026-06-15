package com.example.user_service.dto;

import lombok.Data;

@Data
public class ResponseAdressDto {
    private Long addressId;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
