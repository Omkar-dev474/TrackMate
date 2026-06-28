package com.example.user_service.Model;



import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Users")
public class User_Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProfileId;


    private String keycloakUserid;
    private String firstName;
    private String lastName;
    private String email;
    private String role="USERS";
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AdressId",referencedColumnName = "addressId")
    @JsonManagedReference
    private Address address;
    private String profilePictureUrl;
    private String bio;
    private String dateOfBirth;
    private String gender;

    @CreationTimestamp
    private LocalDateTime  createdAt;
    @UpdateTimestamp
    private LocalDateTime  updatedAt;

}
