package com.example.tracking_service.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tracking_service.Dto.CreateTrackingRequest;
import com.example.tracking_service.Dto.TrackingResponse;
import com.example.tracking_service.Model.TrackingEvent;
import com.example.tracking_service.Service.TrackingService;

import lombok.RequiredArgsConstructor;
import java.util.*;
@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingController {
   private final TrackingService trackingservice;

   @PostMapping("/createrequest")
   public ResponseEntity<?> createRequest(@RequestBody CreateTrackingRequest request){
    try{
       TrackingResponse response=trackingservice.createRequest(request);
       if(response!=null){
        return ResponseEntity.ok(response);
       }
      return ResponseEntity.status(404).body("tracking not found");
    }catch(Exception e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
   }

   @GetMapping("/api/getalltracking")
   public ResponseEntity<?>getTracking(){
      try{
           List<TrackingEvent> response=trackingservice.getAllTracking();
       if(response!=null){
        return ResponseEntity.ok(response);
       }
       return ResponseEntity.status(404).body("tracking not found");
      }catch(Exception e){
        return ResponseEntity.status(500).body(e.getMessage());
    }
   }
}
