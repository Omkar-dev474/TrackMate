package com.example.partner_service.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.partner_service.Dto.PartnerDetailsResponse;
import com.example.partner_service.Service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminPartnerController {
    
     private final AdminService adminService;
     
    @GetMapping("/allpartner")
    public  ResponseEntity<List<PartnerDetailsResponse>>  allPartners(
        @RequestParam(required = false,defaultValue = "1") int pageNo,
            @RequestParam(required = false,defaultValue = "10") int pageSize,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search){
          try{
           List<PartnerDetailsResponse> partnerDetailsResponse=adminService.getAllPartners(pageNo, pageSize, sortBy, sortDir, search);
           if(partnerDetailsResponse!=null){
            return ResponseEntity.ok(partnerDetailsResponse);
           }
           return ResponseEntity.badRequest().build();
          }catch(Exception e){
            return ResponseEntity.status(500).build();
          }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deletePartner(@PathVariable int id){
         try{
           Boolean status=adminService.delete(id);
           if(status){
            return ResponseEntity.ok(status);
           }
           return ResponseEntity.badRequest().build();
         }catch(Exception e){
           return ResponseEntity.status(500).build();
         }
    }
}
