package com.arisee.shop.model.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
@Data
public class MailForm {
    private String email;
}
