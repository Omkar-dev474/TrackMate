package com.example.user_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.Service.UserService;
import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.RequestUserDto;
import com.example.user_service.dto.ResponseUserDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserContorller {

    private final UserService userService;

    @GetMapping("/get/{email}")
    public ResponseEntity<String> getUser(@PathVariable String email) {
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

    @GetMapping("/{userId}/exists")
    public ResponseEntity<Boolean> checkUserExists(@PathVariable String userId) {
        try {
            if (userId == null) {
                return ResponseEntity.badRequest().build();
            }
            boolean exists = userService.checkUserExists(userId);
            return ResponseEntity.ok(exists);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/{UserId}")
    public ResponseEntity<ResponseUserDto> getUserById(@PathVariable String UserId) {
        try {
            if (UserId == null) {
                return ResponseEntity.badRequest().build();
            }
            ResponseUserDto responseUserDto = userService.getUserById(UserId);
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

    @PutMapping("/update/{email}")
    public ResponseEntity<ResponseUserDto> updateUser(@RequestBody RequestUserDto requestUserDto, @PathVariable String email) {
        try {
            if (email == null) {
                return ResponseEntity.badRequest().build();
            }
            ResponseUserDto responseUserDto = userService.UpdateData(requestUserDto,email);
            return ResponseEntity.ok(responseUserDto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
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
 
    @GetMapping("/login/{email}")
    public ResponseEntity<?> login(@PathVariable String email){
        try{
           LoginDto login=userService.getlogindetails(email);
           if(login!=null){
          return ResponseEntity.ok(login);
           }
           return ResponseEntity.badRequest().body("Failed to find");
        }catch(Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
}
