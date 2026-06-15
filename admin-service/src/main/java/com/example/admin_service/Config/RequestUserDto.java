package com.example.admin_service.Config;



import lombok.Data;

@Data
public class RequestUserDto {

   private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
    private String profilePictureUrl;
    private String bio;
    private String dateOfBirth;
    private String gender;

}
