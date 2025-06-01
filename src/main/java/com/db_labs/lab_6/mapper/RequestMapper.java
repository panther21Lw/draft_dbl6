package com.db_labs.lab_6.mapper;

import com.db_labs.lab_6.dto.RequestDto;
import com.db_labs.lab_6.entity.Request;
import com.db_labs.lab_6.repository.UserRepository;

public class RequestMapper {

    public static Request toEntity(RequestDto dto, UserRepository userRepository) {
        Request request = new Request();
        request.setTime(dto.time());
        request.setUrl(dto.url());

        if (dto.userId() != null) {
            request.setUser(userRepository.findById(dto.userId()).orElse(null));
        }

        return request;
    }

    public static RequestDto toDto(Request request) {
        return new RequestDto(
            request.getUser() != null ? request.getUser().getId() : null,
            request.getTime(),
            request.getUrl()
        );
    }
}
