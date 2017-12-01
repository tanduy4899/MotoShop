package com.arisee.shop.domain.user;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {
    @EmbeddedId
    private BigInteger userId;

    private BigInteger roleId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public com.arisee.shop.model.user.UserRole toUserRole(){
        com.arisee.shop.model.user.UserRole rs = new com.arisee.shop.model.user.UserRole();
//        rs.setUserId(userId);
        rs.setRoleId(roleId);
//        if(user != null) rs.setUser(user.toUser());
        return rs;
    }
}
