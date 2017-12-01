package com.arisee.shop.controller;

import com.arisee.shop.model.user.User;
import com.arisee.shop.model.user.UserForm;
import com.arisee.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(AbstractController.API+"/registers")
public class RegisterController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User insert(@Valid @RequestBody UserForm userForm) {
        return this.userService.create(userForm).toUser();
    }

}
