package com.arisee.shop.controller;

import com.arisee.shop.domain.PagingObject;
import com.arisee.shop.exception.NotFoundException;
import com.arisee.shop.model.user.User;
import com.arisee.shop.model.user.UserForm;
import com.arisee.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController
@RequestMapping(AbstractController.API + "/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping
    public PagingObject<User> getAllUsers(Pageable pageable,
                                          @RequestParam(required = false, defaultValue = "") String username,
                                          @RequestParam(required = false, defaultValue = "") String fullName) {
        return userService.getAllUsers(pageable, username, fullName);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getById(@PathVariable("id") BigInteger id) {
        return this.userService.getById(id).map(com.arisee.shop.domain.user.User::toUser).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        this.userService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User update(@PathVariable("id") BigInteger id, @Valid @RequestBody UserForm userForm) {
        return this.userService.update(id, userForm).map(com.arisee.shop.domain.user.User::toUser).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User insert(@Valid @RequestBody UserForm userForm) {
        return this.userService.create(userForm).toUser();
    }



}
