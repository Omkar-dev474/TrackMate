package com.example.user_service.Repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_service.Model.User_Profile;


@Repository
public interface User_ProfileRepo extends JpaRepository<User_Profile, Long> {

    User_Profile findByEmail(String email);



    boolean existsByUserProfileId(String userId);



    User_Profile findByUserProfileId(String userId);



    Page<User_Profile> findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String search, String search2,
            PageRequest pageable);



    void deleteByUserProfileId(String userProfileId);
    
}
