package com.db_labs.lab_6.dto;

import java.time.LocalDateTime;

public record RequestDto (
    Long userId,
    LocalDateTime time,
    String url
    ) {}
