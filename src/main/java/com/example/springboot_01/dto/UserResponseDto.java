package com.example.springboot_01.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"user_id","username", "age", "email", "role"})
public class UserResponseDto {
    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("username")
    private String name;

    private Integer age;
    private String email;
    private String role = "USER";
}
