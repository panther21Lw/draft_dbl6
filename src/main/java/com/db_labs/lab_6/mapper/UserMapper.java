package com.db_labs.lab_6.mapper;

import com.db_labs.lab_6.dto.UserDto;
import com.db_labs.lab_6.entity.Request;
import com.db_labs.lab_6.entity.User;
import com.db_labs.lab_6.repository.RequestRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class UserMapper {

    public static User toEntity(UserDto userDto, RequestRepository requestRepository) {
        User user = new User();

        user.setRoleId(userDto.roleId());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setAge(userDto.age());
        user.setEmail(userDto.email());
        user.setPassword(userDto.password());
        user.setPhoneNumber(userDto.phoneNumber());

        if (userDto.requestsIds() != null && !userDto.requestsIds().isEmpty()) {
            List<Request> requests = requestRepository.findAllById(userDto.requestsIds());
            requests.forEach(request -> request.setUser(user));
            user.setRequests(requests);
        }

        return user;
    }

    public static UserDto toDto(User user) {
        List<Long> requestIds = user.getRequests() != null
                ? user.getRequests().stream()
                .map(Request::getId)
                .collect(Collectors.toList())
                : null;

        return new UserDto(
            user.getRoleId(),
            user.getFirstName(),
            user.getLastName(),
            user.getAge(),
            user.getEmail(),
            user.getPassword(),
            user.getPhoneNumber(),
            requestIds
        );
    }
}
