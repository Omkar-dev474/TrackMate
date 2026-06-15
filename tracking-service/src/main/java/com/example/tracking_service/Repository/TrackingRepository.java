package com.example.tracking_service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tracking_service.Model.TrackingEvent;

@Repository
public interface TrackingRepository extends JpaRepository<TrackingEvent, Long> {

    List<TrackingEvent> findByTrackingNumberOrderByTimestampAsc(String trackingNumber);
}
