package com.example.admin_service.Controller;


import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin_service.Config.ResponseUserDto;
import com.example.admin_service.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<Page<ResponseUserDto>> showAllUsers(
            @RequestParam(required = false,defaultValue = "1") int pageNo,
            @RequestParam(required = false,defaultValue = "10") int pageSize,
            @RequestParam(required = false,defaultValue = "userProfileId") String sortBy,
            @RequestParam(required = false,defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {

        return ResponseEntity.ok(
                userService.showAllUsers(
                        pageNo,
                        pageSize,
                        sortBy,
                        sortDir,
                        search));
    }

    @DeleteMapping("/users/{userProfileId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("userProfileId") String userProfileId) {

    Boolean deleted = userService.deleUsers(userProfileId);

    return ResponseEntity.ok(deleted);
}
}
