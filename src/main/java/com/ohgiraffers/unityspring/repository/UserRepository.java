package com.ohgiraffers.unityspring.repository;

import com.ohgiraffers.unityspring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id); // 사용자 ID(String) 기반 조회 메서드
    Optional<User> findByNickname(String nickName);
}

