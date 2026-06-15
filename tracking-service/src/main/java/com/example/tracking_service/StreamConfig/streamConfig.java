// package com.example.tracking_service.StreamConfig;

// import java.util.function.Consumer; // Changed from Function to Consumer
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import com.example.tracking_service.Config.ShipmentResponse;
// import lombok.extern.slf4j.Slf4j;

// @Configuration
// @Slf4j
// public class streamConfig { // Capitalized class name (standard Java practice)
    
//     @Bean
//     public Consumer<ShipmentResponse> ShipmentResponse() { // Renamed to camelCase
//         return event -> {
//             // Log the actual 'event' object that came from Kafka
//             log.info("Processing event: {}", event); 
            
//             // Your database tracking logic goes here (e.g., saving to PostgreSQL)
//         };
//     }
// }