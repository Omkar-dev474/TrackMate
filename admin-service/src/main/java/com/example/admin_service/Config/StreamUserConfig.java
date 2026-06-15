package com.example.admin_service.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;





@FeignClient("USER-SERVICE")
public  interface StreamUserConfig {
   
     @GetMapping("/admin/user/showAllUsers")
    Page<ResponseUserDto> showAllUsers(@RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) ;
        
    

     @DeleteMapping("/admin/user/deleteUser/{userProfileId}")   
    Boolean deleteuserById(@PathVariable("userProfileId") String userProfileId);
}
