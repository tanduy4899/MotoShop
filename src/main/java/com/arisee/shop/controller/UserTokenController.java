package com.arisee.shop.controller;

import com.arisee.shop.exception.NotFoundException;
import com.arisee.shop.model.user.mail.MailForm;
import com.arisee.shop.model.user.password.PasswordRestForm;
import com.arisee.shop.model.user.password.PasswordUpdateForm;
import com.arisee.shop.model.user.token.UserToken;
import com.arisee.shop.model.user.token.UserTokenForm;
import com.arisee.shop.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping(AbstractController.API+"/forgotPassword")
@RestController
public class UserTokenController {
    @Autowired
    private UserTokenService userTokenService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserToken> getAll(){
        return this.userTokenService.getAll();
    }

    @RequestMapping(value ="/sendMail" ,method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserToken processForgotPassword(@Valid @RequestBody MailForm mailForm){
        return userTokenService.processForgotPassword(mailForm.getEmail()).toUserTokenModel();
    }
    @RequestMapping(value ="/confirm" ,method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserToken findByToken(@Valid @RequestBody UserTokenForm userTokenForm){
        return this.userTokenService.findByToken(userTokenForm.getToken())
                .map(com.arisee.shop.domain.user.UserToken::toUserTokenModel).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value ="/reset-password" ,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserToken resetPassword(@RequestParam("token")String token,
                                   @Valid @RequestBody PasswordUpdateForm passwordUpdateForm){
        return this.userTokenService.resetPassword(token,passwordUpdateForm)
                .map(com.arisee.shop.domain.user.UserToken::toUserTokenModel).orElseThrow(NotFoundException::new);

}


}
