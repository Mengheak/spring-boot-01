package com.example.springboot_01.service;

import com.example.springboot_01.entity.User;
import com.example.springboot_01.model.ApiResponseModel;
import com.example.springboot_01.model.BaseResponseModel;
import com.example.springboot_01.model.UserModel;
import com.example.springboot_01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<ApiResponseModel<List<User>>> listUsers () {
       List<User> userData = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get user successfully", userData));
    }


    public ResponseEntity<BaseResponseModel> createUser (@RequestBody UserModel payload){
        User user = new User();
        user.setName(payload.getName());
        user.setAge(payload.getAge());
        user.setEmail(payload.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(payload.getRole());
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseModel("success", "Successfully created user"));
    }


    public ResponseEntity<ApiResponseModel<User>> updateUser(
            @PathVariable("user_id") Long userId,
            @RequestBody UserModel payload) {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseModel<>(
                            "error",
                            "User with id=" + userId + " not found",
                            null));
        }
        User existingUser = user.get();
        existingUser.setName(payload.getName());
        existingUser.setAge(payload.getAge());
        existingUser.setRole(payload.getRole());
        existingUser.setEmail(payload.getEmail());
        existingUser.setUpdatedAt(LocalDateTime.now());

        User updatedUser = userRepository.save(existingUser);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseModel<User>(
                        "success",
                        "User with id=" + userId + " has been updated successfully", updatedUser));
    }

    public ResponseEntity<BaseResponseModel> deleteUser (@PathVariable("user_id") Long userId){
        Optional<User> existingUser = userRepository.findById(userId);
        if(existingUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "User with id = " + userId+ " does not exist"));
        }
        userRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseModel("success", "User with id = " + userId+ " has been deleted"));

    }

    public ResponseEntity<BaseResponseModel> getUserById (@PathVariable("user_id") Long userId){
        Optional<User> fetchedUser = userRepository.findById(userId);
        if(fetchedUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "User is not found"));
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponseModel<>("success", "get user successfully", fetchedUser));
    }
}
