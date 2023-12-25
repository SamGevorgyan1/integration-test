package com.digi.helper;

import com.digi.dto.UserDTO;
import com.digi.enums.Role;

public class AuthorizationHelper {

    public static UserDTO createClient(){
        return UserDTO.builder()
                .name("Integrationtest")
                .surname("Integrationtest")
                .year(2000)
                .email("test@test.com")
                .password("PASSWORD_123")
                .role(Role.PROJECT_ADMIN)
                .build();
    }
}
