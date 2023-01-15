package com.lukyanov.userservice.controller;

import com.lukyanov.userservice.dto.UserDto;
import com.lukyanov.userservice.dto.UserInfoDto;
import com.lukyanov.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('users.read')")
    public Page<UserDto> findAllUsers(Pageable pageable){
        return userService.findAll(pageable);
    }

    @GetMapping("/self")
    public UserDto findSelf(Authentication authentication){
        return userService.findByUsername(authentication.getName());
    }

    @GetMapping("/{id}")
    public UserDto findSelf(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping("/self")
    public UserDto updateSelf(Authentication authentication, @RequestBody UserInfoDto userInfo){
        return userService.updateByUsername(authentication.getName(), userInfo);
    }

}
