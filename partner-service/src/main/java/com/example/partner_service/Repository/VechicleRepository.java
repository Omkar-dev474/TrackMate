package com.example.partner_service.Repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.example.partner_service.Model.Vehicle;

@Repository
public interface VechicleRepository extends MongoRepository<Vehicle, String> {

   List<Vehicle> findByPartnerId(String partnerId);

   boolean existsByPartnerId(int id);

   void deleteByPartnerId(int id);
    
}
