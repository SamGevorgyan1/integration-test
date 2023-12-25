package com.digi.dto;


import com.digi.enums.Role;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserDTO {


    private String name;


    private String surname;


    private Integer year;


    private String email;


    private String password;

    private Role role;

}