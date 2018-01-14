package com.arisee.shop.domain.user;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_token")
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String token;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    public com.arisee.shop.model.user.token.UserToken toUserTokenModel(){
        com.arisee.shop.model.user.token.UserToken rs = new com.arisee.shop.model.user.token.UserToken();
        rs.setId(id);
        rs.setToken(token);
        rs.setExpiryDate(expiryDate);
        rs.setUser(getUser().toUser());
        return  rs;
    }


}
