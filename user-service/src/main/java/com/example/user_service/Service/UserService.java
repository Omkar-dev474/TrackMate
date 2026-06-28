package com.example.user_service.Service;


import org.springframework.stereotype.Service;

import com.example.user_service.Model.User_Profile;
import com.example.user_service.Repository.User_ProfileRepo;
import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.RequestUpdateDto;
import com.example.user_service.dto.RequestUserDto;
import com.example.user_service.dto.ResponseUpdateDto;
import com.example.user_service.dto.ResponseUserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final User_ProfileRepo user_profileRepo;
    private final  KeycloakService keycloakService;

    public ResponseUserDto registerUser(RequestUserDto requestUserDto) {
        System.out.println("=== SERVICE HIT ===");
       String userId=keycloakService.createUser(requestUserDto);
    System.out.println("In service class "+userId);
    User_Profile userProfile = new User_Profile();
    userProfile.setKeycloakUserid(userId);
    userProfile.setFirstName(requestUserDto.getFirstName());
    userProfile.setLastName(requestUserDto.getLastName());
    userProfile.setEmail(requestUserDto.getEmail());
    userProfile.setBio(requestUserDto.getBio());
    userProfile.setDateOfBirth(requestUserDto.getDateOfBirth());
    userProfile.setPhoneNumber(requestUserDto.getPhoneNumber());
    userProfile.setAddress(requestUserDto.getAddress());
    userProfile.setGender(requestUserDto.getGender());
    userProfile.setProfilePictureUrl(requestUserDto.getProfilePictureUrl());
    System.out.println("User saved");
    user_profileRepo.save(userProfile);

    return mapDtoResponseDto(requestUserDto);
    }

    public ResponseUserDto mapDtoResponseDto(RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setFirstName(requestUserDto.getFirstName());
        responseUserDto.setLastName(requestUserDto.getLastName());
        responseUserDto.setEmail(requestUserDto.getEmail());
        responseUserDto.setPhoneNumber(requestUserDto.getPhoneNumber());
        responseUserDto.setAddress(requestUserDto.getAddress());
        responseUserDto.setProfilePictureUrl(requestUserDto.getProfilePictureUrl());
        responseUserDto.setBio(requestUserDto.getBio());
        responseUserDto.setDateOfBirth(requestUserDto.getDateOfBirth());
        responseUserDto.setGender(requestUserDto.getGender());

        return responseUserDto;

    }
 public ResponseUpdateDto mapDtoResponseUpdateDto(RequestUpdateDto requestUpadateDto) {
        ResponseUpdateDto responseUpdateDto = new ResponseUpdateDto();
        responseUpdateDto.setFirstName(requestUpadateDto.getFirstName());
        responseUpdateDto.setLastName(requestUpadateDto.getLastName());
        responseUpdateDto.setEmail(requestUpadateDto.getEmail());
        responseUpdateDto.setPhoneNumber(requestUpadateDto.getPhoneNumber());
        responseUpdateDto.setAddress(requestUpadateDto.getAddress());
        responseUpdateDto.setProfilePictureUrl(requestUpadateDto.getProfilePictureUrl());
        responseUpdateDto.setBio(requestUpadateDto.getBio());
        responseUpdateDto.setDateOfBirth(requestUpadateDto.getDateOfBirth());
        responseUpdateDto.setGender(requestUpadateDto.getGender());

        return responseUpdateDto;

    }
  

    public ResponseUpdateDto UpdateData(RequestUpdateDto requesUpdateDto,String email) {
        User_Profile userProfile = user_profileRepo.findByEmail(requesUpdateDto.getEmail());
        if (userProfile == null) {
            throw new RuntimeException("User not found");
        }

        System.out.println(userProfile.getEmail()+" "+email);
        if(!userProfile.getEmail().equalsIgnoreCase(email)){
            throw new RuntimeException("Email cannot be updated");
        }
        if (requesUpdateDto.getFirstName() != null) {
            userProfile.setFirstName(requesUpdateDto.getFirstName());
        }
        if (requesUpdateDto.getLastName() != null) {
            userProfile.setLastName(requesUpdateDto.getLastName());
        }
        if (requesUpdateDto.getPhoneNumber() != null) {
            userProfile.setPhoneNumber(requesUpdateDto.getPhoneNumber());
        }
        if (requesUpdateDto.getAddress() != null) {
            userProfile.setAddress(requesUpdateDto.getAddress());
        }
        if (requesUpdateDto.getProfilePictureUrl() != null) {
            userProfile.setProfilePictureUrl(requesUpdateDto.getProfilePictureUrl());
        }
        if (requesUpdateDto.getBio() != null) {
            userProfile.setBio(requesUpdateDto.getBio());
        }
        if (requesUpdateDto.getDateOfBirth() != null) {
            userProfile.setDateOfBirth(requesUpdateDto.getDateOfBirth());
        }
        if (requesUpdateDto.getGender() != null) {
            userProfile.setGender(requesUpdateDto.getGender());
        }
        user_profileRepo.save(userProfile);
        return mapDtoResponseUpdateDto(requesUpdateDto);
    }

    public boolean deleteUser(String email) {
        User_Profile userProfile = user_profileRepo.findByEmail(email);
        if (userProfile == null) {
            throw new RuntimeException("User not found");
        }
        user_profileRepo.delete(userProfile);
        return true;
    }

    public ResponseUserDto getUserByEmail(String email) {
        User_Profile userProfile = user_profileRepo.findByEmail(email);
        if (userProfile == null) {
            throw new RuntimeException("User not found");
        }
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setFirstName(userProfile.getFirstName());
        responseUserDto.setLastName(userProfile.getLastName());
        responseUserDto.setEmail(userProfile.getEmail());
        responseUserDto.setPhoneNumber(userProfile.getPhoneNumber());
        responseUserDto.setAddress(userProfile.getAddress());
        responseUserDto.setProfilePictureUrl(userProfile.getProfilePictureUrl());
        responseUserDto.setBio(userProfile.getBio());
        responseUserDto.setDateOfBirth(userProfile.getDateOfBirth());
        responseUserDto.setGender(userProfile.getGender());

        return responseUserDto;
    }

    public boolean checkUserExists(String keycloakUserid) {
        return user_profileRepo.existsByKeycloakUserid(keycloakUserid);
    }

    public ResponseUserDto getUserById(String keycloakUserid) {
        User_Profile userProfile= user_profileRepo.findByKeycloakUserid(keycloakUserid);
        if (userProfile == null) {
            throw new RuntimeException("User not found");
        }
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setFirstName(userProfile.getFirstName());
        responseUserDto.setLastName(userProfile.getLastName());
        responseUserDto.setEmail(userProfile.getEmail());
        responseUserDto.setPhoneNumber(userProfile.getPhoneNumber());
        responseUserDto.setAddress(userProfile.getAddress());
        responseUserDto.setProfilePictureUrl(userProfile.getProfilePictureUrl());
        responseUserDto.setBio(userProfile.getBio());
        responseUserDto.setDateOfBirth(userProfile.getDateOfBirth());
        responseUserDto.setGender(userProfile.getGender());

        return responseUserDto;
    }

    public LoginDto getlogindetails(String email) {
      User_Profile user=user_profileRepo.findByEmail(email);
  
        if(user!=null){
            LoginDto login=new LoginDto();
            login.setUserProfileId(user.getUserProfileId());
            login.setEmail(user.getEmail());
           // login.setPassword(user.getPassword());
            login.setRole(user.getRole());
            return login;
        }
        return null;
    }

}
