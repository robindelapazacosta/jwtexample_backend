package com.robin.jwtbackend.mappers;


import com.robin.jwtbackend.dto.UserDto;
import com.robin.jwtbackend.dto.UserRegisterDto;
import com.robin.jwtbackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDto toUserDto(User user);


    @Mapping(target = "password", ignore = true)
    User userRegistrationToUser(UserRegisterDto registerDto);


}
