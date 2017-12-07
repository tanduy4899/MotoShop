package com.arisee.shop.domain.user;


import com.arisee.shop.domain.product.Product;
import com.arisee.shop.domain.user.User;
import com.arisee.shop.model.user.RoleForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        rs.setId(id);
        rs.setRole(role);
        return rs;
    }

    public RoleForm toRoleForm(){
        RoleForm rs = new RoleForm();
        rs.setRole(role);
        return rs;
    }

}
