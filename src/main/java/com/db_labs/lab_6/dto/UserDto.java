package com.db_labs.lab_6.dto;



import java.util.List;

public record UserDto (
    Long roleId,
    String firstName,
    String lastName,
    int age,
    String email,
    String password,
    String phoneNumber,
    List<Long> requestsIds
    ) {}
