package com.example.springboot_01.controller;

import com.example.springboot_01.entity.User;
import com.example.springboot_01.model.ApiResponseModel;
import com.example.springboot_01.model.BaseResponseModel;
import com.example.springboot_01.model.UserModel;
import com.example.springboot_01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<ApiResponseModel<List<User>>> GetUser () {
       return userService.listUsers();
    }


    @PostMapping
    public ResponseEntity<BaseResponseModel> createUser (@RequestBody UserModel user){
        return userService.createUser(user);
     }


     @PutMapping("/{user_id}")
    public ResponseEntity<ApiResponseModel<User>> updateUser(@PathVariable("user_id")  Long userId, @RequestBody UserModel payload){
        return userService.updateUser(userId, payload);
     }

     @DeleteMapping("/{user_id}")
    public ResponseEntity<BaseResponseModel> deleteUser(@PathVariable("user_id") Long userId){
        return userService.deleteUser(userId);
     }
    @GetMapping("/{user_id}")
    public ResponseEntity<BaseResponseModel> getOneUser(@PathVariable("user_id") Long userId){
        return  userService.getUserById(userId);
    }
}
