package com.lukyanov.itemservice.client;

import com.lukyanov.itemservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-service")
public interface UserApiClient {
    @GetMapping("/api/v1/users/self")
    UserDto getUserByAuth(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorizationHeader);
}
