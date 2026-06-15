package com.example.partner_service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "partners")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Partner {

    @Id
    private String id;

    private String userId;

    private String companyName;

    private String gstNumber;

    private String contactPerson;

    private String email;

    private String phone;

    private String address;

    private String status;

    @CreatedDate
    private LocalDateTime createdAt;
}
