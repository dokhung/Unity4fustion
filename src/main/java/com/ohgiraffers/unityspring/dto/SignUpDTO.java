package com.ohgiraffers.unityspring.dto;

public class SignUpDTO {
    private String id;        // 유일한 ID로 사용 (username 대신)
    private String password;
    private String nickname;  // 사용자에게 표시될 이름

    public SignUpDTO() {
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
