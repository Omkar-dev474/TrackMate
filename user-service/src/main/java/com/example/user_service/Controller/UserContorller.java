package com.example.user_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.Service.UserService;
import com.example.user_service.dto.RequestUpdateDto;
import com.example.user_service.dto.RequestUserDto;
import com.example.user_service.dto.ResponseUpdateDto;
import com.example.user_service.dto.ResponseUserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserContorller {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<String> getUser(@RequestHeader("X-USER-EMAIL") String email) {
        try {
            if (email == null) {
                return ResponseEntity.badRequest().build();
            }
            ResponseUserDto responseUserDto = userService.getUserByEmail(email);
            if (responseUserDto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(responseUserDto.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{keycloakUserid}/exists")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable String keycloakUserid) {
        try {
            if (keycloakUserid == null) {
                return ResponseEntity.badRequest().build();
            }
            boolean exists = userService.checkUserExists(keycloakUserid);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/{keycloakUserid}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable String keycloakUserid) {
        try {
            if (keycloakUserid == null) {
                return ResponseEntity.badRequest().build();
            }
            ResponseUserDto responseUserDto = userService.getUserById(keycloakUserid);
            if (responseUserDto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(responseUserDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseUserDto> registerUser(@RequestBody RequestUserDto requestUserDto) {
        try {
             System.out.println("=== REGISTER API HIT ===");
            if (requestUserDto.getEmail() == null || requestUserDto.getPassword() == null) {
                return ResponseEntity.badRequest().build();
            }
            ResponseUserDto responseUserDto = userService.registerUser(requestUserDto);
            return ResponseEntity.ok(responseUserDto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseUpdateDto> updateUser(@RequestBody RequestUpdateDto requesUpdateDto, @RequestHeader("X-USER-EMAIL") String email) {
        try {
            if (email == null) {
                return ResponseEntity.badRequest().build();
            }
            ResponseUpdateDto responseUserDto = userService.UpdateData(requesUpdateDto,email);
            return ResponseEntity.ok(responseUserDto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestHeader("X-USER-EMAIL") String email) {
        try {
            if (email == null) {
                return ResponseEntity.badRequest().build();
            }
            boolean isDeleted = userService.deleteUser(email);
            if (isDeleted) {
                return ResponseEntity.ok("User deleted successfully");
            }
            return ResponseEntity.badRequest().body("Failed to delete");
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
 
   
    
}
