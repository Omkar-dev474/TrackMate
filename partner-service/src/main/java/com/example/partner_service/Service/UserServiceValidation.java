package com.example.partner_service.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceValidation {
    
    private final WebClient userSerivceWebClient;

    // public boolean validateUser(String userId){
    //    try{
    //      return userSerivceWebClient.get()
    //     .uri("/api/users/{userId}/validateUser",userId)
    //     .retrieve()
    //     .bodyToMono(Boolean.class)
    //     .block();
    //    }catch(WebClientResponseException e){
    //     e.printStackTrace();
    //    }
    //    return false;
    // }

    public  Boolean checkUser(String userId){
        try{
         return userSerivceWebClient.get()
         .uri("/api/users/{userId}/exists",userId)
         .retrieve()
         .bodyToMono(Boolean.class)
         .block();
        }catch(WebClientResponseException e){
        e.printStackTrace();
       }
        return null;
    }

    
}

