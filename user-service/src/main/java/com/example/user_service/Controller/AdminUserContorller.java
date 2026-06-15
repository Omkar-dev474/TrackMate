package com.example.user_service.Controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.Service.AdminUserService;
import com.example.user_service.dto.ResponseUserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserContorller {

    private final AdminUserService adminUserService;

    @GetMapping("/showAllUsers")
    public ResponseEntity<?> showAllUsers(@RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize,
            @RequestParam(required = false, defaultValue = "userProfileId") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir,
            @RequestParam(required = false) String search) {
        try {
            Page<ResponseUserDto> users = adminUserService.showAllUsers(pageNo, pageSize, sortBy, sortDir, search);
            if (users != null) {
                return ResponseEntity.ok(users);
            }
            return ResponseEntity.badRequest().body("No Users Found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteUser/{userProfileId}")
    public ResponseEntity<?> deleteuserById(@PathVariable String userProfileId) {
        try {
            boolean status = adminUserService.deleteById(userProfileId);
            if (status) {
                return ResponseEntity.ok().body("Deleted");
            }
            return ResponseEntity.badRequest().body("Failed to delete");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

}
