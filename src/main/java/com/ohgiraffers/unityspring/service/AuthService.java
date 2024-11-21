package com.ohgiraffers.unityspring.service;

import com.ohgiraffers.unityspring.dto.SignUpDTO;
import com.ohgiraffers.unityspring.entity.User;
import com.ohgiraffers.unityspring.jwt.JwtTokenProvider;
import com.ohgiraffers.unityspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인 로직
    public String login(String id, String password) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null && currentAuth.isAuthenticated() && !"anonymousUser".equals(currentAuth.getName())) {
            System.out.println("User is already logged in: " + currentAuth.getName());
            throw new IllegalStateException("Already logged in.");
        }

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                System.out.println("User authenticated successfully: " + user.getId());
                String token = jwtTokenProvider.createToken(user.getId());
                System.out.println("Generated JWT token: " + token);
                return token;
            } else {
                System.out.println("Invalid password for user: " + id);
                throw new IllegalArgumentException("Invalid password");
            }
        } else {
            System.out.println("User not found: " + id);
            throw new IllegalArgumentException("User not found");
        }
    }

    // 회원가입 로직
    public User register(SignUpDTO signUpDTO) {
        if (userRepository.findById(signUpDTO.getId()).isPresent()) {
            throw new IllegalArgumentException("ID already exists");
        }

        if (userRepository.findByNickname(signUpDTO.getNickname()).isPresent()) {
            throw new IllegalArgumentException("Nickname already exists");
        }

        User newUser = new User();
        newUser.setId(signUpDTO.getId());
        newUser.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        newUser.setNickname(signUpDTO.getNickname());

        return userRepository.save(newUser);
    }

    public String getUserIdFromToken(String token) {
        return jwtTokenProvider.getUsernameFromToken(token);
    }

    public User getUserInfo(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
