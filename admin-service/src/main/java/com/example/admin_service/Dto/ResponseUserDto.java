package com.example.admin_service.Dto;



import lombok.Data;

@Data
public class ResponseUserDto {
     private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private String profilePictureUrl;
    private String bio;
    private String dateOfBirth;
    private String gender;

}
