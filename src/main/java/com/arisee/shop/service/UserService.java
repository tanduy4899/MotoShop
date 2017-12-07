package com.arisee.shop.service;


import com.arisee.shop.domain.PagingObject;
import com.arisee.shop.domain.user.User_;
import com.arisee.shop.model.user.User;
import com.arisee.shop.model.user.UserForm;
import com.arisee.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import static com.arisee.shop.domain.user.User_.address;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

//    @PreAuthorize("hasRole(ADMIN)")
    public PagingObject<User> getAllUsers(Pageable pageable, String username, String fullName) {
        if (pageable.getPageSize() > 500) throw new RuntimeException("Page size too big");
        PagingObject<User> rs = new PagingObject<>();
        Page<com.arisee.shop.domain.user.User> userPage;
        if (StringUtils.hasText(username) || StringUtils.hasText(fullName)) {
            userPage = userRepository.findAll((root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(username)) {
                    predicates.add(cb.like(root.get(User_.username), "%" + username + "%"));
                }
                if (StringUtils.hasText(fullName)) {
                    predicates.add(cb.like(root.get(address), "%" + address + "%"));
                }
                return cb.or(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }
        rs.setTotal(userPage.getTotalElements());
        rs.setTotalPage(userPage.getTotalPages());
        rs.setData(userPage.getContent().stream().map(com.arisee.shop.domain.user.User::toUser).collect(Collectors.toList()));

        return rs;
    }

    public Optional<com.arisee.shop.domain.user.User> getById(BigInteger id) {
        return this.userRepository.getById(id);
    }

    public void delete(BigInteger id) {
        getById(id).ifPresent(this.userRepository::delete);
    }

    public Optional<com.arisee.shop.domain.user.User> update(BigInteger id, UserForm userForm) {
        return getById(id).map(user -> {
            user.setUsername(userForm.getUsername());
            user.setPassword(encoder.encode(userForm.getPassword()));
            user.setEmail(userForm.getEmail());
            user.setAddress(userForm.getAddress());
            user.setFullName(userForm.getFullName());
            user.setPhone(userForm.getPhone());
            user.setSex(userForm.getSex());
            user.setRoles(userForm.getRoles());
            return this.userRepository.save(user);
        });
    }

    public com.arisee.shop.domain.user.User create(UserForm userForm) {

        com.arisee.shop.domain.user.User user = new com.arisee.shop.domain.user.User();
        user.setUsername(userForm.getUsername());
        user.setPassword(encoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setAddress(userForm.getAddress());
        user.setPhone(userForm.getPhone());
        user.setFullName(userForm.getFullName());
        user.setSex(userForm.getSex());
        user.setRoles(userForm.getRoles());
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.arisee.shop.domain.user.User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return user
                .map(CustomUserDetails::new).get();
    }

}