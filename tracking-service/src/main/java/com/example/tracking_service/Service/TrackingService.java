package com.example.tracking_service.Service;

import java.util.List;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.example.tracking_service.Config.ShipmentFeignClient;
import com.example.tracking_service.Dto.CreateTrackingRequest;
import com.example.tracking_service.Dto.TrackingResponse;
import com.example.tracking_service.Model.TrackingEvent;
import com.example.tracking_service.Repository.TrackingRepository;
import com.example.tracking_service.Config.ShipmentResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingRepository trackingRepository;

    private final ShipmentFeignClient shipmentFeignClient;

    private final StreamBridge streamBridge;

    public TrackingResponse createRequest(CreateTrackingRequest request) {
     ShipmentResponse response=shipmentFeignClient.getTrackingnumber(request.getTrackingNumber());
     if(response.getTrackingNumber()==null && request.getTrackingNumber()==response.getTrackingNumber()){
           throw new RuntimeException("Tracking number not found");
     }

     TrackingEvent te=new TrackingEvent();
     te.setTrackingNumber(response.getTrackingNumber());
     te.setLocation(request.getLocation());
     te.setRemarks(request.getRemarks());
     te.setStatus(request.getStatus());
     te.setShipmentId(response.getId());

     trackingRepository.save(te);
     streamBridge.send("Shipment-details", mapDtoTrackingResponse(te));

    return mapDtoTrackingResponse(te);
    
    }

    public TrackingResponse mapDtoTrackingResponse(TrackingEvent event){
        return TrackingResponse.builder()
                               .id(event.getId())
                               .trackingNumber(event.getTrackingNumber())
                               .location(event.getLocation())
                               .remarks(event.getRemarks())
                               .status(event.getStatus())
                               .shipmentId(event.getShipmentId())
                               .timestamp(event.getTimestamp())
                               .build();
    }

   

    public List<TrackingEvent> getAllTracking() {
       List<TrackingEvent> event=trackingRepository.findAll();
       return (event);
    }
}
