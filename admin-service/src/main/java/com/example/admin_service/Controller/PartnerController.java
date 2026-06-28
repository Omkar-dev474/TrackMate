package com.example.admin_service.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin_service.Dto.PartnerDetailsResponse;
import com.example.admin_service.Service.PartnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/partners")
@RequiredArgsConstructor
public class PartnerController {
    
    private final PartnerService partnerService;

    @GetMapping("/allpartners")
   public ResponseEntity<List<PartnerDetailsResponse>>  allPartners(
        @RequestParam(required = false,defaultValue = "1") int pageNo,
            @RequestParam(required = false,defaultValue = "10") int pageSize,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search){
              
          List<PartnerDetailsResponse>partnerDetailsResponses=  partnerService.getPartners(pageNo,pageSize,sortBy,sortDir,search);
                return ResponseEntity.ok(  ).body(partnerDetailsResponses);
            }
    
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Boolean> deletePartner(@PathVariable int id){
    Boolean status=partnerService.deletePartner(id);
    if(status){
        return ResponseEntity.ok(status);
    }

    return ResponseEntity.badRequest().body(status);
   }

}
