package com.db_labs.lab_6.mapper;

import com.db_labs.lab_6.dto.UserDto;
import com.db_labs.lab_6.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        return modelMapper.map(dto, User.class);
    }
}
