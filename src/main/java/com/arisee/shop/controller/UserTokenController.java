package com.arisee.shop.controller;

import com.arisee.shop.exception.NotFoundException;
import com.arisee.shop.model.user.*;
import com.arisee.shop.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(AbstractController.API+"/restPassword")
@RestController
public class UserTokenController {
    @Autowired
    private UserTokenService userTokenService;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserToken> getAll(){
        return this.userTokenService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String processForgotPassword(@Valid @RequestBody MailForm mailForm){
        return userTokenService.processForgotPassword(mailForm.getEmail());
    }
    @RequestMapping(value ="/confirm" ,method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserToken confirmToken(@Valid @RequestBody UserTokenForm userTokenForm){
        return this.userTokenService.confirmToken(userTokenForm.getToken()).toUserTokenModel();
    }



//    @RequestMapping(value ="/reset" ,method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String reset(@Valid @RequestBody PasswordRestForm passwordRestForm){
//        return this.userTokenService.reset(passwordRestForm);
//    }


}
