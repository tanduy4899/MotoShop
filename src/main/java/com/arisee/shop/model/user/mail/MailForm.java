package com.arisee.shop.model.user.mail;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
@Data
public class MailForm {
    private String email;
}
