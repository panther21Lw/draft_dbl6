package com.db_labs.lab_6.config;

import com.db_labs.lab_6.mapper.RequestMapper;
import com.db_labs.lab_6.mapper.UserMapper;
import com.db_labs.lab_6.services.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

//    @Bean
//    public ModelMapper modelMapper(){
//        return new ModelMapper();
//    }

    @Bean
    public RequestMapper requestMapper() {
        return new RequestMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

}
