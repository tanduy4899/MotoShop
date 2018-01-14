package com.arisee.shop.model.user.token;

import com.arisee.shop.domain.user.User;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class UserToken {
    private BigInteger id;
    private String token;
    private LocalDateTime expiryDate;
    private com.arisee.shop.model.user.User user;

}
