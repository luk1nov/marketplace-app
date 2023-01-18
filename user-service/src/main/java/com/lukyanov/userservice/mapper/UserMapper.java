package com.lukyanov.userservice.mapper;

import com.lukyanov.userservice.dto.UserDto;
import com.lukyanov.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto userToDto(User user);
}
