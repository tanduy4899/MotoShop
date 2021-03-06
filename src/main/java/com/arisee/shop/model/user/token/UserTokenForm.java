package com.arisee.shop.model.user.token;

import com.arisee.shop.domain.user.User;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


import java.time.LocalDateTime;
import java.util.Date;
@Data
public class UserTokenForm {
    @NotBlank
    private String token;
}
