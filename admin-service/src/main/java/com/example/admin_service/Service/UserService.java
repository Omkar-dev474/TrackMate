package com.example.admin_service.Service;


import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.admin_service.Config.ResponseUserDto;
import com.example.admin_service.Config.StreamUserConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final StreamUserConfig streamUserConfig;

     public Page<ResponseUserDto> showAllUsers(
            int pageNo,
            int pageSize,
            String sortBy,
            String sortDir,
            String search) {

        return streamUserConfig.showAllUsers(
                pageNo,
                pageSize,
                sortBy,
                sortDir,
                search);
    }

     public Boolean deleUsers(String userProfileId) {
      streamUserConfig.deleteuserById(userProfileId);
      
        return true;
      
     }

     


}
