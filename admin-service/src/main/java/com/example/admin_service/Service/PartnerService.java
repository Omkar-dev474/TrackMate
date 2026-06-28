package com.example.admin_service.Service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.admin_service.Config.StreamPartnerConfig;
import com.example.admin_service.Dto.PartnerDetailsResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final StreamPartnerConfig streamPartnerConfig;

    public List<PartnerDetailsResponse> getPartners(int pageNo, int pageSize, String sortBy, String sortDir,String search) {
        List<PartnerDetailsResponse>partnerDetailsResponses= streamPartnerConfig.allPartners(pageNo,pageSize,sortBy,sortDir,search);
        return partnerDetailsResponses;
    }
    
    public Boolean deletePartner(int id) {
      boolean stat=streamPartnerConfig.deletePartner(id);
     if(stat){
        return true;
     }
     return false;
    }

}
