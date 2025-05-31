package com.db_labs.lab_6.mapper;

import com.db_labs.lab_6.dto.RequestDto;
import com.db_labs.lab_6.entity.Request;
import com.db_labs.lab_6.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final ModelMapper modelMapper;

    public RequestDto toRequestDto(Request request){
        RequestDto dto = modelMapper.map(request, RequestDto.class);
        dto.setUserId(request.getUser().getId());
        return dto;
    }

    public Request toRequest(RequestDto dto, User user){
        Request request = modelMapper.map(dto, Request.class);
        request.setUser(user);
        return request;
    }
}
