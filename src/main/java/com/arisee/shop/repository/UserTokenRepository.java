package com.arisee.shop.repository;

import com.arisee.shop.domain.user.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken ,BigInteger> {
      Optional<UserToken> findByToken(String token);

      Optional<UserToken> getById(BigInteger id);
}
