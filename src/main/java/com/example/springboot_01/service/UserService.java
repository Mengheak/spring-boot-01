package com.example.springboot_01.service;

import com.example.springboot_01.dto.UserResponseDto;
import com.example.springboot_01.entity.User;
import com.example.springboot_01.mapper.UserMapper;
import com.example.springboot_01.model.ApiResponseModel;
import com.example.springboot_01.model.BaseResponseModel;
import com.example.springboot_01.dto.UserDto;
import com.example.springboot_01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper mapper;


    public ResponseEntity<ApiResponseModel<List<UserResponseDto>>> listUsers () {
       List<User> userData = userRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseModel<>("success", "get user successfully", mapper.toDtoList(userData)));
    }


    public ResponseEntity<BaseResponseModel> createUser (UserDto payload){
        if(userRepository.existsByName(payload.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponseModel("failed", "User with this username already exists"));
        }
        if(userRepository.existsByEmail(payload.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseResponseModel("failed", "User with this email already exists"));
        }
        User user = mapper.toEntity(payload);
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponseModel("success", "Successfully created user"));
    }


    public ResponseEntity<ApiResponseModel<User>> updateUser(
            Long userId,
            UserDto payload) {

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

        User updatedUser = userRepository.save(existingUser);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponseModel<User>(
                        "success",
                        "User with id=" + userId + " has been updated successfully", updatedUser));
    }

    public ResponseEntity<BaseResponseModel> deleteUser (Long userId){
        Optional<User> existingUser = userRepository.findById(userId);
        if(existingUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "User with id = " + userId+ " does not exist"));
        }
        userRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponseModel("success", "User with id = " + userId+ " has been deleted"));

    }

    public ResponseEntity<BaseResponseModel> getUserById (Long userId){
        Optional<User> fetchedUser = userRepository.findById(userId);

        if(fetchedUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponseModel("failed", "User is not found"));
        }
        UserResponseDto dto = mapper.toDto(fetchedUser.get());
        return ResponseEntity.status(HttpStatus.FOUND).body(new ApiResponseModel<>("success", "get user successfully", dto));
    }
}
