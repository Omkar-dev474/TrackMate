package com.example.user_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_service.Model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

}
