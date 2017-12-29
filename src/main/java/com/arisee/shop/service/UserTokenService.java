package com.arisee.shop.service;

import com.arisee.shop.domain.user.User;
import com.arisee.shop.domain.user.UserToken;
import com.arisee.shop.model.user.PasswordRestForm;
import com.arisee.shop.repository.UserRepository;
import com.arisee.shop.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserTokenService {
    @Autowired
    MailSender mailSender;

    private final UserTokenRepository userTokenRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public UserTokenService(UserTokenRepository userTokenRepository, UserRepository userRepository, UserService userService, UserRepository userRepository1) {
        this.userTokenRepository = userTokenRepository;
        this.userService = userService;
        this.userRepository = userRepository1;
    }
    public List<com.arisee.shop.model.user.UserToken> getAll(){
        List<com.arisee.shop.model.user.UserToken> rs = new ArrayList<>();
        this.userTokenRepository.findAll().forEach(userToken -> {
            rs.add(userToken.toUserTokenModel());
        });
        return rs;
    }

    public String processForgotPassword(String email) {
            User user = this.userService.findByEmail(email).orElse(null);;
            UserToken userToken = new UserToken();
            userToken.setToken(UUID.randomUUID().toString());
            userToken.setExpiryDate(LocalDateTime.now());
            userToken.setUser(user);
            userTokenRepository.save(userToken);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setText("Hello from Spring Boot Application URL: http://localhost:8080/api/products" + " token " + userToken.getToken());
            message.setTo(user.getEmail());
            message.setFrom("tanduy899@gmail.com");
            try {
                mailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "ok";
    }

//    public String reset(PasswordRestForm passwordRestForm){
//       UserToken token = userTokenRepository.findByToken(passwordRestForm.getToken());
//        User user = token.getUser();
//        String updatedPassword = encoder.encode(passwordRestForm.getPassword());
//        userRepository.updatePassword(updatedPassword, user.getId());
//        userTokenRepository.delete(token);
//        return "ok";
//    }

    public UserToken confirmToken(String token){
        return this.userTokenRepository.findByToken(token);
    }


}
