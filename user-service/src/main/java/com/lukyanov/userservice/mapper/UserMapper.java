package com.lukyanov.userservice.mapper;

import com.lukyanov.userservice.dto.UserDto;
import com.lukyanov.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(source = "id", target = "id")
    UserDto userToDto(User user);

    User dtoToUser(UserDto userDto);
}
