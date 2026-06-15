package com.example.user_service.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.user_service.Model.User_Profile;
import com.example.user_service.Repository.User_ProfileRepo;
import com.example.user_service.dto.ResponseUserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserService {

    private final User_ProfileRepo user_ProfileRepo;

    public Page<ResponseUserDto> showAllUsers(
        int pageNo,
        int pageSize,
        String sortBy,
        String sortDir,
        String search) {

    Sort sort = sortDir.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

    PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);

    Page<User_Profile> users;

    if (search != null && !search.isBlank()) {
        users = user_ProfileRepo
                .findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        search,
                        search,
                        pageable);
    } else {
        users = user_ProfileRepo.findAll(pageable);
    }

    return users.map(this::mapToResponseDto);
}

    public ResponseUserDto mapToResponseDto(User_Profile user) {

        ResponseUserDto responseUserDto = new ResponseUserDto();

        responseUserDto.setFirstName(user.getFirstName());
        responseUserDto.setLastName(user.getLastName());
        responseUserDto.setEmail(user.getEmail());
        responseUserDto.setPhoneNumber(user.getPhoneNumber());
        responseUserDto.setAddress(user.getAddress());
        responseUserDto.setProfilePictureUrl(user.getProfilePictureUrl());
        responseUserDto.setBio(user.getBio());
        responseUserDto.setDateOfBirth(user.getDateOfBirth());
        responseUserDto.setGender(user.getGender());

        return responseUserDto;
    }

	public boolean deleteById(String userProfileId) {
        log.info ("Deleting");
	if(user_ProfileRepo.existsByUserProfileId(userProfileId)){
        log.info("Found");
        Long id = Long.parseLong(userProfileId);
         user_ProfileRepo.deleteById(id);
         log.info("failed to delete");
         return true;
    }
    return false;
	}

}
