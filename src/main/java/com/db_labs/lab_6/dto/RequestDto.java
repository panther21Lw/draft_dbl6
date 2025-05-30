package com.db_labs.lab_6.dto;

import com.db_labs.lab_6.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long userId;
    private LocalDateTime time;
    private String url;

}
