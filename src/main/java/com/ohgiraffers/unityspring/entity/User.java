package com.ohgiraffers.unityspring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;  // 데이터베이스에서의 고유 인덱스 (Primary Key)

    @Column(nullable = false, unique = true)
    private String id;    // 사용자가 입력하는 유저 아이디 (로그인용 ID)

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname; // 사용자에게 표시될 이름

    private LocalDateTime registrationDate;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }
}

