package com.lukyanov.userservice.service;


import com.lukyanov.userservice.dto.UserDto;
import com.lukyanov.userservice.dto.UserInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDto findById(Long id);
    Page<UserDto> findAll(Pageable pageable);
    Page<UserDto> findByIds(Pageable pageable, Long...ids);

    UserDto updateByUsername(String username, UserInfoDto userInfo);

    UserDto findByUsername(String username);
}
