package com.arisee.shop.domain.user;


import com.arisee.shop.model.user.RoleForm;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String role;


    public com.arisee.shop.model.user.Role toRoleModel(){
        com.arisee.shop.model.user.Role rs = new com.arisee.shop.model.user.Role();
        rs.setId(2);
        rs.setRole(role);
        return rs;
    }

    public RoleForm toRoleForm(){
        RoleForm rs = new RoleForm();

        rs.setRole(role);
        return rs;
    }

}
