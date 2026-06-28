package com.example.partner_service.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import com.example.partner_service.Model.Partner;

@Repository
public interface PartnerRepository extends MongoRepository<Partner, String> {
    
    @Query("SELECT p FROM Partner p WHERE " +
           "LOWER(p.companyName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.contactPerson) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(p.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Partner> searchPartners(@Param("keyword") String keyword, Pageable pageable);

    boolean existsById(int id);

    void deleteById(int id);
}
