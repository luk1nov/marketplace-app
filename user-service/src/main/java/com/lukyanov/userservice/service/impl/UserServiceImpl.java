package com.lukyanov.userservice.service.impl;

import com.lukyanov.userservice.dto.UserDto;
import com.lukyanov.userservice.dto.UserInfoDto;
import com.lukyanov.userservice.mapper.UserMapper;
import com.lukyanov.userservice.repository.UserRepository;
import com.lukyanov.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::userToDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
            return userRepository.findAll(pageable)
                    .map(userMapper::userToDto);
    }

    @Override
    public Page<UserDto> findByIds(Pageable pageable, Long... ids) {
        return userRepository.findByIdIn(Arrays.asList(ids), pageable).map(userMapper::userToDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDto updateByUsername(String username, UserInfoDto userInfo) {
        UserDto currentUser = findByUsername(username);
        if(userRepository.updateUserInfo(currentUser.getId(), setCurrentValuesIfNull(userInfo, currentUser)) != 0){
            return findByUsername(username);
        } else {
            throw new RuntimeException("not modified");
        }
    }

    @Override
    public UserDto findByUsername(String username) {
        if(StringUtils.isNotBlank(username)){
            return userRepository.findByUsername(username)
                    .map(userMapper::userToDto)
                    .orElseThrow(EntityNotFoundException::new);
        }
        throw new IllegalArgumentException();
    }

    private UserInfoDto setCurrentValuesIfNull(UserInfoDto userInfoDto, UserDto userDto){
        if(StringUtils.isBlank(userInfoDto.getFirstName())) {
            userInfoDto.setFirstName(userDto.getFirstName());
        }
        if(StringUtils.isBlank(userInfoDto.getLastName())) {
            userInfoDto.setLastName(userDto.getLastName());
        }
        if(StringUtils.isBlank(userInfoDto.getEmail())) {
            userInfoDto.setEmail(userDto.getEmail());
        }
        return userInfoDto;
    }
}
