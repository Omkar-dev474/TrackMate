package com.example.partner_service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.partner_service.Model.Partner;

@Repository
public interface PartnerRepository extends MongoRepository<Partner, String> {
    
}
