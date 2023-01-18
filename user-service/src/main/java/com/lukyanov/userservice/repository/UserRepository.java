package com.lukyanov.userservice.repository;

import com.lukyanov.userservice.dto.UserInfoDto;
import com.lukyanov.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByIdIn(List<Long> ids, Pageable pageable);

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.email = :#{#userInfo.email}, u.firstName = :#{#userInfo.firstName}, u.lastName = :#{#userInfo.lastName} where u.id = :id")
    int updateUserInfo(@Param(value = "id") Long id, @Param(value = "userInfo") UserInfoDto userInfoDto);
}
