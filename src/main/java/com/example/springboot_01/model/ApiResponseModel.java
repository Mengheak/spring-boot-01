package com.example.springboot_01.model;

public class ApiResponseModel<T> extends BaseResponseModel{
    public  T data;
    public ApiResponseModel(String status, String message, T data){
        super(status, message);
        this.data = data;
    }
}
