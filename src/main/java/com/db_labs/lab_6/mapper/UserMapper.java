package com.db_labs.lab_6.mapper;

import com.db_labs.lab_6.dto.UserDto;
import com.db_labs.lab_6.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final RequestMapper requestMapper;

    public UserDto toUserDto(User user){
        UserDto dto = modelMapper.map(user, UserDto.class);
        if (user.getRequests() != null){
            dto.setRequests(
                    user.getRequests().stream()
                    .map(requestMapper::toRequestDto)
                    .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public User toUser(UserDto dto){
        User user = new User();

        user.setRoleId(dto.getRoleId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getRequests() == null || dto.getRequests().isEmpty()) {
            user.setRequests(Collections.emptyList());
        } else {
            user.setRequests(
                    dto.getRequests().stream()
                        .map(requestDto -> requestMapper.toRequest(requestDto, user))
                        .collect(Collectors.toList())
            );
        }

        return user;
    }

}
