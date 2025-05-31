package com.db_labs.lab_6.controller;

import com.db_labs.lab_6.dto.UserDto;
import com.db_labs.lab_6.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto dto = userService.findById(userId);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.create(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateById(@PathVariable("id") Long userId, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.update(userId, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long userId) {
        Boolean status = userService.deleteById(userId);
        return ResponseEntity.ok("User with ID: " + userId + " DELETED successfully. Confirm: " + status);
    }

}
