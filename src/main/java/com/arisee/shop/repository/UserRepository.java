package com.arisee.shop.repository;


import com.arisee.shop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,BigInteger>,JpaSpecificationExecutor<User> {
    Optional<User> getById(BigInteger id);
    Optional<User> findByUsername(String username);

//    @Query("SELECT u FROM #{#entityName} u WHERE u.username =?1 AND u.password = ?2 ")
//    Optional<User> login(String userName, String passWord);
}
