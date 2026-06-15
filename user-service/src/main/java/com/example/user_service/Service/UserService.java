package com.example.user_service.Service;


import org.springframework.stereotype.Service;

import com.example.user_service.Model.User_Profile;
import com.example.user_service.Repository.User_ProfileRepo;
import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.RequestUserDto;
import com.example.user_service.dto.ResponseUserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final User_ProfileRepo user_profileRepo;

    public ResponseUserDto registerUser(RequestUserDto requestUserDto) {
        User_Profile userProfile = new User_Profile();
        userProfile.setFirstName(requestUserDto.getFirstName());
        userProfile.setLastName(requestUserDto.getLastName());
        userProfile.setEmail(requestUserDto.getEmail());
        userProfile.setPassword(requestUserDto.getPassword());
        userProfile.setPhoneNumber(requestUserDto.getPhoneNumber());
        userProfile.setAddress(requestUserDto.getAddress());
        userProfile.setProfilePictureUrl(requestUserDto.getProfilePictureUrl());
        userProfile.setBio(requestUserDto.getBio());
        userProfile.setDateOfBirth(requestUserDto.getDateOfBirth());
        userProfile.setGender(requestUserDto.getGender());
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

  

    public ResponseUserDto UpdateData(RequestUserDto requestUserDto,String email) {
        User_Profile userProfile = user_profileRepo.findByEmail(requestUserDto.getEmail());
        if (userProfile == null) {
            throw new RuntimeException("User not found");
        }
        System.out.println(userProfile.getEmail()+" "+email);
        if(!userProfile.getEmail().equalsIgnoreCase(email)){
            throw new RuntimeException("Email cannot be updated");
        }
        if (requestUserDto.getFirstName() != null) {
            userProfile.setFirstName(requestUserDto.getFirstName());
        }
        if (requestUserDto.getLastName() != null) {
            userProfile.setLastName(requestUserDto.getLastName());
        }
        if (requestUserDto.getPhoneNumber() != null) {
            userProfile.setPhoneNumber(requestUserDto.getPhoneNumber());
        }
        if (requestUserDto.getAddress() != null) {
            userProfile.setAddress(requestUserDto.getAddress());
        }
        if (requestUserDto.getProfilePictureUrl() != null) {
            userProfile.setProfilePictureUrl(requestUserDto.getProfilePictureUrl());
        }
        if (requestUserDto.getBio() != null) {
            userProfile.setBio(requestUserDto.getBio());
        }
        if (requestUserDto.getDateOfBirth() != null) {
            userProfile.setDateOfBirth(requestUserDto.getDateOfBirth());
        }
        if (requestUserDto.getGender() != null) {
            userProfile.setGender(requestUserDto.getGender());
        }
        user_profileRepo.save(userProfile);
        return mapDtoResponseDto(requestUserDto);
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

    public boolean checkUserExists(String userId) {
        return user_profileRepo.existsByUserProfileId(userId);
    }

    public ResponseUserDto getUserById(String userId) {
        User_Profile userProfile= user_profileRepo.findByUserProfileId(userId);
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
            login.setPassword(user.getPassword());
            login.setRole(user.getRole());
            return login;
        }
        return null;
    }

}
