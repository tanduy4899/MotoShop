package com.arisee.shop.service;

import com.arisee.shop.domain.user.User;
import com.arisee.shop.domain.user.UserToken;
import com.arisee.shop.model.user.password.PasswordUpdateForm;
import com.arisee.shop.repository.UserRepository;
import com.arisee.shop.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserTokenService {
    @Autowired
    MailSender mailSender;

    private final UserTokenRepository userTokenRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Autowired
    public UserTokenService(UserTokenRepository userTokenRepository, UserService userService, UserRepository userRepository) {
        this.userTokenRepository = userTokenRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<com.arisee.shop.model.user.token.UserToken> getAll() {
        List<com.arisee.shop.model.user.token.UserToken> rs = new ArrayList<>();
        this.userTokenRepository.findAll().forEach(userToken -> {
            rs.add(userToken.toUserTokenModel());
        });
        return rs;
    }

    public UserToken processForgotPassword(String email) {
        User user = this.userService.findByEmail(email).orElse(null);
        UserToken userToken = new UserToken();
        userToken.setToken(UUID.randomUUID().toString());
        userToken.setExpiryDate(LocalDateTime.now());
        userToken.setUser(user);
//            userTokenRepository.save(userToken);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hello from Spring Boot Application URL: http://localhost:8080/index.html#!/forgot-password/reset-password?token="+ userToken.getToken());
        message.setTo(user.getEmail());
        message.setFrom("tanduy899@gmail.com");
        try {
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.userTokenRepository.save(userToken);

    }
    public Optional<UserToken> findByToken(String token) {
        return this.userTokenRepository.findByToken(token);
    }



    @Transactional
    public Optional<UserToken> resetPassword(String token ,PasswordUpdateForm passwordUpdateForm) {
        return this.findByToken(token).map(userToken -> {
            com.arisee.shop.domain.user.User user = userToken.getUser();
            String updatedPassword = encoder.encode(passwordUpdateForm.getPassword());
            this.userRepository.resetPassword(updatedPassword, user.getId());
            return userTokenRepository.save(userToken);
        });
    }



}
