package com.digi.model;

import com.digi.enums.Role;
import com.digi.enums.Status;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    private int id;

    private String name;

    private String surname;
    private Integer year;
    private String email;
    private String password;

    private String verifyCode;

    private Status status;


    private String resetToken;


    private Role role;
    private Integer balance;
}