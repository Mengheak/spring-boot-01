package com.example.springboot_01.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private  String role = "USER";
}
